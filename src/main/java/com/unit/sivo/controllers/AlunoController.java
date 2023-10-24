package com.unit.sivo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unit.sivo.models.Aluno;
import com.unit.sivo.repositories.AlunoRepository;
import com.unit.sivo.viewModels.LoginViewModel;

@RestController
@RequestMapping("/api/aluno")
@CrossOrigin
public class AlunoController {
    private final AlunoRepository repository;

    public AlunoController(AlunoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Aluno> alunos = repository.findAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Aluno aluno) {
        Aluno novAluno = repository.save(aluno);
        return new ResponseEntity<>(novAluno, HttpStatus.OK);
    }

    @PutMapping ResponseEntity<Object> update(@RequestBody Aluno novoAluno) {
        Aluno aluno = repository.getById(novoAluno.getId());
        aluno.setEmail(novoAluno.getEmail());
        aluno.setMatricula(novoAluno.getMatricula());
        aluno.setNome(novoAluno.getNome());
        aluno.setSenha(novoAluno.getSenha());

        return new ResponseEntity<>(novoAluno, HttpStatus.OK);
    }

    @DeleteMapping ResponseEntity<Object> delete(int id) {
        Aluno aluno = repository.getById(id);
        repository.delete(aluno);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginViewModel login) {
        Aluno aluno = repository.getByEmail(login.getEmail());
        if (aluno != null && aluno.getSenha().equals(login.getSenha())) {
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login ou senha inválidos", HttpStatus.BAD_REQUEST);
        }
    }
}
