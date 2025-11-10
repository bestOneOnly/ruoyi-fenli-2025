package com.sww.document.maintenance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sww.document.maintenance.domain.Maintenance;
import com.sww.document.maintenance.domain.req.MaintenanceQueryReq;
import com.sww.document.maintenance.enums.MaintenancePageQuery;

import java.util.List;

public interface IMaintenanceService  extends IService<Maintenance> {



    List<Maintenance> queryList(MaintenanceQueryReq queryReq,MaintenancePageQuery maintenancePageQuery);




}
