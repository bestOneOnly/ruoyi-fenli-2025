<template>
    <!-- 人员转派工单对话框 -->
    <el-dialog :title="title" :visible="open" width="500px" append-to-body @close="cancel" close-on-click-modal>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="工单名称" prop="name">
                <span>{{ formReadOnly.name }}</span>
            </el-form-item>
            <el-form-item label="工单内容" prop="content">
                <span>{{ formReadOnly.content }}</span>
            </el-form-item>
            <el-form-item label="派发人员" prop="dispatchUserId">
                <el-select v-model="form.dispatchUserId" placeholder="请选择责任人" style="width: 100%;">
                    <el-option v-for="item in userList" :key="item.userId"
                        :label="`${item.nickName}-${item.remark}-${item.userName}`" :value="item.userId"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="派发描述" prop="dispatchDesc">
                <el-input type="textarea" v-model="form.dispatchDesc" />
            </el-form-item>

        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
import { listUser } from "@/api/system/user";
import { handleDispatch } from "@/api/document/maintenance";

export default {
    name: "DispatchPersonDialog",
    props: {
        title: {
            type: String,
            default: "转派工单"
        },
        open: {
            type: Boolean,
            default: false
        },
        data: {
            type: Object,
            default: () => ({})
        }
    },
    data() {
        return {
            form: {},
            rules: {},
            formReadOnly: {

            },
            userList: []
        }
    },
    created() {
        this.getUserList();
    },
    methods: {
        //提交表单
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    handleDispatch(this.form).then(response => {
                        this.$modal.msgSuccess("派单操作成功");
                        this.$emit("reload");
                        this.cancel();
                    });
                }
            });
        },
        /** 查询用户列表 */
        getUserList() {
            return new Promise((resolve, reject) => {
                listUser({ pageNum: 1, pageSize: 1000 }).then(response => {
                    this.userList = response.rows;
                    resolve();
                }).catch(error => {
                    reject(error);
                });
            });
        },
        //取消
        cancel() {
            this.$emit("update:open", false)
        },
        //TODO 重置表单
        reset() {
            this.form = {
                id: null,
                dispatchUserId: null,
                dispatchDesc: null,
            }
            this.resetForm("form")
        },
    },
    watch: {
        open(bool) {
            if (bool) {
                this.reset();
                //如果有数据，填充表单
                if (this.data && this.data.id) {
                    this.form.id = this.data.id;
                    this.formReadOnly.name = this.data.name;
                    this.formReadOnly.content = this.data.content;
                    this.form.dispatchUserId = this.data.dispatchUserId;
                }
            }
        }
    }
}
</script>

<style></style>