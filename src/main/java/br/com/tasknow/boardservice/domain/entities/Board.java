package br.com.tasknow.boardservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("boards_tb")
public class Board {
    @Id
    private Long id;
    private String name;
    private List<Swimlane> swimlanes;
}
