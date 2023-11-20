package com.unit.sivo.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_aluno")
@Getter
@Setter
public class Aluno extends BaseEntity {
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "curso")
    private String cruso;

    @Column(name = "periodo")
    private int periodo;
    
    @ManyToMany
    private Set<Projeto> projetos;

    @ManyToMany
    @JoinTable(
      name = "tb_aluno_disciplinas", 
      joinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "id"))
    private Set<Disciplina> disciplinas;
}
