
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    public void cadastrarProduto(ProdutosDTO produto) {

        try {
            conectaDAO conexao = new conectaDAO();

            String sql = "INSERT INTO produtos(nome,valor,status)  VALUES(?,?,?)";

            PreparedStatement prep = conexao.connectDB().prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();

        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar no banco" + ex.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();
        try {

            conectaDAO conexao = new conectaDAO();

            String sql = "SELECT * FROM produtos";

            PreparedStatement prep = conexao.connectDB().prepareStatement(sql);
            ResultSet resposta = prep.executeQuery();
            while (resposta.next()) {
                ProdutosDTO produtos = new ProdutosDTO();
                produtos.setId(resposta.getInt("id"));
                produtos.setNome(resposta.getString("nome"));
                produtos.setValor(resposta.getInt("valor"));
                produtos.setStatus(resposta.getString("status"));

                lista.add(produtos);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao listar no banco" + ex.getMessage());

        }

        return lista;
    }

    public void venderProduto(int id) {

        conectaDAO conexao = new conectaDAO();
        try {
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ? ";
            PreparedStatement prep = conexao.connectDB().prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Algo deu errado " + ex.getMessage());
        }

    }

    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> listaVendidos = new ArrayList<ProdutosDTO>();

    try {
        conectaDAO conexao = new conectaDAO();

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        PreparedStatement prep = conexao.connectDB().prepareStatement(sql);
        ResultSet lista = prep.executeQuery();

        while (lista.next()) {
            ProdutosDTO produtos = new ProdutosDTO(); // Cria nova instância dentro do loop
            produtos.setId(lista.getInt("id"));
            produtos.setNome(lista.getString("nome"));
            produtos.setStatus(lista.getString("status"));
            produtos.setValor(lista.getInt("valor"));

            listaVendidos.add(produtos); // Adiciona cada novo objeto à lista
        }

    } catch (SQLException ex) {
        System.out.println("Erro " + ex.getMessage());
    }
    return listaVendidos;
}
}
