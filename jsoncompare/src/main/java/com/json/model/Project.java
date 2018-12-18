
package com.json.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id1",
    "id2",
    "type",
    "level",
    "children"
})
public class Project {

    @JsonProperty("id1")
    private String id1;
    @JsonProperty("id2")
    private String id2;
    @JsonProperty("type")
    private String type;
    @JsonProperty("level")
    private Integer level;
    @JsonProperty("chapter")
    private List<Chapters> chapter = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id1")
    public String getId1() {
        return id1;
    }

    @JsonProperty("id1")
    public void setId1(String id1) {
        this.id1 = id1;
    }

    @JsonProperty("id2")
    public String getId2() {
        return id2;
    }

    @JsonProperty("id2")
    public void setId2(String id2) {
        this.id2 = id2;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("level")
    public Integer getLevel() {
        return level;
    }

    @JsonProperty("level")
    public void setLevel(Integer level) {
        this.level = level;
    }

    @JsonProperty("chapter")
    public List<Chapters> getChildren() {
        return chapter;
    }

    @JsonProperty("children")
    public void setChildren(List<Chapters> chapter) {
        this.chapter = chapter;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
