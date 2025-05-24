package br.com.tasknow.boardservice.domain.service.impl;

import br.com.tasknow.boardservice.domain.entities.Board;
import br.com.tasknow.boardservice.domain.repository.BoardRepository;
import br.com.tasknow.boardservice.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoadServiceImpl implements BoardService {

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
    public Board save(Board board) {
        return boardRepository.save(board);
    }
}
