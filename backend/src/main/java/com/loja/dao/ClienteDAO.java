
package com.loja.dao;

import com.loja.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

	public ClienteDAO() {
	    try (Connection conn = ConnectionFactory.getConnection()) {
	        String sql = """
	            CREATE TABLE IF NOT EXISTS cliente (
	                id INTEGER PRIMARY KEY AUTOINCREMENT,
	                nome TEXT NOT NULL,
	                sobrenome TEXT NOT NULL,
	                usuarioInstagram TEXT,
	                endereco TEXT,
	                dataDeAniversario TEXT
	            );
	        """;
	        conn.createStatement().execute(sql);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void salvar(Cliente cliente) {
	    try (Connection conn = ConnectionFactory.getConnection()) {
	        String sql = "INSERT INTO cliente (nome, sobrenome, usuarioInstagram, endereco, dataDeAniversario) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, cliente.getNome());
	        stmt.setString(2, cliente.getSobrenome());
	        stmt.setString(3, cliente.getUsuarioInstagram());
	        stmt.setString(4, cliente.getEndereco());
	        String dataFormatada = cliente.getDataDeAniversario()
	                .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM"));
	        stmt.setString(5, dataFormatada);

	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cliente");

            while (rs.next()) {
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String usuarioInstagram = rs.getString("usuarioInstagram");
                String endereco = rs.getString("endereco");
                String dataAniversarioStr = rs.getString("dataDeAniversario"); 

                Cliente c = new Cliente(nome, sobrenome, usuarioInstagram, endereco, dataAniversarioStr);
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Cliente getById(Long id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String usuarioInstagram = rs.getString("usuarioInstagram");
                String endereco = rs.getString("endereco");
                String dataAniversarioStr = rs.getString("dataDeAniversario");

                cliente = new Cliente(nome, sobrenome, usuarioInstagram, endereco, dataAniversarioStr);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }
    
    
    
    
}
