package org.team6.projectserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.apache.commons.lang3.arch.Processor.Arch;
import org.apache.jena.query.*;
import org.team6.projectserver.model.primitive.ArchitectureInfo;
import org.team6.projectserver.model.primitive.ArtInfo;
import org.team6.projectserver.model.primitive.ArtistInfo;
import org.team6.projectserver.model.response.ArchitectureResponse;
import org.team6.projectserver.model.response.ArtInfoResponse;
import org.team6.projectserver.model.response.ArtistInfoResponse;
import org.team6.projectserver.utility.ApplicationProperties;
import org.team6.projectserver.utility.SparqlQueryCreator;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MuseumInfoController {

    @Autowired
    private ApplicationProperties properties;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "getArchitecture", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getArchitecture(@RequestParam String artId) throws JsonProcessingException {
        List<String> headers = new ArrayList<>();
        headers.add("rdfs");
        headers.add("dbo");
        headers.add("foaf");
        headers.add("dbp");
        headers.add("g6sm");
        ParameterizedSparqlString qs = new ParameterizedSparqlString(
                SparqlQueryCreator.createHeader(headers) + "\n"
                + "SELECT  ?art ?buildingName ?location ?builtYear\n"
                + "  WHERE {\n"
                + artId + " g6sm:has_Architecture ?arch.\n"
                + "?arch g6sm:WikiURL ?URLStr.\n"
                + "BIND(IRI(?URLStr) as ?URL).\n"
                + "SERVICE <http://dbpedia.org/sparql>{\n"
                + "?URL foaf:primaryTopic ?building.\n"
                + "?building foaf:name ?buildingName.\n"
                + "?building dbp:location ?location.\n"
                + "OPTIONAL {?building dbp:built ?ybuiltYear. }\n"
                + "}\n"
                + "  }");

        QueryExecution exec = QueryExecutionFactory.sparqlService(properties.getServerSparqlAws(), qs.asQuery());

        ResultSet results = exec.execSelect();
        
        ArchitectureResponse resp = new ArchitectureResponse();
        while (results.hasNext()) {
            QuerySolution solution = results.next();
            ArchitectureInfo archInfo = new ArchitectureInfo();
            archInfo.setBuildingName(solution.get("buildingName").toString());
            archInfo.setLocation((solution.get("location") != null)?
                    solution.get("location").toString(): "");
            archInfo.setBuiltYear((solution.get("builtYear") != null)?
                    solution.get("builtYear").toString(): "");

            resp.getResults().add(archInfo);
        }
        return mapper.writeValueAsString(resp);
    }

    @GetMapping(value = "getArtInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getArtInfo(@RequestParam String artId) throws JsonProcessingException {
        List<String> headers = new ArrayList<>();
        headers.add("rdfs");
        headers.add("g6sm");
        headers.add("foaf");
        headers.add("rdf");
        ParameterizedSparqlString qs = new ParameterizedSparqlString(
                SparqlQueryCreator.createHeader(headers) + "\n"
                + "SELECT ?title ?year ?medium ?classification ?department\n"
                + "  WHERE {\n"
                + artId + " rdf:type g6sm:Artwork;\n"
                + "  g6sm:Title ?title;\n"
                + "  g6sm:Date ?year;\n"
                + "  g6sm:Medium ?medium;\n"
                + "  g6sm:Classification ?classification;\n"
                + "  g6sm:Department ?department.\n"
                + "  }");
        

        QueryExecution exec = QueryExecutionFactory.sparqlService(properties.getServerSparqlAws(), qs.asQuery());

        ResultSet results = exec.execSelect();
        //System.out.println(qs);
        ArtInfoResponse resp = new ArtInfoResponse();
        while (results.hasNext()) {
            QuerySolution solution = results.next();
            ArtInfo artInfo = new ArtInfo();
            artInfo.setTitle(solution.get("title").toString());
            artInfo.setYear((solution.get("year") != null)?
                    solution.get("year").toString(): "");
            artInfo.setMedium((solution.get("medium") != null)?
                    solution.get("medium").toString(): "");
            artInfo.setClassification((solution.get("classification") != null)?
                    solution.get("classification").toString(): "");
            artInfo.setDepartment((solution.get("department") != null)?
                    solution.get("department").toString(): "");
            resp.getResults().add(artInfo);
        }
        return mapper.writeValueAsString(resp);
    }

    @GetMapping(value = "getArtistInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getArtistInfo(@RequestParam String artId) throws JsonProcessingException {
        List<String> headers = new ArrayList<>();
        headers.add("rdfs");
        headers.add("g6sm");
        headers.add("foaf");
        headers.add("rdf");
        ParameterizedSparqlString qs = new ParameterizedSparqlString(
                SparqlQueryCreator.createHeader(headers) + "\n"
                + "SELECT ?name ?artistBio ?nationality ?gender ?born ?died\n"
                + "  WHERE {\n"
                + artId + " rdf:type g6sm:Artwork;\n"
                + "  g6sm:made_by ?artist.\n"
                + "  ?artist rdf:type g6sm:Artist;\n"
                + "  g6sm:ArtistName ?name;\n"
                + "  g6sm:ArtistBio ?artistBio;\n"
                + "  g6sm:Nationality ?nationality;\n"
                + "  g6sm:Gender ?gender;\n"
                + "  g6sm:Born ?born;\n"
                + "  g6sm:Died ?died.\n"
                + "  }");
        
        
        QueryExecution exec = QueryExecutionFactory.sparqlService(properties.getServerSparqlAws(), qs.asQuery());

        ResultSet results = exec.execSelect();
        
        ArtistInfoResponse resp = new ArtistInfoResponse();
        while (results.hasNext()) {
            QuerySolution solution = results.next();
            ArtistInfo artistInfo = new ArtistInfo();
            artistInfo.setName((solution.get("name") != null)?
                    solution.get("name").toString(): "");
            artistInfo.setArtistBio((solution.get("artistBio") != null)?
                    solution.get("artistBio").toString(): "");
            artistInfo.setNationality((solution.get("nationality") != null)?
                    solution.get("nationality").toString(): "");
            artistInfo.setGender((solution.get("gender") != null)?
                    solution.get("gender").toString(): "");
            artistInfo.setBorn((solution.get("born") != null)?
                    solution.get("born").toString(): "");
            artistInfo.setDied((solution.get("died") != null)?
                    solution.get("died").toString(): "");
            resp.getResults().add(artistInfo);
        }
        return mapper.writeValueAsString(resp);
    }

}