package com.marcel.Api.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @JsonProperty(value = "id" ,access = JsonProperty.Access.READ_ONLY)
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("type")
    public String type;
    @JsonProperty("sex")
    public String sex;
    @JsonProperty("description")
    public String description;
    @JsonProperty("owner_email")
    public String ownerEmail;
    @JsonProperty("image_url")
    public String imageUrl;
}
