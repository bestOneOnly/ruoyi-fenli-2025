package com.sww.document.maintenance.service.handler;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import com.sww.document.maintenance.domain.Maintenance;
import com.sww.document.maintenance.domain.req.MaintenanceDispatchReq;
import com.sww.document.maintenance.enums.MaintenanceAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class MaintenanceDispatchHandler extends AbstractMaintenanceHandler<MaintenanceDispatchReq> {

    @Override
    protected MaintenanceAction support() {
        return MaintenanceAction.DISPATCH;
    }

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    protected boolean handle(MaintenanceDispatchReq body, Integer from, Integer to) {
        SysUser dispatchUser = sysUserMapper.selectUserById(body.getDispatchUserId());
        return this.lambdaUpdate()
                .set(Maintenance::getDispatchUserId, body.getDispatchUserId())
                .set(Maintenance::getDispatchDesc, body.getDispatchDesc())
                .set(Maintenance::getDispatchUserName,dispatchUser.getNickName())
                .set(Maintenance::getDispatchTime, LocalDate.now())
                .set(Maintenance::getStatus, to)
                .eq(Maintenance::getId, body.getId())
                .eq(Maintenance::getStatus, from)
                .update();
    }


}
