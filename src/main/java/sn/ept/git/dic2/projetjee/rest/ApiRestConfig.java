package sn.ept.git.dic2.projetjee.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api")
@OpenAPIDefinition(
        info = @Info(
                title = "Documentation de l'API de vente de vélos",
                description = "Bienvenue dans la documentation de l'API de vente de vélos. Cette API a été conçue pour vous permettre d'intégrer facilement votre site de vente de vélos à notre plateforme. Elle offre un large éventail de fonctionnalités pour gérer les produits, les commandes, les utilisateurs et bien plus encore.",
                contact = @Contact(
                        name = "Ndeye Awa SALANE",
                        email = "salanendeyea@ept.sn",
                        url = "blabla"
                ),
                version = "1.0",
                license = @License(name = "OpenSource")
        ),
        servers = {@Server(
                url = "{urlDeployment}",
                variables = {@ServerVariable(
                        name = "urlDeployment",
                        defaultValue = "http://localhost:8080/projetJEE-1.0-SNAPSHOT/")}
        )
        }
)
public class ApiRestConfig extends Application {


}
