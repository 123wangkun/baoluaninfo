<template>
  <div class="login-container">
    <div class="left">
      <img src="../../assets/img/4.png" class="img1">
      <img src="../../assets/img/5.png" class="img2">
      <img src="../../assets/img/6.png" class="img2">
    </div>
    <div class="right">
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        class="login-form"
        label-position="left"
      >
        <img src="../../assets/img/2.png" class="logo-img">
        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user"/>
          </span>
          <el-input
            v-model="loginForm.username"
            name="username"
            type="text"
            auto-complete="off"
            placeholder="请输入手机号"
          />
        </el-form-item>
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password"/>
          </span>
          <el-input
            :type="pwdType"
            v-model="loginForm.password"
            name="password"
            auto-complete="off"
            placeholder="请输入密码"
            @keyup.enter.native="handleLogin"
          />

        </el-form-item>
        <el-button
          :loading="loading"
          type="primary"
          class="login-bt"
          style="width:100%;height:40px"
          @click.native.prevent="handleLogin"
        >登录</el-button>
        <div class="login-bottom">
          <router-link to="/validateTel">用户注册</router-link>
          <router-link to="/forgetpwd">忘记密码</router-link>
        </div>
      </el-form>
    </div>
    <div class="footer">技术支持:江苏中路信息科技有限公司</div>
  </div>
</template>

<script>
import { isvalidUsername } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error('请输入手机号'))
      } else {
        callback()
      }
    }
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [
          { required: true, trigger: 'blur', validator: validateUsername }
        ],
        password: [{ required: true, trigger: 'blur', validator: validatePass }]
      },
      loading: false,
      pwdType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store
            .dispatch('Login', this.loginForm)
            .then(() => {
              this.loading = false
              this.$router.push({ path: '/' })
            })
            .catch(() => {
              this.loading = false
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
$bg: white;
$light_gray: #eee;

/* reset element-ui css */
.login-container {
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
  }
  .el-form-item {
    border: 1px solid #dddddd;
    border-radius: 5px;
    color: #646464;
  }
}
</style>

<style  lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;
.login-container {
  display: flex;
  position: fixed;
  width: 100%;
  height: 100%;
  background: url(../../assets/img/bg.jpg) no-repeat center center;
  background-size: cover;
  .left {
    flex: 7;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    .img1 {
      width: 31.25vw;
    }
    .img2 {
      margin-top: 20px;
      width: 18.75vw;
    }
  }
  .right {
    flex: 3;
    display: flex;
    background: rgba(0, 0, 0, 0.3);
    flex-direction: column;
    align-items: center;
    justify-content: center;
    .login-form {
      width: 100%;
      padding: 30px 50px 30px 50px;
      border-radius: 10px;
      .logo-img {
        width: 130px;
        margin-bottom: 20px;
      }
      .logo-box {
        width: 100%;
        display: flex;
        justify-content: center;
        margin: 15px 0;
      }
      .login-bt {
        background: #1164c8;
        outline: 0;
        border: 0;
      }
    }
  }
  .footer {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
    text-align: center;
    padding: 10px;
    color: #ffffff;
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
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
  .title {
    font-size: 26px;
    font-weight: 400;
    color: $light_gray;
    margin: 0px auto 40px auto;
    text-align: center;
    font-weight: bold;
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 2px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
<style lang="scss">
.login-container {
  .el-button {
    font-size: 16px;
  }
  .login-bottom {
    display:-webkit-box;
    display: -webkit-flex;
    display: flex;
    padding-top: 20px;
    width: 100%;
    cursor: pointer;
    justify-content: space-between;
    color: #fff;
  }
}
</style>

