package com.loja.resource;

import com.loja.dao.ClienteDAO;
import com.loja.model.Cliente;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@Path("/clientes")
@Tag(name = "Clientes", description = "Endpoints relacionados a clientes da loja")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private ClienteDAO dao = new ClienteDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista todos os clientes")
    public List<Cliente> listar() {
        return dao.listar();
    }

    @POST
    @Operation(summary = "Salva um cliente")
    public Response salvar(Cliente cliente) {
        try {
            dao.salvar(cliente);
            if (cliente != null) {
                return Response.status(Response.Status.CREATED).entity(cliente).build();
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
    @Operation(summary = "Retorna um cliente pelo ID")
    public Cliente getById(@PathParam("id") Long id) {
    	return dao.getById(id);
    }
    
    @PUT
    @Path("/atualizar/{id}")
    @Operation(summary = "Atualiza um produto pelo id")
    public Response atualizar(@PathParam("id")Long id, Cliente c) {
    	try {
    		dao.atualizar(id, c);
    		if(c != null) {
    			return Response.status(Response.Status.OK)
    					.entity(c)
    					.build();
    		}else {
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(c)
                        .build();
    		}
    	}catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity("Erro ao atualizar cliente" + e.getMessage())
    				.build();    	
    		}
    }
    
    @DELETE
    @Path("/deletar/{id}")
    @Operation(summary = "Delete um produto de acordo com o id")
    public Response deletar(@PathParam("id") Long id) {
        boolean sucesso = dao.deletar(id);
        if (sucesso) {
            return Response.ok("Cliente deletado com sucesso.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado.").build();
        }
    }
    
    
}