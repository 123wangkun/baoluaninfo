import request from '@/utils/request'
import qs from 'qs'
import { getToken } from '@/utils/auth'

export function typeList() {
  return request({
    url: '/pccertificate/certificateTypeList',
    method: 'post',
    data: ''
  })
}
export function addCertificate(payload) {
  const { name, type, file, remark } = payload
  const formData = new FormData()
  formData.append('token', getToken())
  formData.append('name', name)
  formData.append('type', type)
  formData.append('remark', remark)
  formData.append('file', file)
  const config = {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  }

  return request.post('/pccertificate/addCertificate', formData, config)
}
export function certificateList(payload) {
  const { page, limit, key, state, type } = payload
  return request({
    url: '/pccertificate/certificateList',
    method: 'post',
    data: qs.stringify({
      currentPage: page,
      showCount: limit,
      key: key,
      state: state,
      type: type
    })
  })
}
export function eidtCertificate(payload) {
  const { id, name, type, file, remark } = payload
  const formData = new FormData()
  formData.append('token', getToken())
  formData.append('id', id)
  if (name && name !== '') {
    formData.append('name', name)
  }
  if (type && type !== '') {
    formData.append('type', type)
  }
  if (remark && remark !== '') {
    formData.append('remark', remark)
  }
  if (file && file.size) {
    formData.append('file', file)
  }
  const config = {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  }

  return request.post('/pccertificate/eidtCertificate', formData, config)
}
export function delCertificate(payload) {
  const { id } = payload
  return request({
    url: '/pccertificate/delCertificate',
    method: 'post',
    data: qs.stringify({
      id: id
    })
  })
}
export function certificateDetail(payload) {
  const { id } = payload
  return request({
    url: '/pccertificate/certificateDetail',
    method: 'post',
    data: qs.stringify({
      id: id
    })
  })
}
