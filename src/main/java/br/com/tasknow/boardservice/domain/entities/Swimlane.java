package br.com.tasknow.boardservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("swimlanes_tb")
public class Swimlane {
    @Id
    private Long id;

    private String name;

    @Column("board_id")
    private Long boardId;
}
