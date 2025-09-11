package com.loja.resource;


import com.loja.dao.VendaDAO;
import com.loja.dao.ProdutoDAO;
import com.loja.model.Cliente;
import com.loja.model.Venda;
import com.loja.model.VendaDTO;
import com.loja.model.Produto;



import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.PathParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Operation;



@Path("/vendas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VendaResource {

    private VendaDAO dao = new VendaDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @POST
    @Operation(summary = "Cadastra uma venda")
    public Response salvar(VendaDTO dto) {
        try {
            Venda venda = new Venda();

            Cliente cliente = new Cliente();
            cliente.setId(dto.getClienteId());
            venda.setCliente(cliente);

            List<Produto> produtos = new ArrayList<>();
            BigDecimal total = BigDecimal.ZERO;

            for (Long produtoId : dto.getProdutoIds()) {
                Produto produto = ProdutoDAO.getById(produtoId);
                if (produto == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Produto com ID " + produtoId + " não encontrado.").build();
                }
                produtos.add(produto);
                total = total.add(produto.getPreco()); 
            }

            venda.setProdutos(produtos);
            venda.setValorTotal(total);
            venda.setDataHora(LocalDateTime.now());

            dao.salvar(venda);

            return Response.status(Response.Status.CREATED).entity("Venda salva com sucesso!").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar venda: " + e.getMessage()).build();
        }
    }
    
    @GET
    @Operation(summary = "Lista todas as vendas")
    public List<Venda> listar(){
    	return dao.listar();
    }
    
    @GET
    @Path("/cliente/{id}")
    @Operation(summary = "Lista as vendas por cliente")
    public List<Venda> listarPorCliente(@PathParam("id") Long id){
    	return dao.getByCliente(id);
    }

    }

