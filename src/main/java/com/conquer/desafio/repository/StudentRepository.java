package com.conquer.desafio.repository;

import com.conquer.desafio.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByIdNotIn(List<Integer> ids);
}
