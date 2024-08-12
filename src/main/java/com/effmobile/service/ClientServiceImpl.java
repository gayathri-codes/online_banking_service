package com.effmobile.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.effmobile.model.Account;
import com.effmobile.model.Client;
import com.effmobile.repo.ClientRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Client createClient(Client client) {
		// TODO Auto-generated method stub
		Account account=client.getAccount();
		if (account.getCurrentbal() == null) {
		account.setCurrentbal(account.getInitialbal());
		}
		
		String encodedPassword = passwordEncoder.encode(client.getPassword());
		client.setPassword(encodedPassword);
		return clientRepo.save(client);
	}

	@Override
	public Client updatePhone(Long clientId, List<String> phones ) {
		// TODO Auto-generated method stub
		Optional<Client> client=clientRepo.findById(clientId);
		if(client.isPresent()) {
			Client c=client.get();
			c.setPhones(phones);
			clientRepo.save(c);
			return c;
		}
		else {
			throw new IllegalStateException("Client not found with id:"+clientId);
		}
		
	}

	@Override
	public void updateEmails(Long clientId, List<String> emails) {
		// TODO Auto-generated method stub
		Optional<Client> client=clientRepo.findById(clientId);
		if(client.isPresent()) {
			Client c=client.get();
			c.setEmails(emails);
			clientRepo.save(c);
		}
		else {
			throw new IllegalStateException("Client not found with id:"+clientId);
		}
	}

	@Override
	public Client deletePhone(Long clientId, String phone) {
		// TODO Auto-generated method stub
		Optional<Client> client=clientRepo.findById(clientId);
		if(client.isPresent()) {
			Client c=client.get();
			List<String> phns=c.getPhones();
			if(phns.size()>1) {
				phns.remove(phone);
				clientRepo.save(c);
				return c;
			}else {
				throw new IllegalStateException("Client must have at least one mail address");
			}
		}
		else {
			throw new IllegalStateException("Client not found with id:"+clientId);

		}
	}

	@Override
	public void deleteEmail(Long clientId, String email) {
		// TODO Auto-generated method stub
		Optional<Client> client=clientRepo.findById(clientId);
		if(client.isPresent()) {
			Client c=client.get();
			List<String> emls=c.getEmails();
			if(emls.size()>1) {
				emls.remove(email);
				clientRepo.save(c);
//				return c;
			}else {
				throw new IllegalStateException("Client must have at least one mail address");
			}
		}
		else {
			throw new IllegalStateException("Client not found with id:"+clientId);

		}
	
	}

	@Override
	public Page<Client> searchClients(String name, String phones, String emails, LocalDate dateOfBirth,
			Pageable pageable) {
		// TODO Auto-generated method stub
		Specification<Client> specification=(root,query,criteriaBuilder)->{
			List<Predicate> predicates =new ArrayList<>();
			
			if(name!=null && !name.isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("name"),"%"+name+"%" ));
			}
			if(phones!=null && !phones.isEmpty()) {
				predicates.add(criteriaBuilder.isMember("%"+phones+"%",root.get("phones") ));
			}
			if(emails!=null && !emails.isEmpty()) {
				predicates.add(criteriaBuilder.isMember("%"+emails+"%" ,root.get("emails")));
			}
			if(dateOfBirth!=null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateOfBirth"),dateOfBirth ));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		return clientRepo.findAll(specification, pageable);
	}
	
}
