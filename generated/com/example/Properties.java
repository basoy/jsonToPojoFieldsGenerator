
package com.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Properties {

    private String propertyName;
    private String propertyDesc;

 public Properties(@JsonProperty("propertyName") String propertyName, @JsonProperty("propertyDesc") String propertyDesc) {
        this.propertyName = propertyName;
        this.propertyDesc = propertyDesc;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyDesc() {
        return propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Properties.class.getName()).append('[');
        sb.append("propertyName=");
        sb.append(propertyName);
        sb.append(",propertyDesc=");
        sb.append(propertyDesc);
        sb.append(']');
        return sb.toString();
    }

}
