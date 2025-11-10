package com.sww.document.maintenance.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.sww.document.maintenance.enums.MaintenanceStatus;
import com.sww.document.maintenance.service.IMaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document/maintenance/common")
@RequiredArgsConstructor
public class MaintenanceCommonController extends BaseController {

    private final IMaintenanceService maintenanceService;

    @GetMapping("/{id}")
    public AjaxResult getById(@PathVariable("id") Long id) {
        return AjaxResult.success(maintenanceService.getById(id));
    }


    //获取状态枚举列表
    @GetMapping("/enums/status")
    public AjaxResult getStatusOptions() {
        return AjaxResult.success(MaintenanceStatus.values());
    }






}
