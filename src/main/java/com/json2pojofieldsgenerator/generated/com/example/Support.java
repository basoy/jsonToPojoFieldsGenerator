
package com.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Support {

    private String url;
    private String text;

 public Support(@JsonProperty("url") String url, @JsonProperty("text") String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Support.class.getName()).append('[');
        sb.append("url=");
        sb.append(url);
        sb.append(",text=");
        sb.append(text);
        sb.append(']');
        return sb.toString();
    }

}
