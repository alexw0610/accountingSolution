package com.app.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IncomeController {

    @Autowired
    IncomeService incomeService;

    @GetMapping("/listIncome")
    public Iterable<Income> listIncome() {
        return this.incomeService.getAllIncomes();
    }

    @PostMapping("/addIncome")
    public String addIncome(@RequestBody Income income) {
        return incomeService.addIncome(income);
    }

    @PostMapping("/deleteIncome")
    public String deleteIncome(@RequestBody Income income) {
        return incomeService.deleteIncome(income.getId());
    }

}
