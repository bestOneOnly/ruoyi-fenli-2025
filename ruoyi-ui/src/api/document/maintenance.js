import request from '@/utils/request'


// 查询保养工单详细
export function getMaintenance(id) {
  return request({
    url: '/document/maintenance/common/' + id,
    method: 'get'
  })
}

// 查询保养工单全部状态
export function getMaintenanceStatus() {
  return request({
    url: '/document/maintenance/common/enums/status',
    method: 'get'
  })
}

// 查询派单保养工单列表
export function listMaintenanceCreateAndDispatch(query) {
  return request({
    url: '/document/maintenance/dispatch/list',
    method: 'get',
    params: query
  })
}

// 新增保养工单
export function addMaintenance(data) {
  return request({
    url: '/document/maintenance/dispatch',
    method: 'post',
    data: data
  })
}

// 修改保养工单
export function updateMaintenance(data) {
  return request({
    url: '/document/maintenance/dispatch',
    method: 'put',
    data: data
  })
}

// 删除保养工单
export function delMaintenance(id) {
  return request({
    url: '/document/maintenance/dispatch/delete/' + id,
    method: 'delete'
  })
}

/**
 *  派发工单
 * @param {*} params 
 * @returns 
 */
export function  handleDispatch(params) {
  return request({
    url: '/document/maintenance/dispatch/handleDispatch',
    method: 'post',
    data: params
  })
}

/**
 * 处置工单
 * @param {*} params 
 * @returns 
 */
export function  handleDispose(params) {
  return request({
    url: '/document/maintenance/dispose/handle',
    method: 'post',
    data: params
  })
}

// 处置工单查询
export function listMaintenanceDispose(query) {
  return request({
    url: '/document/maintenance/dispose/list',
    method: 'get',
    params: query
  })
}

// 审批工单
export function  handleApprove(params) {
  return request({
    url: '/document/maintenance/approve/handle',
    method: 'post',
    data: params
  })
}

// 审批工单查询
export function listMaintenanceApprove(query) {
  return request({
    url: '/document/maintenance/approve/list',
    method: 'get',
    params: query
  })
}









