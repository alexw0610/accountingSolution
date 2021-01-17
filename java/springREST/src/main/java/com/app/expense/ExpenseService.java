package com.app.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public String addExpense(Expense s) {

        try {
            expenseRepository.save(s);
            return "{\"status\":\"success\"}";
        } catch(Exception ex) {
            return "{\"status\":\"failed\"}";
        }
    }

    public Optional<Expense> getById(int id){
        return expenseRepository.findById(id);
    }

    public Iterable<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public String deleteExpense(int id) {
        try{
            expenseRepository.deleteById(id);
            return "{\"status\":\"success\"}";
        }catch(Exception ex) {
            return "{\"status\":\"failed\"}";
        }
    }

}
