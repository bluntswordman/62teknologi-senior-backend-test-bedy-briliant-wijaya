package com.bedevenamdua.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryData {
    private UUID businessId;
    private String alias;
    private String title;
}
