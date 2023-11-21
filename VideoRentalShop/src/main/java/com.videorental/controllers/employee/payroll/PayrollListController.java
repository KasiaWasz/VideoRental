package com.videorental.controllers.employee.payroll;

import com.videorental.dtos.employee.payroll.PayrollDto;
import com.videorental.services.employee.payroll.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RequestMapping("/payroll-list")
@Controller
class PayrollListController {

    private static final String M_PAYROLL_LIST = "payrolls";
    private static final String V_PAYROLL_LIST = "payroll-list-view";
    private final PayrollService payrollService;


    @Autowired
    private PayrollListController(PayrollService payrollService) {

        Assert.notNull(payrollService, "payrollService must not be null");

        this.payrollService = payrollService;
    }

    @ModelAttribute(M_PAYROLL_LIST)
    private List<PayrollDto> payrolls(@RequestParam(value = "startDate", required = false) String startDate,
        @RequestParam(value = "endDate", required = false) String endDate) {

        if (startDate != null && endDate != null) {

            LocalDate lStartDate = LocalDate.parse(startDate);
            LocalDate lEndDate = LocalDate.parse(endDate);

            return payrollService.getAllPayrolls(lStartDate, lEndDate);
        }

        return Collections.emptyList();
    }

    @GetMapping
    private String showPayrollList() {

        return V_PAYROLL_LIST;
    }
}
