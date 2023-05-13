/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    private Connection conn;
    private conectaDAO conexao;

    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();
    }

    public int cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES" + "(?,?,?)";
        int status;
        try {
            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());

            status = st.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar para adicionar: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto");
            return ex.getErrorCode();
        }
    }

    public List<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            List<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                listaProdutos.add(produto);
            }
            return listaProdutos;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado");
            return null;
        }
    }
    
    public void venderProduto(int idProd){
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setInt(1, idProd);
            st.execute();
            
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso");
        } catch (Exception e) {
            /* tratando erro caso ele ocorra**/
            JOptionPane.showMessageDialog(null, "Erro ao editar o produto, tente novamente");
            System.out.println("Erro ao editar produto: " + e.getMessage());
        }
    }
    
    public List<ProdutosDTO> listarProdutosVendidos(){
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            List<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                
                listaProdutos.add(produto);
            }
            return listaProdutos;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado");
            return null;
        }
    }
}
