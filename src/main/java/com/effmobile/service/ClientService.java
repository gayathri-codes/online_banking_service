package com.effmobile.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.effmobile.model.Client;

@Service
public interface ClientService {
	public Client createClient(Client client);
	public Client updatePhone(Long clientId,List<String> phones);
	public void updateEmails(Long clientId,List<String> emails);
	public Client deletePhone(Long clientId,String phone);
	public void deleteEmail(Long clientId,String email);
	
	public Page<Client> searchClients(String name,String phones,String emails,LocalDate dateOfBirth,Pageable pageable);
}
