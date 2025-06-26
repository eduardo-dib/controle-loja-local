package com.loja.model;
import java.util.List;
import java.time.LocalDateTime;
public class Venda {
  private Cliente cliente;
  private List<Produto> produtos;
  private Double valorTotal;
  private LocalDateTime dataHora;
  
  public Venda() {}

  public Venda(Cliente cliente, List<Produto> produtos, Double valorTotal, LocalDateTime dataHora) {
	this.cliente = cliente;
	this.produtos = produtos;
	this.valorTotal = valorTotal;
	this.dataHora = dataHora;
  }

  public Cliente getCliente() {
	return cliente;
  }

  public void setCliente(Cliente cliente) {
	this.cliente = cliente;
  }

  public List<Produto> getProdutos() {
	return produtos;
  }

  public void setProdutos(List<Produto> produtos) {
	this.produtos = produtos;
  }

  public Double getValorTotal() {
	return valorTotal;
  }

  public void setValorTotal(Double valorTotal) {
	this.valorTotal = valorTotal;
  }

  public LocalDateTime getDataHora() {
	return dataHora;
  }

  public void setDataHora(LocalDateTime dataHora) {
	this.dataHora = dataHora;
  }

  @Override
  public String toString() {
	return "Venda [cliente=" + cliente + ", produtos=" + produtos + ", valorTotal=" + valorTotal + ", dataHora="
			+ dataHora + "]";
  }
  
  
  
  
  
}
