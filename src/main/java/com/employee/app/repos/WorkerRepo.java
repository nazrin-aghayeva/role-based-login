package com.employee.app.repos;

import com.employee.app.entities.Workers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepo extends JpaRepository<Workers, Integer> {
    Optional<Workers> findByFull_name (String worker_name);
}
