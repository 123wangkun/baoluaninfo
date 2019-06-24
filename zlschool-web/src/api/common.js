import request from '@/utils/request'

// 上传文件
// 000001学历，000002学位，000003职称，000004从业资格证书，000005专业工作年限，5营业执照，6通知公告封面，7培训活动封面，8导入excel,9PC图片,10手机图片，11课程文件
export function uploadFile(params, type) {
  return request({
    url: `/system/upload_files/${type}`,
    method: 'post',
    data: params
  })
}
// 删除上传文件
export function delFile(params) {
  return request({
    url: `/system/deleteResources`,
    method: 'post',
    data: params
  })
}
// 发送短信验证码(type:注册1,忘记/修改密码2)
export function sendSmsCode(phone, type, params) {
  return request({
    url: `/system/sendSmsCode/${phone}/${type}`,
    method: 'get',
    params
  })
}
// 校验短信验证码
export function checkSmsCode(params) {
  return request({
    url: `/system/checkSmsCode`,
    method: 'post',
    data: params
  })
}
// 查询用户登陆session
export function getSession() {
  return request({
    url: `/system/getSession`,
    method: 'post'
  })
}
