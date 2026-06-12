package view;

import controller.AulaController;
import exceptions.ValidacaoException;
import model.Aula;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LoggerService;

public class AulaView {
    private final Scanner scanner;
    private final AulaController aulaController;

    public AulaView(Scanner scanner, AulaController aulaController) {
        this.scanner = scanner;
        this.aulaController = aulaController;
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n--- Menu de Aulas ---");
                System.out.println("1 - Cadastrar aula");
                System.out.println("2 - Listar aulas");
                System.out.println("3 - Atualizar aula");
                System.out.println("4 - Remover aula");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarAula();
                        break;
                    case 2:
                        listarAulas();
                        break;
                    case 3:
                        atualizarAula();
                        break;
                    case 4:
                        removerAula();
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

    private void cadastrarAula() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Professor: ");
            String professor = scanner.nextLine();

            System.out.print("Horario: ");
            String horario = scanner.nextLine();

            System.out.print("Capacidade: ");
            int capacidade = scanner.nextInt();
            scanner.nextLine();

            aulaController.cadastrar(nome, professor, horario, capacidade);
            System.out.println("Aula cadastrada com sucesso.");
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao cadastrar aula: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void listarAulas() {
        ArrayList<Aula> aulas = aulaController.listar();

        if (aulas.isEmpty()) {
            System.out.println("Nenhuma aula cadastrada.");
            return;
        }

        System.out.println("\n--- Aulas cadastradas ---");
        for (Aula aula : aulas) {
            aula.exibirDados();
            System.out.println("--------------------");
        }
    }

    private void atualizarAula() {
        try {
            System.out.print("ID da aula: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            System.out.print("Novo professor: ");
            String professor = scanner.nextLine();

            System.out.print("Novo horario: ");
            String horario = scanner.nextLine();

            System.out.print("Nova capacidade: ");
            int capacidade = scanner.nextInt();
            scanner.nextLine();

            boolean atualizada = aulaController.atualizar(id, nome, professor, horario, capacidade);

            if (atualizada) {
                System.out.println("Aula atualizada com sucesso.");
            } else {
                System.out.println("Aula nao encontrada.");
            }
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao atualizar aula: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void removerAula() {
        System.out.print("ID da aula: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removida = aulaController.remover(id);

        if (removida) {
            System.out.println("Aula removida com sucesso.");
        } else {
            System.out.println("Aula nao encontrada.");
        }
    }
}
