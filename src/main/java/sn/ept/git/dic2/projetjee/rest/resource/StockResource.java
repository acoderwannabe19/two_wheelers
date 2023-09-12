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
import sn.ept.git.dic2.projetjee.facades.MagasinFacade;
import sn.ept.git.dic2.projetjee.facades.ProduitFacade;
import sn.ept.git.dic2.projetjee.facades.StockFacade;
import sn.ept.git.dic2.projetjee.rest.response.StockResponse;

import java.util.List;

@Path("/stocks")
public class StockResource {

    @EJB
    private StockFacade stockFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des stocks", description = "Affichage de la liste des stocks")
    public List<Stock> getStocksList() {
        return stockFacade.findAll();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Ajouter un stock", description = "Ajout d'un stock à la liste des stocks")
    public Stock ajout(Stock e){
        if(e.getMagasin() != null && e.getProduit() != null){
            Stock tmp = stockFacade.findStockByIDs(e.getMagasin().getId(), e.getProduit().getId());
            if (tmp != null) {
                stockFacade.edit(tmp);
            }
        }
        stockFacade.create(e);

        return e;
    }



    @GET
    @Path("{magasinId}/{produitId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Rechercher un stock",
            description = "Recherche d'un stock",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Le stock correspondant aux magasinId et produitId fournis est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(name = "Stock not found",
                                                            value = "{\"msg\": \"Le stock correspondant est introuvable.\"}"
                                                    )
                                            }
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "Le stock correspondant aux magasinId et produitId fournis a été trouvé.")
            }
    )
    public Response findStock(@PathParam("magasinId") Integer magasinId, @PathParam("produitId") Integer produitId) {
        Stock stock = stockFacade.findStockByIDs(magasinId, produitId);
        if (stock == null) {
            StockResponse response = new StockResponse("Le stock correspondant est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(stock).build();
    }

    @DELETE
    @Path("{magasinId}/{produitId}")
    @Operation(
            summary = "Supprimer un stock",
            description = "Suppression d'un stock"
    )
    public Response deleteStock(@PathParam("magasinId") Integer magasinId, @PathParam("produitId") Integer produitId) {
        Stock stock = stockFacade.findStockByIDs(magasinId, produitId);
        if (stock == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        stockFacade.remove(stock);
        StockResponse response = new StockResponse("Le stock a été supprimé avec succès");
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @PUT
    @Path("{magasinId}/{produitId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Modification d'une commande", description = "Modifier une commande existante")
    public Response modifStock(@PathParam("magasinId") Integer magasinId, @PathParam("produitId") Integer produitId, Stock stockModifiee) {
        Stock stock = stockFacade.findStockByIDs(magasinId, produitId);

        if (stock == null) {
            StockResponse response = new StockResponse("La stock correspondant est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }


        stock.setMagasin(stockModifiee.getMagasin());
        stock.setProduit(stockModifiee.getProduit());
        stock.setQuantite(stockModifiee.getQuantite());


        stockFacade.edit(stock);

        return Response.status(Response.Status.OK).entity(stock).build();
    }

}
