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


ALTER TABLE `colaborador` CHANGE `id` `id` INT( 11 ) NOT NULL AUTO_INCREMENT;
ALTER TABLE `acomodacao` CHANGE `id` `id` INT( 11 ) NOT NULL AUTO_INCREMENT;

CREATE TABLE disponibilidade (
  id INT NOT NULL AUTO_INCREMENT,
  id_acomodacao INT NOT NULL,
  datainicio DATETIME NOT NULL,
  datafim DATETIME NOT NULL,
  CONSTRAINT PK_disponibilidade PRIMARY KEY (id,id_acomodacao)
);

ALTER TABLE disponibilidade ADD CONSTRAINT FK_disponibilidade_0 FOREIGN KEY (id_acomodacao) REFERENCES acomodacao (id);