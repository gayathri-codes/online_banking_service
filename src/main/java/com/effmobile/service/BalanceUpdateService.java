//package com.effmobile.service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.effmobile.model.Account;
//import com.effmobile.model.Client;
//import com.effmobile.repo.ClientRepository;
//
//import jakarta.transaction.Transactional;
//
//@Service
//public class BalanceUpdateService {
//	
//    Logger log = LoggerFactory.getLogger(BalanceUpdateService.class);
//
//	
//	@Autowired
//	private ClientRepository clientRepo;
//	
//	@Transactional
//	@Scheduled(fixedRate = 60000)
//	public String updateBalance() {
//		List<Client> clients=clientRepo.findAll();
//		log.info("Clients list------------>"+clients);
//		for(Client c:clients) {
//			Account acc=c.getAccount();
//			log.info("Client Account ------------>"+acc);
//			BigDecimal limit=acc.getInitialbal().multiply(new BigDecimal(2.07));
//			BigDecimal curBal=acc.getCurrentbal();	
//			log.info("Client Current Balance------------>"+curBal);
//			if(curBal==null) {
//				continue;
//			}
//			if(acc.getCurrentbal().compareTo(limit)<=0) {
//				curBal=curBal.add(curBal.multiply(new BigDecimal(0.05)));
//				acc.setCurrentbal(curBal);
//				log.info("Client updated Balance ------------>"+curBal);
//			}
//			log.info("Final Client Balance is "+c.getAccount().getCurrentbal());
//			return "Updated Balance is " +c.getAccount().getCurrentbal();			
//		}
//		return null;
//	}
//}
