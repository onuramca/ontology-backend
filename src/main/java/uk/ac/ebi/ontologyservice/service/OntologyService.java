package uk.ac.ebi.ontologyservice.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.ontologyservice.model.Ontology;
import uk.ac.ebi.ontologyservice.repository.OntologyRepository;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OntologyService {

    private final Logger logger = Logger.getLogger(OntologyService.class.getName());

    @Autowired
    private OntologyRepository ontologyRepository;

    public Ontology getOntologyById(String ontologyId) {
        Optional<Ontology> ontology = ontologyRepository.findByOntologyId(ontologyId);
        return ontology.orElse(null);
    }

    @Transactional
    public Ontology storeOntology(Ontology ontology) {
        try {
            ontology.getConfig().getDefinitionProperties().removeIf(Strings::isEmpty);
            ontology.getConfig().getSynonymProperties().removeIf(Strings::isEmpty);
            return ontologyRepository.save(ontology);
        } catch (Exception ex) {
            logger.log(Level.SEVERE,"An error occured while saving ontology: " + ontology);
            return null;
        }
    }
}
