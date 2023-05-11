package com.bedevenamdua.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionData {
    private UUID businessId;
    private String type;
}
