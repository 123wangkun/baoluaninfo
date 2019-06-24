import router from './router'
// import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css' // Progress 进度条样式
import { getSession } from '@/api/common'

const whiteList = ['/login', '/register', '/forgetpwd', '/validateTel'] // no redirect whitelist

router.beforeEach((to, from, next) => {
  getSession().then(res => {
    const hasUser = res.user
    if (hasUser) {
      if (to.path === '/login') {
        // if is logged in, redirect to the home page
        next({ path: '/' })
        NProgress.done()
      } else {
        next()
      }
    } else {
      /* has no token*/
      if (whiteList.indexOf(to.path) !== -1) {
        // in the free login whitelist, go directly
        next()
      } else {
        // other pages that do not have permission to access are redirected to the login page.
        next('/login')
      }
    }
  }).catch(error => {
    this.$message.error(error.err_msg)
  })
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
