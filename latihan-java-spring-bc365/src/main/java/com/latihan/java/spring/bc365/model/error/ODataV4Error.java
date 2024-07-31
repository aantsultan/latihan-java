package com.latihan.java.spring.bc365.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "error"
})
public class ODataV4Error {

    @JsonProperty("error")
    private ODataV4ErrorMessage error;

    @JsonProperty("error")
    public ODataV4ErrorMessage getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(ODataV4ErrorMessage error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ODataV4Error.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
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
        result = ((result* 31)+((this.error == null)? 0 :this.error.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ODataV4Error) == false) {
            return false;
        }
        ODataV4Error rhs = ((ODataV4Error) other);
        return ((this.error == rhs.error)||((this.error!= null)&&this.error.equals(rhs.error)));
    }

}

