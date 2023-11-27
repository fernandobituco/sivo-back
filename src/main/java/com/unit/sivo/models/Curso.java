package com.unit.sivo.models;
import java.util.Set;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.unit.sivo.viewModels.CursoViewModel;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_curso")
@Getter
@Setter
@NoArgsConstructor
public class Curso extends BaseEntity {

    @OneToMany(mappedBy = "curso")
    @JsonManagedReference
    private Set<Aluno> alunos;

    @ManyToMany
    @JoinTable(
      name = "tb_curso_disciplinas", 
      joinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "id"))
    private Set<Disciplina> disciplinas;

    public Curso(CursoViewModel curso) {
        this.alunos = new HashSet<Aluno>();
        this.disciplinas = new HashSet<Disciplina>();
        this.setNome(curso.getNome());
    }

    public void addDisciplina(Disciplina disciplina) {
      this.disciplinas.add(disciplina);
    }

    public void removeDisciplina(Disciplina disciplina) {
      this.disciplinas.remove(disciplina);
    }
}
