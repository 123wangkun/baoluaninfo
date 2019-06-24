import request from '@/utils/request'

// 查询企业组织树
export function getEnterpriseTree() {
  return request({
    url: `/system/getEnterpriseTree`,
    method: 'post'
  })
}

// 新增企业部门
export function addEnterprise(params) {
  return request({
    url: `/system/addEnterprise`,
    method: 'post',
    data: params
  })
}

// 查询企业部门
export function getEnterprise(params) {
  return request({
    url: `/system/getEnterprise`,
    method: 'post',
    data: params
  })
}

// 修改部门
export function editDepartment(params) {
  return request({
    url: `/system/editDepartment`,
    method: 'post',
    data: params
  })
}

// 修改企业
export function editEnterprise(params) {
  return request({
    url: `/system/editEnterprise`,
    method: 'post',
    data: params
  })
}

// 删除企业企业
export function deleteEnterprise(params) {
  return request({
    url: `/system/deleteEnterprise`,
    method: 'post',
    data: params
  })
}
