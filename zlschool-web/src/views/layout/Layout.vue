<template>
  <div :class="classObj" class="app-wrapper">
    <!-- <div v-if="device==="mobile'&&sidebar.opened' class="drawer-bg" @click="handleClickOutside"/> -->
    <navbar class="navbar"/>
    <div class="userInfo">
      <div class="left">
        <router-link to="/userInfo"><img src="../../assets/image/user-icon.png" class="usericon"></router-link>
        <div>
          <div class="t1">hi,陈进</div>
          <div class="t2">恭喜，您当前的学习成就</div>
        </div>
      </div>
      <div class="right">
        <div class="item">
          <div class="t1">300</div>
          <div class="t2">积分</div>
        </div>
        <div class="item">
          <div class="t1">5</div>
          <div class="t2">证书</div>
        </div>
        <div class="item">
          <div class="t1">25</div>
          <div class="t2">考试次数</div>
        </div>
        <div class="item">
          <div class="t1">70%</div>
          <div class="t2">通过率</div>
        </div>
      </div>
    </div>
    <div class="main-container">
      <sidebar class="sidebar-container" style="padding-top:130px"/>
      <tags-view v-show="false" class="tags"/>
      <app-main class="main"/>
      <div class="footer">Copyright 2018 技术支持:江苏中路信息科技有限公司,客服热线: 025-52755150</div>
    </div>
  </div>
</template>

<script>
import { Navbar, Sidebar, AppMain, TagsView } from './components'
import ResizeMixin from './mixin/ResizeHandler'

export default {
  name: 'Layout',
  components: {
    Navbar,
    Sidebar,
    AppMain,
    TagsView
  },
  mixins: [ResizeMixin],
  computed: {
    sidebar() {
      return this.$store.state.app.sidebar
    },
    device() {
      return this.$store.state.app.device
    },
    classObj() {
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation,
        mobile: this.device === 'mobile'
      }
    }
  },
  methods: {
    handleClickOutside() {
      this.$store.dispatch('CloseSideBar', { withoutAnimation: false })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import 'src/styles/mixin.scss';
.app-wrapper {
  @include clearfix;
  position: relative;
  height: 100%;
  width: 100%;
  &.mobile.openSidebar {
    position: fixed;
    top: 0;
  }
  .navbar {
    position: fixed;
    top: 0;
    left: 0px;
    right: 0;
    z-index: 100;
  }
  .userInfo {
    background: url(../../assets/img/topbg.png) no-repeat;
    background-size: 100% 81px;
    width: 100%;
    height: 81px;
    position: fixed;
    top: 49px;
    z-index: 99;
    display: flex;
    flex-direction: row;
    color: white;
    border-bottom: 1px solid white;
    z-index: 1002;
    .left {
      width: 220px;
      display: flex;
      flex-direction: row;
      align-items: center;
      padding: 0 5px;
      .usericon {
        width: 50px;
        margin-right: 5px;
      }
      .t1 {
        font-size: 20px;
        font-weight: bold;
      }
      .t2 {
        font-size: 14px;
        margin-top: 10px;
      }
    }
    .right {
      flex: 1;
      display: flex;
      flex-direction: row;
      align-items: center;
      padding: 0 20px;
      .item {
        width: 100px;
        text-align: center;
        .t1 {
          font-size: 20px;
          font-weight: bold;
        }
        .t2 {
          font-size: 14px;
          margin-top: 10px;
        }
      }
    }
  }
  .main-container {
    background: #ffffff;
    position: relative;

    .tags {
      position: fixed;
      top: 70px;
      left: 220px;
      right: 0;
      z-index: 100;
    }
    .main {
      padding-top: 130px;
      width: 100%;
    }
    .footer {
      text-align: center;
      padding: 10px;
      color: rgb(114, 114, 114);
    }
  }
}
.drawer-bg {
  background: #000;
  opacity: 0.3;
  width: 100%;
  top: 0;
  height: 100%;
  position: absolute;
  z-index: 999;
}
</style>
