package com.effmobile.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.effmobile.model.Client;
import com.effmobile.service.ClientService;

@RestController
@RequestMapping("/api/effectivemobilebanking")
public class SearchController {
	
	@Autowired
	private ClientService clientService;
	@GetMapping("/search") 
	public ResponseEntity<Page<Client>> searchClients(@RequestParam(required=false) String name,
			@RequestParam(required=false) String phones,
			@RequestParam(required=false) String emails,
			@RequestParam(required=false) LocalDate dateOfBirth,
			Pageable pageable) {
		Page<Client> clients=clientService.searchClients(name,phones,emails,dateOfBirth,pageable);
		return ResponseEntity.ok(clients);

	}
}
