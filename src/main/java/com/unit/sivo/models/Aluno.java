package com.unit.sivo.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unit.sivo.viewModels.AlunoViewModel;
import com.unit.sivo.viewModels.ProfessorViewModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_aluno")
@Getter
@Setter
@NoArgsConstructor
public class Aluno extends BaseEntity {
    
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "matricula", unique = true)
    private String matricula;

    @ManyToOne
    @JoinColumn(name="curso_id", nullable=false)
    @JsonBackReference
    private Curso curso;

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

    public void addDisciplina(Disciplina disciplina) {
      this.disciplinas.add(disciplina);
    }

    public void removeDisciplina(Disciplina disciplina) {
      this.disciplinas.remove(disciplina);
    }

    public Aluno(AlunoViewModel professor) {
      this.setNome(professor.getNome());
      this.email = professor.getEmail();
      this.senha = professor.getSenha();
      this.matricula = professor.getMatricula();
      this.disciplinas = new HashSet<Disciplina>();
    }
}
