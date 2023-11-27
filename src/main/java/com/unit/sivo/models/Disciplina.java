package com.unit.sivo.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_disciplina")
@Getter
@Setter
public class Disciplina extends BaseEntity {

    @ManyToMany(mappedBy = "disciplinas")
    @JsonIgnore
    private Set<Professor> professores;

    @ManyToMany(mappedBy = "disciplinas")
    @JsonIgnore
    private Set<Aluno> alunos;

    @ManyToMany(mappedBy = "disciplinas")
    @JsonIgnore
    private Set<Projeto> projetos;

    @ManyToMany(mappedBy = "disciplinas")
    @JsonIgnore
    private Set<Curso> cursos;
}
