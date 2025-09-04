package com.loja.model;
import java.util.List;
import java.time.LocalDateTime;
import java.math.BigDecimal;
public class Venda {
  private Long id;


  private Cliente cliente;
  private List<Produto> produtos;
  private BigDecimal valorTotal;
  private LocalDateTime dataHora;
  
  public Venda() {}

  public Venda(Cliente cliente, List<Produto> produtos, BigDecimal valorTotal, LocalDateTime dataHora) {
	this.cliente = cliente;
	this.produtos = produtos;
	this.valorTotal = valorTotal;
	this.dataHora = dataHora;
  }
  
  public Long getId() {
	return id;
}

  public void setId(Long id) {
	this.id = id;
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

  public BigDecimal getValorTotal() {
	return valorTotal;
  }

  public void setValorTotal(BigDecimal valorTotal) {
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
