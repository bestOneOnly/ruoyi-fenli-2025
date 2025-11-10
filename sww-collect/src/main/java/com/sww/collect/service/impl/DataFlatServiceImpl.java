package com.sww.collect.service.impl;


import com.sww.collect.domain.StandardData;
import com.sww.collect.service.IDataFlatServerice;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class DataFlatServiceImpl implements IDataFlatServerice {



    @Override
    public List<StandardData> getStandardDataList(String code, LocalDate startDate, LocalDate endDate) {
        System.out.println("code = " + code);
        System.out.println("startDate = " + startDate);
        System.out.println("endDate = " + endDate);



        return null;
    }
//




}
