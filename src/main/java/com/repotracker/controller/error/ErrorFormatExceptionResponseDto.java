package com.repotracker.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorFormatExceptionResponseDto(HttpStatus status, String message) {
}
