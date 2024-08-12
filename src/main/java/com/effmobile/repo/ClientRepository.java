package com.effmobile.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.effmobile.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client>{
	
	Optional<Client> findByUsername(String username);
//	@Query("SELECT c FROM Client c LEFT JOIN c.emails e LEFT JOIN c.phones p WHERE " +
//		       "c.name LIKE %:query% " +
//		       "OR e LIKE %:query% " +
//		       "OR p LIKE %:query% " +
//		       "OR c.date_of_birth >= :query")
//    public List<Client> searchClients(String query);
}
