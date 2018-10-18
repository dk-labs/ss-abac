package dk.labs.ss.abac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Thing {

    private int id;

    private String description;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private String modifiedBy;
}
