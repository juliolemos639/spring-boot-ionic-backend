package com.juliolemos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.juliolemos.cursomc.domain.Cliente;
import com.juliolemos.cursomc.dto.ClienteDTO;
import com.juliolemos.cursomc.repositories.ClienteRepository;
import com.juliolemos.cursomc.services.exceptions.DataIntegrityException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id)  {
		
		Optional<Cliente> obj = repo.findById(id);
					
		return obj.orElseThrow(() -> new com.juliolemos.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado ! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}

	// Atualização na tabela
		public Cliente update(Cliente obj) {
			Cliente newObj = buscar(obj.getId());
			updateData(newObj, obj); // Atualiza
			return repo.save(newObj);
		}
		
		// Exclusão na tabela
		public void delete(Integer id) {
			buscar(id);
			try {
			repo.deleteById(id);
			}
			catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
			}
		}
		
		// endpoint para retornar as categorias
		public List<Cliente> findALL() {
			return repo.findAll();
		}
		
		// Paginação
		public Page<Cliente> findPage(
				Integer page, 
				Integer linesPerPage, 
				String orderBy, 
				String direction) {
			PageRequest pageRequest = PageRequest.of(
					page, 
					linesPerPage, 
					Direction.valueOf(direction), 
					orderBy);
			return repo.findAll(pageRequest);
			}
		
		// pegar para validação
		public Cliente fromDTO(ClienteDTO objDto) {
			return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		}
		private void updateData(Cliente newObj, Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
}
