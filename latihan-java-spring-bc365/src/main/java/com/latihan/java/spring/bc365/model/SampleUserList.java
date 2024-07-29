package com.latihan.java.spring.bc365.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "@odata.etag",
        "UID",
        "Email",
        "Address",
        "Phone",
        "Ulang_Tahun"
})
public class SampleUserList {

    @JsonProperty("@odata.etag")
    private String odataEtag;
    @JsonProperty("UID")
    private Integer uid;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("Phone")
    private Integer phone;
    @JsonProperty("Ulang_Tahun")
    private String ulangTahun;

    @JsonProperty("@odata.etag")
    public String getOdataEtag() {
        return odataEtag;
    }

    @JsonProperty("@odata.etag")
    public void setOdataEtag(String odataEtag) {
        this.odataEtag = odataEtag;
    }

    @JsonProperty("UID")
    public Integer getUid() {
        return uid;
    }

    @JsonProperty("UID")
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("Address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("Phone")
    public Integer getPhone() {
        return phone;
    }

    @JsonProperty("Phone")
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @JsonProperty("Ulang_Tahun")
    public String getUlangTahun() {
        return ulangTahun;
    }

    @JsonProperty("Ulang_Tahun")
    public void setUlangTahun(String ulangTahun) {
        this.ulangTahun = ulangTahun;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SampleUserList.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("odataEtag");
        sb.append('=');
        sb.append(((this.odataEtag == null)?"<null>":this.odataEtag));
        sb.append(',');
        sb.append("uid");
        sb.append('=');
        sb.append(((this.uid == null)?"<null>":this.uid));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("phone");
        sb.append('=');
        sb.append(((this.phone == null)?"<null>":this.phone));
        sb.append(',');
        sb.append("ulangTahun");
        sb.append('=');
        sb.append(((this.ulangTahun == null)?"<null>":this.ulangTahun));
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
        result = ((result* 31)+((this.uid == null)? 0 :this.uid.hashCode()));
        result = ((result* 31)+((this.address == null)? 0 :this.address.hashCode()));
        result = ((result* 31)+((this.phone == null)? 0 :this.phone.hashCode()));
        result = ((result* 31)+((this.odataEtag == null)? 0 :this.odataEtag.hashCode()));
        result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
        result = ((result* 31)+((this.ulangTahun == null)? 0 :this.ulangTahun.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SampleUserList) == false) {
            return false;
        }
        SampleUserList rhs = ((SampleUserList) other);
        return (((((((this.uid == rhs.uid)||((this.uid!= null)&&this.uid.equals(rhs.uid)))&&((this.address == rhs.address)||((this.address!= null)&&this.address.equals(rhs.address))))&&((this.phone == rhs.phone)||((this.phone!= null)&&this.phone.equals(rhs.phone))))&&((this.odataEtag == rhs.odataEtag)||((this.odataEtag!= null)&&this.odataEtag.equals(rhs.odataEtag))))&&((this.email == rhs.email)||((this.email!= null)&&this.email.equals(rhs.email))))&&((this.ulangTahun == rhs.ulangTahun)||((this.ulangTahun!= null)&&this.ulangTahun.equals(rhs.ulangTahun))));
    }

}

