package com.example.expensetracker.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthsTable {
    private Long id;
    private String Date;
    private String Name;
    private String Amount;
}
