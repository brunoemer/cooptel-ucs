CREATE TABLE colaborador (
 id INT NOT NULL,
 cpf CHAR(11),
 nome VARCHAR(250),
 endereco VARCHAR(250),
 email VARCHAR(100),
 ativo INT
);

ALTER TABLE colaborador ADD CONSTRAINT PK_colaborador PRIMARY KEY (id);


CREATE TABLE acomodacao (
 id INT NOT NULL,
 id_colaborador INT NOT NULL,
 tipo INT,
 cafe INT,
 endereco VARCHAR(250),
 latitude DOUBLE PRECISION,
 longitude DOUBLE PRECISION,
 foto1 VARCHAR(250),
 foto2 VARCHAR(250),
 valordiario FLOAT(10),
 descricao VARCHAR(250)
);

ALTER TABLE acomodacao ADD CONSTRAINT PK_acomodacao PRIMARY KEY (id,id_colaborador);


ALTER TABLE acomodacao ADD CONSTRAINT FK_acomodacao_0 FOREIGN KEY (id_colaborador) REFERENCES colaborador (id);


