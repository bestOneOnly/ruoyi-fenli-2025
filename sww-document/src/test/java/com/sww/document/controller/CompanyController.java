package com.sww.document.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document/company")
public class CompanyController extends BaseController {

    @RequestMapping("/list")
    public AjaxResult getCompany(){
        return AjaxResult.success("sww-document");
    }



}
