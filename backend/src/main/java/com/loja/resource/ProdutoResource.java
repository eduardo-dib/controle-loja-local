package com.loja.resource;

import com.loja.dao.ProdutoDAO;
import com.loja.model.Produto;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Operation;



@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    private ProdutoDAO dao = new ProdutoDAO();

    @GET
    @Operation(summary = "Lista todos os produtos")
    public List<Produto> listar() {
        return dao.listar();
    }

    @POST
    @Operation(summary = "Salva um novo produto")
    public Response salvar(Produto produto) {
        try {
            dao.salvar(produto);
            if (produto != null) {
                return Response.status(Response.Status.CREATED).entity(produto).
                		build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Erro: Produto nulo após tentativa de salvar.").
                        build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar produto: " + e.getMessage()).
                    build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Operation(summary = "Retorna um produto por id")
    public Produto getById(@PathParam("id") Long id) throws Exception{
    	return dao.getById(id);
    }
    
    @PUT
    @Path("/atualizar/{id}")
    @Operation(summary = "Atualiza um produto")
    public Response atualizar(@PathParam("id")Long id, Produto p) {
    	try {
    		dao.atualizar(id, p);
    		if(p != null) {
    			return Response.status(Response.Status.OK)
    					.entity(p)
    					.build();
    		}else {
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(p)
                        .build();
    		}
    	}catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity("Erro ao atualizar Produto" + e.getMessage())
    				.build();    	
    		}
    }
    
    @DELETE
    @Path("/deletar/{id}")
    @Operation(summary = "Deleta um produto")
    public Response deletar(@PathParam("id") Long id) {
        boolean sucesso = dao.deletar(id);
        if (sucesso) {
            return Response.ok("Produto deletado com sucesso.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Produto não encontrado.").build();
        }
    }
    
    
}