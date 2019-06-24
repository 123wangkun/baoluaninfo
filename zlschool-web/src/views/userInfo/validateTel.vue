<template>
  <!-- 验证手机号码 -->
  <div class="validate-tel">
    <el-form
      ref="validateTelForm"
      :model="validateTelForm"
      :rules="findPwdRules"
      class="register-form"
      label-position="left"
    >
      <p class="register-title">验证手机号码</p>
      <div class="flex tel">
        <el-form-item prop="telePhone">
          <span class="svg-container">
            <svg-icon icon-class="user"/>
          </span>
          <el-input
            v-model="validateTelForm.telePhone"
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
          v-model="validateTelForm.smscode"
          type="text"
          name="smscode"
          auto-complete="off"
          placeholder="请输入验证码"
        />
      </el-form-item>
      <div class="flex bottom">
        <el-button
          :loading="loading"
          type="primary"
          class="login-bt"
          @click.native.prevent="next"
        >下一步</el-button>
        <el-button
          :loading="loading"
          type="primary"
          class="login-bt"
          @click.native.prevent="cancel"
        >取消</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import { validatTel } from '@/utils/validate'
import { sendSmsCode, checkSmsCode } from '@/api/common'
export default {
  name: 'ValidateTel',
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
    return {
      validateTelForm: {
        telePhone: '',
        smscode: ''
      },
      findPwdRules: {
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
      const phone = this.validateTelForm.telePhone
      if (!phone || !validatTel(phone)) {
        this.$refs.validateTelForm.validateField('telePhone')
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
    // 点击下一步
    next() {
      this.$refs.validateTelForm.validate(valid => {
        if (valid) {
          const smsCodeParams = {
            phone: this.validateTelForm.telePhone,
            smsCode: this.validateTelForm.smscode,
            type: 2
          }
          checkSmsCode(smsCodeParams).then(res => {
            this.$message.success('验证成功')
            setTimeout(() => {
              this.$router.push({ name: 'register', params: { telePhone: this.validateTelForm.telePhone }})
            }, 2000)
          }).catch(error => {
            this.$message.error(error.err_msg)
          })
        } else {
          return false
        }
      })
    },
    // 点击取消
    cancel() {
      this.$router.push('/login')
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
  $bg: #2d3a4b;
  $dark_gray: #889aa4;
  .validate-tel {
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
    .bottom{
      flex-wrap: nowrap;
    }
  }
</style>

