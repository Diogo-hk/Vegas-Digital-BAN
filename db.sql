CREATE TABLE Usuario (
    idUser INT PRIMARY KEY,
    nomeUsuario VARCHAR(100) NOT NULL,
    CPF CHAR(11) UNIQUE,
    Email VARCHAR(150) UNIQUE,
    Senha VARCHAR(255) NOT NULL,
    Status VARCHAR(20) NOT NULL,
    Saldo DECIMAL(15,2) NOT NULL
);

CREATE TABLE Cassino (
    idCassino INT PRIMARY KEY,
    CNPJ VARCHAR(60) NOT NULL UNIQUE,
    nome VARCHAR(60) NOT NULL,
    cep VARCHAR(12) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    banca DECIMAL(15,2) NOT NULL
);

CREATE TABLE Jogos (
    idJogos INT PRIMARY KEY,
    taxaRTP DECIMAL(6,2) NOT NULL i
);

CREATE TABLE Roleta (
    idJogos INT PRIMARY KEY,
    tipoRoleta VARCHAR(30) NOT NULL,
    CONSTRAINT fk_roleta_jogos FOREIGN KEY (idJogos) REFERENCES Jogos(idJogos)
);

CREATE TABLE Blackjack (
    idJogos INT PRIMARY KEY,
    quantidadeBaralhos INT NOT NULL,
    CONSTRAINT fk_blackjack_jogos FOREIGN KEY (idJogos) REFERENCES Jogos(idJogos)
);

CREATE TABLE Poker (
    idJogos INT PRIMARY KEY,
    tipoPoker INT NOT NULL,
    CONSTRAINT fk_poker_jogos FOREIGN KEY (idJogos) REFERENCES Jogos(idJogos)
);

CREATE TABLE Sessao (
    idSessao INT PRIMARY KEY,
    idJogos INT,
    idCassino INT,
    nomeSessao VARCHAR(60) NOT NULL,
    CONSTRAINT fk_sessao_jogos FOREIGN KEY (idJogos) REFERENCES Jogos(idJogos),
    CONSTRAINT fk_sessao_cassino FOREIGN KEY (idCassino) REFERENCES Cassino(idCassino)
);

CREATE TABLE Jogar (
    idSessao INT,
    idUsuario INT,
    valorApostado DECIMAL(15,2) NOT NULL,
    retorno DECIMAL(15,2) NOT NULL,
    dataHora DATE NOT NULL,
    PRIMARY KEY (idSessao, idUsuario),
    CONSTRAINT fk_jogar_sessao FOREIGN KEY (idSessao) REFERENCES Sessao(idSessao),
    CONSTRAINT fk_jogar_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario(idUser)
);


CREATE TABLE Funcionario (
    idFunc INT PRIMARY KEY,
    idSessao INT,
    idCassino INT,
    nome VARCHAR(60) NOT NULL,
    cpf VARCHAR(60) NOT NULL UNIQUE,
    CONSTRAINT fk_funcionario_sessao FOREIGN KEY (idSessao) REFERENCES Sessao(idSessao),
    CONSTRAINT fk_funcionario_cassino FOREIGN KEY (idCassino) REFERENCES Cassino(idCassino)
);


CREATE TABLE PagamentoSalario (
    idPag INT PRIMARY KEY,
    idCassino INT,
    idFunc INT,
    valor DECIMAL(15,2) NOT NULL,
    dataPagamento DATE NOT NULL,
    CONSTRAINT fk_pagamento_cassino FOREIGN KEY (idCassino) REFERENCES Cassino(idCassino),
    CONSTRAINT fk_pagamento_func FOREIGN KEY (idFunc) REFERENCES Funcionario(idFunc)
);

CREATE TABLE MovimentacaoFinanceira (
    idMovimentacao INT PRIMARY KEY,
    idCassino INT,
    idUsuario INT,
    tipoTransacao VARCHAR(30) NOT NULL,
    valor DECIMAL(15,2) NOT NULL,
    dataHora DATE NOT NULL,
    metodoPagamento VARCHAR(30) NOT NULL,
    CONSTRAINT fk_movimentacao_cassino FOREIGN KEY (idCassino) REFERENCES Cassino(idCassino),
    CONSTRAINT fk_movimentacao_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario(idUser)
);