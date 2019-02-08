package com.juliolemos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.juliolemos.cursomc.domain.Categoria;
import com.juliolemos.cursomc.repositories.CategoriaRepository;
import com.juliolemos.cursomc.services.exceptions.DataIntegrityException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id)  {
		
		Optional<Categoria> obj = repo.findById(id);
					
		return obj.orElseThrow(() -> new com.juliolemos.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado ! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
	}
	// Inserção na tabela
	public Categoria insert(Categoria obj) {
		obj.setId(null); // Se não for nulo será considerado uma atualização
		return repo.save(obj);
	}
	
	// Atualização na tabela
	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
	
	// Exclusão na tabela
	public void delete(Integer id) {
		buscar(id);
		try {
		repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
}
