package sn.ept.git.dic2.projetjee.rest.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.projetjee.entities.Personne;
import sn.ept.git.dic2.projetjee.facades.PersonneFacade;
import sn.ept.git.dic2.projetjee.rest.response.PersonneResponse;

import java.util.List;

@Path("/personnes")
public class PersonneResource {

    @EJB
    private PersonneFacade personneFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des personnes", description = "Affichage de la liste des personnes")
    public List<Personne> getPersonnesList(){
        return personneFacade.findAll();
    }


    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(
            summary = "Ajout d'une personne",
            description = "Ajout d'une personne à la liste des personnes")
    public Personne ajoutPersonne(Personne e){
        if (e.getId() != null) {
            Personne tmp = personneFacade.find(e.getId());
            if(tmp != null){
                personneFacade.edit(tmp);
            }}
        personneFacade.create(e);
        return e;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//    @ApiResponses({
//            @ApiResponse(responseCode = "404", description = "Personne corresponding to the referred number was not found."),
//            @ApiResponse(responseCode = "200", description = "Personne corresponding to the referred number was found.")
//    })
    @Operation(
            summary = "Rechercher une personne",
            description = "Recherche d'une personne",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "La personne correspondant à cet id est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {@ExampleObject(name = "Personne introuvable",
                                                    value = "{\"msg\": \"La personne correspondant à cet id est introuvable.\"}"
                                            )}
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "La personne correspondant à cet id est introuvable.")

            }
    )
    public Response find(@PathParam("id") Integer id){
        Personne personne = personneFacade.find(id);
        if(personne == null){
            PersonneResponse response = new PersonneResponse("La personne correspondant à cet id est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(personne).build();

    }

    @DELETE
    @Path("{id}")
    @Operation(
            summary = "Supprimer une personne",
            description = "Suppression d'une personne")
    public Response delete(@PathParam("id") Integer id){
        Personne personne = personneFacade.find(id);
        if(personne == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        personneFacade.remove(personne);
        PersonneResponse reponse = new PersonneResponse("La personne " + personne.getPrenom() + " " + personne.getNom() +" a été supprimé avec succès");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }


    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Modifier une personne",
            description = "Modification d'une personne existante")
    public Response update(@PathParam("id") Integer id, Personne personneModifiee) {
        Personne personneExistante = personneFacade.find(id);
        if (personneExistante == null) {
            PersonneResponse response = new PersonneResponse("La personne correspondant à cet id est introuvable.");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }

        personneExistante.setPrenom(personneModifiee.getPrenom());
        personneExistante.setNom(personneModifiee.getNom());
        personneExistante.setTelephone(personneModifiee.getTelephone());
        personneExistante.setEmail(personneModifiee.getEmail());


        personneFacade.edit(personneExistante);

        return Response.status(Response.Status.OK).entity(personneExistante).build();
    }

}
