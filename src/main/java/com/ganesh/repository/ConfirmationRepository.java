package com.ganesh.repository;

import com.ganesh.entity.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationRepository, Long> {

    Confirmation findByToken(String token);
}
