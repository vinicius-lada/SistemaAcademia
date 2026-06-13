package view;

import controller.AlunoController;
import exceptions.ValidacaoException;
import model.Aluno;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LoggerService;

public class AlunoView {
    private final Scanner scanner;
    private final AlunoController alunoController;

    public AlunoView(Scanner scanner, AlunoController alunoController) {
        this.scanner = scanner;
        this.alunoController = alunoController;
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n--- Menu de Alunos ---");
                System.out.println("1 - Cadastrar aluno");
                System.out.println("2 - Listar alunos");
                System.out.println("3 - Atualizar aluno");
                System.out.println("4 - Remover aluno");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarAluno();
                        break;
                    case 2:
                        listarAlunos();
                        break;
                    case 3:
                        atualizarAluno();
                        break;
                    case 4:
                        removerAluno();
                        break;
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                        break;
                }
            } catch (InputMismatchException erro) {
                System.out.println("Erro: digite um numero valido.");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private void cadastrarAluno() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            alunoController.cadastrar(nome, cpf, telefone);
            System.out.println("Aluno cadastrado com sucesso.");
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao cadastrar aluno: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void listarAlunos() {
        ArrayList<Aluno> alunos = alunoController.listar();

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n--- Alunos cadastrados ---");
        for (Aluno aluno : alunos) {
            aluno.exibirDados();
            System.out.println("--------------------");
        }
    }

    private void atualizarAluno() {
        try {
            System.out.print("ID do aluno: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            System.out.print("Novo CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Novo telefone: ");
            String telefone = scanner.nextLine();

            boolean atualizado = alunoController.atualizar(id, nome, cpf, telefone);

            if (atualizado) {
                System.out.println("Aluno atualizado com sucesso.");
            } else {
                System.out.println("Aluno nao encontrado.");
            }
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao atualizar aluno: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void removerAluno() {
        System.out.print("ID do aluno: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removido = alunoController.remover(id);

        if (removido) {
            System.out.println("Aluno removido com sucesso.");
        } else {
            System.out.println("Aluno nao encontrado.");
        }
    }
}
