package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.dto.CidadeDTO;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;

	public Cidade find(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
	
	public List<Cidade> findByEstado(Integer id) {
		return repo.findCidades(id);
	}
	
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cidade update(Cidade obj) {
		Cidade newobj = find(obj.getId());
		updateData(newobj,obj);
		find(obj.getId());
		return repo.save(newobj);
	}
	
	private void updateData(Cidade newobj, Cidade obj) {
		newobj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e)  {
			throw new DataIntegrityException("Não é possível excluir uma Cidade que possui produtos!");
		}
	}
	
	public List<Cidade> findAll(){		
		return repo.findAll();
	}
	
	public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cidade fromDTO(CidadeDTO objDto) {
		return new Cidade(objDto.getId(),objDto.getNome(),null);
	}
}
