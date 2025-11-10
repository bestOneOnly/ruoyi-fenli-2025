package com.sww.document.maintenance.service.handler;

import com.ruoyi.common.utils.SecurityUtils;
import com.sww.document.maintenance.domain.Maintenance;
import com.sww.document.maintenance.domain.req.MaintenanceDisposeReq;
import com.sww.document.maintenance.enums.MaintenanceAction;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class MaintenanceDisposeHandler extends AbstractMaintenanceHandler<MaintenanceDisposeReq> {
    @Override
    protected MaintenanceAction support() {
        return MaintenanceAction.DISPOSE;
    }

    @Override
    protected boolean handle(MaintenanceDisposeReq body, Integer from, Integer to) {
        return this.lambdaUpdate()
                .set(Maintenance::getDisposeUserId, SecurityUtils.getLoginUser().getUserId())
                .set(Maintenance::getDisposeUserName, SecurityUtils.getLoginUser().getUser().getNickName())
                .set(Maintenance::getDisposeDesc, body.getDisposeDesc())
                .set(Maintenance::getDisposeTime, LocalDateTime.now())
                .set(Maintenance::getStatus,to)
                .eq(Maintenance::getId, body.getId())
                .eq(Maintenance::getStatus,from)
                .update();
    }
}
