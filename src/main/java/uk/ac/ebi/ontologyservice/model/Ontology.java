package uk.ac.ebi.ontologyservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ontologies")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ontology {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String ontologyId;
    private Config config;

    @Override
    public String toString() {
        return "Ontology{" +
                "ontologyId='" + ontologyId + '\'' +
                ", config=" + config +
                '}';
    }
}
