package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ResponseService {
	
	@Autowired
	private ResponseRepository repo;
	
	public Response save(Response response) {
	    return repo.save(response);
	}
	
	public List<Response> getAll(){
		return repo.findAll();
	}

}
