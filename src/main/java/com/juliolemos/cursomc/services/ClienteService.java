package com.juliolemos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliolemos.cursomc.domain.Cliente;
import com.juliolemos.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id)  {
		
		Optional<Cliente> obj = repo.findById(id);
					
		return obj.orElseThrow(() -> new com.juliolemos.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado ! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}

}
