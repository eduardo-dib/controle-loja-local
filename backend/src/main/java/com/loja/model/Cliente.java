package com.loja.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.MonthDay;
import com.fasterxml.jackson.annotation.JsonFormat;


public class Cliente{
	
	private String nome;
	private String sobrenome;
	private String usuarioInstagram;
	private String endereco;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM")
	private MonthDay dataDeAniversario;
	
	public Cliente() {}
	public Cliente(String nome, String sobrenome, String usuarioInstagram, String endereco, String dataAniversarioStr) {
	        this.nome = nome;
	        this.sobrenome = sobrenome;
	        this.usuarioInstagram = usuarioInstagram;
	        this.endereco = endereco;
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
	        this.dataDeAniversario = dataDeAniversario.parse(dataAniversarioStr, formatter);
	    }
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getUsuarioInstagram() {
		return usuarioInstagram;
	}
	public void setUsuarioInstagram(String usuarioInstagram) {
		this.usuarioInstagram = usuarioInstagram;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public MonthDay getDataDeAniversario() {
		return dataDeAniversario;
	}
	public void setDataDeAniversario(MonthDay dataDeAniversario) {
		this.dataDeAniversario = dataDeAniversario;
	}
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", sobrenome=" + sobrenome + ", usuarioInstagram=" + usuarioInstagram
				+ ", endereco=" + endereco + ", dataDeAniversario=" + dataDeAniversario + "]";
	}
	
	
	
	

}
