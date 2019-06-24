import request from '@/utils/request'

// 查询角色列表
export function getRoleList(params) {
  return request({
    url: `/system/getRoleList`,
    method: 'post',
    data: params
  })
}
