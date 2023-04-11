package com.example.expensetracker.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table
public class MonthsData implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(nullable = false,updatable = false)
//    @GeneratedValue
@TableGenerator(
        name = "yourTableGenerator",
        allocationSize = 1,
        initialValue = 1)
@Id
@GeneratedValue(
        strategy=GenerationType.TABLE,
        generator="yourTableGenerator")
    private Long id;
    @Column
    private String monthYear;
    @Column
    private String monthNumber;
    @Column
    private String tableName;
    @Column
    private String Date;
    @Column
    private String Name;
    @Column
    private String Amount;

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(String monthNumber) {
        this.monthNumber = monthNumber;
    }



    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }



}
