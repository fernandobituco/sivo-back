package com.unit.sivo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private List<Projeto> projetos;
}
