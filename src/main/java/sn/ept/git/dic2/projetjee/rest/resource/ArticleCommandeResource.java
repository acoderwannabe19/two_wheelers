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
import sn.ept.git.dic2.projetjee.facades.*;
import sn.ept.git.dic2.projetjee.rest.response.ArticleCommandeResponse;


import java.util.List;

@Path("/articlesCommandes")
public class ArticleCommandeResource {
    @EJB
    private ArticleCommandeFacade articleCommandeFacade;

    @EJB
    private CommandeFacade commandeFacade;

    @EJB
    private ProduitFacade produitFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des articles commandés", description = "Affichage de la liste des articles commandés")
    public List<ArticleCommande> getArticleCommandesList(){
        return articleCommandeFacade.findAll();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Ajout d'un article commandé", description = "Ajout d'un article à la liste des articles commandés")
    public ArticleCommande ajout(ArticleCommande a){
        if (a.getLigne() != 0 && a.getNumeroCommande() != null){
            ArticleCommande tmp = articleCommandeFacade.find(a.getLigne());
            if (tmp != null) {
                articleCommandeFacade.edit(tmp);
            }
        }
        articleCommandeFacade.create(a);
        return a;
    }

    @DELETE
    @Path("{ligne}/{numeroCommande}")
    @Operation(
            summary = "Supprimer un articleCommande",
            description = "Suppression d'un articleCommande"
    )
    public Response deleteArticleCommande(@PathParam("ligne") Integer ligne, @PathParam("numeroCommande") Integer numeroCommande) {
        ArticleCommande articleCommande = articleCommandeFacade.findArticleCommandeByIDs(ligne, numeroCommande);
        if (articleCommande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        articleCommandeFacade.remove(articleCommande);
        ArticleCommandeResponse response = new ArticleCommandeResponse("Le articleCommande a été supprimé avec succès");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @GET
    @Path("{ligne}/{numeroCommande}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Rechercher un articleCommande",
            description = "Recherche d'un articleCommande",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Le articleCommande correspondant aux ligne et numeroCommande fournis est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(name = "ArticleCommande not found",
                                                            value = "{\"msg\": \"Le articleCommande correspondant est introuvable.\"}"
                                                    )
                                            }
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "Le articleCommande correspondant aux ligne et numeroCommande fournis a été trouvé.")
            }
    )
    public Response findArticleCommande(@PathParam("ligne") Integer ligne, @PathParam("numeroCommande") Integer numeroCommande) {
        ArticleCommande articleCommande = articleCommandeFacade.findArticleCommandeByIDs(ligne, numeroCommande);
        if (articleCommande == null) {
            ArticleCommandeResponse response = new ArticleCommandeResponse("Le articleCommande correspondant est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(articleCommande).build();
    }


    @PUT
    @Path("{ligne}/{numeroCommande}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Modification d'une commande", description = "Modifier une commande existante")
    public Response modifArticleCommande(@PathParam("ligne") Integer ligne, @PathParam("numeroCommande") Integer numeroCommande, ArticleCommande articleCommandeModifiee) {
        ArticleCommande articleCommande = articleCommandeFacade.findArticleCommandeByIDs(ligne, numeroCommande);

        if (articleCommande == null) {
            ArticleCommandeResponse response = new ArticleCommandeResponse("La articleCommande correspondant est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }


        articleCommande.setPrixDepart(articleCommandeModifiee.getPrixDepart());
        articleCommande.setRemise(articleCommandeModifiee.getRemise());
        articleCommande.setProduitId(articleCommandeModifiee.getProduitId());
        articleCommande.setQuantite(articleCommandeModifiee.getQuantite());


        articleCommandeFacade.edit(articleCommande);

        return Response.status(Response.Status.OK).entity(articleCommande).build();
    }



}
