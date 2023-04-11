package com.example.expensetracker.controller;

import com.example.expensetracker.model.MonthsData;
import com.example.expensetracker.model.MonthsTable;
import com.example.expensetracker.model.MonthsYear;
import com.example.expensetracker.repo.MonthsDataRepo;
import com.example.expensetracker.service.MonthsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/expense")

public class MonthsDataController {
    private final MonthsDataService monthsDataService;
    @Autowired
    private MonthsDataRepo monthsDataRepo;

    public MonthsDataController(MonthsDataService monthsDataService) {

        this.monthsDataService = monthsDataService;
    }

    @GetMapping("/all/sorted")
    public ResponseEntity<List<MonthsYear>>getAllMonthsDataSorted(){
        List<MonthsYear> monthsdatas=monthsDataService.findSortedDetail();
        return new ResponseEntity<>(monthsdatas,HttpStatus.OK);
    }
    @GetMapping("/all/distinct")
    public ResponseEntity<List<MonthsYear>>getAllMonthsDataDistinct(){
        List<MonthsYear> monthsdatas=monthsDataService.findDistinctDetail();
        return new ResponseEntity<>(monthsdatas,HttpStatus.OK);
    }


    @GetMapping("/table/{tableName}/{monthYear}/{monthNumber}")
    public ResponseEntity<List<MonthsTable>>getAllTableData(@PathVariable("tableName")String tableName,@PathVariable("monthYear")String monthYear,@PathVariable("monthNumber")String monthNumber){
        List<MonthsTable> monthsTable=monthsDataService.findByTableName(tableName,monthYear,monthNumber);
        return new ResponseEntity<>(monthsTable,HttpStatus.OK);

    }

    @PostMapping("/data")
    public ResponseEntity<MonthsData>addMonthsData(@RequestBody MonthsData monthsData){
            MonthsData newMonthsData=monthsDataService.addMonthsData(monthsData);
            return new ResponseEntity<>(newMonthsData,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteDataMonth(@PathVariable("id") Long id){
        monthsDataService.deleteData(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
