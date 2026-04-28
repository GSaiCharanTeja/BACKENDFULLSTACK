package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlagService {

    @Autowired
    private FlagRepository flagRepository;

    public List<Flag> getAllFlags() {
        return flagRepository.findAll();
    }
}