package view;

import controller.EquipamentoController;
import exceptions.ValidacaoException;
import model.Equipamento;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LoggerService;

public class EquipamentoView {
    private final Scanner scanner;
    private final EquipamentoController equipamentoController;

    public EquipamentoView(Scanner scanner, EquipamentoController equipamentoController) {
        this.scanner = scanner;
        this.equipamentoController = equipamentoController;
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n--- Menu de Equipamentos ---");
                System.out.println("1 - Cadastrar equipamento");
                System.out.println("2 - Listar equipamentos");
                System.out.println("3 - Atualizar equipamento");
                System.out.println("4 - Remover equipamento");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarEquipamento();
                        break;
                    case 2:
                        listarEquipamentos();
                        break;
                    case 3:
                        atualizarEquipamento();
                        break;
                    case 4:
                        removerEquipamento();
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

    private void cadastrarEquipamento() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Disponivel (s/n): ");
            boolean disponivel = lerDisponibilidade();

            equipamentoController.cadastrar(nome, quantidade, disponivel);
            System.out.println("Equipamento cadastrado com sucesso.");
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao cadastrar equipamento: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void listarEquipamentos() {
        ArrayList<Equipamento> equipamentos = equipamentoController.listar();

        if (equipamentos.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado.");
            return;
        }

        System.out.println("\n--- Equipamentos cadastrados ---");
        for (Equipamento equipamento : equipamentos) {
            equipamento.exibirDados();
            System.out.println("--------------------");
        }
    }

    private void atualizarEquipamento() {
        try {
            System.out.print("ID do equipamento: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            System.out.print("Nova quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Disponivel (s/n): ");
            boolean disponivel = lerDisponibilidade();

            boolean atualizado = equipamentoController.atualizar(id, nome, quantidade, disponivel);

            if (atualizado) {
                System.out.println("Equipamento atualizado com sucesso.");
            } else {
                System.out.println("Equipamento nao encontrado.");
            }
        } catch (ValidacaoException erro) {
            LoggerService.log("ERROR", "Erro ao atualizar equipamento: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void removerEquipamento() {
        System.out.print("ID do equipamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removido = equipamentoController.remover(id);

        if (removido) {
            System.out.println("Equipamento removido com sucesso.");
        } else {
            System.out.println("Equipamento nao encontrado.");
        }
    }

    private boolean lerDisponibilidade() {
        String resposta = scanner.nextLine();
        return resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim");
    }
}
