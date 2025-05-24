package br.com.tasknow.boardservice.domain.service.impl;

import br.com.tasknow.boardservice.domain.entities.Swimlane;
import br.com.tasknow.boardservice.domain.repository.SwimlineRepository;
import br.com.tasknow.boardservice.domain.service.SwimlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SwimlaneServiceImpl implements SwimlaneService {

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
    public Swimlane save(Swimlane swimlane) {
        return swimlineRepository.save(swimlane);
    }
}
