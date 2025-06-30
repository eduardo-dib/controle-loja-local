package com.loja.resource;

import com.loja.dao.ProdutoDAO;
import com.loja.model.Produto;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import jakarta.ws.rs.core.Response;



@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    private ProdutoDAO dao = new ProdutoDAO();

    @GET
    public List<Produto> listar() {
        return dao.listar();
    }

    @POST
    public Response salvar(Produto produto) {
        try {
            dao.salvar(produto);
            if (produto != null) {
                return Response.status(Response.Status.CREATED).entity(produto).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Erro: Cliente nulo após tentativa de salvar.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar cliente: " + e.getMessage()).build();
        }
    }
    
    @GET
    @Path("/{id}")
    public Produto getById(@PathParam("id") Long id) {
    	return dao.getById(id);
    }
    
    
}