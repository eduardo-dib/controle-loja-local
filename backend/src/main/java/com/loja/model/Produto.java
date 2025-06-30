package com.loja.model;
import java.math.BigDecimal;

public class Produto {
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private String imagemUrl;
	
	public Produto() {}

	public Produto(String nome, String descricao, BigDecimal preco, String imagemUrl) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imagemUrl = imagemUrl;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + ", imagemUrl=" + imagemUrl + "]";
	}
	
	
	
	
	

}
