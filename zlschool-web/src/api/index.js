import request from '@/utils/request'
import qs from 'qs'
export function messageList(payload) {
  const { page, limit, type, key } = payload
  return request({
    url: '/pcmessagenew/messageList',
    method: 'post',
    data: qs.stringify({
      currentPage: page,
      showCount: limit,
      KEY: key,
      TYPE: type
    })
  })
}
