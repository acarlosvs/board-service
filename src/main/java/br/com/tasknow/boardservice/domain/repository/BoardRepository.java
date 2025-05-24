package br.com.tasknow.boardservice.domain.repository;

import br.com.tasknow.boardservice.domain.entities.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {
}
