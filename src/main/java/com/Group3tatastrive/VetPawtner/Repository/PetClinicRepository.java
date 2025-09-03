package com.Group3tatastrive.VetPawtner.Repository;

import com.Group3tatastrive.VetPawtner.Entity.PetClinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetClinicRepository extends JpaRepository<PetClinic, Long> {
}
