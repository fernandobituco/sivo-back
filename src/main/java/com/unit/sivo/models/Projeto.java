package com.unit.sivo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_projeto")
@Getter
@Setter
public class Projeto extends BaseEntity {
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "cronograma")
    private String cronograma;
    
    @Column(name = "ativo")
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name="professor_id", nullable=false)
    @JsonBackReference
    private Professor professor;

    @ManyToMany
    private List<Disciplina> disciplinas;

    @ManyToMany
    private List<Aluno> alunos;
    
}
