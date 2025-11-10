package com.sww.collect.service;

import com.sww.collect.domain.StandardData;
import com.sww.collect.service.impl.DataFlatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Test {

    @Autowired
    private IDataFlatServerice dataFlatServerice;

     public void test(){
         List<StandardData> standardDataList = dataFlatServerice.getStandardDataList("0001",DateRanges.currentMonth());
     }


}
