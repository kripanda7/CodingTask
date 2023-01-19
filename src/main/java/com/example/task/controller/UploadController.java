package com.example.task.controller;

import com.example.task.dto.DataSnapshot;
import com.example.task.service.DataSnapshotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value = "/datasnapshot")
public class UploadController {

    private final DataSnapshotService dataSnapshotService;

    @Autowired
    public UploadController(DataSnapshotService dataSnapshotService) {
        this.dataSnapshotService = dataSnapshotService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataSnapshot> getById(@PathVariable("id") Long id) {
        log.info("Searching data by id={}", id);
        DataSnapshot result = dataSnapshotService.getById(id);
        if (result == null) {
            log.info("Data was not found by id={}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("Data was found by id={}", id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/upload")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file) {
        log.info("Uploading file.");
        return dataSnapshotService.upload(file);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        log.info("Deleting data by id={}", id);
        dataSnapshotService.deleteById(id);
        log.info("Data was deleted by id={}", id);
        return "deleted";
    }
}
