import request from '@/utils/request'
import qs from 'qs'
export function offlinelearnList(payload) {
  const { page, limit } = payload
  return request({
    url: '/pcExamOfflin/offlinelearnList',
    method: 'post',
    data: qs.stringify({
      currentPage: page,
      showCount: limit
    })
  })
}
export function getCoursetype() {
  return request({
    url: '/pcnewcourse/getcoursetype',
    method: 'post',
    data: ''
  })
}

export function getcourselistPage(payload) {
  const { page, limit, courselistid } = payload
  return request({
    url: '/pcnewcourse/getcourselistPage',
    method: 'post',
    data: qs.stringify({
      currentpage: page,
      showamount: limit,
      listtype: 3,
      courselistid: courselistid
    })
  })
}
export function getcourselistfortype1(payload) {
  const { courselistid } = payload
  return request({
    url: '/pcnewcourse/getcourselistfortype1',
    method: 'post',
    data: qs.stringify({
      courselistid: courselistid
    })
  })
}
export function getcourselistfortype2(payload) {
  const { courselistid } = payload
  return request({
    url: '/pcnewcourse/getcourselistfortype2',
    method: 'post',
    data: qs.stringify({
      courselistid: courselistid
    })
  })
}

export function getCourseDetail(payload) {
  const { coursedetailid } = payload
  return request({
    url: '/pcnewcourse/getCourseDetail',
    method: 'post',
    data: qs.stringify({
      coursedetailid: coursedetailid
    })
  })
}
export function updateRead(payload) {
  const { coursedetailid } = payload
  return request({
    url: '/pcnewcourse/updateRead',
    method: 'post',
    data: qs.stringify({
      coursedetailid: coursedetailid
    })
  })
}
export function dolike(payload) {
  const { LIKEFOR_ID } = payload
  return request({
    url: '/pcnewcourse/dolike',
    method: 'post',
    data: qs.stringify({
      LIKEFOR_ID: LIKEFOR_ID,
      LIKEFOR_TYPE: 'course'
    })
  })
}
export function cancellike(payload) {
  const { LIKEFOR_ID } = payload
  return request({
    url: '/pcnewcourse/cancellike',
    method: 'post',
    data: qs.stringify({
      LIKEFOR_ID: LIKEFOR_ID
    })
  })
}
export function docollect(payload) {
  const { COLLECTFOR_ID } = payload
  return request({
    url: '/pcnewcourse/docollect',
    method: 'post',
    data: qs.stringify({
      COLLECTFOR_ID: COLLECTFOR_ID,
      COLLECTFOR_TYPE: 'course'
    })
  })
}
export function cancelcollect(payload) {
  const { COLLECTFOR_ID } = payload
  return request({
    url: '/pcnewcourse/cancelcollect',
    method: 'post',
    data: qs.stringify({
      COLLECTFOR_ID: COLLECTFOR_ID
    })
  })
}
export function submitAnswer(payload) {
  const { coursedetailid, answer } = payload
  return request({
    url: '/pcnewcourse/submitAnswer',
    method: 'post',
    data: qs.stringify({
      coursedetailid: coursedetailid,
      answer: answer
    })
  })
}
export function addComment(payload) {
  const { coursedetail_id, coursedetail_name, content } = payload
  return request({
    url: '/pcnewcomment/addComment',
    method: 'post',
    data: qs.stringify({
      coursedetail_id: coursedetail_id,
      coursedetail_name: coursedetail_name,
      content: content
    })
  })
}
export function commentList(payload) {
  const { coursedetail_id, showCount, currentPage } = payload
  return request({
    url: '/pcnewcomment/commentList',
    method: 'post',
    data: qs.stringify({
      coursedetail_id: coursedetail_id,
      showCount: showCount,
      currentPage: currentPage
    })
  })
}
