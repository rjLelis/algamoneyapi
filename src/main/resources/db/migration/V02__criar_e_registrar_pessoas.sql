CREATE TABLE pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(20),
    numero VARCHAR(10),
    complemento VARCHAR(20),
    bairro VARCHAR(20),
    cep VARCHAR(20),
    cidade VARCHAR(20),
    estado VARCHAR(50)
);

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Renato', true, 'Rua', '27', '-', 'Boa Vista', '50060020', 'Recife', 'Pernambuco');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Yanka', true, 'Rua', '27', '-', 'Boa Vista', '50060020', 'Recife', 'Pernambuco');
INSERT INTO pessoa (nome, ativo) VALUES ('Ricado', true);
INSERT INTO pessoa (nome, ativo, numero, complemento, bairro, cep, cidade, estado) VALUES ('Ana', true, '27', '-', 'Boa Vista', '50060020', 'Recife', 'Pernambuco');
INSERT INTO pessoa (nome, ativo, estado) VALUES ('Rita', true, 'Rio Grande do Norte');
INSERT INTO pessoa (nome, ativo) VALUES ('Geraldo', true);
INSERT INTO pessoa (nome, ativo, cidade) VALUES ('Rayanne', true, 'Olinda');
INSERT INTO pessoa (nome, ativo, logradouro) VALUES ('Roberto', true, 'Rua');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Paulo', true, 'Rua', '27', '-', 'Boa Vista', '50060020', 'Recife', 'Pernambuco');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Marcos', true, 'Rua', '27', '-', 'Boa Vista', '50060020', 'Recife', 'Pernambuco');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Fabio', true, 'Rua', '27', '-', 'Boa Vista', '50060020', 'Recife', 'Pernambuco');


