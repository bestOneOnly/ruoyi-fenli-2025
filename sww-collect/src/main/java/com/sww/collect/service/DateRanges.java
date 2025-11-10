package com.sww.collect.service;

import java.time.LocalDate;

public final class DateRanges {

    private DateRanges(){}


    public  static DateRange currentMonth(){
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());
        return  (code,service) ->  service.getStandardDataList(code,startDate, endDate);
    }



    public static DateRange prevYearCurrentMonth(){
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1).minusYears(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth()).minusYears(1);
        return  (code,service) ->  service.getStandardDataList(code,startDate, endDate);
    }

}
