
package com.example;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Xz {

    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;
    private List<Datum> data = new ArrayList<Datum>();
    private Support support;

 public Xz(@JsonProperty("page") Integer page, @JsonProperty("perPage") Integer perPage, @JsonProperty("total") Integer total, @JsonProperty("totalPages") Integer totalPages, @JsonProperty("data") List<Datum> data, @JsonProperty("support") Support support) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.totalPages = totalPages;
        this.data = data;
        this.support = support;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<Datum> getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Xz.class.getName()).append('[');
        sb.append("page=");
        sb.append(page);
        sb.append(",perPage=");
        sb.append(perPage);
        sb.append(",total=");
        sb.append(total);
        sb.append(",totalPages=");
        sb.append(totalPages);
        sb.append(",data=");
        sb.append(data);
        sb.append(",support=");
        sb.append(support);
        sb.append(']');
        return sb.toString();
    }

}
