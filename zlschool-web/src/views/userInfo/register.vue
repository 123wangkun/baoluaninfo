<template>
  <!-- 注册 -->
  <div class="register-container">
    <el-form
      ref="registerForm"
      :model="registerForm"
      :rules="registerRules"
      class="register-form"
      label-position="left"
    >
      <p class="register-title">用户注册</p>
      <div class="flex">
        <div class="register-left">
          <el-form-item prop="realName">
            <span class="svg-container">
              <svg-icon icon-class="user"/>
            </span>
            <el-input
              v-model="registerForm.realName"
              name="realName"
              auto-complete="off"
              placeholder="请输入真实姓名"
            />
          </el-form-item>
          <el-form-item prop="post">
            <span class="svg-container">
              <svg-icon icon-class="user"/>
            </span>
            <el-input
              v-model="registerForm.post"
              name="post"
              auto-complete="off"
              placeholder="请输入职务"
            />
          </el-form-item>
          <el-form-item prop="email">
            <span class="svg-container">
              <svg-icon icon-class="user"/>
            </span>
            <el-input
              v-model="registerForm.email"
              name="email"
              auto-complete="off"
              placeholder="请输入邮箱"
            />
          </el-form-item>
          <el-form-item prop="telePhone">
            <span class="svg-container">
              <svg-icon icon-class="user"/>
            </span>
            <el-input
              v-model.number="registerForm.telePhone"
              :disabled="true"
              name="telePhone"
              auto-complete="off"
              placeholder="请输入手机号码"
            />
          </el-form-item>
          <el-form-item prop="password">
            <span class="svg-container">
              <svg-icon icon-class="user"/>
            </span>
            <el-input
              v-model="registerForm.password"
              name="password"
              auto-complete="off"
              placeholder="请输入登录密码"
            />
          </el-form-item>
        </div>
        <div>
          <el-form-item prop="age">
            <el-date-picker
              v-model="registerForm.age"
              :editable="false"
              type="date"
              placeholder="请选择出生日期"
              class="both"/>
          </el-form-item>
          <el-form-item prop="position">
            <span class="svg-container">
              <svg-icon icon-class="user"/>
            </span>
            <el-input
              v-model="registerForm.position"
              name="position"
              auto-complete="off"
              placeholder="请输入职称"
            />
          </el-form-item>
          <el-form-item prop="sex">
            <span class="sex">性别：</span>
            <el-radio v-model="registerForm.sex" :label="0">男</el-radio>
            <el-radio v-model="registerForm.sex" :label="1">女</el-radio>
          </el-form-item>
          <el-form-item prop="entCode">
            <span class="svg-container">
              <svg-icon icon-class="user"/>
            </span>
            <el-input
              v-model="registerForm.entCode"
              name="entCode"
              auto-complete="off"
              placeholder="请输入部门邀请码"
            />
          </el-form-item>
          <el-form-item prop="confirmPwd">
            <span class="svg-container">
              <svg-icon icon-class="user"/>
            </span>
            <el-input
              v-model="registerForm.confirmPwd"
              name="confirmPwd"
              auto-complete="off"
              placeholder="请再次输入密码"
            />
          </el-form-item>
        </div>
      </div>
      <div class="upload">
        <p>上传证书</p>
        <div slot="tip" class="el-upload__tip">* 只能上传jpg/jpeg/png文件，且不超过2M</div>
        <div class="flex">
          <div class="tag">
            <el-tag>学历</el-tag>
            <el-upload
              ref="upload"
              :file-list="educationFileList"
              :on-remove="handleRemove"
              :before-upload="beforeUpload"
              :multiple="multiple"
              :http-request="uploadEducation"
              action="customize"
              accept=".jpg,.jpeg,.png"
              list-type="picture"
              class="upload-demo">
              <el-button size="small" type="primary" plain>点击上传</el-button>
            </el-upload>
          </div>
          <div class="tag">
            <el-tag>学位</el-tag>
            <el-upload
              ref="upload"
              :file-list="degreeFileList"
              :on-remove="handleRemove"
              :before-upload="beforeUpload"
              :multiple="multiple"
              :http-request="uploadDegree"
              action="customize"
              accept=".jpg,.jpeg,.png"
              list-type="picture"
              class="upload-demo">
              <el-button size="small" type="primary" plain>点击上传</el-button>
            </el-upload>
          </div>
          <div class="tag">
            <el-tag>职称</el-tag>
            <el-upload
              ref="upload"
              :file-list="positionFileList"
              :on-remove="handleRemove"
              :before-upload="beforeUpload"
              :multiple="multiple"
              :http-request="uploadPosition"
              action="customize"
              accept=".jpg,.jpeg,.png"
              list-type="picture"
              class="upload-demo">
              <el-button size="small" type="primary" plain>点击上传</el-button>
            </el-upload>
          </div>
          <div class="tag">
            <el-tag>从业资格证书</el-tag>
            <el-upload
              ref="upload"
              :file-list="certificateFileList"
              :on-remove="handleRemove"
              :before-upload="beforeUpload"
              :multiple="multiple"
              :http-request="uploadCertificate"
              action="customize"
              accept=".jpg,.jpeg,.png"
              list-type="picture"
              class="upload-demo">
              <el-button size="small" type="primary" plain>点击上传</el-button>
            </el-upload>
          </div>
          <div class="tag">
            <el-tag>专业工作年限</el-tag>
            <el-upload
              ref="upload"
              :file-list="workYearFileList"
              :on-remove="handleRemove"
              :before-upload="beforeUpload"
              :multiple="multiple"
              :http-request="uploadWorkYear"
              action="customize"
              accept=".jpg,.jpeg,.png"
              list-type="picture"
              class="upload-demo">
              <el-button size="small" type="primary" plain>点击上传</el-button>
            </el-upload>
          </div>
        </div>
      </div>
      <el-button
        :loading="loading"
        type="primary"
        class="login-bt"
        @click.native.prevent="register"
      >注册</el-button>
    </el-form>
  </div>
</template>

<script>
import { validatTel } from '@/utils/validate'
import { uploadFile, delFile } from '@/api/common'
import { register } from '@/api/userInfo/login'
export default {
  name: 'Register',
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
    // 验证登录密码
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入登录密码'))
      } else {
        if (this.registerForm.confirmPwd !== '') {
          this.$refs.registerForm.validateField('confirmPwd')
        }
        callback()
      }
    }
    // 验证确认密码
    const validateConfirmPwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      registerForm: {
        realName: '',
        age: '',
        post: '',
        position: '',
        telePhone: '',
        email: '',
        entCode: '',
        fileList: [],
        password: '',
        confirmPwd: '',
        sex: 0 // 0男生，1女生
      },
      educationFileList: [],
      degreeFileList: [],
      positionFileList: [],
      certificateFileList: [],
      workYearFileList: [],
      dialogVisible: false,
      loading: false,
      redirect: undefined,
      multiple: true,
      fileType: '000001',
      fileObj: null,
      registerRules: {
        realName: [
          { required: true, message: '请输入真实姓名', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请选择出生日期', trigger: 'blur' }
        ],
        post: [
          { required: true, message: '请输入职务', trigger: 'blur' }
        ],
        position: [
          { required: true, message: '请输入职称', trigger: 'blur' }
        ],
        telePhone: [
          { validator: validatePhone, trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱', trigger: 'blur' }
        ],
        password: [
          { validator: validatePassword, trigger: 'blur' }
        ],
        confirmPwd: [
          { validator: validateConfirmPwd, trigger: 'blur' }
        ]
      }
    }
  },
  mounted() {
    const telePhone = this.$route.params.telePhone
    if (telePhone) {
      this.registerForm.telePhone = telePhone
    }
  },
  methods: {
    // 上传文件前限制文件大小
    beforeUpload(file) {
      const size = file.size / 1024 / 1024
      if (size > 2) {
        this.$message.info('图片大小不可超过2M')
        return false
      }
    },
    // 删除证件
    handleRemove(file) {
      console.log(file)
      console.log(this.educationFileList)
      const fileList = this.registerForm.fileList
      for (const i in fileList) {
        if (file.url === fileList[i].url) {
          fileList.splice(i, 1)
          const params = {
            fileUrl: file.url
          }
          delFile(params).then(res => {
          }).catch(error => {
            this.$message.error(error.err_msg)
          })
          break
        }
      }
    },
    // 上传学历
    uploadEducation(param) {
      this.fileType = '000001'
      this.upload(param, this.educationFileList)
    },
    // 上传学位
    uploadDegree(param) {
      this.fileType = '000002'
      this.upload(param, this.degreeFileList)
    },
    // 上传职称
    uploadPosition(param) {
      this.fileType = '000003'
      this.upload(param, this.positionFileList)
    },
    // 上传从业资格证书
    uploadCertificate(param) {
      this.fileType = '000004'
      this.upload(param, this.certificateFileList)
    },
    // 上传专业工作年限
    uploadWorkYear(param) {
      this.fileType = '000005'
      this.upload(param, this.workYearFileList)
    },
    // 上传
    upload(param, fileList) {
      this.fileObj = new FormData()
      this.fileObj.append('editormd-image-file', param.file)
      uploadFile(this.fileObj, this.fileType).then(res => {
        const file = { name: res.name, type: res.type, url: res.url, size: res.fileSize }
        this.registerForm.fileList.push(file)
        fileList.push(res)
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 点击注册
    register() {
      console.log(this.registerForm)
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          const registerParams = {
            age: this.registerForm.age,
            email: this.registerForm.email,
            entCode: this.registerForm.entCode,
            fileList: this.registerForm.fileList,
            password: this.registerForm.password,
            post: this.registerForm.post,
            position: this.registerForm.position,
            realName: this.registerForm.realName,
            sex: this.registerForm.sex,
            telePhone: this.registerForm.telePhone
          }
          register(registerParams).then(registerRes => {
            this.$message.success('注册成功')
            setTimeout(() => {
              this.$router.push({ path: '/login' })
            }, 2000)
          }).catch(error => {
            this.$message.error(error.err_msg)
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
  $bg: #2d3a4b;
  $dark_gray: #889aa4;
  .register-container {
    width: 100%;
    overflow: hidden;
    background: url(../../assets/img/bg.jpg) no-repeat;
    background-size: 100% 100%;
    .el-form-item__content {
      background: #fff;
      .el-input {
        display: inline-block;
        height: 40px;
        width: 85%;
        font-size: 16px;
        input {
          background: transparent;
          border: 0px;
          -webkit-appearance: none;
          border-radius: 0px;
          padding: 0px 5px 0px 15px;
          color: #646464;
          height: 40px;
          background: white;
          &:-webkit-autofill {
            box-shadow: 0 0 0px 1000px $bg inset !important;
            -webkit-text-fill-color: #646464 !important;
          }
        }
      }
      .svg-container {
        padding: 0px 5px 0px 15px;
        color: $dark_gray;
        vertical-align: middle;
        width: 30px;
        display: inline-block;
      }
    }
    .both {
      input {
        padding-left: 48px!important;
      }
    }
    .el-form-item {
      border: 1px solid #dddddd;
      border-radius: 5px;
      color: #646464;
    }
    .register-title {
      font-size: 26px;
      color: #fff;
      text-align: center;
    }
    .upload{
      padding: 1px 15px 20px 15px;
      border-radius: 4px;
      background: #fff;
    }
    .register-left {
      margin-right: 45px;
    }
    .el-upload-list__item,
    .el-upload--picture-card {
      position: relative;
      width: 60px;
      height: 60px;
      .el-icon-plus {
        position: absolute;
        top: 25%;
        left: 25%;
      }
    }
    .tag{
      padding: 20px 0;
      width: 50%;
      border-bottom: 1px solid #e8e8e8;
      .el-tag{
        margin-bottom: 10px;
        color: #fff;
        background: #409EFF;
      }
    }
    .tag:last-child{
      width: 100%;
      border: 0;
    }
    .el-upload__tip {
      margin-top: 0;
    }
    .el-dialog__wrapper {
      z-index: 9999!important;
    }
    .el-upload-list__item-preview {
      display: none!important;
    }
    .el-icon-delete {
      position: relative;
      top: 3px;
      left: 0px;
    }
    .tag-code {
      margin-bottom: 24px;
      padding: 0;
      width: 100px;
      height: 44px;
      line-height: 44px;
      font-size: 14px;
    }
    .login-bt {
      margin-top: 30px;
      margin-left: 20%;
      width: 60%;
    }
    .register-form {
      margin: 0 auto;
      padding-bottom: 40px;
      width: 508px;
    }
    .el-upload-list--picture{
      display:-webkit-box;
      display: -webkit-flex;
      display: flex;
      flex-wrap: wrap;
      .el-upload-list__item {
        margin-right: 10px;
        padding: 0px 0px 0px 60px;
        height: 60px;
        border: 0;
        .el-upload-list__item-thumbnail {
          margin-left: -60px;
          width: 60px;
          height: 60px;
        }
        .el-upload-list__item-status-label,.el-icon-close {
          z-index: 99;
        }
      }
    }
    .sex {
      margin-left: 10px;
    }
  }
</style>

