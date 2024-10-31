
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
            System.out.println("Erro ao cadastrar no banco" + ex.getMessage());

        }

        return lista;
    }

}
