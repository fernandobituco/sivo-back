package com.unit.sivo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unit.sivo.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    public Professor getByEmail(String email);

    public Professor getById(int id);
}
