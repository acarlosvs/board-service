package br.com.tasknow.boardservice.application.web.controllers;

import br.com.tasknow.boardservice.domain.entities.Board;
import br.com.tasknow.boardservice.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/boards")
public class BoardControllers {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<Board>> getAll() {
        log.info("Acesso ao endpoint GET /boards");
        return ResponseEntity.ok(boardService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Board> save(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.save(board));
    }
}
