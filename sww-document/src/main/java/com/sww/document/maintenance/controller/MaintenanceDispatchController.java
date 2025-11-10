package com.sww.document.maintenance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.sww.document.maintenance.domain.Maintenance;
import com.sww.document.maintenance.domain.req.MaintenanceCreateReq;
import com.sww.document.maintenance.domain.req.MaintenanceDispatchReq;
import com.sww.document.maintenance.domain.req.MaintenanceQueryReq;
import com.sww.document.maintenance.enums.MaintenanceAction;
import com.sww.document.maintenance.enums.MaintenancePageQuery;
import com.sww.document.maintenance.enums.MaintenanceStatus;
import com.sww.document.maintenance.service.IMaintenanceService;
import com.sww.document.maintenance.service.handler.MaintenanceFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 保养工单派单控制器
 */
@RestController
@RequestMapping("/document/maintenance/dispatch")
@RequiredArgsConstructor
public class MaintenanceDispatchController extends BaseController {


    private final MaintenanceFactory factory;

    private final IMaintenanceService maintenanceService;

    @PostMapping
    public AjaxResult create(@RequestBody  MaintenanceCreateReq req) {
        return toAjax(factory.getHandler(MaintenanceAction.CREATE).handle(req));
    }

    @PutMapping
    public AjaxResult update(@RequestBody @Validated MaintenanceCreateReq req) {
        boolean update = maintenanceService.lambdaUpdate()
                .set(StringUtils.isNotBlank(req.getName()), Maintenance::getName, req.getName())
                .set(StringUtils.isNotBlank(req.getContent()), Maintenance::getContent, req.getContent())
                .eq(Maintenance::getId, req.getId())
                .eq(Maintenance::getStatus, MaintenanceStatus.CREATED.getCode())
                .update();
        return toAjax(update);
    }

    @GetMapping("/list")
    public TableDataInfo list(MaintenanceQueryReq req) {
        startPage();
        List<Maintenance> list = maintenanceService.queryList(req, MaintenancePageQuery.PAGE_CREATE_AND_DISPATCH);
        return getDataTable(list);
    }

    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        LambdaQueryWrapper<Maintenance> queryWrapper = new LambdaQueryWrapper<Maintenance>()
                .eq(Maintenance::getId, id)
                .eq(Maintenance::getStatus, MaintenanceStatus.CREATED.getCode());
        return toAjax(maintenanceService.remove(queryWrapper));
    }

    /**
     * 派单
     * @param req 派单请求体
     * @return 派单结果
     */
    @PostMapping("/handleDispatch")
    public AjaxResult handleDispatch(@RequestBody @Validated MaintenanceDispatchReq req){
        return toAjax(factory.getHandler(MaintenanceAction.DISPATCH).handle(req));
    }


}
