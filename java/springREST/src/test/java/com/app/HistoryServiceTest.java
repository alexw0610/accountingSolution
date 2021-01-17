package com.app;

import com.app.expense.ExpenseRepository;
import com.app.history.HistoryService;
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

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class HistoryServiceTest {

    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    @Rollback
    public void getDeltaHistoryTest() {

        HistoryService historyService = new HistoryService(this.expenseRepository,this.incomeRepository);
        LocalDate currentMock = LocalDate.of(2020,12,1);

        Map<LocalDate,Float> result = historyService.getMonthlyHistory(currentMock);
        float[] expectedValues = {
            904.35f,
            894.36f,
            904.35f,
            913.35f,
            904.35f,
            904.35f,
            904.35f,
            904.35f,
            904.35f,
            904.35f,
            904.35f,
            904.35f
        };
        Map<LocalDate,Float> expectedMap = new LinkedHashMap<LocalDate, Float>();
        java.time.LocalDate start = currentMock.minus(12,ChronoUnit.MONTHS).withDayOfMonth(1);
        for (int i = 1; i <= 12; i++) {
            expectedMap.put(start.plus(i, ChronoUnit.MONTHS), expectedValues[i-1]);
        }

        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(result.size(),12);
        Assert.assertArrayEquals(expectedMap.values().toArray(), result.values().toArray());
        Assert.assertArrayEquals(expectedMap.keySet().toArray(), result.keySet().toArray());

    }

    @Test
    @Rollback
    public void getHistoryTest() {

        HistoryService historyService = new HistoryService(this.expenseRepository,this.incomeRepository);
        LocalDate currentMock = LocalDate.of(2020,12,1);

        Map<LocalDate,Float> result = historyService.getMonthlyHistoryCumulative(currentMock);

        result.forEach((k,v) ->{
            System.out.println(String.format("Key: %s Value: %s", k,v));
        });

        float[] expectedValues = {
            904.35f,
            1798.71f,
            2703.06f,
            3616.4102f,
            4520.7603f,
            5425.1104f,
            6329.4604f,
            7233.8105f,
            8138.1606f,
            9042.511f,
            9946.86f,
            10851.21f
        };

        Map<LocalDate,Float> expectedMap = new LinkedHashMap<LocalDate, Float>();
        java.time.LocalDate start = currentMock.minus(12,ChronoUnit.MONTHS).withDayOfMonth(1);
        for (int i = 1; i <= 12; i++) {
            expectedMap.put(start.plus(i, ChronoUnit.MONTHS), expectedValues[i-1]);
        }

        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(result.size(),12);
        Assert.assertArrayEquals(expectedMap.values().toArray(), result.values().toArray());
        Assert.assertArrayEquals(expectedMap.keySet().toArray(), result.keySet().toArray());

    }




}
