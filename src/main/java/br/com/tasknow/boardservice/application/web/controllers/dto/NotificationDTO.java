package br.com.tasknow.boardservice.application.web.controllers.dto;

import lombok.Builder;

@Builder
public record NotificationDTO(String title, String message, String recipient) {
}
