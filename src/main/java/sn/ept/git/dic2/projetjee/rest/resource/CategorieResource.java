package sn.ept.git.dic2.projetjee.rest.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.projetjee.entities.Categorie;
import sn.ept.git.dic2.projetjee.facades.CategorieFacade;
import sn.ept.git.dic2.projetjee.rest.response.CategorieResponse;

import java.util.List;

@Path("/categories")
public class CategorieResource {
    @EJB
    private CategorieFacade categorieFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des catégories", description = "Affichage de la liste des catégories")
    public List<Categorie> getCategoriesList(){
        return categorieFacade.findAll();
    }


    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(
            summary = "Ajout d'une catégorie",
            description = "Ajout d'une catégorie à la liste des catégories")
    public Categorie add(Categorie e){
        if (e.getId() != null){
            Categorie tmp = categorieFacade.find(e.getId());
            if (tmp != null) {
                categorieFacade.edit(tmp);
            }
        }
        categorieFacade.create(e);
        return e;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//    @ApiResponses({
//            @ApiResponse(responseCode = "404", description = "Categorie corresponding to the referred number was not found."),
//            @ApiResponse(responseCode = "200", description = "Categorie corresponding to the referred number was found.")
//    })
    @Operation(
            summary = "Rechercher une catégorie",
            description = "Recherche d'une catégorie",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "La catégorie correspondant à cet id est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {@ExampleObject(name = "Catégorie not found",
                                                    value = "{\"msg\": \"La catégorie correspondant à cet id est introuvable.\"}"
                                            )}
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "La catégorie correspondant à cet id est introuvable.")

            }
    )
    public Response find(@PathParam("id") Integer id){
        Categorie categorie = categorieFacade.find(id);
        if(categorie == null){
            CategorieResponse response = new CategorieResponse("La catégorie correspondant à cet id est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(categorie).build();

    }

    @DELETE
    @Path("{id}")
    @Operation(
            summary = "Supprimer une catégorie",
            description = "Suppression d'une catégorie")
    public Response delete(@PathParam("id") Integer id){
        Categorie categorie = categorieFacade.find(id);
        if(categorie == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categorieFacade.remove(categorie);
        CategorieResponse reponse = new CategorieResponse("La catégorie " + categorie.getNom() + " a été supprimé avec succès");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }


    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Modifier une categorie",
            description = "Modification d'une categorie existante")
    public Response update(@PathParam("id") Integer id, Categorie categorieModifiee) {
        Categorie categorieExistante = categorieFacade.find(id);
        if (categorieExistante == null) {
            CategorieResponse response = new CategorieResponse("La categorie correspondant à cet id est introuvable.");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }


        categorieExistante.setNom(categorieModifiee.getNom());

        categorieFacade.edit(categorieExistante);

        return Response.status(Response.Status.OK).entity(categorieExistante).build();
    }

}
