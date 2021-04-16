
package com.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Datum {

    private String name;
    private String color;

 public Datum(@JsonProperty("name") String name, @JsonProperty("color") String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Datum.class.getName()).append('[');
        sb.append("name=");
        sb.append(name);
        sb.append(",color=");
        sb.append(color);
        sb.append(']');
        return sb.toString();
    }

}
