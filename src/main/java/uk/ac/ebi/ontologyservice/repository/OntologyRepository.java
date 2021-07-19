package uk.ac.ebi.ontologyservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.ontologyservice.model.Ontology;

import java.util.Optional;

public interface OntologyRepository extends MongoRepository<Ontology, String> {
    Optional<Ontology> findByOntologyId(String ontologyId);
}
