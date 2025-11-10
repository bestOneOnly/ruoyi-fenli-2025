<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择">
          <el-option v-for="item in statusList" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="工单编号" prop="no">
        <el-input v-model="queryParams.no" placeholder="请输入工单编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="工单名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入工单名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="派单时间" prop="dispatchTime">
        <el-date-picker clearable v-model="queryParams.dispatchTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择派单时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="maintenanceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工单编号" align="center" prop="no" />
      <el-table-column label="工单状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.statusEnum.label }}
        </template>
      </el-table-column>
      <el-table-column label="工单名称" align="center" prop="name" />
      <el-table-column label="派单时间" align="center" prop="dispatchTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dispatchTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="派单人" align="center" prop="dispatchUserName" />
      <el-table-column label="派单描述" align="center" prop="dispatchDesc" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope" v-if="scope.row.statusEnum.enumName == 'CREATED'" >
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-position" @click="handleDispatch(scope.row)">派发</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改保养工单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body @close="cancel" close-on-click-modal>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工单名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入工单名称" />
        </el-form-item>
        <el-form-item label="工单内容" prop="content">
          <el-input type="textarea" v-model="form.content" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <dispatch-person-dialog :open.sync="dispatchPersonDialog.open" :title="dispatchPersonDialog.title"
      :data="dispatchPersonDialog.data" @reload="this.getList"></dispatch-person-dialog>
  </div>
</template>

<script>
import { listMaintenanceCreateAndDispatch, getMaintenance, delMaintenance, addMaintenance, updateMaintenance, getMaintenanceStatus } from "@/api/document/maintenance"

import DispatchPersonDialog from "./dispatchPersonDialog.vue"

export default {
  name: "Maintenance",
  components: { DispatchPersonDialog },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 保养工单表格数据
      maintenanceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        no: null,
        status: null,
        name: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "请输入工单名称", trigger: "blur" },
          { min: 0, max: 50, message: "长度在 0 到 50 个字符", trigger: "blur" }
        ],
        content: [
          { min: 0, max: 500, message: "长度在 0 到 500 个字符", trigger: "blur" }
        ]
      },
      dispatchPersonDialog: {
        title: '派发工单',
        open: false,
        data: {}
      },
      statusList: []
    }
  },
  created() {
    this.initMaintenanceStatus();
    this.getList();

  },
  methods: {
    initMaintenanceStatus() {
      getMaintenanceStatus().then(res => {
        this.statusList = res.data;
      });
    },
    /** 查询保养工单列表 */
    getList() {
      this.loading = true
      listMaintenanceCreateAndDispatch(this.queryParams).then(response => {
        this.maintenanceList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    
    
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        content: null,
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 派发工单按钮操作  */
    handleDispatch(row) {
      this.dispatchPersonDialog.data = row;
      this.dispatchPersonDialog.open = true;
      this.dispatchPersonDialog.title = "派发工单";
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加保养工单"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getMaintenance(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改保养工单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMaintenance(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addMaintenance(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除保养工单编号为"' + ids + '"的数据项？').then(function () {
        return delMaintenance(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => { })
    },
  }
}
</script>
