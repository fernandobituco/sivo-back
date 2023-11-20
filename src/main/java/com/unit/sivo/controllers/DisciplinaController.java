package com.unit.sivo.controllers;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

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

import com.unit.sivo.models.Curso;
import com.unit.sivo.models.Disciplina;
import com.unit.sivo.repositories.CursoRepository;
import com.unit.sivo.repositories.DisciplinaRepository;
import com.unit.sivo.viewModels.DisciplinaViewModel;

@RestController
@RequestMapping("/api/disciplina")
@CrossOrigin
public class DisciplinaController {
    private final DisciplinaRepository repository;
    private final CursoRepository cursoRepository;

    public DisciplinaController(DisciplinaRepository repository, CursoRepository cursoRepository) {
        this.repository = repository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Disciplina> Disciplinas = repository.findAll();
        return new ResponseEntity<>(Disciplinas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody DisciplinaViewModel disciplina) {
        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setNome(disciplina.getNome());
        novaDisciplina = repository.save(novaDisciplina);
        return new ResponseEntity<>(novaDisciplina, HttpStatus.OK);
    }

    @PutMapping ResponseEntity<Object> update(@RequestBody Disciplina novoDisciplina) {
        Disciplina Disciplina = repository.getById(novoDisciplina.getId());
        Disciplina.setNome(novoDisciplina.getNome());

        return new ResponseEntity<>(novoDisciplina, HttpStatus.OK);
    }

    @DeleteMapping ResponseEntity<Object> delete(int id) {
        Disciplina Disciplina = repository.getById(id);
        repository.delete(Disciplina);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
