package com.unit.sivo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unit.sivo.models.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    public Projeto getById(int id);

    public List<Projeto> getByProfessorId(int id);

    public List<Projeto> getByAlunosId(int id);
}
