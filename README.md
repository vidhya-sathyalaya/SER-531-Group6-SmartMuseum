**Steps to run**
From the root directory, run the following commands on terminal
```
mvn clean install
mvn spring-boot:run
```
**Sample API Calls**
```
http://localhost:8080/getArtInfo?artId=g6sm:218
http://localhost:8080/getArtistInfo?artId=g6sm:218
http://localhost:8080/getArchitecture?artId=g6sm:218
```

**SPARQL Queries**<br>
- Endpoints : <br>
  Group6 Endpoint : http://ec2-3-20-236-248.us-east-2.compute.amazonaws.com:3030/g6smartmuseum <br>
  DBPedia Endpoint : http://dbpedia.org/sparql

- Samples
  - Query 1 : Get Art info given Art id( say g6sm:218)
  ```
  SELECT ?artist ?artistBio ?nationality ?gender ?born ?died
  WHERE {
    g6sm:728 rdf:type g6sm:Artwork;
    g6sm:made_by ?artist.
    ?artist rdf:type g6sm:Artist.
    ?artist g6sm:ArtistBio ?artistBio;
    g6sm:Nationality ?nationality;
    g6sm:Gender ?gender;
    g6sm:Born ?born;
    g6sm:Died ?died.
  }
  ```
  - Query 2 : Get Artist Info
  ```
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX g6sm: <http://www.semanticweb.org/admin/ontologies/2021/11/G6_SmartMuseumTour#>

  SELECT ?name ?artistBio ?nationality ?gender ?born ?died
  WHERE {
    g6sm:728 rdf:type g6sm:Artwork;
    g6sm:made_by ?artist.
    ?artist rdf:type g6sm:Artist.
    ?artist g6sm:ArtistName ?name.
    ?artist g6sm:ArtistBio ?artistBio;
    g6sm:Nationality ?nationality;
    g6sm:Gender ?gender;
    g6sm:Born ?born;
    g6sm:Died ?died.
  }

  ```
  
  - Query 3 : Federated Query to get Architecture info from DBPedia
  
  ```
  prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
  PREFIX g6sm: <http://www.semanticweb.org/admin/ontologies/2021/11/G6_SmartMuseumTour#>
  prefix foaf: <http://xmlns.com/foaf/0.1/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX dbo: <http://dbpedia.org/ontology/>
  PREFIX dbp: <http://dbpedia.org/property/>

  SELECT   ?buildingName ?location ?builtYear
    WHERE {
    g6sm:218 g6sm:has_Architecture ?arch.
    ?arch g6sm:WikiURL ?URLStr.
    BIND(IRI(?URLStr) as ?URL).
    SERVICE <http://dbpedia.org/sparql>{
      ?URL foaf:primaryTopic ?building.
      ?building foaf:name ?buildingName.
      ?building dbp:location ?location.
      OPTIONAL {?building dbp:built ?builtYear. }
    }
   }

  ```
  
  
  
![image](https://user-images.githubusercontent.com/90420924/144951349-619cf043-451b-4fb2-a047-7a3cebb43fa7.png)
![image](https://user-images.githubusercontent.com/90420924/144951396-7bbc9ee7-88c8-4dac-ab85-a2abfe679138.png)
![image](https://user-images.githubusercontent.com/90420924/144951889-b00fa1b6-87ee-4b85-83d8-78c8ad471911.png)


