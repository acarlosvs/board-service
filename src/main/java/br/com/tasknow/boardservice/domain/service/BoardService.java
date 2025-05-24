package br.com.tasknow.boardservice.domain.service;

import br.com.tasknow.boardservice.domain.entities.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAll();
    Board getById(Long id);
    Board save(Board board);
}
