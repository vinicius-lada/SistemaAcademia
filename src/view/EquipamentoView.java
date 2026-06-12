package view;

import controller.EquipamentoController;
import exceptions.ValidacaoException;
import model.Equipamento;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LogUtil;

public class EquipamentoView {
    private final Scanner scanner;
    private final EquipamentoController equipamentoController;

    public EquipamentoView(Scanner scanner, EquipamentoController equipamentoController) {
        this.scanner = scanner;
        this.equipamentoController = equipamentoController;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n--- Menu de Equipamentos ---");
            System.out.println("1 - Cadastrar equipamento");
            System.out.println("2 - Listar equipamentos");
            System.out.println("3 - Atualizar equipamento");
            System.out.println("4 - Remover equipamento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro();

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
        } while (opcao != 0);
    }

    private void cadastrarEquipamento() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Quantidade: ");
            int quantidade = lerInteiro();

            System.out.print("Disponivel (s/n): ");
            boolean disponivel = lerDisponibilidade();

            equipamentoController.cadastrar(nome, quantidade, disponivel);
            System.out.println("Equipamento cadastrado com sucesso.");
        } catch (ValidacaoException erro) {
            LogUtil.registrar("ERROR", "Erro ao cadastrar equipamento: " + erro.getMessage());
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
            int id = lerInteiro();

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            System.out.print("Nova quantidade: ");
            int quantidade = lerInteiro();

            System.out.print("Disponivel (s/n): ");
            boolean disponivel = lerDisponibilidade();

            boolean atualizado = equipamentoController.atualizar(id, nome, quantidade, disponivel);

            if (atualizado) {
                System.out.println("Equipamento atualizado com sucesso.");
            } else {
                System.out.println("Equipamento nao encontrado.");
            }
        } catch (ValidacaoException erro) {
            LogUtil.registrar("ERROR", "Erro ao atualizar equipamento: " + erro.getMessage());
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private void removerEquipamento() {
        System.out.print("ID do equipamento: ");
        int id = lerInteiro();

        boolean removido = equipamentoController.remover(id);

        if (removido) {
            System.out.println("Equipamento removido com sucesso.");
        } else {
            System.out.println("Equipamento nao encontrado.");
        }
    }

    private int lerInteiro() {
        while (true) {
            try {
                int numero = scanner.nextInt();
                scanner.nextLine();
                return numero;
            } catch (InputMismatchException erro) {
                scanner.nextLine();
                LogUtil.registrar("WARNING", "Entrada invalida em equipamento: numero inteiro esperado.");
                System.out.print("Digite um numero valido: ");
            }
        }
    }

    private boolean lerDisponibilidade() {
        String resposta = scanner.nextLine();
        return resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim");
    }
}
