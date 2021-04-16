
package com.example;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Xz {

    private List<Datum> data = new ArrayList<Datum>();

 public Xz(@JsonProperty("data") List<Datum> data) {
        this.data = data;
    }

    public List<Datum> getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Xz.class.getName()).append('[');
        sb.append("data=");
        sb.append(data);
        sb.append(']');
        return sb.toString();
    }

}
