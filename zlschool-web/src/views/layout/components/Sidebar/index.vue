<template>
  <el-scrollbar wrap-class="scrollbar-wrapper">
    <el-menu
      :show-timeout="200"
      :default-active="getpath($route.path)"
      :collapse="isCollapse"
      mode="vertical"
      background-color="#FAFAFA"
      text-color="#000"
      active-text-color="white"
    >
      <div class="mulu">
        <svg-icon icon-class="mulu" class="icon"/>
        <div class="text">学习目录</div>
      </div>
      <sidebar-item
        v-for="route in routes"
        :key="route.path"
        :item="route"
        :base-path="route.path"
      />
    </el-menu>
  </el-scrollbar>
</template>

<script>
import { mapGetters } from 'vuex'
import SidebarItem from './SidebarItem'

export default {
  components: { SidebarItem },
  computed: {
    ...mapGetters(['sidebar']),
    routes() {
      return this.$router.options.routes
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  },
  methods: {
    getpath(path) {
      console.log(this.$route.path)
      if (this.$route.path === '/learn/learnCenter') {
        path = '/learn'
      }
      if (this.$route.path === '/exam/examCenter') {
        path = '/exam'
      }
      if (this.$route.path === '/certificate/index') {
        path = '/certificate'
      }
      return path
    },
    openscore() {
      this.$router.push('/userInfo/score')
    }
  }
}
</script>
<style  lang="scss" scoped>
.mulu {
  display: flex;
  flex-direction: row;
  justify-content: center;
  padding: 20px 0;
  .icon {
    font-size: 30px;
  }
  .text {
    font-size: 24px;
  }
}
</style>

