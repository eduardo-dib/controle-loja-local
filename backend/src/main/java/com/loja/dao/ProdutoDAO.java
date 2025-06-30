
package com.loja.dao;

import com.loja.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProdutoDAO {

	public ProdutoDAO() {
	    try (Connection conn = ConnectionFactory.getConnection()) {
	        String sql = """
	            CREATE TABLE IF NOT EXISTS produto (
	                id INTEGER PRIMARY KEY AUTOINCREMENT,
	                nome TEXT NOT NULL,
	                descricao TEXT NOT NULL,
	                preco NUMERIC,
	                imagemUrl TEXT
	            );
	        """;
	        conn.createStatement().execute(sql);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void salvar(Produto produto) {
	    try (Connection conn = ConnectionFactory.getConnection()) {
	        String sql = "INSERT INTO produto (nome, descricao, preco, imagemUrl) VALUES (?, ?, ?, ?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, produto.getNome());
	        stmt.setString(2, produto.getDescricao());
	        stmt.setBigDecimal(3, produto.getPreco());
	        stmt.setString(4, produto.getImagemUrl());
	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM produto");

            while (rs.next()) {
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                String imagemUrl = rs.getString("imagemUrl");
                Produto p = new Produto(nome, descricao, preco, imagemUrl);
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Produto getById(Long id) {
        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                String imagemUrl = rs.getString("imagemUrl");
                produto = new Produto(nome, descricao, preco, imagemUrl);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produto;
    }
    
    public Produto atualizar(Long id, Produto p) {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, imagemUrl = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setBigDecimal(3, p.getPreco());
            stmt.setString(4, p.getImagemUrl());
            stmt.setLong(5, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {

                p.setId(id);
                return p;
            } else {
                return null; 
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
}
