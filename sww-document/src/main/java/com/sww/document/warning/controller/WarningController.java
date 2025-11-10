package com.sww.document.warning.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

import com.sww.document.warning.enums.WarningActionEnum;
import com.sww.document.warning.service.IWarningService;
import com.sww.document.warning.service.WarningFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarningController extends BaseController {

    @Autowired
    private WarningFactory warningFactory;


    @PostMapping("sendPeople")
    public AjaxResult sendPeople(){
//        IWarningService warningHandler = warningFactory.getWarningHandler(WarningActionEnum.DELEGATE_PERSON);
//        WarningFactory.getHandler(WarningActionEnum.APPROVE).handle();
//        warningHandler.handle(提示Object t);
        return null;
    }

}
