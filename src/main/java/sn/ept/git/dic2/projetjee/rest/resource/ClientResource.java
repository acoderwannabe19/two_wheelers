package sn.ept.git.dic2.projetjee.rest.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.projetjee.entities.Client;
import sn.ept.git.dic2.projetjee.facades.ClientFacade;
import sn.ept.git.dic2.projetjee.rest.response.ClientResponse;

import java.util.List;

@Path("/clients")
public class ClientResource {

    @EJB
    private ClientFacade clientFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des clients", description = "Affichage de la liste des clients")
    public List<Client> getClientsList(){
        return clientFacade.findAll();
    }


    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(
            summary = "Ajouter d'un client",
            description = "Ajout d'un client à la liste des clients")
    public Client add(Client c){
        if (c.getId() != null){
            Client tmp = clientFacade.find(c.getId());
            if (tmp != null) {
                clientFacade.edit(tmp);
            }
        }
        clientFacade.create(c);
        return c;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//    @ApiResponses({
//            @ApiResponse(responseCode = "404", description = "Client corresponding to the referred number was not found."),
//            @ApiResponse(responseCode = "200", description = "Client corresponding to the referred number was found.")
//    })
    @Operation(
            summary = "Rechercher un client",
            description = "Recherche d'un client",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Le client correspondant à cet id est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {@ExampleObject(name = "Client not found",
                                                    value = "{\"msg\": \"Le client correspondant à cet id est introuvable.\"}"
                                            )}
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "Le client correspondant à cet id est trouvé.")

            }
    )
    public Response find(@PathParam("id") Integer id){
        Client client = clientFacade.find(id);
        if(client == null){
            ClientResponse response = new ClientResponse("Le client correspondant à cet id est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(client).build();

    }

    @DELETE
    @Path("{id}")
    @Operation(
            summary = "Supprimer un client",
            description = "Suppression d'un client")
    public Response delete(@PathParam("id") Integer id){
        Client client = clientFacade.find(id);
        if(client == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        clientFacade.remove(client);
        ClientResponse reponse = new ClientResponse("Le client " + client.getPrenom() + " " + client.getNom() +" a été supprimé avec succès");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }


    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Modifier une client",
            description = "Modification d'une client existante")
    public Response update(@PathParam("id") Integer id, Client clientModifiee) {
        Client clientExistante = clientFacade.find(id);
        if (clientExistante == null) {
            ClientResponse response = new ClientResponse("La client correspondant à cet id est introuvable.");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }

        clientExistante.setPrenom(clientModifiee.getPrenom());
        clientExistante.setNom(clientModifiee.getNom());
        clientExistante.setTelephone(clientModifiee.getTelephone());
        clientExistante.setEmail(clientModifiee.getEmail());
        clientExistante.setAdresse(clientModifiee.getAdresse());
        clientExistante.setVille(clientModifiee.getVille());
        clientExistante.setCodeZip(clientModifiee.getCodeZip());
        clientExistante.setEtat(clientModifiee.getEtat());



        clientFacade.edit(clientExistante);

        return Response.status(Response.Status.OK).entity(clientExistante).build();
    }
}
