package com.effmobile.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effmobile.dto.TransferRequestDTO;
import com.effmobile.model.Client;
import com.effmobile.repo.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferService {
	@Autowired
	private ClientRepository clientRepo;
	
    Logger log = LoggerFactory.getLogger(TransferService.class);

	@Transactional
	public void transferMoney(TransferRequestDTO transferRequestDTO) {
		Long fromAccountId =transferRequestDTO.getFromAccountId();
		Long toAccountId = transferRequestDTO.getToAccountId();
		BigDecimal amount = transferRequestDTO.getAmount();
		
		Client sender= clientRepo.getById(toAccountId);
//				clientRepo.findById(fromAccountId);
		Client receiver = clientRepo.getById(toAccountId);
		BigDecimal balInSenderAccnt=sender.getAccount().getCurrentbal();
		BigDecimal balInReceiverAccnt=sender.getAccount().getCurrentbal();

		if(amount.compareTo(balInSenderAccnt)<=0) {			
			balInSenderAccnt=balInSenderAccnt.subtract(amount);
			balInReceiverAccnt=balInReceiverAccnt.add(amount);
			
			sender.getAccount().setCurrentbal(balInSenderAccnt);
			receiver.getAccount().setCurrentbal(balInReceiverAccnt);
		}
		else {
			throw new IllegalArgumentException("Insufficient balance");
		}
		log.info("After transaction sender account Details------------>"+sender.getAccount());
		log.info("After transaction receiver account Details------------>"+receiver.getAccount());
	}
}
