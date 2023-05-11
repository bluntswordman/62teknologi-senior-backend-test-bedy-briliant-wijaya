package com.bedevenamdua.service;

import com.bedevenamdua.entity.Transaction;
import com.bedevenamdua.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction addTransaction(String type, UUID businessId) {
        return transactionRepository.addTransaction(type, businessId);
    }

    public Iterable<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Iterable<Transaction> findByBusinessId(UUID businessId) {
        return transactionRepository.findByBusinessId(businessId);
    }

    public Transaction findById(int id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    public Transaction updateTransaction(int id, String type) {
        Transaction transaction = findById(id);
        transaction.setType(type);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(int id) {
        transactionRepository.deleteById(id);
    }
}
