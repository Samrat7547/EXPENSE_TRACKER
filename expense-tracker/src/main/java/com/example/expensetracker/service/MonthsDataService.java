package com.example.expensetracker.service;

import com.example.expensetracker.model.MonthsData;
import com.example.expensetracker.model.MonthsTable;
import com.example.expensetracker.model.MonthsYear;
import com.example.expensetracker.repo.MonthsDataRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service
@Transactional
public class MonthsDataService {
    private final MonthsDataRepo monthsDataRepo;

    @Autowired
    public MonthsDataService(MonthsDataRepo monthsDataRepo) {
        this.monthsDataRepo = monthsDataRepo;
    }


   public List<MonthsTable> findByTableName(String tableName,String monthYear, String monthNumber) {
       return monthsDataRepo.findByTableName(tableName,monthYear,monthNumber);
   }
   public List<MonthsYear> findSortedDetail(){
        return monthsDataRepo.getSortedDetails();
   }
    public List<MonthsYear> findDistinctDetail(){
        return monthsDataRepo.getDistinctDetails();
    }

   public MonthsData addMonthsData(MonthsData monthsData) {
    return monthsDataRepo.save(monthsData);
   }

   public void deleteData(Long id){
        monthsDataRepo.deleteById(id);
   }




}
