package com.app.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/listExpenses")
    public Iterable<Expense> listExpenses() {
        return this.expenseService.getAllExpenses();
    }

    @PostMapping("/addExpense")
    public String addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    @PostMapping("/deleteExpense")
    public String deleteExpense(@RequestBody Expense expense) {
        return expenseService.deleteExpense(expense.getId());
    }
}