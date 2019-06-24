import axios from 'axios'
import { Message } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 20000 // 请求超时时间
})
// request拦截器
service.interceptors.request.use(
  config => {
    console.log('request.url' + JSON.stringify(config.url))
    console.log('request.data' + JSON.stringify(config.data))
    if (config.headers['Content-Type'] !== 'multipart/form-data') {
      if (store.getters.token) {
        if (config.data === '') {
          config.data = 'token=' + getToken()
        } else {
          config.data += '&token=' + getToken()
        }
      }
    }

    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    console.log('response.data' + JSON.stringify(response.data))
    /**
     * response.status为非200是抛错 可结合自己业务进行修改
     */
    // 后台处理成功
    if (response.status === 502) {
      alert('服务请求失败')
    } else if (response.status === 200) {
      return response.data
    } else {
      alert(response.data)
    }
  },
  error => {
    console.log('error.response.data' + error.response.data)
    if (error.response.data.err_code) {
      Message({
        message: error.response.data.err_msg,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(error.response.data)
    } else {
      // Message({
      //   message: '后台错误',
      //   type: 'error',
      //   duration: 5 * 1000
      // })
    }
  }
)

export default service
