import request from '@/utils/request'
import qs from 'qs'
export function examOfflineList(payload) {
  const { page, limit } = payload
  return request({
    url: '/pcExamOfflin/examOfflineList',
    method: 'post',
    data: qs.stringify({
      currentPage: page,
      showCount: limit
      //   key: key,
      //   state: state,
      //   type: type
    })
  })
}

export function listPaper(payload) {
  const { page, limit, key } = payload
  return request({
    url: '/pcpapernew/listPaper',
    method: 'post',
    data: qs.stringify({
      currentPage: page,
      showCount: limit,
      key: key
      //   key: key,
      //   state: state,
      //   type: type
    })
  })
}
export function paperDetails(payload) {
  const { id } = payload
  return request({
    url: '/pcpapernew/paperDetails',
    method: 'post',
    data: qs.stringify({
      id: id
    })
  })
}
export function submitExam(payload) {
  const { paper_id, answers } = payload
  return request({
    url: '/pcpapernew/submitExam',
    method: 'post',
    data: qs.stringify({
      paper_id: paper_id,
      answers: answers
    })
  })
}
