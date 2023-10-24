package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Livro;
import com.projetojpa.services.LivroServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Livro", description = "API REST DE GERENCIAMENTO SE USUÁRIOS")
@RestController
@RequestMapping("/livro")
public class LivroController {
	private final LivroServices livroServices;
	
	@Autowired
	public LivroController (LivroServices livroServices) {
		this.livroServices = livroServices;
	}
	@GetMapping("/{id}")
	@Operation(summary = "Localiza livro por ID")
	public ResponseEntity <Livro> buscaLivroIdControlId(@PathVariable Long id){
		Livro livro = livroServices.buscaLivroId(id);
		if(livro!= null) {
			return ResponseEntity.ok(livro);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	@Operation(summary = "Apresenta todos os livros")
	public ResponseEntity<List<Livro>> buscaTodosLivroControl() {
		List<Livro> Livro = livroServices.buscaTodosLivro();

		return ResponseEntity.ok(Livro);
	}
	@PostMapping("/")
	@Operation(summary = "Cadastra um livro")
	public ResponseEntity<Livro> salvaLivroControl(@RequestBody @Valid Livro livro){
		Livro salvaLivro = livroServices.salvaLivro(livro);

		return ResponseEntity.status(HttpStatus.CREATED).body(salvaLivro);

	}
	@PutMapping ("/{id}")
	@Operation(summary = "altera as informações do id")
	public ResponseEntity<Livro> alterarUsuario(@PathVariable Long id, @RequestBody @Valid Livro livro) {
		Livro alterarLivro = livroServices.alterarLivro(id,livro);
		if (alterarLivro  != null) {
			return ResponseEntity.ok(alterarLivro);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar o id selecionado")
	public ResponseEntity<String> apagaLivroControl(@PathVariable Long id) {
		boolean apagar = livroServices.apagarLivro(id);
		if(apagar) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}

