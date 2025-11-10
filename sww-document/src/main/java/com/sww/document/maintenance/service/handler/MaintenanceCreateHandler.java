package com.sww.document.maintenance.service.handler;

import com.sww.document.maintenance.domain.Maintenance;
import com.sww.document.maintenance.domain.req.MaintenanceCreateReq;
import com.sww.document.maintenance.enums.MaintenanceAction;
import com.sww.document.maintenance.mapper.MaintenanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceCreateHandler extends AbstractMaintenanceHandler<MaintenanceCreateReq> {

    @Autowired
    private MaintenanceMapper maintenanceMapper;

    @Override
    protected MaintenanceAction support() {
        return MaintenanceAction.CREATE;
    }

    @Override
    protected  boolean handle(MaintenanceCreateReq body, Integer from, Integer to){
        Long no = maintenanceMapper.countAllIncludingDeleted();
        Maintenance maintenance = new Maintenance()
                .setName(body.getName())
                .setContent(body.getContent())
                .setNo(String.format("%08d", no).transform("BY"::concat))
                .setStatus(to);
        return this.save(maintenance);
    }
}



