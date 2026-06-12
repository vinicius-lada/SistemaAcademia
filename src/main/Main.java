package main;

import controller.EquipamentoController;
import view.EquipamentoView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EquipamentoController equipamentoController = new EquipamentoController();
        EquipamentoView equipamentoView = new EquipamentoView(scanner, equipamentoController);

        int opcao;

        do {
            System.out.println("\n--- Sistema Academia ---");
            System.out.println("1 - Equipamentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    equipamentoView.exibirMenu();
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
}
