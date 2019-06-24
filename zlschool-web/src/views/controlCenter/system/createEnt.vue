<template>
  <!-- 创建企业 -->
  <div class="create-ent pd-20">
    <el-button type="primary" plain class="btn" @click="add">新增</el-button>
    <el-button type="primary" plain class="btn" @click="join">加入组织</el-button>
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
        prop="name"
        label="企业名称"/>
      <el-table-column
        prop="code"
        label="企业编码"/>
      <el-table-column
        prop="allowSize"
        label="已上传文件大小"
        min-width="130"/>
      <el-table-column
        prop="allowUser"
        label="已有用户数"
        min-width="100"/>
      <el-table-column
        prop="validate"
        label="有效期"
        min-width="100"/>
      <el-table-column
        prop="status"
        label="状态"/>
      <el-table-column prop="license" label="营业执照">
        <template slot-scope="scope">
          <el-button size="mini" @click="viewLicense(scope.row.license)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="createdTime"
        label="创建时间"
        min-width="100"/>
      <el-table-column label="操作" min-width="140" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增/修改弹窗 -->
    <el-dialog
      :visible.sync="addDialog"
      :title="operate === 0 ? '创建企业' : '修改企业信息'"
      class="create-dialog"
      @close="closeDialog">
      <el-form
        ref="addMsg"
        :model="addMsg"
        :rules="addMsgRules"
        label-position="left">
        <el-form-item prop="name" class="dept mt-15">
          <div class="name">
            <i>*</i>
            <span>企业名称：</span>
          </div>
          <el-input
            v-model="addMsg.name"
            placeholder="请输入企业名称"
            class="search-input"/>
        </el-form-item>
        <el-form-item prop="code" class="dept mt-15">
          <div class="name">
            <i>*</i>
            <span>企业编码：</span>
          </div>
          <el-input
            v-model="addMsg.code"
            placeholder="请输入企业编码"
            class="search-input"/>
        </el-form-item>
        <el-form-item prop="sysName" class="dept mt-15">
          <div class="name">
            <i>*</i>
            <span>系统名称：</span>
          </div>
          <el-input
            v-model="addMsg.sysName"
            placeholder="请输入系统名称"
            class="search-input"/>
        </el-form-item>
        <el-form-item prop="sysLogo" class="mt-15 dept license">
          <div class="name">
            <i>*</i>
            <span>系统logo：</span>
          </div>
          <span class="upcode upload-size">（大小不超过2M）</span>
          <el-upload
            v-if="addMsg.sysLogo[0].url == ''"
            v-model="addMsg.sysLogo[0].url"
            :file-list="addMsg.sysLogo"
            :before-upload="beforeUpload"
            :http-request="uploadLogo"
            :show-file-list="false"
            drag
            action="customize"
            accept=".jpg,.jpeg,.png"
            class="upload-demo">
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
          <div v-else class="license-pic">
            <img :src="addMsg.sysLogo[0].url">
            <i class="el-icon-close" @click="removeLogo"/>
          </div>
        </el-form-item>
        <el-form-item prop="licenseTerm" class="dept mt-15">
          <div class="name">
            <i>*</i>
            <span>营业执照有效期：</span>
          </div>
          <el-date-picker
            v-model="addMsg.licenseTerm"
            :editable="false"
            type="date"
            placeholder="请选择日期"/>
        </el-form-item>
        <el-form-item prop="license" class="mt-15 dept license">
          <div class="name">
            <i>*</i>
            <span>营业执照：</span>
          </div>
          <span class="upcode upload-size">（大小不超过2M）</span>
          <el-upload
            v-if="addMsg.license[0].url == ''"
            v-model="addMsg.license[0].url"
            :file-list="addMsg.license"
            :before-upload="beforeUpload"
            :http-request="uploadLicense"
            :show-file-list="false"
            drag
            action="customize"
            accept=".jpg,.jpeg,.png"
            class="upload-demo">
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
          <div v-else class="license-pic">
            <img :src="addMsg.license[0].url">
            <i class="el-icon-close" @click="removeLicense"/>
          </div>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="addSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 加入组织弹窗 -->
    <el-dialog
      :visible.sync="joinDialog"
      :title="'加入组织'"
      class="create-dialog"
      @close="closeDialog">
      <el-form
        ref="addMsg"
        :model="addMsg"
        :rules="addMsgRules"
        label-position="left">
        <el-form-item prop="telePhone" class="dept mt-15">
          <div class="name">
            <i>*</i>
            <span>手机号码：</span>
          </div>
          <el-input
            v-model="addMsg.telePhone"
            placeholder="请输入手机号码"
            class="search-input"/>
        </el-form-item>
        <el-form-item prop="code" class="dept mt-15">
          <div class="name">
            <i>*</i>
            <span>部门邀请码：</span>
          </div>
          <el-input
            v-model="addMsg.code"
            placeholder="请输入部门邀请码"
            class="search-input"/>
        </el-form-item>
        <el-form-item prop="post" class="dept mt-15">
          <div class="name">
            <i>*</i>
            <span>职务：</span>
          </div>
          <el-input
            v-model="addMsg.post"
            placeholder="请输入职务名称"
            class="search-input"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="addSure">确 定</el-button>
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
import { uploadFile, delFile } from '@/api/common'
// import { addEnterprise, deleteEnterprise, getEnterprise, editEnterprise } from '@/api/system/organize'
export default {
  name: 'CreateEnt',
  data() {
    const validateLicense = (rule, value, callback) => {
      if (value[0].url === '') {
        return callback(new Error('请上传营业执照'))
      } else {
        callback()
      }
    }
    return {
      tableList: [{
        name: '江苏高速养护公司',
        code: '001',
        allowSize: '100M',
        statue: '启用',
        validate: '2019-01-01',
        allowUser: 100,
        license: '',
        createdTime: '2019-01-01'
      }],
      addMsg: {
        name: '',
        code: '',
        sysName: '',
        sysLogo: [
          {
            url: ''
          }
        ],
        licenseTerm: '',
        license: [
          {
            url: ''
          }
        ]
      },
      addMsgRules: {
        name: [
          { required: true, message: '请输入企业名称', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入企业编码', trigger: 'blur' }
        ],
        licenseTerm: [
          { required: true, message: '请选择日期', trigger: 'blur' }
        ],
        license: [
          { validator: validateLicense, trigger: 'blur' }
        ]
      },
      addDialog: false,
      delDialog: false,
      operate: 0,
      selectIndex: 0
    }
  },
  created() {
    this.getEnterpriseList
  },
  methods: {
    // 获取企业列表
    getEnterpriseList() {
      // const departmentParams = {
      //   id: this.deptSelect.data.id
      // }
      // getEnterprise(departmentParams).then(res => {
      // }).catch(error => {
      //   this.$message.error(error.err_msg)
      // })
    },
    // 查看营业执照
    viewLicense(url) {
      window.open(url)
    },
    // 新增
    add() {
      this.operate = 0
      this.addMsg = {}
      this.addMsg = {
        name: '',
        code: '',
        sysName: '',
        sysLogo: [
          {
            url: ''
          }
        ],
        licenseTerm: '',
        license: [
          {
            url: ''
          }
        ]
      }
      this.addDialog = true
    },
    // 点击新增/修改弹窗确定
    addSure() {
      console.log(this.addMsg)
      this.$refs.addMsg.validate(valid => {
        if (valid) {
          if (this.operate === 0) {
            // 新建企业
            this.tableList.push(this.addMsg)
            // const organizeParams = {
            //   name: this.addOrganizeForm.organizeName,
            //   code: this.addOrganizeForm.organizeCode,
            //   parentId: 'root',
            //   license: this.addOrganizeForm.license[0].url,
            //   licenseTerm: this.addOrganizeForm.licenseDate
            // }
            // addEnterprise(organizeParams).then(res => {
            //   this.addOrganizeDialog = false
            //   this.getEnterpriseList()
            // }).catch(error => {
            //   this.$message.error(error.err_msg)
            // })
          } else if (this.operate === 1) {
            // 修改企业
            this.tableList.splice(this.selectIndex, 1, this.addMsg)
            // const organizeParams = {
            //   id: this.deptSelect.data.id,
            //   name: this.addOrganizeForm.organizeName,
            //   code: this.addOrganizeForm.organizeCode,
            //   license: this.addOrganizeForm.license[0].url,
            //   licenseTerm: this.addOrganizeForm.licenseDate,
            //   type: 1
            // }
            // editEnterprise(organizeParams).then(res => {
            //   // 企业修改界面
            //   this.addOrganizeDialog = false
            //   this.getEnterpriseList()
            // }).catch(error => {
            //   this.$message.error(error.err_msg)
            // })
          }
          this.addDialog = false
        }
      })
    },
    // 加入组织
    join() {},
    // 修改企业信息
    edit(index, row) {
      this.operate = 1
      this.selectIndex = index
      this.addMsg = {
        name: row.name,
        code: row.code,
        sysName: row.sysName,
        sysLogo: row.sysLogo,
        licenseTerm: row.licenseTerm,
        license: row.license
      }
      this.addDialog = true
    },
    // 删除
    del(index) {
      this.selectIndex = index
      this.delDialog = true
    },
    // 点击删除弹窗确定
    delSure() {
      this.tableList.splice(this.selectIndex, 1)
      // const departmentParams = {
      //   depId: this.deptSelect.data.id
      // }
      // deleteEnterprise(departmentParams).then(res => {
      //   this.$message.success('删除成功')
      //   this.getEnterpriseList()
      // }).catch(error => {
      //   this.$message.error(error.err_msg)
      // })
      this.delDialog = false
    },
    // 关闭弹窗
    closeDialog() {
      this.addDialog = false
      this.delDialog = false
    },
    // 上传文件前限制大小
    beforeUpload(file) {
      const size = file.size / 1024 / 1024
      if (size > 2) {
        this.$message.info('大小不可超过2M')
        return false
      }
    },
    // 上传文件
    uploadFile(param, fileList) {
      const fileObj = new FormData()
      fileObj.append('editormd-image-file', param.file)
      uploadFile(fileObj, 5).then(res => {
        const file = { url: res.url, size: res.fileSize, type: res.type }
        fileList = []
        fileList.push(file)
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 删除文件
    removeFile(fileList) {
      const params = {
        fileUrl: fileList[0].url
      }
      delFile(params).then(res => {
        fileList[0].url = ''
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 上传系统logo
    uploadLogo(param) {
      this.uploadFile(param, this.addMsg.sysLogo)
    },
    // 删除系统logo
    removeLogo() {
      this.removeFile(this.addMsg.sysLogo)
    },
    // 上传营业执照
    uploadLicense(param) {
      this.uploadFile(param, this.addMsg.license)
    },
    // 删除营业执照
    removeLicense() {
      this.removeFile(this.addMsg.license)
    }
  }
}
</script>
<style lang="scss">
  .create-ent {
    .create-dialog {
      .el-dialog {
        width: 410px;
      }
      .el-dialog__body {
        margin-left: 7px;
        .name {
          width: 125px;
        }
        .el-form-item__error {
          left: 128px;
        }
        .license {
          .el-icon-upload {
            width: auto;
            color: #C0C4CC;
          }
          .el-form-item__error {
            top: 96%;
            left: 138px;
          }
          .license-pic {
            position: relative;
            top: 24px;
            img {
              display: block;
              margin-left: 3px;
              width: 350px;
              height: auto;
            }
            i {
              position: absolute;
              top: -10px;
              right: 0px;
            }
          }
        }
      }
    }
  }
</style>
