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
import sn.ept.git.dic2.projetjee.facades.EmployeFacade;
import sn.ept.git.dic2.projetjee.rest.response.EmployeResponse;

import java.util.List;

@Path("/employes")
public class EmployeResource {

    @EJB
    private EmployeFacade employeFacade;


    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des employés", description = "Affichage de la liste des employés")
    public List<Employe> getEmployesList(){
        return employeFacade.findAll();
    }



    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Ajout d'une employe",
            description = "Ajout d'une employe à la liste des employes")
    public Employe ajout(Employe e){
        if (e.getId() != null){
            Employe tmp = employeFacade.find(e.getId());
            if (tmp != null) {
                employeFacade.edit(tmp);
            }
        }
        employeFacade.create(e);
        return e;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//    @ApiResponses({
//            @ApiResponse(responseCode = "404", description = "Employe corresponding to the referred number was not found."),
//            @ApiResponse(responseCode = "200", description = "Employe corresponding to the referred number was found.")
//    })
    @Operation(
            summary = "Rechercher un employé",
            description = "Recherche d'une employé",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "L'employé correspondant à cet id est introuvable.",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {@ExampleObject(name = "Employé not found",
                                                    value = "{\"msg\": \"L'employé correspondant à cet id est introuvable.\"}"
                                            )}
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "200", description = "L'employé correspondant à cet id est introuvable.")

            }
    )
    public Response find(@PathParam("id") Integer id){
        Employe employe = employeFacade.find(id);
        if(employe == null){
            EmployeResponse response = new EmployeResponse("L'employé correspondant à cet id est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(employe).build();

    }

    @DELETE
    @Path("{id}")
    @Operation(
            summary = "Supprimer un employé",
            description = "Suppression d'une employé")
    public Response delete(@PathParam("id") Integer id){
        Employe employe = employeFacade.find(id);
        if(employe == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        employeFacade.remove(employe);
        EmployeResponse reponse = new EmployeResponse("L'employé " + employe.getPrenom() + " " + employe.getNom() +" a été supprimé avec succès");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Modifier une employe",
            description = "Modification d'une employe existante")
    public Response update(@PathParam("id") Integer id, Employe employeModifiee) {
        Employe employeExistante = employeFacade.find(id);
        if (employeExistante == null) {
            EmployeResponse response = new EmployeResponse("La employe correspondant à cet id est introuvable.");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }

        employeExistante.setPrenom(employeModifiee.getPrenom());
        employeExistante.setNom(employeModifiee.getNom());
        employeExistante.setTelephone(employeModifiee.getTelephone());
        employeExistante.setEmail(employeModifiee.getEmail());
        employeExistante.setActif(employeModifiee.getActif());
        employeExistante.setManagerId(employeModifiee.getManagerId());
        employeExistante.setMagasinId(employeModifiee.getMagasinId());
        employeExistante.setAdresse(employeModifiee.getAdresse());
        employeExistante.setVille(employeModifiee.getVille());
        employeExistante.setCodeZip(employeModifiee.getCodeZip());
        employeExistante.setEtat(employeModifiee.getEtat());


        employeFacade.edit(employeExistante);

        return Response.status(Response.Status.OK).entity(employeExistante).build();
    }

}
