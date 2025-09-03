package com.Group3tatastrive.VetPawtner.Repository;

import com.Group3tatastrive.VetPawtner.Entity.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundingRepository extends JpaRepository<Funding,Integer> {
}
