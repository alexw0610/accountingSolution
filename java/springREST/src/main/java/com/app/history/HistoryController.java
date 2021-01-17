package com.app.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/monthlyHistory")
    public Map<LocalDate, Float> returnMonthlyHistory() {
        return this.historyService.getMonthlyHistory(LocalDate.now());
    }

    @GetMapping("/monthlyHistoryCumulative")
    public Map<LocalDate, Float> returnMonthlyHistoryCumulative() {
        return this.historyService.getMonthlyHistoryCumulative(LocalDate.now());
    }


}
