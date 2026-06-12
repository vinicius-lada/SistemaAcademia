package main;

import controller.*;
import view.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.LogUtil;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EquipamentoController equipamentoController = new EquipamentoController();
        EquipamentoView equipamentoView = new EquipamentoView(scanner, equipamentoController);

        AulaController aulaController = new AulaController();
        AulaView aulaView = new AulaView(scanner, aulaController);

        int opcao;

        do {
            System.out.println("\n--- Sistema Academia ---");
            System.out.println("1 - Equipamentos");
            System.out.println("2 - Aulas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    equipamentoView.exibirMenu();
                    break;
                case 2:
                    aulaView.exibirMenu();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                int numero = scanner.nextInt();
                scanner.nextLine();
                return numero;
            } catch (InputMismatchException erro) {
                scanner.nextLine();
                LogUtil.registrar("WARNING", "Entrada invalida no menu principal: numero inteiro esperado.");
                System.out.print("Digite um numero valido: ");
            }
        }
    }
}
