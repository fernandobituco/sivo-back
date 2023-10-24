package com.unit.sivo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unit.sivo.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    public Aluno getByEmail(String email);
    public Aluno getById(int id);
}
