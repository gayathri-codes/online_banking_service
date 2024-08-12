package com.effmobile.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effmobile.dto.TransferRequestDTO;
import com.effmobile.model.Client;
import com.effmobile.service.TransferService;

@RestController
@RequestMapping("/api/effectivemobilebanking/transfer")
public class TransferController {
	
	@Autowired
	private TransferService transferService;
	
	
	@PostMapping()
	public ResponseEntity<String> transferMoney(@RequestBody TransferRequestDTO transferRequestDTO) {
//		Client receiver=transferService.transferMoney(fromAccountId, toAccountId, amount);
		transferService.transferMoney(transferRequestDTO);
		return new ResponseEntity<>("Transaction is successfull",HttpStatus.OK);

	}

}
