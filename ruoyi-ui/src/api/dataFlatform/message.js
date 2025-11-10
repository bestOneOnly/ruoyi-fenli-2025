import request from '@/utils/request.js'

export function getMessageList(params) {
  return request({
    url: '/company/getCompany',
    method: 'get',
    params
  })
}