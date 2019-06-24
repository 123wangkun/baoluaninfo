<template>
  <!-- 企业管理 -->
  <div class="ent-manage pd-20">
    <el-input
      v-model="entName"
      placeholder="请输入企业名称"
      class="mr-15"/>
    <el-select v-model="stateSelect" placeholder="请选择" class="state mr-15">
      <el-option
        v-for="item in stateOptions"
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
        prop="entName"
        label="企业名称"/>
      <el-table-column
        prop="entCode"
        label="企业编码"/>
      <el-table-column
        prop="fileSize"
        label="已上传文件大小"
        min-width="130"/>
      <el-table-column
        prop="userNum"
        label="已有用户数"
        min-width="100"/>
      <el-table-column
        prop="validity"
        label="有效期"
        min-width="100"/>
      <el-table-column
        prop="state"
        label="状态"/>
      <el-table-column prop="license" label="营业执照">
        <template slot-scope="scope">
          <el-button size="mini" @click="viewLicense(scope.row.license)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
        min-width="100"/>
      <el-table-column label="操作" min-width="140" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="primary" @click="check(scope.$index, scope.row)">审核</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改企业信息弹窗 -->
    <el-dialog
      :visible.sync="editDialog"
      :title="'修改企业信息'"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="entMsg"
        :model="entMsg"
        :rules="entMsgRules"
        label-position="left">
        <el-form-item prop="entName" class="dept">
          <div class="name">
            <i>*</i>
            <span>企业名称：</span>
          </div>
          <el-input
            v-model="entMsg.entName"
            placeholder="请输入企业名称"/>
        </el-form-item>
        <el-form-item prop="entCode" class="dept">
          <div class="name">
            <i>*</i>
            <span>企业编码：</span>
          </div>
          <el-input
            v-model="entMsg.entCode"
            placeholder="请输入企业编码"/>
        </el-form-item>
        <el-form-item prop="fileSize" class="dept">
          <div class="name">
            <i>*</i>
            <span>允许上传文件大小：</span>
          </div>
          <el-input
            v-model="entMsg.fileSize"
            placeholder="请输入文件大小"/>
        </el-form-item>
        <el-form-item prop="userNum" class="dept">
          <div class="name">
            <i>*</i>
            <span>允许使用用户数：</span>
          </div>
          <el-input
            v-model="entMsg.userNum"
            placeholder="请输入用户数"/>
        </el-form-item>
        <el-form-item prop="validity" class="dept">
          <div class="name">
            <i>*</i>
            <span>有效期：</span>
          </div>
          <el-date-picker
            v-model="entMsg.validity"
            :editable="false"
            type="date"
            placeholder="请选择时间"/>
        </el-form-item>
        <el-form-item prop="state" class="dept">
          <div class="name">
            <i>*</i>
            <span>企业状态：</span>
          </div>
          <el-radio v-model="entMsg.state" label="启用">启用</el-radio>
          <el-radio v-model="entMsg.state" label="禁用">禁用</el-radio>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="editSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 审核信息弹窗 -->
    <el-dialog
      :visible.sync="checkDialog"
      title="审核信息"
      class="check-dialog"
      @close="closeDialog">
      <el-form
        ref="checkMsg"
        :model="checkMsg"
        label-position="left">
        <el-form-item prop="entName" class="dept">
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
            <i/>
            <span>审核内容：</span>
          </div>
          <el-input
            :rows="2"
            v-model="checkMsg.checkContent"
            type="textarea"
            placeholder="请输入审核内容"/>
        </el-form-item>
        <el-form-item prop="remark" class="dept">
          <div class="name">
            <i/>
            <span>审核备注：</span>
          </div>
          <el-input
            :rows="2"
            v-model="checkMsg.remark"
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
  name: 'EntManage',
  data() {
    return {
      tableList: [{
        entName: '江苏高速养护公司',
        entCode: '001',
        fileSize: '100M',
        state: '启用',
        validity: '2019-01-01',
        userNum: 100,
        license: '',
        createTime: '2019-01-01'
      }],
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
      stateSelect: '全部',
      entMsg: {},
      checkMsg: {
        state: '',
        checkContent: '',
        remark: ''
      },
      entMsgRules: {
        entName: [
          { required: true, message: '请输入企业名称', trigger: 'blur' }
        ],
        entCode: [
          { required: true, message: '请输入企业编码', trigger: 'blur' }
        ],
        fileSize: [
          { required: true, message: '请输入文件大小', trigger: 'blur' }
        ],
        userNum: [
          { required: true, message: '请输入用户数', trigger: 'blur' }
        ]
      },
      entName: '',
      editDialog: false,
      checkDialog: false,
      selectIndex: 0
    }
  },
  methods: {
    // 搜索
    search() { },
    // 查看营业执照
    viewLicense(url) {
      window.open(url)
    },
    // 修改企业信息
    edit(index, row) {
      this.selectIndex = index
      this.entMsg = {
        entName: row.entName,
        entCode: row.entCode,
        fileSize: row.fileSize,
        state: row.state,
        userNum: row.userNum,
        validity: row.validity
      }
      this.editDialog = true
    },
    // 点击修改企业信息弹窗确定
    editSure() {
      this.$refs.entMsg.validate(valid => {
        if (valid) {
          this.tableList.splice(this.selectIndex, 1, this.entMsg)
          this.editDialog = false
        }
      })
    },
    // 审核信息
    check(index, row) {
      this.selectIndex = index
      this.checkMsg = {
        state: row.state,
        checkContent: row.checkContent,
        remark: row.remark
      }
      this.checkDialog = true
    },
    // 点击审核信息弹窗确定
    checkSure() {
      this.tableList.splice(this.selectIndex, 1, this.checkMsg)
      this.checkDialog = false
    },
    // 关闭弹窗
    closeDialog() {
      this.editDialog = false
      this.checkDialog = false
    }
  }
}
</script>
<style lang="scss">
  .ent-manage {
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
        width: 395px;
      }
    }
  }
</style>
