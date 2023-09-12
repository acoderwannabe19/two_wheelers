package sn.ept.git.dic2.projetjee.rest.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.projetjee.entities.*;
import sn.ept.git.dic2.projetjee.facades.CommandeFacade;
import sn.ept.git.dic2.projetjee.rest.response.ArticleCommandeResponse;
import sn.ept.git.dic2.projetjee.rest.response.CommandeResponse;



import java.util.List;

@Path("/commandes")
public class CommandeResource {
    @EJB
    private CommandeFacade commandeFacade;


    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des commandes", description = "Affichage de la liste des commandes")
    public List<Commande> getCommandesList(){
        return commandeFacade.findAll();
    }



    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Ajouter une commande", description = "Ajout d'une commande à la liste des commandes")
    public Commande add(Commande c){
        if (c.getNumero() != null){
            Commande tmp = commandeFacade.find(c.getNumero());
            if (tmp != null) {
                commandeFacade.edit(tmp);
            }
        }
        commandeFacade.create(c);
        return c;
    }


    @DELETE
    @Path("{numero}")
    @Operation(
            summary = "Supprimer une commande",
            description = "Suppression d'une commande"
    )
    public Response delete(@PathParam("numero") Integer numero) {
        Commande commande = commandeFacade.find(numero);
        if (commande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        commandeFacade.remove(commande);
        CommandeResponse response = new CommandeResponse("La commande a été supprimée avec succès");
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("{numero}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Rechercher une commande",
            description = "Recherche d'un commande",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "La commande correspondant aux magasinId et commandeId fournis est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(name = "Commande not found",
                                                            value = "{\"msg\": \"La commande correspondant est introuvable.\"}"
                                                    )
                                            }
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "La commande correspondant aux magasinId et commandeId fournis a été trouvé.")
            }
    )
    public Response find(@PathParam("numero") Integer numero) {
        Commande commande = commandeFacade.find(numero);
        if (commande == null) {
            CommandeResponse response = new CommandeResponse("La commande correspondant est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(commande).build();
    }

    @PUT
    @Path("{numero}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Modification d'une commande", description = "Modifier une commande existante")
    public Response modifCommande(@PathParam("numero") Integer numero, Commande commandeModifiee) {
        // Retrieve the existing product and store (categorie) from the database
        Commande commande = commandeFacade.find(numero);
//        Client client = clientFacade.find(clientId);
        if (commande == null) {
            ArticleCommandeResponse response = new ArticleCommandeResponse("La commande correspondant est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }


        commande.setClientId(commandeModifiee.getClientId());
        commande.setStatut(commandeModifiee.getStatut());
        commande.setDateCommande(commandeModifiee.getDateCommande());
        commande.setDateLivraison(commandeModifiee.getDateLivraison());
        commande.setDateLivraisonVoulue(commandeModifiee.getDateLivraisonVoulue());
        commande.setMagasinId(commandeModifiee.getMagasinId());
        commandeModifiee.setVendeurId(commandeModifiee.getVendeurId());


        // Add the produit to the database using the ProduitFacade
        commandeFacade.edit(commande);

        return Response.status(Response.Status.OK).entity(commande).build();
    }


}
