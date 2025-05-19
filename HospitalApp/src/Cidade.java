import java.sql.*;

public class Cidade {

    public static void cadastrarCidade(String nome, int populacao) throws Exception {
        Connection conn = DB.connect();
        String sql = "INSERT INTO cidades (nome, populacao) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setInt(2, populacao);
        stmt.executeUpdate();
        System.out.println("Cidade cadastrada com sucesso.");
    }

    public static void listarCidades() throws Exception {
        Connection conn = DB.connect();
        String sql = "SELECT * FROM cidades";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n=== Cidades Cadastradas ===");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            int populacao = rs.getInt("populacao");
            System.out.println("ID: " + id + " | Cidade: " + nome + " | População: " + populacao);
        }
    }
}
    