package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.dto.EmailCollectionDto;
import com.nosy.admin.nosyadmin.service.EmailCollectionService;
import com.nosy.admin.nosyadmin.utils.EmailCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping("/api/v1/nosy/emailgroups")
public class EmailCollectionController {
    private EmailCollectionService emailCollectionService;

    @Autowired
    public EmailCollectionController(EmailCollectionService emailCollectionService) {
        this.emailCollectionService = emailCollectionService;
    }

    @PostMapping(value = "/{name}")
    public ResponseEntity<EmailCollectionDto> uploadMultipart(
            @RequestParam("file") String file,
            @PathVariable String name,
            Principal principal) {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService
                .parseEmailCollection(file, name, principal.getName())), HttpStatus.CREATED);
    }

    @PostMapping(value = "/list/{name}")
    public ResponseEntity<EmailCollectionDto> createGroup(
            @RequestBody List<String> emails,
            @PathVariable String name,
            Principal principal) {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService
                .createEmailCollection(emails, name, principal.getName())), HttpStatus.CREATED);    }

    @GetMapping(value = "/{emailCollectionId}")
    public ResponseEntity<EmailCollectionDto> getEmailCollectionById(
            @PathVariable String emailCollectionId) {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService
                .getEmailCollectionById(emailCollectionId)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmailCollectionDto>> getAllEmailCollections() {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDtoList(emailCollectionService
                .getAllEmailCollections()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{emailCollectionId}")
    public ResponseEntity<String> deleteEmailCollectionById(
            @PathVariable String emailCollectionId) {
        emailCollectionService.deleteEmailCollectionById(emailCollectionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
