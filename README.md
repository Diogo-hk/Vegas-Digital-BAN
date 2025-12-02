# 🎰 Vegas Digital - Sistema de Gerenciamento de Cassino (BAN II)

Este projeto é uma aplicação **Java (Console)** desenvolvida seguindo o padrão de arquitetura **MVC (Model-View-Controller)**. O sistema foi criado como requisito avaliativo para a disciplina de Banco de Dados, simulando o gerenciamento completo de uma rede de cassinos e a interação de jogadores.

## 📋 Sobre o Projeto

O objetivo principal foi criar uma aplicação robusta conectada a um banco de dados relacional (**PostgreSQL**), garantindo a integridade dos dados, o uso correto de chaves estrangeiras e a implementação de regras de negócio complexas via código e SQL.

### 🚀 Funcionalidades

#### 👤 Para o Jogador (Cliente)
* **Cadastro e Login:** Sistema de autenticação.
* **Carteira Digital:** Realizar depósitos, com atualização de saldo em tempo real no banco.
* **Jogar:**
    * Visualizar sessões (mesas) abertas.
    * Realizar apostas em jogos como Roleta.
    * **Sistema de RTP:** O prêmio é calculado dinamicamente baseado na taxa de retorno (RTP) configurada para cada jogo.

#### 🛠️ Para o Administrador (Admin)
* **Gestão de Cassinos:** Cadastrar, listar e remover filiais.
* **Gestão de Jogos:**
    * Cadastro complexo utilizando lógica de herança no banco (Tabela pai `Jogos` + Tabelas filhas `Roleta`, `Poker`, `Blackjack`).
    * Listagem utilizando `LEFT JOIN` para identificar o tipo de jogo.
    * Remoção de jogos.
* **Gestão de Sessões:** Abrir e fechar mesas de apostas vinculadas a um cassino e um jogo.
* **Gestão de Usuários:** Listar todos os usuários e banir (remover) contas.
* **Relatórios:**
    * Histórico global das últimas apostas (`Jogar`).
    * **Relatório Financeiro:** Consulta de agregação (`SUM`) mostrando o patrimônio total de todos os cassinos somados.

---

## ✅ Requisitos Acadêmicos Atendidos

O projeto cumpre 100% dos requisitos solicitados para a avaliação:

- [x] **Mínimo de 5 tabelas relacionadas:** O sistema utiliza `Usuario`, `Cassino`, `Jogos`, `Sessao` e `Jogar` (Tabela Associativa/Histórico).
- [x] **CRUD em TODAS as tabelas:**
    - Funções de **Inserir**, **Listar** e **Remover** implementadas para todas as 5 entidades principais através do Painel Admin e Ações do Jogador.
- [x] **Consulta com Junção (JOIN):**
    - Utilizada na listagem de jogos (unindo tabela pai e filhas).
    - Utilizada na aposta para buscar o RTP do jogo através da sessão.
- [x] **Consulta com Agregação e Subconsulta:**
    - **Agregação:** Cálculo do patrimônio total da rede (`SELECT SUM(banca)...`).
    - **Subconsulta:** Atualização da banca do cassino específico ao registrar uma aposta (`UPDATE ... WHERE id = (SELECT ...)`).

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 21)
* **Banco de Dados:** PostgreSQL
* **Conexão:** JDBC Driver (`postgresql-42.6.0.jar`)
* **IDE:** IntelliJ IDEA
* **Controle de Versão:** Git / GitHub

---

## 🗄️ Modelo de Dados (Estrutura Simplificada)

1.  **Usuario:** `iduser` (PK), `nome`, `cpf`, `email`, `senha`, `saldo`, `status`.
2.  **Cassino:** `idcassino` (PK), `nome`, `cnpj`, `banca`, `cidade`.
3.  **Jogos:** `idjogos` (PK), `taxartp`.
    * *Tabelas Filhas (1:1):* `Roleta`, `Poker`, `Blackjack`.
4.  **Sessao:** `idsessao` (PK), `fk_idcassino` (FK), `fk_idjogos` (FK), `nomesessao`.
5.  **Jogar:** `idsessao` (PK, FK), `idusuario` (PK, FK), `data_hora` (PK), `valorapostado`, `retorn`.

---

## ▶️ Como Executar o Projeto

1.  **Banco de Dados:**
    * Certifique-se de ter o PostgreSQL rodando.
    * Crie um banco de dados chamado `trabalhoban`.
    * Execute o script SQL fornecido (ou restaure o backup) para criar as tabelas.

2.  **Configuração:**
    * Abra o arquivo `src/Util/Conexao.java`.
    * Verifique se o usuário e senha do banco correspondem ao seu ambiente local:
      ```java
      String user = "postgres";
      String senha = "sua_senha_aqui";
      ```

3.  **Bibliotecas:**
    * Adicione o arquivo `.jar` do driver JDBC ao Classpath do seu projeto na IDE.

4.  **Execução:**
    * Rode a classe `src/Main.java`.
    * **Para acesso Admin:** Utilize um usuário no banco que tenha a coluna `status` definida como `'ADMIN'`.

---

## 👥 Autores

* **Diogo**
* **André**
