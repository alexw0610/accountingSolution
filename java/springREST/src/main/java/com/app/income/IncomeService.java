package com.app.income;

import com.app.expense.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository){
        this.incomeRepository = incomeRepository;
    }

    public String addIncome(Income income) {

        try {
            incomeRepository.save(income);
            return "{\"status\":\"success\"}";
        } catch(Exception e) {
            return "{\"status\":\"failed\"}";
        }
    }

    public Iterable<Income> getAllIncomes(){
        return incomeRepository.findAll();
    }

    public Optional<Income> getById(int id){
        return incomeRepository.findById(id);
    }

    public String deleteIncome(int id) {
        try{
            incomeRepository.deleteById(id);
            return "{\"status\":\"success\"}";
        }catch(Exception e) {
            return "{\"status\":\"failed\"}";
        }
    }

}
