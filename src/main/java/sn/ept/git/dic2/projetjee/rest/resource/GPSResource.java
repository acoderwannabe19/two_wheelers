package sn.ept.git.dic2.projetjee.rest.resource;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import sn.ept.git.dic2.projetjee.entities.GPSLocation;
import sn.ept.git.dic2.projetjee.facades.GPSLocationFacade;

import java.util.List;

@Path("/gpsLocations")
public class GPSResource {
    @EJB
    private GPSLocationFacade gpsLocationFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Operation(summary = "Liste des localisations gps", description = "Affichage de la liste des localisation gps")
    public List<GPSLocation> getGPSLocationsList(){
        return gpsLocationFacade.findAll();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public GPSLocation receiveGPSData(GPSLocation gpsLocation) {

        gpsLocationFacade.create(gpsLocation);
        return gpsLocation;
    }

}
