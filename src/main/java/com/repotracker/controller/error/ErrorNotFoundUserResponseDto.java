package com.repotracker.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorNotFoundUserResponseDto(HttpStatus status, String message) {
}
