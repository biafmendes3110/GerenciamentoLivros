package com.projetojpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.Livro;
import com.projetojpa.repository.LivroRepository;

@Service
public class LivroServices {

	private final LivroRepository livroRepository;

	@Autowired
	public LivroServices(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	public List<Livro> buscaTodosLivro(){
		return livroRepository.findAll();
	}
	public Livro buscaLivroId(Long id) {
		Optional <Livro> Livro = livroRepository.findById(id);
		return Livro.orElse(null);
	}
	public Livro salvaLivro (Livro livro) {
		return livroRepository.save(livro);
	}
	public Livro alterarLivro(Long id, Livro alterarL) {
		Optional <Livro> existeLivro = livroRepository.findById(id);
		if (existeLivro.isPresent()) {
			alterarL.setId(id);
			return livroRepository.save(alterarL);
		}
		return null;
	
	}
	public boolean apagarLivro(Long id) {
		Optional <Livro> existeLivro = livroRepository.findById(id);
		if (existeLivro.isPresent()) {
			 livroRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
