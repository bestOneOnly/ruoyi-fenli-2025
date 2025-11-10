package com.sww.document.maintenance.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.sww.document.maintenance.domain.Maintenance;
import com.sww.document.maintenance.domain.req.MaintenanceApproveReq;
import com.sww.document.maintenance.domain.req.MaintenanceQueryReq;
import com.sww.document.maintenance.enums.MaintenanceAction;
import com.sww.document.maintenance.enums.MaintenancePageQuery;
import com.sww.document.maintenance.service.IMaintenanceService;
import com.sww.document.maintenance.service.handler.MaintenanceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 保养工单审批控制器
 */
@RestController
@RequestMapping("/document/maintenance/approve")
@RequiredArgsConstructor
public class MaintenanceApproveController extends BaseController {

    private final MaintenanceFactory maintenanceFactory;
    private final IMaintenanceService maintenanceService;

    /**
     * 审批
     * @param req 审批请求体
     * @return 审批结果
     */
    @PostMapping("/handle")
    public AjaxResult approve(@RequestBody @Validated MaintenanceApproveReq req){
        return toAjax(maintenanceFactory.getHandler(MaintenanceAction.APPROVE).handle(req));
    }

    @GetMapping("/list")
    public TableDataInfo list(MaintenanceQueryReq req){
        // 分页查询
        startPage();
        List<Maintenance> list = maintenanceService.queryList(req, MaintenancePageQuery.PAGE_APPROVE);
        return getDataTable(list);
    }

}
