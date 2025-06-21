package br.com.tasknow.boardservice.domain.service.impl;

import br.com.tasknow.boardservice.application.web.controllers.dto.NotificationDTO;
import br.com.tasknow.boardservice.domain.entities.Swimlane;
import br.com.tasknow.boardservice.domain.repository.SwimlineRepository;
import br.com.tasknow.boardservice.domain.service.SwimlaneService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SwimlaneServiceImpl implements SwimlaneService {

    private final WebClient webClient;
    private final SwimlineRepository swimlineRepository;

    @Override
    public List<Swimlane> getAll() {
        return (List<Swimlane>) swimlineRepository.findAll();
    }

    @Override
    public Swimlane getById(Long id) {
        return swimlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raia não encontrada."));
    }

    @Override
    @Cacheable(cacheNames = "swimlinesByBoard", key = "#swimlaneId")
    public List<Swimlane> getByBoardId(Long boardId) {
        return swimlineRepository.getByBoardId(boardId);
    }

    @Override
    @Retry(name = "retrySaveSwimlane", fallbackMethod = "fallbackMethod")
    @CircuitBreaker(name = "circuitBreakerSaveSwimlane", fallbackMethod = "fallbackMethod")
    public Swimlane save(Swimlane swimlane) {
        swimlane = swimlineRepository.save(swimlane);

        webClient.post()
                .uri("notificacao")
                .bodyValue(NotificationDTO
                        .builder()
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new RuntimeException("Erro 4xx"))
                )
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Erro 5xx"))
                );

        return swimlane;
    }

    private void fallback(Exception ex) {
        log.error("Resposta alternativa (fallback): " + ex.getMessage());
    }
}
