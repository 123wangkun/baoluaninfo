import request from '@/utils/request'
export function getInfo() {
  return request({
    url: '/pcsjlogin/getToken',
    method: 'post',
    data: ''
  })
}
// 退出
export function logout() {
  return request({
    url: '/system/loginOut',
    method: 'post'
  })
}
// 用户注册----证书类型：0学历，1学位，2职称，3从业资格证书，4专业工作年限
export function register(params) {
  return request({
    url: '/system/register',
    method: 'post',
    data: params
  })
}
// 登录
export function login(params) {
  return request({
    url: '/system/login',
    method: 'post',
    data: params
  })
}
// 忘记密码
export function forgetPassword(params) {
  return request({
    url: '/system/forgetPassword',
    method: 'post',
    data: params
  })
}
// 修改密码
export function editPassword(params) {
  return request({
    url: '/system/editPassword',
    method: 'post',
    data: params
  })
}
