# Sistema Academia

## Sobre o projeto
Projeto de um sistema de academia feito em Java para o trabalho da faculdade. A ideia do projeto é funcionar como um 
sistema de cadastro de clientes em uma academia, feita por um gerente ou recepção, onde o responsável poderá adicionar
alunos, fazer sua matrícula, escolher um plano e sua forma de pagamento, receber um professor e aulas, que tem
equipamentos e treinos para cada um.

## Integrantes e partes
- Vitória: Aluno e Matrícula
- Vinicius: Aula e Equipamento
- Davi: Professor e Treino
- Kamila: Plano e Pagamento

## Funcionalidades
- Cadastro de todas as classes
- Listagem dos registros
- Atualização dos registros
- Remoção dos registros
- Persistência em arquivos
- Registros de logs

## CRUD 
- O Sistema possui um CRUD completo para todas as classes, com: 
- cadastrar
- listar
- atualizar
- remover

## MVC
O sistema segue uma estrutura de MVC, com main, model, view e controller.

## Conceitos
O sistema utiliza dos seguintes conceitos:
- Classes e objetos
- Encapsulamento
- Construtores
- Getters e Setters
- Exception
- Herança
- Classe abstrata
- Interface
- Polimorfismo
- Associação
- Persistência de dados

## Classes e suas relações
Cada classe possui um CRUD completo, com uma interface principal no Main, para possibilitar o cadastro de todos os recursos de todas as classes.
- Pessoa
  é uma classe abstrata que é herdada por Aluno e Professor.
- Matrícula
  é uma classe que possui associação com Aluno e Plano.

## Logs
O sistema possui registro de Logs na classe LoggerService, que são salvos no data/log.txt

## Como Executar O Projeto
- 1 - Baixe ou clone o repositório.
- 2 - Abra o projeto em uma IDE Java
- 3 - Execute o método main.

## Uso De IA
Algumas ferramentas de IA, como GPT 5.5, Gemini 3.1 e NotebookLM foram utilizadas como apoio para organização estrutural do projeto, revisão de classes, exemplos de CRUD e interfaces, organização de documentos e de sugestão. A IA foi utilizada como APOIO, a equipe foi responsável pela compreensão e construção geral do código e do projeto.
