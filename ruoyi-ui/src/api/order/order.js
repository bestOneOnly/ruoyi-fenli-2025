import request from '@/utils/request'

// 查询订单列表
export function listOrder(query) {
    
    return request({
        url: '/order/getOrder',
        method: 'get',
        params: query
    })
}