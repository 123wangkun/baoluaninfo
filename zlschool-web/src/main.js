import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // global css
import '@/styles/common.scss'

import App from './App'
import router from './router'
import store from './store'

import '@/icons' // icon
import '@/permission' // permission control
import moment from 'moment'
// 富文本编辑
import '../static/UE/ueditor.config.js'
import '../static/UE/ueditor.all.min.js'
import '../static/UE/lang/zh-cn/zh-cn.js'
import '../static/UE/ueditor.parse.min.js'

Vue.use(ElementUI, { locale })

Vue.config.productionTip = false
// 全局过滤起 图片地址添加前缀
Vue.filter('imgUrlFilter', function(value) {
  return process.env.BASE_API + value
})
Vue.filter('timeFilter', function(value) {
  return moment(value).format('YYYY-MM-DD  HH:mm')
})

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
