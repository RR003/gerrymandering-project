package cse416.server.controller;

import cse416.server.dao.InitializeImpl;
import cse416.server.dao.StateInformationRepository;
import cse416.server.model.StateInformation;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/initialize")
public class InitializeController {

    @Autowired
    private StateInformationRepository stateInformationRepository;

    @Autowired
    private InitializeImpl initializeImpl;

    @GetMapping("/{stateName}")
    public ResponseEntity<String> getStateInfo(@PathVariable String stateName) {
        return initializeImpl.getStateInfo(stateName);
    }

    @GetMapping("/")
    public ResponseEntity<String> getInitialization() {
        return initializeImpl.getInitializationInfo();
    }
}
