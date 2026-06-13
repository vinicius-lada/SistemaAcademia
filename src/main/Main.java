package main;

import controller.*;
import view.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EquipamentoController equipamentoController = new EquipamentoController();
        EquipamentoView equipamentoView = new EquipamentoView(scanner, equipamentoController);

        AulaController aulaController = new AulaController();
        AulaView aulaView = new AulaView(scanner, aulaController);

        AlunoController alunoController = new AlunoController();
        AlunoView alunoView = new AlunoView(scanner, alunoController);

        MatriculaController matriculaController = new MatriculaController();
        MatriculaView matriculaView = new MatriculaView(scanner, matriculaController);

        PlanoController planoController = new PlanoController();
        PlanoView planoView = new PlanoView(scanner, planoController);

        PagamentoController pagamentoController = new PagamentoController();
        PagamentoView pagamentoView = new PagamentoView(scanner, pagamentoController);

        int opcao = -1;

        do {
            try {
                System.out.println("\n--- Sistema Academia ---");
                System.out.println("1 - Equipamentos");
                System.out.println("2 - Aulas");
                System.out.println("3 - Alunos");
                System.out.println("4 - Matriculas");
                System.out.println("5 - Planos");
                System.out.println("6 - Pagamentos");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opcao: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        equipamentoView.exibirMenu();
                        break;
                    case 2:
                        aulaView.exibirMenu();
                        break;
                    case 3:
                        alunoView.exibirMenu();
                        break;
                    case 4:
                        matriculaView.exibirMenu();
                        break;
                    case 5:
                        planoView.exibirMenu();
                        break;
                    case 6:
                        pagamentoView.exibirMenu();
                        break;
                    case 0:
                        System.out.println("Sistema encerrado.");
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

        scanner.close();
    }
}
