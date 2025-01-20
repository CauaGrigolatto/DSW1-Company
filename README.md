# Sistema de Gerenciamento de Pedidos

## Autor
- **Nome:** Cauã Grigolatto Domingos  
- **Prontuário:** AQ3022323  

## Descrição do Projeto
Este projeto foi desenvolvido como parte do exercício avaliativo 2 da disciplina **Desenvolvimento de Software para Web 1 - ARQDSW1** do curso de **CST em Análise e Desenvolvimento de Sistemas**. A aplicação web gerencia pedidos de uma empresa, permitindo que usuários autenticados realizem operações como cadastro, manutenção e consulta de pedidos, além de gerarem relatórios específicos.

## Funcionalidades

### 1. Página Inicial
- **index.jsp**: Página de login que permite o acesso ao sistema.

### 2. Funcionalidades para Usuários Autenticados
- **Manutenção de Pedidos**:
  - Incluir, deletar, alterar e consultar pedidos.
- **Gerar Relatórios**:
  - Relatório de todos os pedidos.
  - Relatório de pedidos de um cliente específico.
- **Gerenciamento de Usuários**:
  - Cadastro de novos usuários.
- **Autenticação**:
  - Login e logout com controle de sessão.

## Arquitetura e Padrões de Projeto Utilizados
O projeto foi desenvolvido seguindo a arquitetura **MVC (Model-View-Controller)** e os seguintes padrões de projeto:

1. **Front Controller**: Centralização do controle das requisições.
2. **Command**: Organização e encapsulamento das ações do sistema.
3. **DAO (Data Access Object)**: Abstração do acesso ao banco de dados.
4. **Factory**: Criação de instâncias de DAO.

## Tecnologias Utilizadas
- **Linguagem de Programação**: Java (Servlets e JSP)
- **IDE**: Eclipse IDE
- **Banco de Dados**: MySQL
- **Servidor de Aplicação**: Apache Tomcat

## Requisitos para Execução
1. **Ambiente de Desenvolvimento**:
   - Eclipse IDE configurado com Apache Tomcat.
2. **Banco de Dados**:
   - MySQL instalado e configurado.
   - Criação do banco de dados seguindo o esquema sugerido no projeto.
3. **Clonagem do Repositório**:
   - Clone o repositório deste projeto para o seu ambiente local utilizando:
     ```bash
     git clone https://github.com/CauaGrigolatto/DSW1-Company.git
     ```

## Configuração e Execução do Projeto
1. Configure o banco de dados e insira as credenciais no arquivo de configuração do Connection Factory.
2. Importe o projeto no Eclipse IDE.
3. Configure o servidor Tomcat no Eclipse e associe o projeto a ele.
4. Execute o projeto no servidor e acesse pelo navegador na URL: `http://localhost:8080/company`.

## Estrutura do Projeto
- **Model**: Classes responsáveis pela lógica de negócio e comunicação com o banco de dados (DAO).
- **View**: Páginas JSP para interação com o usuário.
- **Controller**: Servlets e classes responsáveis pelo controle das requisições e chamadas ao modelo.
- **Filtros**: Controle de autenticação e acesso às áreas restritas do sistema.

## Estrutura do Banco de Dados
```sql
CREATE SCHEMA company;
USE company;

CREATE TABLE users (
  user_id INTEGER AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  identifier VARCHAR(100) NOT NULL
);

-- Usuário padrão do sistema
INSERT INTO users (username, identifier) VALUES ('admin', 'admin');

CREATE TABLE orders (
  order_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  order_description TEXT NOT NULL,
  price DOUBLE NOT NULL,
  client_name VARCHAR(255) NOT NULL,
  client_address VARCHAR(255) NOT NULL,
  user_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);
```

## Critérios de Avaliação Atendidos
- Implementação dos padrões de projeto solicitados.
- Funcionalidade completa de manutenção de pedidos.
- Controle de autenticação funcional com sessões de login e logout.
- Código organizado e estruturado.

## Licença
Este projeto foi desenvolvido exclusivamente para fins acadêmicos e não deve ser utilizado para outros propósitos sem autorização.

---
**Prof. Ednilson Geraldo Rossi**  
Email: ednilsonrossi@ifsp.edu.br
