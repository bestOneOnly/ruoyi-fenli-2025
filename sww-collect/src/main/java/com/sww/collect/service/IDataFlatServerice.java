package com.sww.collect.service;

import com.sww.collect.domain.StandardData;
import java.time.LocalDate;
import java.util.List;

public interface IDataFlatServerice {


    List<StandardData> getStandardDataList(String code, LocalDate startDate, LocalDate endDate);


    default List<StandardData> getStandardDataList(String code,DateRange dateRange){
        return dateRange.apply(code,this);
    }



}
