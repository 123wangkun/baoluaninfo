<template>
  <div class="navbar">
    <div class="logo-box">
      <img class="logo" src="../../../assets/img/logo_b.png" @click="toggleSideBar">
      <img src="../../../assets/img/1_b.png" style="height:30px">
    </div>
    <div class="home">
      <div class="home-icon" @click="home"/>
    </div>
    <div class="personal">
      <div class="info-icon">
        <div class="point">1</div>
      </div>
    </div>
    <div class="personal" >
      <div class="logout-icon"/>
      <span class="logout-text">系统设置</span>
    </div>
    <div class="logout" @click="logout">
      <div class="logout-icon"/>
      <span class="logout-text">退出</span>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters(['sidebar', 'avatar'])
  },
  methods: {
    toggleSideBar() {
      // this.$store.dispatch('ToggleSideBar')
    },
    home() {
      this.$router.push({
        path: '/dashboard'
      })
    },
    logout() {
      this.$store.dispatch('LogOut').then((res) => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
        this.$router.push({ path: '/login' })
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  border-radius: 0px !important;
  display: flex;
  flex-direction: row;
  align-items: center;
  border-bottom: 1px solid white;
  background: white;
  padding: 10px 20px 10px 10px;
  .logo-box {
    flex: 1;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding-left: 10px;
    .logo {
      height: 40px;
      margin-right: 10px;
    }
    .logoName {
      font-size: 30px;
      color: rgb(25, 124, 204);
    }
  }
  .home {
    .home-icon {
      width: 28px;
      height: 28px;
      background: url(../../../assets/img/home.png) no-repeat;
      background-size: 28px 28px;
      position: relative;
      margin-right: 25px;
      cursor: pointer;
    }
  }
  .personal {
    display: flex;
    flex-direction: row;
    align-items: center;
    cursor: pointer;
    .info-icon {
      width: 32px;
      height: 32px;
      background: url(../../../assets/img/info.png) no-repeat;
      background-size: 32px 32px;
      position: relative;
      margin-right: 25px;
      .point {
        position: absolute;
        top: -5px;
        right: -5px;
        background: red;
        color: white;
        font-size: 14px;
        padding: 0 4px;
        border-radius: 9px;
      }
    }
    .logout-icon {
      width: 28px;
      height: 28px;
      background: url(../../../assets/img/setting.png) no-repeat;
      background-size: 28px 28px;
      margin-right: 5px;
    }
    .logout-text {
      font-size: 18px;
      color: #0168b7;
      margin-right: 25px;
    }
  }
  .logout {
    display: flex;
    flex-direction: row;
    align-items: center;
    cursor: pointer;
    .logout-icon {
      width: 28px;
      height: 28px;
      background: url(../../../assets/img/exit.png) no-repeat;
      background-size: 28px 28px;
      margin-right: 5px;
    }
    .logout-text {
      font-size: 18px;
      color: #0168b7;
    }
  }
}
</style>

