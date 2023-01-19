package com.example.task.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "DATA_SNAPSHOTS")
@SequenceGenerator(name="DATA_SNAPSHOTS_SEQ",sequenceName="DATA_SNAPSHOTS_SEQ", allocationSize=1)
public class DataSnapshotEntity {

    @Id
    private Long id;

    private String name;

    private String description;

    private Timestamp updatedTimestamp;
}
