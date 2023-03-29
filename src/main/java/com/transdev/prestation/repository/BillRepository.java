package com.transdev.prestation.repository;

import com.transdev.prestation.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
