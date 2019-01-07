package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.Estado;

public class EstadoDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	@Length(min=5,max=80,message="O tamanho dever ser entre 5 e 80 caracteres")
	private String nome;
	
	private String sigla;
	
	

	
	public EstadoDTO() {
		
	}
	
	public EstadoDTO(Estado obj) {
	  this.setId(obj.getId()); 
	  this.nome= obj.getNome();
	  this.sigla = obj.getSigla();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
