package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
