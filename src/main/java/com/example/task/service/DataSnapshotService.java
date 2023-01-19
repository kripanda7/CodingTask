package com.example.task.service;

import com.example.task.dto.DataSnapshot;
import com.example.task.repository.DataSnapshotEntity;
import com.example.task.repository.DataSnapshotRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Slf4j
@Service
public class DataSnapshotService {

    private final DataSnapshotRepository dataSnapshotRepository;
    private final ModelMapper mapper;

    @Autowired
    public DataSnapshotService(DataSnapshotRepository dataSnapshotRepository, ModelMapper mapper) {
        this.dataSnapshotRepository = dataSnapshotRepository;
        this.mapper = mapper;
    }

    public String upload(MultipartFile file) {
        if (!file.isEmpty()) {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<DataSnapshot> csvToBean = new CsvToBeanBuilder<DataSnapshot>(reader)
                        .withType(DataSnapshot.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                        .build();
                List<DataSnapshot> dataSnapshots = csvToBean.parse();
                List<DataSnapshotEntity> dataSnapshotEntities = dataSnapshots.stream()
                        .filter(dataSnapshot -> dataSnapshot.getId() != 0)
                        .map(dataSnapshot -> mapper.map(dataSnapshot, DataSnapshotEntity.class))
                        .toList();
                dataSnapshotRepository.saveAll(dataSnapshotEntities);
                return "File successfully uploaded.";
            } catch (Exception ex) {
                log.error("An error occurred while processing the CSV file.", ex);
                return "An error occurred while processing the CSV file.";
            }
        }
        log.debug("File is empty.");
        return "File is empty.";
    }

    public DataSnapshot getById(Long id) {
        return mapper.map(dataSnapshotRepository.findById(id), DataSnapshot.class);
    }

    public void deleteById(Long id) {
        dataSnapshotRepository.deleteById(id);
    }
}
