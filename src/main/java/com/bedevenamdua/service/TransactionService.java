package com.bedevenamdua.service;

import com.bedevenamdua.dto.TransactionData;
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

    public Transaction addTransaction(TransactionData transactionData) {
        return transactionRepository.addTransaction(transactionData.getType(), transactionData.getBusinessId());
    }

    public Iterable<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public Iterable<Transaction> findTransactionsByBusinessId(UUID businessId) {
        return transactionRepository.findByBusinessId(businessId);
    }

    public Transaction findTransactionById(int id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    public Transaction updateTransactionType(int id, String type) {
        Transaction transaction = findTransactionById(id);
        transaction.setType(type);
        return transactionRepository.save(transaction);
    }

    public void deleteTransactionById(int id) {
        transactionRepository.deleteById(id);
    }
}
