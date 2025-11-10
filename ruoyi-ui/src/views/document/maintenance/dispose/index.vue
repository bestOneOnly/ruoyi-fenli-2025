<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
    <el-table v-loading="loading" :data="maintenanceList">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工单编号" align="center" prop="no" />
      <el-table-column label="工单状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.statusEnum.label }}
        </template>
      </el-table-column>
      <el-table-column label="工单名称" align="center" prop="name" />
      <el-table-column label="处置人" align="center" prop="disposeUserName" />
      <el-table-column label="处置描述" align="center" prop="disposeDesc" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope" v-if="scope.row.statusEnum.enumName==='DISPATCHED'">
          <el-button size="mini" type="text" icon="el-icon-thumb" @click="handleDisposeButton(scope.row)">处置</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改保养工单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body @close="cancel" close-on-click-modal>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="处置描述" prop="disposeDesc">
          <el-input type="textarea" v-model="form.disposeDesc" placeholder="请输入处置描述" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMaintenanceDispose, getMaintenance, handleDispose, getMaintenanceStatus } from "@/api/document/maintenance"

export default {
  name: "Maintenance",
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
        dispatchTime: null,
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
      statusList: []
    }
  },
  created() {
    this.initMaintenanceStatus();
    this.getList()
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
      listMaintenanceDispose(this.queryParams).then(response => {
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
        disposeDesc: null,
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

    /** 派发工单按钮操作  */
    handleDisposeButton(row) {
      this.reset()
      const id = row.id || this.ids
      getMaintenance(id).then(response => {
        this.form.id = response.data.id;
        this.open = true
        this.title = "处置保养工单"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          handleDispose(this.form).then(response => {
            this.$modal.msgSuccess("修改成功")
            this.open = false
            this.getList()
          })

        }
      })
    },
  }
}
</script>
