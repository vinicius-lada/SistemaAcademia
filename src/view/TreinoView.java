package view;

import controller.TreinoController;
import exceptions.ValidacaoException;
import model.Treino;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LoggerService;

public class TreinoView {
    private final Scanner scanner;
    private final TreinoController treinoController;

    public TreinoView(Scanner scanner, TreinoController treinoController) {
        this.scanner = scanner;
        this.treinoController = treinoController;
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n--- Menu de Treinos ---");
                System.out.println("1 - Cadastrar treino");
                System.out.println("2 - Listar treinos");
                System.out.println("3 - Atualizar treino");
                System.out.println("4 - Remover treino");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarTreino();
                        break;
                    case 2:
                        listarTreinos();
                        break;
                    case 3:
                        atualizarTreino();
                        break;
                    case 4:
                        removerTreino();
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

    private void cadastrarTreino() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Objetivo: ");
            String objetivo = scanner.nextLine();

            System.out.print("Descricao: ");
            String descricao = scanner.nextLine();

            System.out.print("Nivel: ");
            String nivel = scanner.nextLine();

            treinoController.cadastrar(nome, objetivo, descricao, nivel);
            System.out.println("Treino cadastrado com sucesso.");
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao cadastrar treino: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void listarTreinos() {
        ArrayList<Treino> treinos = treinoController.listar();

        if (treinos.isEmpty()) {
            System.out.println("Nenhum treino cadastrado.");
            return;
        }

        System.out.println("\n--- Treinos cadastrados ---");
        for (Treino treino : treinos) {
            treino.exibirDados();
            System.out.println("--------------------");
        }
    }

    private void atualizarTreino() {
        try {
            System.out.print("ID do treino: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            System.out.print("Novo objetivo: ");
            String objetivo = scanner.nextLine();

            System.out.print("Nova descricao: ");
            String descricao = scanner.nextLine();

            System.out.print("Novo nivel: ");
            String nivel = scanner.nextLine();

            boolean atualizado = treinoController.atualizar(id, nome, objetivo, descricao, nivel);

            if (atualizado) {
                System.out.println("Treino atualizado com sucesso.");
            } else {
                System.out.println("Treino nao encontrado.");
            }
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao atualizar treino: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void removerTreino() {
        System.out.print("ID do treino: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removido = treinoController.remover(id);

        if (removido) {
            System.out.println("Treino removido com sucesso.");
        } else {
            System.out.println("Treino nao encontrado.");
        }
    }
}
