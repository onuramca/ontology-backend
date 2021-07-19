package uk.ac.ebi.ontologyservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.ontologyservice.model.Ontology;
import uk.ac.ebi.ontologyservice.service.OntologyService;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/ontologies")
@Api(value = "Ontology API")
public class OntologyController {

    @Autowired
    private OntologyService ontologyService;

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = Logger.getLogger(OntologyController.class.getName());

    @GetMapping("/{ontologyId}")
    @ApiOperation(value = "Get ontology by id")
    public ResponseEntity<Ontology> getOntologyById(@PathVariable String ontologyId) {
        Ontology ontology = ontologyService.getOntologyById(ontologyId);
        if (ontology != null) {
            return ResponseEntity.ok(ontology);
        } else {
            String resourceUrl = "https://www.ebi.ac.uk/ols/api/ontologies/";
            ResponseEntity<Ontology> response;
            try {
                response = restTemplate.getForEntity(resourceUrl + ontologyId, Ontology.class);
            } catch (HttpClientErrorException.NotFound ex) {
                logger.log(Level.WARNING, "Resource not found: " + ontologyId);
                response = ResponseEntity.notFound().build();
            }
            return response;
        }
    }

    @PostMapping
    @ApiOperation(value = "Store an ontology resource")
    public ResponseEntity<Ontology> getOntologyById(@RequestBody Ontology ontology) {
        return ResponseEntity.ok(ontologyService.storeOntology(ontology));
    }

}
