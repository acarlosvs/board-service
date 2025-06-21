package br.com.tasknow.boardservice.domain.repository;

import br.com.tasknow.boardservice.domain.entities.Swimlane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwimlineRepository extends CrudRepository<Swimlane, Long> {
    List<Swimlane> getByBoardId(Long boardId);
}
