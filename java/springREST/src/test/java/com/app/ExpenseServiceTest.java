package com.app;


import com.app.expense.Expense;
import com.app.expense.ExpenseRepository;
import com.app.expense.ExpenseService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ExpenseServiceTest {

    @Autowired
    private ExpenseRepository expenseRepository;


    @Test
    @Rollback
    public void addExpenseTest(){
        ExpenseService expenseService = new ExpenseService(expenseRepository);

        Expense testExpenseOne = new Expense();
        testExpenseOne.setValue(4.99f);
        testExpenseOne.setDate("2020-01-01");
        testExpenseOne.setReoccurring(false);

        String result = expenseService.addExpense(testExpenseOne);
        Assert.assertEquals("{\"status\":\"success\"}",result);

        Optional<Expense> resultObj = expenseService.getById(4);

        Assert.assertTrue(resultObj.isPresent());
        Assert.assertEquals(testExpenseOne.getValue(), resultObj.get().getValue(),0.0f);
        Assert.assertEquals(testExpenseOne.getDate(), resultObj.get().getDate());
        Assert.assertFalse(resultObj.get().isReoccurring());

    }

    @Test
    @Rollback
    public void removeExpense(){
        ExpenseService expenseService = new ExpenseService(expenseRepository);

        String result = expenseService.deleteExpense(3);
        Assert.assertEquals("{\"status\":\"success\"}",result);

        Optional<Expense> resultObj = expenseService.getById(3);

        Assert.assertFalse(resultObj.isPresent());

    }

    @Test
    @Rollback
    public void getAllExpenses(){
        ExpenseService expenseService = new ExpenseService(expenseRepository);

        List<Expense> expensesList = new LinkedList<>();
        expenseService.getAllExpenses().forEach(expensesList::add);

        Assert.assertEquals(expensesList.size(),4);

    }


}
