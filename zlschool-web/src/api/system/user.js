import request from '@/utils/request'

// 查询用户列表
export function getUserList(params) {
  return request({
    url: `/system/getUserList`,
    method: 'post',
    data: params
  })
}
