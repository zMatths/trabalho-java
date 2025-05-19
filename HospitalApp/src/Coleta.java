import java.sql.*;

public class Coleta {

    public static void cadastrarColeta(int cidadeId, Date data, int confirmados, int sintomas, int mortes) throws Exception {
        Connection conn = DB.connect();
        String sql = "INSERT INTO coletas (cidade_id, data_coleta, casos_confirmados, casos_com_sintomas, mortes) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, cidadeId);
        stmt.setDate(2, data);
        stmt.setInt(3, confirmados);
        stmt.setInt(4, sintomas);
        stmt.setInt(5, mortes);
        stmt.executeUpdate();
        System.out.println("Coleta registrada com sucesso.");
    }

    public static void alterarColeta(int coletaId, int confirmados, int sintomas, int mortes) throws Exception {
        Connection conn = DB.connect();
        String sql = "UPDATE coletas SET casos_confirmados = ?, casos_com_sintomas = ?, mortes = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, confirmados);
        stmt.setInt(2, sintomas);
        stmt.setInt(3, mortes);
        stmt.setInt(4, coletaId);
        stmt.executeUpdate();
        System.out.println("Coleta alterada com sucesso.");
    }

    public static void relatorioCidade(int cidadeId, int limite) throws Exception {
        Connection conn = DB.connect();
        String sql = "SELECT * FROM coletas WHERE cidade_id = ? ORDER BY data_coleta DESC LIMIT ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, cidadeId);
        stmt.setInt(2, limite);
        ResultSet rs = stmt.executeQuery();

        System.out.println("\n=== Relatório da Cidade ===");
        while (rs.next()) {
            Date data = rs.getDate("data_coleta");
            int casos = rs.getInt("casos_confirmados");
            int pop = getPopulacao(cidadeId);
            double perc = (casos * 100.0) / pop;
            System.out.printf("Data: %s | Casos: %d | Percentual: %.2f%%\n", data, casos, perc);
        }
    }

    public static void relatorioComparativo(int cid1, int cid2, Date inicio, Date fim) throws Exception {
        Connection conn = DB.connect();
        String sql = "SELECT cidade_id, SUM(casos_confirmados) as total FROM coletas WHERE cidade_id IN (?, ?) AND data_coleta BETWEEN ? AND ? GROUP BY cidade_id";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, cid1);
        stmt.setInt(2, cid2);
        stmt.setDate(3, inicio);
        stmt.setDate(4, fim);
        ResultSet rs = stmt.executeQuery();

        System.out.println("\n=== Comparativo de Cidades ===");
        while (rs.next()) {
            int id = rs.getInt("cidade_id");
            int total = rs.getInt("total");
            System.out.printf("Cidade ID: %d | Total de Casos: %d\n", id, total);
        }
    }

    public static void relatorioTodasCidades(Date inicio, Date fim) throws Exception {
        Connection conn = DB.connect();
        String sql = "SELECT cidade_id, SUM(casos_confirmados) as total FROM coletas WHERE data_coleta BETWEEN ? AND ? GROUP BY cidade_id";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, inicio);
        stmt.setDate(2, fim);
        ResultSet rs = stmt.executeQuery();

        System.out.println("\n=== Relatório Geral ===");
        while (rs.next()) {
            int id = rs.getInt("cidade_id");
            int total = rs.getInt("total");
            System.out.printf("Cidade ID: %d | Total de Casos: %d\n", id, total);
        }
    }

    private static int getPopulacao(int cidadeId) throws Exception {
        Connection conn = DB.connect();
        String sql = "SELECT populacao FROM cidades WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, cidadeId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) return rs.getInt("populacao");
        else return 1; // evitar divisão por zero
    }
}
