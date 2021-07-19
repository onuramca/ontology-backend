package uk.ac.ebi.ontologyservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "config")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String title;
    private String description;
    private List<String> synonymProperties;
    private List<String> definitionProperties;

    @Override
    public String toString() {
        return "Config{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", synonymProperties=" + synonymProperties +
                ", definitionProperties=" + definitionProperties +
                '}';
    }
}
