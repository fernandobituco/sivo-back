package com.unit.sivo.models;


import java.util.List;

import jakarta.persistence.Column;
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
    
    @Column(name = "nome")
    private String nome;

    @ManyToMany
    private List<Projeto> projetos;
}
