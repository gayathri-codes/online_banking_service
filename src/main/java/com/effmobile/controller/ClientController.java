package com.effmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effmobile.model.Client;
import com.effmobile.service.ClientService;

@RestController
@RequestMapping("/api/effectivemobilebanking/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/createClient")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		Client savedClient=clientService.createClient(client);
		return new ResponseEntity<>(savedClient,HttpStatus.CREATED);
	}
	@PutMapping("updatePhone/{clientId}")
	public ResponseEntity<Client> updatePhone(@PathVariable("clientId") Long clientId,@RequestBody List<String> phones) {
		Client updatedClient=clientService.updatePhone(clientId, phones);
		return new ResponseEntity<>(updatedClient,HttpStatus.OK);

	}
	@PutMapping("updateMail/{clientId}")
	public ResponseEntity<String> updateEmails(@PathVariable Long clientId,@RequestBody List<String> emails) {
		clientService.updateEmails(clientId, emails);
		return new ResponseEntity<>("updated Email Successfully",HttpStatus.OK);

	}
	@DeleteMapping("deletePhone/{clientId}/{phone}")
	public ResponseEntity<Client> deletePhone(@PathVariable Long clientId,@PathVariable String phone) {
		Client updatedClient=clientService.deletePhone(clientId, phone);
		return new ResponseEntity<>(updatedClient,HttpStatus.OK);

	}
	@DeleteMapping("deleteEmail/{clientId}/{email}")
	public ResponseEntity<String> deleteEmail(@PathVariable Long clientId,@PathVariable String email) {
		clientService.deleteEmail(clientId, email);
		return new ResponseEntity<>("deleted Email",HttpStatus.OK);

	}

}
