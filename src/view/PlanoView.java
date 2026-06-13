package view;

import controller.PlanoController;
import exceptions.ValidacaoException;
import model.Plano;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LoggerService;

public class PlanoView {
    private final Scanner scanner;
    private final PlanoController planoController;

    public PlanoView(Scanner scanner, PlanoController planoController) {
        this.scanner = scanner;
        this.planoController = planoController;
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n--- Menu de Planos ---");
                System.out.println("1 - Cadastrar plano");
                System.out.println("2 - Listar planos");
                System.out.println("3 - Atualizar plano");
                System.out.println("4 - Remover plano");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarPlano();
                        break;
                    case 2:
                        listarPlanos();
                        break;
                    case 3:
                        atualizarPlano();
                        break;
                    case 4:
                        removerPlano();
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

    private void cadastrarPlano() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Duracao em meses: ");
            int duracaoMeses = scanner.nextInt();
            scanner.nextLine();

            planoController.cadastrar(nome, valor, duracaoMeses);
            System.out.println("Plano cadastrado com sucesso.");
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao cadastrar plano: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void listarPlanos() {
        ArrayList<Plano> planos = planoController.listar();

        if (planos.isEmpty()) {
            System.out.println("Nenhum plano cadastrado.");
            return;
        }

        System.out.println("\n--- Planos cadastrados ---");
        for (Plano plano : planos) {
            plano.exibirDados();
            System.out.println("--------------------");
        }
    }

    private void atualizarPlano() {
        try {
            System.out.print("ID do plano: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            System.out.print("Novo valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Nova duracao em meses: ");
            int duracaoMeses = scanner.nextInt();
            scanner.nextLine();

            boolean atualizado = planoController.atualizar(id, nome, valor, duracaoMeses);

            if (atualizado) {
                System.out.println("Plano atualizado com sucesso.");
            } else {
                System.out.println("Plano nao encontrado.");
            }
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao atualizar plano: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void removerPlano() {
        System.out.print("ID do plano: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removido = planoController.remover(id);

        if (removido) {
            System.out.println("Plano removido com sucesso.");
        } else {
            System.out.println("Plano nao encontrado.");
        }
    }
}
