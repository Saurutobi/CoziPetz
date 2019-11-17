package com.marcel.Api.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @JsonProperty("id")
    public UUID id;
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
