<template>
  <!-- 个人用户 -->
  <div class="user-wrap pd-20">
    <el-input
      v-model="userName"
      placeholder="请输入用户名称"/>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
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
        prop="userName"
        label="用户名称"/>
      <el-table-column
        prop="telePhone"
        label="手机号码"
        min-width="114"/>
      <el-table-column
        prop="registeTime"
        label="注册时间"
        min-width="100"/>
      <el-table-column
        prop="state"
        label="用户状态"
        min-width="100"/>
      <el-table-column
        prop="fileSize"
        label="已上传文件大小"
        min-width="130"/>
      <el-table-column
        prop="integral"
        label="用户总积分"
        min-width="100"/>
      <el-table-column prop="license" label="用户证书">
        <template slot-scope="scope">
          <el-button size="mini" @click="viewLicense(scope.row.license)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="170" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="primary" @click="grouth(scope.$index)">成长报告</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改用户信息弹窗 -->
    <el-dialog
      :visible.sync="editDialog"
      :title="'修改用户信息'"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="userMsg"
        :model="userMsg"
        :rules="userMsgRules"
        label-position="left">
        <el-form-item prop="userName" class="dept">
          <div class="name">
            <i>*</i>
            <span>用户名称：</span>
          </div>
          <el-input
            v-model="userMsg.userName"
            placeholder="请输入用户名称"/>
        </el-form-item>
        <el-form-item prop="telePhone" class="dept">
          <div class="name">
            <i>*</i>
            <span>手机号码：</span>
          </div>
          <el-input
            v-model="userMsg.telePhone"
            placeholder="请输入手机号码"/>
        </el-form-item>
        <el-form-item prop="fileSize" class="dept">
          <div class="name">
            <i>*</i>
            <span>允许上传文件大小：</span>
          </div>
          <el-input
            v-model="userMsg.fileSize"
            placeholder="请输入文件大小"/>
        </el-form-item>
        <el-form-item prop="state" class="dept">
          <div class="name">
            <i>*</i>
            <span>用户状态：</span>
          </div>
          <el-radio v-model="userMsg.state" label="启用">启用</el-radio>
          <el-radio v-model="userMsg.state" label="禁用">禁用</el-radio>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="editSure">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { validatTel } from '@/utils/validate'
export default {
  name: 'User',
  data() {
    // 验证手机号
    const validatePhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号码'))
      } else if (!validatTel(value)) {
        callback(new Error('输入的手机号码格式有误'))
      } else {
        callback()
      }
    }
    return {
      tableList: [{
        userName: '王小虎',
        telePhone: '18895332598',
        registeTime: '2019-01-01',
        state: '启用',
        fileSize: '100M',
        integral: 100,
        license: ''
      }],
      userMsg: {},
      userMsgRules: {
        userName: [
          { required: true, message: '请输入用户名称', trigger: 'blur' }
        ],
        telePhone: [
          { validator: validatePhone, trigger: 'blur' }
        ],
        fileSize: [
          { required: true, message: '请输入文件大小', trigger: 'blur' }
        ]
      },
      userName: '',
      editDialog: false,
      selectIndex: 0
    }
  },
  methods: {
    // 搜索
    search() {
      console.log(this.userName)
    },
    // 查看营业执照
    viewLicense(url) {
      window.open(url)
    },
    // 修改企业信息
    edit(index, row) {
      this.selectIndex = index
      this.userMsg = {
        userName: row.userName,
        telePhone: row.telePhone,
        fileSize: row.fileSize,
        state: row.state
      }
      this.editDialog = true
    },
    // 点击修改企业信息弹窗确定
    editSure() {
      this.$refs.userMsg.validate(valid => {
        if (valid) {
          this.tableList.splice(this.selectIndex, 1, this.userMsg)
          this.editDialog = false
        }
      })
    },
    // 关闭弹窗
    closeDialog() {
      this.editDialog = false
    },
    // 成长报告
    grouth(index) {
      this.selectIndex = index
    }
  }
}
</script>
<style lang="scss">
  .user-wrap {
    .edit-dialog {
      .el-dialog {
        width: 460px;
        .name {
          width: 140px;
        }
      }
    }
    .el-dialog__body {
      .el-form-item__error {
        left: 145px;
      }
    }
    .check-dialog {
      .el-dialog {
        width: 420px;
      }
    }
  }
</style>
