package com.unit.sivo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unit.sivo.models.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    
    public Curso getById(int id);
}
