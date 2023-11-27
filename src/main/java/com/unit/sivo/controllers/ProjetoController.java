package com.unit.sivo.controllers;

import java.util.List;

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
import com.unit.sivo.models.Disciplina;
import com.unit.sivo.models.Projeto;
import com.unit.sivo.repositories.AlunoRepository;
import com.unit.sivo.repositories.DisciplinaRepository;
import com.unit.sivo.repositories.ProfessorRepository;
import com.unit.sivo.repositories.ProjetoRepository;
import com.unit.sivo.viewModels.ProjetoViewModel;

@RestController
@RequestMapping("/api/projeto")
@CrossOrigin
public class ProjetoController {
    private final ProjetoRepository repository;
    private final DisciplinaRepository disciplinaRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public ProjetoController(ProjetoRepository repository, DisciplinaRepository disciplinaRepository, AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.repository = repository;
        this.disciplinaRepository = disciplinaRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Projeto> projetos = repository.findAll();
        return new ResponseEntity<>(projetos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        Projeto projeto = repository.getById(id);
        return new ResponseEntity<>(projeto, HttpStatus.OK);
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity<Object> getByProfessorId(@PathVariable int id) {
        List<Projeto> projetos = repository.getByProfessorId(id);
        return new ResponseEntity<>(projetos, HttpStatus.OK);
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<Object> getByAlunosId(@PathVariable int id) {
        List<Projeto> projetos = repository.getByAlunosId(id);
        return new ResponseEntity<>(projetos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ProjetoViewModel projeto) {
        Projeto novoProjeto = new Projeto(projeto);
        for (int disciplinaId : projeto.getDisciplinas()) {
            novoProjeto.addDisciplina(disciplinaRepository.getById(disciplinaId));
        }
        novoProjeto.setProfessor(professorRepository.getById(projeto.getProfessor_id()));
        novoProjeto = repository.save(novoProjeto);
        return new ResponseEntity<>(novoProjeto, HttpStatus.OK);
    }

    @PutMapping ResponseEntity<Object> update(@RequestBody Projeto novoProjeto) {
        Projeto projeto = repository.getById(novoProjeto.getId());
        projeto.setNome(novoProjeto.getNome());

        return new ResponseEntity<>(novoProjeto, HttpStatus.OK);
    }

    @DeleteMapping ResponseEntity<Object> delete(int id) {
        Projeto projeto = repository.getById(id);
        repository.delete(projeto);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/disciplina/{disciplinaId}") ResponseEntity<Object> addDisciplinas(
        @PathVariable int id,
        @PathVariable int disciplinaId) {
            Projeto projeto = repository.getById(id);
            Disciplina disciplina = disciplinaRepository.getById(disciplinaId);
            if (projeto.getDisciplinas().contains(disciplina)) {
                projeto.removeDisciplina(disciplina);
            } else {
                projeto.addDisciplina(disciplina);
            }
            repository.save(projeto);
            return new ResponseEntity<>(projeto, HttpStatus.OK);
    }

    @PutMapping("/{id}/aluno/{alunoId}") ResponseEntity<Object> addAlunos(
        @PathVariable int id,
        @PathVariable int alunoId) {
            Projeto projeto = repository.getById(id);
            Aluno aluno = alunoRepository.getById(alunoId);
            if (projeto.getAlunos().contains(aluno)) {
                projeto.removeAluno(aluno);
            } else {
                projeto.addAluno(aluno);
            }
            repository.save(projeto);
            return new ResponseEntity<>(projeto, HttpStatus.OK);
    }

    @PutMapping("/{id}/{situacao}") ResponseEntity<Object> updateSituation(
        @PathVariable int id,
        @PathVariable int situacao) {
            Projeto projeto = repository.getById(id);
            projeto.setSituacao(situacao);
            repository.save(projeto);
            return new ResponseEntity<>(projeto, HttpStatus.OK);
    }
}
