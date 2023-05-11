package com.bedevenamdua.repository;

import com.bedevenamdua.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT * FROM transactions WHERE fk_business_id = ?1", nativeQuery = true)
    Iterable<Transaction> findByBusinessId(UUID businessId);
    @Query(value = "INSERT INTO transactions (type, fk_business_id) VALUES (?1, ?2) RETURNING *", nativeQuery = true)
    Transaction addTransaction(String type, UUID businessId);
}
