package com.app;

import com.app.income.Income;
import com.app.income.IncomeRepository;
import com.app.income.IncomeService;
import org.junit.Assert;
import org.junit.Test;
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
public class IncomeServiceTest {

    @Autowired
    private IncomeRepository incomeRepository;


    @Test
    @Rollback
    public void addIncomeTest() {
        IncomeService incomeService = new IncomeService(incomeRepository);

        Income testIncomeOne = new Income();
        testIncomeOne.setValue(4.99f);
        testIncomeOne.setDate("2020-01-01");
        testIncomeOne.setReoccurring(false);

        String result = incomeService.addIncome(testIncomeOne);
        Assert.assertEquals("{\"status\":\"success\"}", result);

        Optional<Income> resultObj = incomeService.getById(4);

        Assert.assertTrue(resultObj.isPresent());
        Assert.assertEquals(testIncomeOne.getValue(), resultObj.get().getValue(), 0.0f);
        Assert.assertEquals(testIncomeOne.getDate(), resultObj.get().getDate());
        Assert.assertFalse(resultObj.get().isReoccurring());

    }

    @Test
    @Rollback
    public void removeIncomeTest() {
        IncomeService incomeService = new IncomeService(incomeRepository);

        String result = incomeService.deleteIncome(3);
        Assert.assertEquals("{\"status\":\"success\"}", result);

        Optional<Income> resultObj = incomeService.getById(3);

        Assert.assertFalse(resultObj.isPresent());

    }

    @Test
    @Rollback
    public void getAllIncomes() {
        IncomeService incomeService = new IncomeService(incomeRepository);

        List<Income> incomeList = new LinkedList<>();
        incomeService.getAllIncomes().forEach(incomeList::add);

        Assert.assertEquals(incomeList.size(), 4);

    }


}
