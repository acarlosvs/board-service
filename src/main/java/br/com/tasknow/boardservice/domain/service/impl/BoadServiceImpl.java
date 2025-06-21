package br.com.tasknow.boardservice.domain.service.impl;

import br.com.tasknow.boardservice.application.web.controllers.dto.NotificationDTO;
import br.com.tasknow.boardservice.domain.entities.Board;
import br.com.tasknow.boardservice.domain.repository.BoardRepository;
import br.com.tasknow.boardservice.domain.service.BoardService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoadServiceImpl implements BoardService {

    private final WebClient webClient;
    private final BoardRepository boardRepository;

    @Override
    public List<Board> getAll() {
        return (List<Board>) boardRepository.findAll();
    }

    @Override
    public Board getById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quadro não encontrado."));
    }

    @Override
    @Retry(name = "retrySaveBoard", fallbackMethod = "fallbackMethod")
    @CircuitBreaker(name = "circuitBreakerSaveBoard", fallbackMethod = "fallbackMethod")
    public Board save(Board board) {
        board = boardRepository.save(board);

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

        return board;
    }

    private void fallback(Exception ex) {
        log.error("Resposta alternativa (fallback): " + ex.getMessage());
    }
}
