<template>
  <!-- 证书类型 -->
  <div class="certificate-type-wrap pd-20">
    <el-button type="primary" plain class="btn" @click="add">新增</el-button>
    <el-table
      ref="multipleTable"
      :data="tableList"
      border
      stripe
      tooltip-effect="dark"
      class="table mt-15">
      <el-table-column
        type="index"
        width="55"
        label="序号"/>
      <el-table-column
        prop="typeName"
        label="类型名称"/>
      <el-table-column
        prop="integral"
        label="获取积分"/>
      <el-table-column
        prop="state"
        label="状态"/>
      <el-table-column
        prop="createTime"
        label="创建时间"
        min-width="100"/>
      <el-table-column
        prop="remark"
        label="备注"/>
      <el-table-column label="操作" min-width="140" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增/修改证书类型弹窗 -->
    <el-dialog
      :visible.sync="editDialog"
      :title="operate == 0 ? '新增证书类型' : '修改证书类型'"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="certificateMsg"
        :model="certificateMsg"
        :rules="certificateMsgRules"
        label-position="left">
        <el-form-item prop="typeName" class="dept">
          <div class="name">
            <i>*</i>
            <span>类型名称：</span>
          </div>
          <el-input
            v-model="certificateMsg.typeName"
            placeholder="请输入类型名称"/>
        </el-form-item>
        <el-form-item prop="integral" class="dept">
          <div class="name">
            <i>*</i>
            <span>所获积分：</span>
          </div>
          <el-input
            v-model="certificateMsg.integral"
            placeholder="请输入积分"/>
        </el-form-item>
        <el-form-item prop="userState" class="dept">
          <div class="name">
            <i>*</i>
            <span>用户状态：</span>
          </div>
          <el-radio v-model="certificateMsg.userState" label="启用">启用</el-radio>
          <el-radio v-model="certificateMsg.userState" label="禁用">禁用</el-radio>
        </el-form-item>
        <el-form-item prop="remark" class="dept">
          <div class="name">
            <i/>
            <span>备注：</span>
          </div>
          <el-input
            :rows="2"
            v-model="certificateMsg.remark"
            type="textarea"
            placeholder="请输入备注"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="editSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 删除弹窗 -->
    <el-dialog
      :visible.sync="delDialog"
      title="您确定删除吗？"
      width="30%"
      @close="closeDialog">
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="delSure">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'CertificateType',
  data() {
    return {
      tableList: [{
        typeName: '学历',
        integral: 100,
        state: '已审核',
        userState: '启用',
        createTime: '2019-01-01',
        remark: ''
      }],
      certificateMsg: {},
      certificateMsgRules: {
        typeName: [
          { required: true, message: '请输入类型名称', trigger: 'blur' }
        ],
        integral: [
          { required: true, message: '请输入所获积分', trigger: 'blur' }
        ]
      },
      editDialog: false,
      delDialog: false,
      selectIndex: 0,
      operate: 0
    }
  },
  methods: {
    // 新增证书类型
    add() {
      this.operate = 0
      this.certificateMsg = {}
      this.certificateMsg.userState = '启用'
      this.editDialog = true
    },
    // 修改证书类型
    edit(index, row) {
      this.operate = 1
      this.selectIndex = index
      this.certificateMsg = {
        typeName: row.typeName,
        integral: row.integral,
        userState: row.userState,
        remark: row.remark
      }
      this.editDialog = true
    },
    // 点击修改证书类型弹窗确定
    editSure() {
      this.$refs.certificateMsg.validate(valid => {
        if (valid) {
          if (this.operate === 0) {
            this.tableList.push(this.certificateMsg)
          } else {
            this.tableList.splice(this.selectIndex, 1, this.certificateMsg)
          }
          this.editDialog = false
        }
      })
    },
    // 删除
    del(index) {
      this.selectIndex = index
      this.delDialog = true
    },
    // 点击删除弹窗确定
    delSure() {
      this.tableList.splice(this.selectIndex, 1)
      this.delDialog = false
    },
    // 关闭弹窗
    closeDialog() {
      this.editDialog = false
      this.delDialog = false
    }
  }
}
</script>
<style lang="scss">
  .certificate-type-wrap {
    .table{
      width: 75%;
    }
  }
</style>
