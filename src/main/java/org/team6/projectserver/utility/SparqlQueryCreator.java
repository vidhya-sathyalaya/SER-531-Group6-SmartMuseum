package org.team6.projectserver.utility;

import java.util.Iterator;
import java.util.List;

public class SparqlQueryCreator {

    public static String createHeader(List<String> tokens){
        String header ="";
        Iterator<String> tokenIterator = tokens.iterator();
        while(tokenIterator.hasNext()){
            String token = tokenIterator.next();
            switch(token){
                case "rdfs": header+="prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n";
                break;
                case "dbo": header+="PREFIX dbo: <http://dbpedia.org/ontology/>\n";
                break;
                case "foaf": header+="prefix foaf: <http://xmlns.com/foaf/0.1/>\n";
                break;
                case "g6sm": header+="PREFIX g6sm: <http://www.semanticweb.org/admin/ontologies/2021/11/G6_SmartMuseumTour#>\n";
                break;
                case "rdf": header+="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";
                break;
                case "dbp": header+="PREFIX dbp: <http://dbpedia.org/property/>\n";
                break;
                default:;
            }
        }
        return  header;
    }

}
