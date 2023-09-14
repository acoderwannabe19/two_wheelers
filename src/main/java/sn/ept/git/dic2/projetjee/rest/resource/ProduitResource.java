package sn.ept.git.dic2.projetjee.rest.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.projetjee.entities.*;
import sn.ept.git.dic2.projetjee.entities.Produit;
import sn.ept.git.dic2.projetjee.facades.*;
import sn.ept.git.dic2.projetjee.facades.ProduitFacade;
import sn.ept.git.dic2.projetjee.rest.response.ProduitResponse;

import java.math.BigDecimal;
import java.util.List;

@Path("/produits")
public class ProduitResource {
    @EJB
    private ProduitFacade produitFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des produits", description = "Affichage de la liste des produits")
    public List<Produit> getProduitsList() {
        return produitFacade.findAll();
    }


    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Ajout d'un produit", description = "Ajout d'un produit à la liste des produits")
    public Produit ajout(Produit p) {
        if(p.getId() != null){
            Produit tmp = produitFacade.find(p.getId());
            if (tmp != null) {
                produitFacade.edit(tmp);
            }
        }
        produitFacade.create(p);

        return p;
    }
    @DELETE
    @Path("{Id}")
    @Operation(
            summary = "Supprimer un produit",
            description = "Suppression d'un produit"
    )
    public Response delete(@PathParam("Id") Integer Id) {
        Produit produit = produitFacade.find(Id);
        if (produit == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        produitFacade.remove(produit);
        ProduitResponse response = new ProduitResponse("Le produit a été supprimé avec succès");
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("{Id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Rechercher un produit",
            description = "Recherche d'un produit",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Le produit correspondant aux magasinId et produitId fournis est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(name = "Produit not introuvable",
                                                            value = "{\"msg\": \"Le produit correspondant est introuvable.\"}"
                                                    )
                                            }
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "Le produit correspondant aux magasinId et produitId fournis a été trouvé.")
            }
    )
    public Response find(@PathParam("Id") Integer Id) {
        Produit produit = produitFacade.find(Id);
        if (produit == null) {
            ProduitResponse response = new ProduitResponse("Le produit correspondant est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(produit).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Modifier une produit",
            description = "Modification d'une produit existante")
    public Response update(@PathParam("id") Integer id, Produit produitModifiee) {
        Produit produitExistante = produitFacade.find(id);
        if (produitExistante == null) {
            ProduitResponse response = new ProduitResponse("Le produit correspondant à cet id est introuvable.");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }

        produitExistante.setAnneeModel(produitModifiee.getAnneeModel());
        produitExistante.setNom(produitModifiee.getNom());
        produitExistante.setCategorieId(produitModifiee.getCategorieId());
        produitExistante.setMarqueId(produitModifiee.getMarqueId());
        produitExistante.setPrixDepart(produitModifiee.getPrixDepart());

        produitFacade.edit(produitExistante);

        return Response.status(Response.Status.OK).entity(produitExistante).build();
    }


}
