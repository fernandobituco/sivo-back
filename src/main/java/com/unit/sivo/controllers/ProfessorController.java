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

import com.unit.sivo.models.Professor;
import com.unit.sivo.repositories.ProfessorRepository;
import com.unit.sivo.viewModels.LoginViewModel;

@RestController
@RequestMapping("/api/professor")
@CrossOrigin
public class ProfessorController {
    private final ProfessorRepository repository;

    public ProfessorController(ProfessorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Professor> professores = repository.findAll();
        return new ResponseEntity<>(professores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Professor professor) {
        Professor novoProfessor = repository.save(professor);
        return new ResponseEntity<>(novoProfessor, HttpStatus.OK);
    }

    @PutMapping ResponseEntity<Object> update(@RequestBody Professor novoProfessor) {
        Professor professor = repository.getById(novoProfessor.getId());
        professor.setEmail(novoProfessor.getEmail());
        professor.setMatricula(novoProfessor.getMatricula());
        professor.setNome(novoProfessor.getNome());
        professor.setSenha(novoProfessor.getSenha());

        return new ResponseEntity<>(novoProfessor, HttpStatus.OK);
    }

    @DeleteMapping ResponseEntity<Object> delete(int id) {
        Professor professor = repository.getById(id);
        repository.delete(professor);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginViewModel login) {
        Professor professor = repository.getByEmail(login.getEmail());
        if (professor != null && professor.getSenha().equals(login.getSenha())) {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        } else {
        return new ResponseEntity<>(login, HttpStatus.BAD_REQUEST);
        }
    }
}
