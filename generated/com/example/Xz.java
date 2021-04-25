
package com.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Xz {

    private Properties properties;

 public Xz(@JsonProperty("properties") Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Xz.class.getName()).append('[');
        sb.append("properties=");
        sb.append(properties);
        sb.append(']');
        return sb.toString();
    }

}
