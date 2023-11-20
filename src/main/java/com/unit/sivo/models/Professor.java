package com.unit.sivo.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_professor")
@Getter
@Setter
public class Professor extends BaseEntity {
    
    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "matricula")
    private String matricula;

    @OneToMany(mappedBy = "professor")
    @JsonManagedReference
    private Set<Projeto> projetos;

    @ManyToMany
    @JoinTable(
      name = "tb_professor_disciplinas", 
      joinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "id"))
    private Set<Disciplina> disciplinas;
}
