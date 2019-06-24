<template>
  <!-- 证书列表 -->
  <div class="certificate-list-wrap pd-20">
    <el-input
      v-model="userName"
      placeholder="请输入用户名称"
      class="mr-15"/>
    <el-input
      v-model="certificateName"
      placeholder="请输入证书名称"
      class="mr-15"/>
    <span>证书类型</span>
    <el-select v-model="certificateSelect" placeholder="请选择证书类型" class="state mr-15">
      <el-option
        v-for="item in certificateOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
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
        prop="certificateName"
        label="证书名称"/>
      <el-table-column
        prop="type"
        label="证书类型"/>
      <el-table-column
        prop="state"
        label="证书状态"/>
      <el-table-column prop="url" label="证书路径">
        <template slot-scope="scope">
          <el-button size="mini" @click="viewCertificate(scope.row.url)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="integral"
        label="获取积分"/>
      <el-table-column
        prop="createTime"
        label="创建时间"
        min-width="100"/>
      <el-table-column
        prop="remark"
        label="备注"/>
      <el-table-column label="操作" min-width="90" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="check(scope.$index, scope.row)">审核</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 证书审核弹窗 -->
    <el-dialog
      :visible.sync="checkDialog"
      title="审核信息"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="checkMsg"
        :model="checkMsg"
        :rules="checkMsgRules"
        label-position="left">
        <el-form-item prop="state" class="dept">
          <div class="name">
            <i>*</i>
            <span>审核状态：</span>
          </div>
          <el-select v-model="checkMsg.state" placeholder="请选择">
            <el-option
              v-for="item in stateOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item prop="checkContent" class="dept">
          <div class="name">
            <i>*</i>
            <span>审核内容：</span>
          </div>
          <el-input
            :rows="2"
            v-model="checkMsg.checkContent"
            type="textarea"
            placeholder="请输入审核内容"/>
        </el-form-item>
        <el-form-item prop="checkRemark" class="dept">
          <div class="name">
            <i/>
            <span>审核备注：</span>
          </div>
          <el-input
            :rows="2"
            v-model="checkMsg.checkRemark"
            type="textarea"
            placeholder="请输入审核备注"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="checkSure">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'CertificateList',
  data() {
    return {
      tableList: [{
        userName: '王小虎',
        certificateName: '学历证书',
        type: '学历',
        state: '审核中',
        url: '',
        integral: 100,
        createTime: '2019-01-01',
        remark: '证书备注',
        checkContent: '审核内容',
        checkRemark: '审核备注'
      }],
      certificateOptions: [{
        value: '全部',
        label: '全部'
      }, {
        value: '学位',
        label: '学位'
      }, {
        value: '学历',
        label: '学历'
      }],
      checkMsg: {},
      stateOptions: [{
        value: '全部',
        label: '全部'
      }, {
        value: '审核中',
        label: '审核中'
      }, {
        value: '已审核',
        label: '已审核'
      }],
      checkMsgRules: {
        checkContent: [
          { required: true, message: '请输入审核内容', trigger: 'blur' }
        ]
      },
      userName: '',
      certificateName: '',
      certificateSelect: '全部',
      checkDialog: false,
      selectIndex: 0
    }
  },
  methods: {
    // 搜索
    search() { },
    // 查看证书
    viewCertificate(url) {
      window.open(url)
    },
    // 审核信息
    check(index, row) {
      this.selectIndex = index
      this.checkMsg = {
        state: row.state,
        checkContent: row.checkContent,
        checkRemark: row.checkRemark
      }
      this.checkDialog = true
    },
    // 点击审核信息弹窗确定
    checkSure() {
      this.$refs.checkMsg.validate(valid => {
        if (valid) {
          this.tableList[this.selectIndex].state = this.checkMsg.state
          this.checkDialog = false
        }
      })
    },
    // 关闭弹窗
    closeDialog() {
      this.checkDialog = false
    }
  }
}
</script>

