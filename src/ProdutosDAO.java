 

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        String nome = produto.getNome();
        int valor = produto.getValor();
        String  status = produto.getStatus();
                
        conn = new conectaDAO().connectDB();
        try{
        PreparedStatement prep = this.conn.prepareStatement("INSERT INTO produtos(nome,valor,status)  VALUES(?,?,?)");
        prep.setString(1, nome);
        prep.setInt(2, valor);
        prep.setString(3, status);
        prep.executeUpdate();
        
        }catch(SQLException ex){
            System.out.println("Erro ao cadastrar no banco"+ex.getMessage());
        }
}
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

