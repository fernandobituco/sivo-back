package com.unit.sivo.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.unit.sivo.viewModels.ProjetoViewModel;

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
@Table(name = "tb_projeto")
@Getter
@Setter
@NoArgsConstructor
public class Projeto extends BaseEntity {
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "cronograma")
    private String cronograma;
    
    @Column(name = "situacao")
    private int situacao;
    
    @Column(name = "vagas")
    private int vagas;
    
    // 1 = ABERTO
    // 2 = INICIADO
    // 3 = ENCERRADO
    // 4 = CANCELADO
    @Column(name = "duracao")
    private int duracao;

    @ManyToOne
    @JoinColumn(name="professor_id", nullable=false)
    @JsonManagedReference
    private Professor professor;

    @ManyToMany
    @JoinTable(
      name = "tb_projeto_disciplinas", 
      joinColumns = @JoinColumn(name = "projeto_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "id"))
    private Set<Disciplina> disciplinas;

    @ManyToMany
    @JoinTable(
      name = "tb_projeto_alunos", 
      joinColumns = @JoinColumn(name = "projeto_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id"))
    private Set<Aluno> alunos;

    public void addDisciplina(Disciplina disciplina) {
      this.disciplinas.add(disciplina);
    }

    public void removeAluno(Aluno aluno) {
      this.alunos.remove(aluno);
    }

    public void addAluno(Aluno aluno) {
      this.alunos.add(aluno);
    }

    public void removeDisciplina(Disciplina disciplina) {
      this.disciplinas.remove(disciplina);
    }

    public Projeto(ProjetoViewModel projeto) {
      this.nome = projeto.getNome();
      this.descricao = projeto.getDescricao();
      this.cronograma = projeto.getCronograma();
      this.situacao = projeto.getSituacao();
      this.vagas = projeto.getVagas();
      this.duracao = projeto.getDuracao();
      this.disciplinas = new HashSet<Disciplina>();
      this.alunos = new HashSet<Aluno>();
      this.situacao = 1;  
    }
}
