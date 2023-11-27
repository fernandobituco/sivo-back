package com.unit.sivo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unit.sivo.models.Aluno;
import com.unit.sivo.models.Curso;
import com.unit.sivo.models.Disciplina;
import com.unit.sivo.models.Professor;
import com.unit.sivo.repositories.AlunoRepository;
import com.unit.sivo.repositories.CursoRepository;
import com.unit.sivo.repositories.DisciplinaRepository;
import com.unit.sivo.viewModels.AlunoViewModel;
import com.unit.sivo.viewModels.LoginViewModel;

@RestController
@RequestMapping("/api/aluno")
@CrossOrigin
public class AlunoController {
    private final AlunoRepository repository;

    private final CursoRepository cursoRepository;

    private final DisciplinaRepository disciplinaRepository;
    public AlunoController(AlunoRepository repository, DisciplinaRepository disciplinaRepository, CursoRepository cursoRepository) {
        this.repository = repository;
        this.disciplinaRepository = disciplinaRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Aluno> alunos = repository.findAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody AlunoViewModel aluno) {
        Aluno novoAluno = new Aluno(aluno);
        novoAluno.setCurso(cursoRepository.getById(aluno.getCurso_id()));
        for (int disciplinaId : aluno.getDisciplinas()) {
            novoAluno.addDisciplina(disciplinaRepository.getById(disciplinaId));
        }
        novoAluno = repository.save(novoAluno);
        return new ResponseEntity<>(novoAluno, HttpStatus.OK);
    }

    @PutMapping ResponseEntity<Object> update(@RequestBody AlunoViewModel novoAluno) {
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

    @PutMapping("/{id}/disciplina/{disciplinaId}") ResponseEntity<Object> addDisciplinas(
        @PathVariable int id,
        @PathVariable int disciplinaId) {
            Aluno aluno = repository.getById(id);
            Disciplina disciplina = disciplinaRepository.getById(disciplinaId);
            if (aluno.getDisciplinas().contains(disciplina)) {
                aluno.removeDisciplina(disciplina);
            } else {
                aluno.addDisciplina(disciplina);
            }
            repository.save(aluno);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
    }
}
