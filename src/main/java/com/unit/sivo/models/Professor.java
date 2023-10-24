package com.unit.sivo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_professor")
@Getter
@Setter
public class Professor extends BaseEntity {
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "matricula")
    private String matricula;

    @OneToMany(mappedBy = "professor")
    @JsonManagedReference
    private List<Projeto> projetos;
}
