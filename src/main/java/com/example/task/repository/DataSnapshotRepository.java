package com.example.task.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSnapshotRepository extends CrudRepository<DataSnapshotEntity, Long> {
}
