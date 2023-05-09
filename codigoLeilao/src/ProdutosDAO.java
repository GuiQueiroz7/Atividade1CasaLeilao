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
import javax.swing.JOptionPane;
import java.util.ArrayList;

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

    public ArrayList<ProdutosDTO> listarProdutos() {
        return null;
    }

}
