package sn.ept.git.dic2.projetjee.rest.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.facades.MagasinFacade;
import sn.ept.git.dic2.projetjee.rest.response.MagasinResponse;

import java.util.List;

@Path("/magasins")
public class MagasinResource {
    @EJB
    private MagasinFacade magasinFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des magasins", description = "Affichage de la liste des magasins")
    public List<Magasin> getMagasinsList(){
        return magasinFacade.findAll();
    }


    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(
            summary = "Ajout d'un magasin",
            description = "Ajout d'un magasin à la liste des magasins")
    public Magasin add(Magasin e){
        if (e.getId() != null){
            Magasin tmp = magasinFacade.find(e.getId());
            if (tmp != null) {
                magasinFacade.edit(tmp);
            }
        }
        magasinFacade.create(e);
        return e;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//    @ApiResponses({
//            @ApiResponse(responseCode = "404", description = "Magasin corresponding to the referred number was not found."),
//            @ApiResponse(responseCode = "200", description = "Magasin corresponding to the referred number was found.")
//    })
    @Operation(
            summary = "Rechercher un magasin",
            description = "Recherche d'une magasin",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Le magasin correspondant à cet id est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {@ExampleObject(name = "Magasin not found",
                                                    value = "{\"msg\": \"Le magasin correspondant à cet id est introuvable.\"}"
                                            )}
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "Le magasin correspondant à cet id est introuvable.")

            }
    )
    public Response find(@PathParam("id") Integer id){
        Magasin magasin = magasinFacade.find(id);
        if(magasin == null){
            MagasinResponse response = new MagasinResponse("Le magasin correspondant à cet id est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(magasin).build();

    }

    @DELETE
    @Path("{id}")
    @Operation(
            summary = "Supprimer un magasin",
            description = "Suppression d'un magasin")
    public Response delete(@PathParam("id") Integer id){
        Magasin magasin = magasinFacade.find(id);
        if(magasin == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        magasinFacade.remove(magasin);
        MagasinResponse reponse = new MagasinResponse("Le magasin " + magasin.getNom() + " a été supprimé avec succès");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }


    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Modifier une magasin",
            description = "Modification d'une magasin existante")
    public Response update(@PathParam("id") Integer id, Magasin magasinModifiee) {
        Magasin magasinExistante = magasinFacade.find(id);
        if (magasinExistante == null) {
            MagasinResponse response = new MagasinResponse("La magasin correspondant à cet id est introuvable.");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }


        magasinExistante.setNom(magasinModifiee.getNom());
        magasinExistante.setTelephone(magasinModifiee.getTelephone());
        magasinExistante.setEmail(magasinModifiee.getEmail());
        magasinExistante.setAdresse(magasinModifiee.getAdresse());
        magasinExistante.setVille(magasinModifiee.getVille());
        magasinExistante.setCodeZip(magasinModifiee.getCodeZip());
        magasinExistante.setEtat(magasinModifiee.getEtat());

        magasinFacade.edit(magasinExistante);

        return Response.status(Response.Status.OK).entity(magasinExistante).build();
    }

}
