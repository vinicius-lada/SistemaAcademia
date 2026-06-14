package view;

import controller.ProfessorController;
import exceptions.ValidacaoException;
import model.Professor;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LoggerService;

public class ProfessorView {
    private final Scanner scanner;
    private final ProfessorController professorController;

    public ProfessorView(Scanner scanner, ProfessorController professorController) {
        this.scanner = scanner;
        this.professorController = professorController;
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n--- Menu de Professores ---");
                System.out.println("1 - Cadastrar professor");
                System.out.println("2 - Listar professores");
                System.out.println("3 - Atualizar professor");
                System.out.println("4 - Remover professor");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarProfessor();
                        break;
                    case 2:
                        listarProfessores();
                        break;
                    case 3:
                        atualizarProfessor();
                        break;
                    case 4:
                        removerProfessor();
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

    private void cadastrarProfessor() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Especialidade: ");
            String especialidade = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            professorController.cadastrar(nome, cpf, especialidade, telefone);
            System.out.println("Professor cadastrado com sucesso.");
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao cadastrar professor: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void listarProfessores() {
        ArrayList<Professor> professores = professorController.listar();

        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }

        System.out.println("\n--- Professores cadastrados ---");
        for (Professor professor : professores) {
            professor.exibirDados();
            System.out.println("--------------------");
        }
    }

    private void atualizarProfessor() {
        try {
            System.out.print("ID do professor: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            System.out.print("Novo CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Nova especialidade: ");
            String especialidade = scanner.nextLine();

            System.out.print("Novo telefone: ");
            String telefone = scanner.nextLine();

            boolean atualizado = professorController.atualizar(id, nome, cpf, especialidade, telefone);

            if (atualizado) {
                System.out.println("Professor atualizado com sucesso.");
            } else {
                System.out.println("Professor nao encontrado.");
            }
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao atualizar professor: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void removerProfessor() {
        System.out.print("ID do professor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removido = professorController.remover(id);

        if (removido) {
            System.out.println("Professor removido com sucesso.");
        } else {
            System.out.println("Professor nao encontrado.");
        }
    }
}
