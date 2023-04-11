package com.example.expensetracker.repo;

import com.example.expensetracker.model.MonthsData;
import com.example.expensetracker.model.MonthsTable;
import com.example.expensetracker.model.MonthsYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MonthsDataRepo extends JpaRepository<MonthsData, Long> {
    void deleteById(Long id);

//    List<MonthsData> findByTableName(String tableName);


    @Query("select e.monthYear,e.monthNumber from MonthsData e ORDER BY e.monthYear ASC,e.monthNumber ASC")
    List <Object[]> getSortedDetail();
    default List<MonthsYear>getSortedDetails(){
        List<Object[]>data=getSortedDetail();
        List<MonthsYear>results=new ArrayList<>();
        for (int i =0;i<data.size();i++){
            results.add(MonthsYear.builder()
                    .monthYear((String) data.get(i)[0])
                    .monthNumber((String) data.get(i)[1])
                    .build());
        }
        return results;
    }

    @Query("select DISTINCT e.monthYear,e.monthNumber from MonthsData e ORDER BY e.monthYear ASC,e.monthNumber ASC")
    List <Object[]> getDistinctDetail();
    default List<MonthsYear>getDistinctDetails(){
        List<Object[]>data=getDistinctDetail();
        List<MonthsYear>results=new ArrayList<>();
        for (int i =0;i<data.size();i++){
            results.add(MonthsYear.builder()
                    .monthYear((String) data.get(i)[0])
                    .monthNumber((String) data.get(i)[1])
                    .build());
        }
        return results;
    }





    @Query("select e.Date from MonthsData e WHERE e.tableName=?1 AND e.monthYear=?2 AND e.monthNumber=?3 ")
    List<String> getDate(String tableName, String monthYear, String monthNumber);
    @Query("select e.Name from MonthsData e WHERE e.tableName=?1 AND e.monthYear=?2 AND e.monthNumber=?3 ")
    List<String> getName(String tableName, String monthYear, String monthNumber);
    @Query("select e.Amount from MonthsData e WHERE e.tableName=?1 AND e.monthYear=?2 AND e.monthNumber=?3")
    List<String> getAmount(String tableName, String monthYear, String monthNumber);
    @Query("select e.id from MonthsData e WHERE e.tableName=?1 AND e.monthYear=?2 AND e.monthNumber=?3")
    List<Long> getid(String tableName, String monthYear, String monthNumber);
    default List<MonthsTable> findByTableName(String tableName,String monthYear,String monthNumber) {
        List<String> Dates = getDate(tableName,monthYear,monthNumber);
        List<String> Names = getName(tableName,monthYear,monthNumber);
        List<String> Amounts = getAmount(tableName,monthYear,monthNumber);
        List<Long> ids= getid(tableName,monthYear,monthNumber);
        List<MonthsTable> result = new ArrayList<>();
        for (int i = 0; i < Dates.size() && i < Names.size() && i < Amounts.size() && i< ids.size(); i++) {
            result.add(MonthsTable.builder()
                    .Date(Dates.get(i))
                    .Name(Names.get(i))
                    .Amount(Amounts.get(i))
                    .id(ids.get(i))
                    .build());
        }

        return result;
    }

}
