-- Criação da tabela boards_tb
CREATE TABLE boards_tb (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Criação da tabela swimlanes_tb
CREATE TABLE swimlanes_tb (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    board_id BIGINT NOT NULL,
    FOREIGN KEY (board_id) REFERENCES boards_tb(id) ON DELETE CASCADE
);
