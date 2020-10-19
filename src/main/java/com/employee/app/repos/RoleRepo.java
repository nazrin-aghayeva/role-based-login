package com.employee.app.repos;

import com.employee.app.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Integer > {
     Roles findByRole(String role);
}
