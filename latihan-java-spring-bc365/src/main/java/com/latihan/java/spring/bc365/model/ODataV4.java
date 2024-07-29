package com.latihan.java.spring.bc365.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "@odata.context",
    "value"
})
public class ODataV4<T> {

    @JsonProperty("@odata.context")
    private String odataContext;
    @JsonProperty("value")
    private List<T> value = new ArrayList<>();

    @JsonProperty("@odata.context")
    public String getOdataContext() {
        return odataContext;
    }

    @JsonProperty("@odata.context")
    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    @JsonProperty("value")
    public List<T> getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(List<T> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ODataV4.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("odataContext");
        sb.append('=');
        sb.append(((this.odataContext == null)?"<null>":this.odataContext));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.value == null)? 0 :this.value.hashCode()));
        result = ((result* 31)+((this.odataContext == null)? 0 :this.odataContext.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ODataV4) == false) {
            return false;
        }
        ODataV4 rhs = ((ODataV4) other);
        return (((this.value == rhs.value)||((this.value!= null)&&this.value.equals(rhs.value)))&&((this.odataContext == rhs.odataContext)||((this.odataContext!= null)&&this.odataContext.equals(rhs.odataContext))));
    }

}
