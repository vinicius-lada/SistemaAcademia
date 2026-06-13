package view;

import controller.MatriculaController;
import exceptions.ValidacaoException;
import model.Matricula;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LoggerService;

public class MatriculaView {
    private final Scanner scanner;
    private final MatriculaController matriculaController;

    public MatriculaView(Scanner scanner, MatriculaController matriculaController) {
        this.scanner = scanner;
        this.matriculaController = matriculaController;
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n--- Menu de Matriculas ---");
                System.out.println("1 - Cadastrar matricula");
                System.out.println("2 - Listar matriculas");
                System.out.println("3 - Atualizar matricula");
                System.out.println("4 - Remover matricula");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarMatricula();
                        break;
                    case 2:
                        listarMatriculas();
                        break;
                    case 3:
                        atualizarMatricula();
                        break;
                    case 4:
                        removerMatricula();
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

    private void cadastrarMatricula() {
        try {
            System.out.print("Nome do aluno: ");
            String nomeAluno = scanner.nextLine();

            System.out.print("Plano: ");
            String plano = scanner.nextLine();

            System.out.print("Data da matricula: ");
            String dataMatricula = scanner.nextLine();

            System.out.print("Ativa (s/n): ");
            boolean ativa = lerAtiva();

            matriculaController.cadastrar(nomeAluno, plano, dataMatricula, ativa);
            System.out.println("Matricula cadastrada com sucesso.");
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao cadastrar matricula: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void listarMatriculas() {
        ArrayList<Matricula> matriculas = matriculaController.listar();

        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matricula cadastrada.");
            return;
        }

        System.out.println("\n--- Matriculas cadastradas ---");
        for (Matricula matricula : matriculas) {
            matricula.exibirDados();
            System.out.println("--------------------");
        }
    }

    private void atualizarMatricula() {
        try {
            System.out.print("ID da matricula: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo nome do aluno: ");
            String nomeAluno = scanner.nextLine();

            System.out.print("Novo plano: ");
            String plano = scanner.nextLine();

            System.out.print("Nova data da matricula: ");
            String dataMatricula = scanner.nextLine();

            System.out.print("Ativa (s/n): ");
            boolean ativa = lerAtiva();

            boolean atualizada = matriculaController.atualizar(id, nomeAluno, plano, dataMatricula, ativa);

            if (atualizada) {
                System.out.println("Matricula atualizada com sucesso.");
            } else {
                System.out.println("Matricula nao encontrada.");
            }
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao atualizar matricula: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void removerMatricula() {
        System.out.print("ID da matricula: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removida = matriculaController.remover(id);

        if (removida) {
            System.out.println("Matricula removida com sucesso.");
        } else {
            System.out.println("Matricula nao encontrada.");
        }
    }

    private boolean lerAtiva() {
        String resposta = scanner.nextLine();
        return resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim");
    }
}
