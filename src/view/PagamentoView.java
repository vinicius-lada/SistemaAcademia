package view;

import controller.PagamentoController;
import exceptions.ValidacaoException;
import model.Pagamento;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LoggerService;

public class PagamentoView {
    private final Scanner scanner;
    private final PagamentoController pagamentoController;

    public PagamentoView(Scanner scanner, PagamentoController pagamentoController) {
        this.scanner = scanner;
        this.pagamentoController = pagamentoController;
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n--- Menu de Pagamentos ---");
                System.out.println("1 - Cadastrar pagamento");
                System.out.println("2 - Listar pagamentos");
                System.out.println("3 - Atualizar pagamento");
                System.out.println("4 - Remover pagamento");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarPagamento();
                        break;
                    case 2:
                        listarPagamentos();
                        break;
                    case 3:
                        atualizarPagamento();
                        break;
                    case 4:
                        removerPagamento();
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

    private void cadastrarPagamento() {
        try {
            System.out.print("Nome do aluno: ");
            String nomeAluno = scanner.nextLine();

            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Forma de pagamento: ");
            String formaPagamento = scanner.nextLine();

            System.out.print("Pago (s/n): ");
            boolean pago = lerPago();

            pagamentoController.cadastrar(nomeAluno, valor, formaPagamento, pago);
            System.out.println("Pagamento cadastrado com sucesso.");
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao cadastrar pagamento: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void listarPagamentos() {
        ArrayList<Pagamento> pagamentos = pagamentoController.listar();

        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento cadastrado.");
            return;
        }

        System.out.println("\n--- Pagamentos cadastrados ---");
        for (Pagamento pagamento : pagamentos) {
            pagamento.exibirDados();
            System.out.println("--------------------");
        }
    }

    private void atualizarPagamento() {
        try {
            System.out.print("ID do pagamento: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo nome do aluno: ");
            String nomeAluno = scanner.nextLine();

            System.out.print("Novo valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Nova forma de pagamento: ");
            String formaPagamento = scanner.nextLine();

            System.out.print("Pago (s/n): ");
            boolean pago = lerPago();

            boolean atualizado = pagamentoController.atualizar(id, nomeAluno, valor, formaPagamento, pago);

            if (atualizado) {
                System.out.println("Pagamento atualizado com sucesso.");
            } else {
                System.out.println("Pagamento nao encontrado.");
            }
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao atualizar pagamento: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void removerPagamento() {
        System.out.print("ID do pagamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removido = pagamentoController.remover(id);

        if (removido) {
            System.out.println("Pagamento removido com sucesso.");
        } else {
            System.out.println("Pagamento nao encontrado.");
        }
    }

    private boolean lerPago() {
        String resposta = scanner.nextLine();
        return resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim");
    }
}
