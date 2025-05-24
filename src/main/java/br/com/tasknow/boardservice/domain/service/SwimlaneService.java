package br.com.tasknow.boardservice.domain.service;

import br.com.tasknow.boardservice.domain.entities.Swimlane;

import java.util.List;

public interface SwimlaneService {
    List<Swimlane> getAll();
    Swimlane getById(Long id);
    Swimlane save(Swimlane swimlane);
}
