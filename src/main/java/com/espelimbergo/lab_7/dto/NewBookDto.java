package com.espelimbergo.lab_7.dto;

public record NewBookDto(
        String title,
        int pages,
        Long authId
) {
}