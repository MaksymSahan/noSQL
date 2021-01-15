package com.lab5.resteventhub.web.rest;

import com.lab5.resteventhub.dto.RequestDTO;
import com.lab5.resteventhub.service.SendDataConsoleService;
import com.lab5.resteventhub.service.SendDataEventHubService;
import com.microsoft.azure.eventhubs.EventHubException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class URLController {

    private static final String URL = "/url";

    private final SendDataConsoleService consoleService;
    private final SendDataEventHubService hubService;

    @Autowired
    public URLController(SendDataConsoleService consoleService, SendDataEventHubService hubService) {
        this.consoleService = consoleService;
        this.hubService = hubService;
    }

    @PostMapping(value = URL)
    public void addNewUrl(@RequestBody RequestDTO dto) throws IOException, EventHubException {
        if (dto.getStrategy().equals("redis")) {
            consoleService.sendAndLog(dto.getUrl());
        } else if (dto.getStrategy().equals("eventHub")) {
            hubService.sendAndLog(dto.getUrl());
        } else {
            System.out.println("Wrong Strategy");
        }
    }
}