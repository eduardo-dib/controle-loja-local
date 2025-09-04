package com.loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import com.loja.model.Venda;
import com.loja.model.Produto;
import com.loja.model.Cliente;
import com.loja.dao.*;

public class VendaDAO {
	
	

    public VendaDAO() {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            String sqlVendas = """
                CREATE TABLE IF NOT EXISTS vendas (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cliente_id INTEGER NOT NULL,
                    valor_total REAL NOT NULL,
                    data_hora TEXT NOT NULL,
                    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
                );
            """;

            String sqlVendaProdutos = """
                CREATE TABLE IF NOT EXISTS venda_produtos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    venda_id INTEGER NOT NULL,
                    produto_id INTEGER NOT NULL,
                    FOREIGN KEY (venda_id) REFERENCES vendas(id),
                    FOREIGN KEY (produto_id) REFERENCES produto(id)
                );
            """;

            stmt.execute(sqlVendas);
            stmt.execute(sqlVendaProdutos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void salvar(Venda venda) {
    	String sqlVenda = "INSERT INTO vendas (cliente_id, valor_total, data_hora) VALUES (?, ?, ?)";
        String sqlVendaProduto = "INSERT INTO venda_produtos (venda_id, produto_id) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false); 
            
            PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            stmtVenda.setLong(1, venda.getCliente().getId());
            stmtVenda.setBigDecimal(2, venda.getValorTotal());

            String dataHoraFormatada = venda.getDataHora()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            stmtVenda.setString(3, dataHoraFormatada);

            stmtVenda.executeUpdate();

            ResultSet rs = stmtVenda.getGeneratedKeys();
            Long vendaId = null;
            if (rs.next()) {
                vendaId = rs.getLong(1);
            }

            if (vendaId == null) {
                throw new Exception("Erro ao obter ID da venda inserida");
            }

     
            PreparedStatement stmtProduto = conn.prepareStatement(sqlVendaProduto);
            for (Produto produto : venda.getProdutos()) {
                stmtProduto.setLong(1, vendaId);
                stmtProduto.setLong(2, produto.getId());
                stmtProduto.addBatch();
            }
            stmtProduto.executeBatch();

            conn.commit(); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Venda> listar() {
        List<Venda> vendas = new ArrayList<>();

        String sql = "SELECT * FROM vendas";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getLong("id"));
                venda.setValorTotal(rs.getBigDecimal("valor_total"));
                venda.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());

                // Buscar cliente
                long clienteId = rs.getLong("cliente_id");
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.getById(clienteId);
                venda.setCliente(cliente);

                // Buscar produtos da venda
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List<Produto> produtos = ProdutoDAO.getByVendaId(venda.getId());
                venda.setProdutos(produtos);

                vendas.add(venda);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vendas;
    }

    
    
}
