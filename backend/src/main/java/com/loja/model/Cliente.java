package com.loja.model;

public class Cliente{
	
	private String nome;
	private String sobrenome;
	private String usuarioInstagram;
	
	public Cliente() {}
	public Cliente(String nome, String sobrenome, String usuarioInstagram) {

		this.nome = nome;
		this.sobrenome = sobrenome;
		this.usuarioInstagram = usuarioInstagram;
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
	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", sobrenome=" + sobrenome + ", usuarioInstagram=" + usuarioInstagram + "]";
	}
	
	
	

}
