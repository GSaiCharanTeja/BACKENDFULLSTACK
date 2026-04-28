package com.example.CivicConnect;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.time.LocalDateTime;
@RestController
@RequestMapping("/responses")
@CrossOrigin(origins = "http://localhost:5173")
public class ResponseController {

    private final ResponseService service;

    public ResponseController(ResponseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Response> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Response create(@RequestBody Response response) {
        response.setId(UUID.randomUUID().toString()); 
        response.setRespondedAt(LocalDateTime.now());
        return service.save(response); 
    }
}