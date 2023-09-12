package sn.ept.git.dic2.projetjee.rest.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.projetjee.entities.Marque;
import sn.ept.git.dic2.projetjee.facades.MarqueFacade;
import sn.ept.git.dic2.projetjee.rest.response.MarqueResponse;

import java.util.List;

@Path("/marques")
public class MarqueResource {
    @EJB
    private MarqueFacade marqueFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des marques", description = "Affichage de la liste des marques")
    public List<Marque> getMarquesList(){
        return marqueFacade.findAll();
    }


    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(
            summary = "Ajout d'une marque",
            description = "Ajout d'une marque à la liste des marques")
    public Marque add(Marque e){
        if (e.getId() != null){
            Marque tmp = marqueFacade.find(e.getId());
            if (tmp != null) {
                marqueFacade.edit(tmp);
            }
        }
        marqueFacade.create(e);
        return e;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//    @ApiResponses({
//            @ApiResponse(responseCode = "404", description = "Marque corresponding to the referred number was not found."),
//            @ApiResponse(responseCode = "200", description = "Marque corresponding to the referred number was found.")
//    })
    @Operation(
            summary = "Rechercher une marque",
            description = "Recherche d'une marque",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "La marque correspondant à cet id est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {@ExampleObject(name = "Marque not found",
                                                    value = "{\"msg\": \"La marque correspondant à cet id est introuvable.\"}"
                                            )}
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "La marque correspondant à cet id est introuvable.")

            }
    )
    public Response find(@PathParam("id") Integer id){
        Marque marque = marqueFacade.find(id);
        if(marque == null){
            MarqueResponse response = new MarqueResponse("La marque correspondant à cet id est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(marque).build();

    }

    @DELETE
    @Path("{id}")
    @Operation(
            summary = "Supprimer une marque",
            description = "Suppression d'une marque")
    public Response delete(@PathParam("id") Integer id){
        Marque marque = marqueFacade.find(id);
        if(marque == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        marqueFacade.remove(marque);
        MarqueResponse reponse = new MarqueResponse("La marque " + marque.getNom() + " a été supprimé avec succès");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Modifier une marque",
            description = "Modification d'une marque existante")
    public Response update(@PathParam("id") Integer id, Marque marqueModifiee) {
        Marque marqueExistante = marqueFacade.find(id);
        if (marqueExistante == null) {
            MarqueResponse response = new MarqueResponse("La marque correspondant à cet id est introuvable.");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }


        marqueExistante.setNom(marqueModifiee.getNom());

        marqueFacade.edit(marqueExistante);

        return Response.status(Response.Status.OK).entity(marqueExistante).build();
    }


}
