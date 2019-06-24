<template>
  <!-- 修改密码 -->
  <div class="findpwd-container">
    <el-form
      ref="findPwdForm"
      :model="findPwdForm"
      :rules="findPwdRules"
      class="register-form"
      label-position="left"
    >
      <p class="register-title">忘记密码</p>
      <el-form-item prop="newPassword">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          v-model="findPwdForm.newPassword"
          type="text"
          name="newPassword"
          auto-complete="off"
          placeholder="请输入新密码"
        />
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          v-model="findPwdForm.confirmPassword"
          type="text"
          name="confirmPassword"
          auto-complete="off"
          placeholder="请再次输入密码"
        />
      </el-form-item>
      <div class="flex tel">
        <el-form-item prop="telePhone">
          <span class="svg-container">
            <svg-icon icon-class="user"/>
          </span>
          <el-input
            v-model="findPwdForm.telePhone"
            type="text"
            name="telePhone"
            auto-complete="off"
            placeholder="请输入手机号码"
          />
        </el-form-item>
        <el-button
          :disabled="smsDisabled"
          class="tag-code"
          type="primary"
          plain
          @click="getMsgCode"
        >{{ codeMsg }}</el-button>
      </div>
      <el-form-item prop="smscode">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          v-model="findPwdForm.smscode"
          type="text"
          name="smscode"
          auto-complete="off"
          placeholder="请输入验证码"
        />
      </el-form-item>
      <el-button
        :loading="loading"
        type="primary"
        class="login-bt"
        @click.native.prevent="findPwd"
      >确定</el-button>
    </el-form>
  </div>
</template>

<script>
import { validatTel } from '@/utils/validate'
import { forgetPassword } from '@/api/userInfo/login'
import { sendSmsCode, checkSmsCode } from '@/api/common'
export default {
  name: 'Forgetpwd',
  data() {
    // 验证手机号
    const validatePhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号码'))
      } else if (!validatTel(value)) {
        callback(new Error('请输入正确的手机号'))
      } else {
        callback()
      }
    }
    // 验证新密码
    const validateNewPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入登录密码'))
      } else {
        if (this.findPwdForm.confirmPassword !== '') {
          this.$refs.findPwdForm.validateField('confirmPassword')
        }
        callback()
      }
    }
    // 验证确认密码
    const validateConfirmPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.findPwdForm.newPassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      findPwdForm: {
        newPassword: '',
        confirmPassword: '',
        telePhone: '',
        smscode: ''
      },
      findPwdRules: {
        newPassword: [
          { validator: validateNewPassword, trigger: 'blur' }
        ],
        confirmPassword: [
          { validator: validateConfirmPassword, trigger: 'blur' }
        ],
        telePhone: [
          { validator: validatePhone, trigger: 'blur' }
        ],
        smscode: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
        ]
      },
      loading: false,
      codeMsg: '获取验证码',
      smsDisabled: false
    }
  },
  methods: {
    // 获取验证码
    getMsgCode() {
      const phone = this.findPwdForm.telePhone
      if (!phone || !validatTel(phone)) {
        this.$refs.findPwdForm.validateField('telePhone')
      } else {
        // 倒计时
        this.codeMsg = 60
        this.smsDisabled = true
        const time = setInterval(() => {
          if (this.codeMsg === 1) {
            clearInterval(time)
            this.smsDisabled = false
            this.codeMsg = '重新发送'
          } else {
            this.codeMsg -= 1
          }
        }, 1000)
        sendSmsCode(phone, 2).then(res => {
          this.$message.success('验证码已发送')
        }).catch(error => {
          this.$message.error(error.err_msg)
        })
      }
    },
    // 点击注册
    findPwd() {
      this.$refs.findPwdForm.validate(valid => {
        if (valid) {
          const smsCodeParams = {
            phone: this.findPwdForm.telePhone,
            smsCode: this.findPwdForm.smscode,
            type: 2
          }
          const params = {
            newPassword: this.findPwdForm.newPassword,
            telePhone: this.findPwdForm.telePhone
          }
          checkSmsCode(smsCodeParams).then(res => {
            forgetPassword(params).then(res => {
              this.$message.success('修改成功')
              setTimeout(() => {
                this.$router.push({ path: '/login' })
              }, 2000)
            }).catch(error => {
              this.$message.error(error.err_msg)
            })
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
  .findpwd-container {
    position: fixed;
    width: 100%;
    height: 100%;
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
    .tag{
      padding: 20px 0;
      border-bottom: 1px solid #e8e8e8;
    }
    .el-tag{
      margin-bottom: 10px;
    }
    .tag-code {
      padding: 0;
      width: 100px;
      height: 44px;
      line-height: 44px;
      font-size: 14px;
    }
    .login-bt {
      width: 100%;
    }
    .register-form {
      position: fixed;
      top: 20%;
      left: 50%;
      margin-left: -170px;
      width: 340px;
    }
    .tel{
      justify-content: space-between;
    }
  }
</style>

