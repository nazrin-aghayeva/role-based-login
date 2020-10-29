package com.employee.app.repos;

import com.employee.app.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepo  extends JpaRepository<ConfirmationToken, String> {
  ConfirmationToken findByConfirmToken(String confirmationToken);
}
