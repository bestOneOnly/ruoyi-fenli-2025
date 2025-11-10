package com.sww.collect.service;


import com.sww.collect.domain.StandardData;

import java.util.List;

@FunctionalInterface
public interface DateRange {
    /**
     * 工具类目的就是生成开始时间，结束时间的模板，所以不需要传这两个参数
     * @param code 接口原有参数，不进行任何处理
     * @param dataFlatService 获取当前接口引用，用于调用实际查询接口
     * @return 返回实际查询接口数据
     */
    List<StandardData> apply(String code,IDataFlatServerice dataFlatService);



}
