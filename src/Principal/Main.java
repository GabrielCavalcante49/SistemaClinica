package Principal;

import ClassesGerais.Paciente;
import ClassesGerais.TipoConsulta;
import Sistema.SistemaAtendimento;

import java.util.Scanner;

public class Main {
    private static int nextID = 1;

    public static void main(String[] args) {
        SistemaAtendimento sistema = new SistemaAtendimento(10, 10,10);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearConsole();
            displayHeader();
            displayMenu();

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1:
                        try {
                            Paciente paciente = coletaDados(scanner);
                            sistema.register(paciente);

                            System.out.println("\nPaciente cadastrado com sucesso!");
                            System.out.println("ID: " + String.format("%04d", paciente.getId()) + " | Nome: " + paciente.getNome());
                        } catch (IllegalArgumentException | IllegalStateException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("\nErro: Formato inválido!");
                        }
                        break;

                    case 2:
                        sistema.next();
                        break;

                    case 3:
                        try {
                            sistema.rollback();
                            System.out.println("\nÚltimo atendimento desfeito.");
                        } catch (IllegalStateException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                        break;

                    case 4:
                        sistema.showQueues();
                        break;

                    case 5:
                        sistema.log();
                        break;

                    case 6:
                        System.out.println("\nSaindo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("\nOpção inválida!");
                }
                pause(scanner);

            } catch (Exception e) {
                System.out.println("\nErro: Entrada inválida!");
                scanner.nextLine(); // Limpa entrada incorreta
                pause(scanner);
            }
        }
    }

    private static Paciente coletaDados(Scanner scanner) {
        if (nextID > 9999) {
            throw new IllegalStateException("Limite máximo de pacientes atingido (9999)");
        }

        int novoId = nextID++;
        System.out.print("\nNome completo: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        displayTiposConsulta();

        System.out.print("Tipo de consulta (número): ");
        TipoConsulta tipo = TipoConsulta.fromNumero(scanner.nextInt());
        scanner.nextLine();

        return new Paciente(novoId, nome, tipo, idade);
    }

    private static void displayHeader() {
        System.out.println("====================================");
        System.out.println("  SISTEMA DE ATENDIMENTO MÉDICO  ");
        System.out.println("====================================");
        System.out.println("------------------------------------\n");
    }

    private static void displayMenu() {
        System.out.println("1. Cadastrar novo paciente");
        System.out.println("2. Atender próximo paciente");
        System.out.println("3. Desfazer último atendimento");
        System.out.println("4. Visualizar filas");
        System.out.println("5. Histórico de atendimentos");
        System.out.println("6. Sair do sistema");
        System.out.print("\nDigite sua opção: ");
    }

    private static void displayTiposConsulta() {
        System.out.println("\nTipos de consulta disponíveis:");
        for (TipoConsulta tipo : TipoConsulta.values()) {
            System.out.printf("%2d - %s\n", tipo.getTipoNumero(), tipo);
        }
    }

    private static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Erro ao limpar o console.");
        }
    }

    private static void pause(Scanner scanner) {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}