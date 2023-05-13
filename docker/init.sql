CREATE TABLE pessoa (
    id bigserial NOT NULL,
    nome VARCHAR(200) NOT NULL,
    CONSTRAINT pk_pessoa PRIMARY KEY (id)
);

CREATE TABLE projeto (
    id bigserial NOT NULL,
    nome VARCHAR(200) NOT NULL,
    data_inicio DATE,
    data_previsao_fim DATE,
    data_fim DATE,
    descricao VARCHAR(5000),
    status VARCHAR(45),
    orcamento FLOAT,
    risco VARCHAR(45),
    idgerente bigserial NOT NULL,
    CONSTRAINT pk_projeto PRIMARY KEY (id),
    CONSTRAINT fk_gerente FOREIGN KEY (idgerente)
        REFERENCES pessoa (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE membros (
    idprojeto bigserial NOT NULL,
    idpessoa bigint NOT NULL,
    CONSTRAINT pk_membros_projeto PRIMARY KEY (idprojeto),
    CONSTRAINT fk_membros_pessoa FOREIGN KEY (idpessoa)
        REFERENCES projeto (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_pessoa FOREIGN KEY (idpessoa)
        REFERENCES pessoa (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
