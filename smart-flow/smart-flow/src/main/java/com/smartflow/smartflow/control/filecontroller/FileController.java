package com.smartflow.smartflow.control.filecontroller;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartflow.smartflow.model.Information;
import com.smartflow.smartflow.repository.InformationRepository;

@RestController
@CrossOrigin
@RequestMapping("api/v1/file")
public class FileController {

    @Autowired
    private InformationRepository informationRepository;

    @GetMapping("/download/{informationId}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable("informationId") Integer informationId) {
        Optional<Information> optionalInformation = informationRepository.findById(informationId);
        if (optionalInformation.isPresent()) {
            Information information = optionalInformation.get();
            String filePath = information.getFilePath(); // Supondo que você tenha um método getFilePath() na sua classe
                                                         // Information
            File file = new File(filePath);
            if (file.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
                headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
                FileSystemResource resource = new FileSystemResource(file);
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
