package org.team6.projectserver.utility;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value( "${server.sparql.aws}")
    @Getter @Setter
    String serverSparqlAws;

    @Value( "${server.sparql.dbpedia}")
    @Getter @Setter
    String serverSparqlDBPedia;
}
