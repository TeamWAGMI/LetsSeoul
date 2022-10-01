package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.Hotuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotuserRepository extends JpaRepository<Hotuser, Long> {

}
