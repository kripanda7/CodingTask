package com.example.task.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class DataSnapshot {

    @CsvBindByName(column = "PRIMARY_KEY")
    private long id;
    @CsvBindByName(column = "NAME")
    private String name;
    @CsvBindByName(column = "DESCRIPTION")
    private String description;
    @CsvBindByName(column = "UPDATED_TIMESTAMP")
    private String updatedTimestamp;
}
