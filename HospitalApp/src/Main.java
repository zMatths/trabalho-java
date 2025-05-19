import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== SISTEMA MALÁRIA - CIDADES SP =====");
            System.out.println("[1] Cadastrar Cidade");
            System.out.println("[2] Cadastrar Coleta");
            System.out.println("[3] Alterar Coleta");
            System.out.println("[4] Relatório de uma Cidade");
            System.out.println("[5] Relatório de Comparação entre Cidades");
            System.out.println("[6] Relatório de Todas as Cidades");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Nome da cidade: ");
                    sc.nextLine();  // Limpar o buffer
                    String cidadeNome = sc.nextLine();
                    System.out.print("População: ");
                    int populacao = sc.nextInt();
                    try {
                        Cidade.cadastrarCidade(cidadeNome, populacao);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("ID da cidade: ");
                    int cidadeId = sc.nextInt();
                    System.out.print("Data da coleta (YYYY-MM-DD): ");
                    String data = sc.next();
                    System.out.print("Casos confirmados: ");
                    int casosConfirmados = sc.nextInt();
                    System.out.print("Casos com sintomas: ");
                    int casosComSintomas = sc.nextInt();
                    System.out.print("Mortes: ");
                    int mortes = sc.nextInt();
                    try {
                        Coleta.cadastrarColeta(cidadeId, Date.valueOf(data), casosConfirmados, casosComSintomas, mortes);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("ID da coleta a ser alterada: ");
                    int coletaId = sc.nextInt();
                    System.out.print("Novos casos confirmados: ");
                    int novosCasosConfirmados = sc.nextInt();
                    System.out.print("Novos casos com sintomas: ");
                    int novosCasosComSintomas = sc.nextInt();
                    System.out.print("Novas mortes: ");
                    int novasMortes = sc.nextInt();
                    try {
                        Coleta.alterarColeta(coletaId, novosCasosConfirmados, novosCasosComSintomas, novasMortes);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("ID da cidade: ");
                    int cidadeRelatorioId = sc.nextInt();
                    System.out.print("Número de coletas a serem exibidas: ");
                    int limite = sc.nextInt();
                    try {
                        Coleta.relatorioCidade(cidadeRelatorioId, limite);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("ID da primeira cidade: ");
                    int cidadeId1 = sc.nextInt();
                    System.out.print("ID da segunda cidade: ");
                    int cidadeId2 = sc.nextInt();
                    System.out.print("Data inicial (YYYY-MM-DD): ");
                    String dataInicio = sc.next();
                    System.out.print("Data final (YYYY-MM-DD): ");
                    String dataFim = sc.next();
                    try {
                        Coleta.relatorioComparativo(cidadeId1, cidadeId2, Date.valueOf(dataInicio), Date.valueOf(dataFim));
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Data inicial (YYYY-MM-DD): ");
                    String dataInicioTodas = sc.next();
                    System.out.print("Data final (YYYY-MM-DD): ");
                    String dataFimTodas = sc.next();
                    try {
                        Coleta.relatorioTodasCidades(Date.valueOf(dataInicioTodas), Date.valueOf(dataFimTodas));
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
