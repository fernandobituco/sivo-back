package com.unit.sivo.controllers;

import java.util.List;
import java.util.Set;

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

import com.unit.sivo.models.Curso;
import com.unit.sivo.models.Disciplina;
import com.unit.sivo.repositories.CursoRepository;
import com.unit.sivo.repositories.DisciplinaRepository;
import com.unit.sivo.viewModels.CursoViewModel;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin
public class CursoController {
    private final CursoRepository repository;
    private final DisciplinaRepository disciplinaRepository;

    public CursoController(CursoRepository repository, DisciplinaRepository disciplinaRepository) {
        this.repository = repository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Curso> Cursos = repository.findAll();
        return new ResponseEntity<>(Cursos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody CursoViewModel curso) {
        Curso novoCurso = new Curso(curso);
        novoCurso = repository.save(novoCurso);
        return new ResponseEntity<>(novoCurso, HttpStatus.OK);
    }

    @PutMapping ResponseEntity<Object> update(@RequestBody Curso novoCurso) {
        Curso Curso = repository.getById(novoCurso.getId());
        Curso.setNome(novoCurso.getNome());

        return new ResponseEntity<>(novoCurso, HttpStatus.OK);
    }

    @DeleteMapping ResponseEntity<Object> delete(int id) {
        Curso Curso = repository.getById(id);
        repository.delete(Curso);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/disciplina/{disciplinaId}") ResponseEntity<Object> addDisciplinas(
        @PathVariable int id,
        @PathVariable int disciplinaId) {
            Curso curso = repository.getById(id);
            Disciplina disciplina = disciplinaRepository.getById(disciplinaId);
            if (curso.getDisciplinas().contains(disciplina)) {
                curso.removeDisciplina(disciplina);
            } else {
                curso.addDisciplina(disciplina);
            }
            repository.save(curso);
            return new ResponseEntity<>(curso, HttpStatus.OK);
    }
}
