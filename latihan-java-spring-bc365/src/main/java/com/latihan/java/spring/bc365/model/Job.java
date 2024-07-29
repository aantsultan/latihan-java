package com.latihan.java.spring.bc365.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "@odata.etag",
    "No",
    "Description",
    "Bill_to_Customer_No",
    "Status",
    "Person_Responsible",
    "Search_Description",
    "Project_Manager"
})
public class Job {

    @JsonProperty("@odata.etag")
    private String odataEtag;
    @JsonProperty("No")
    private String no;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Bill_to_Customer_No")
    private String billToCustomerNo;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Person_Responsible")
    private String personResponsible;
    @JsonProperty("Search_Description")
    private String searchDescription;
    @JsonProperty("Project_Manager")
    private String projectManager;

    @JsonProperty("@odata.etag")
    public String getOdataEtag() {
        return odataEtag;
    }

    @JsonProperty("@odata.etag")
    public void setOdataEtag(String odataEtag) {
        this.odataEtag = odataEtag;
    }

    @JsonProperty("No")
    public String getNo() {
        return no;
    }

    @JsonProperty("No")
    public void setNo(String no) {
        this.no = no;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Bill_to_Customer_No")
    public String getBillToCustomerNo() {
        return billToCustomerNo;
    }

    @JsonProperty("Bill_to_Customer_No")
    public void setBillToCustomerNo(String billToCustomerNo) {
        this.billToCustomerNo = billToCustomerNo;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("Person_Responsible")
    public String getPersonResponsible() {
        return personResponsible;
    }

    @JsonProperty("Person_Responsible")
    public void setPersonResponsible(String personResponsible) {
        this.personResponsible = personResponsible;
    }

    @JsonProperty("Search_Description")
    public String getSearchDescription() {
        return searchDescription;
    }

    @JsonProperty("Search_Description")
    public void setSearchDescription(String searchDescription) {
        this.searchDescription = searchDescription;
    }

    @JsonProperty("Project_Manager")
    public String getProjectManager() {
        return projectManager;
    }

    @JsonProperty("Project_Manager")
    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Job.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("odataEtag");
        sb.append('=');
        sb.append(((this.odataEtag == null)?"<null>":this.odataEtag));
        sb.append(',');
        sb.append("no");
        sb.append('=');
        sb.append(((this.no == null)?"<null>":this.no));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("billToCustomerNo");
        sb.append('=');
        sb.append(((this.billToCustomerNo == null)?"<null>":this.billToCustomerNo));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("personResponsible");
        sb.append('=');
        sb.append(((this.personResponsible == null)?"<null>":this.personResponsible));
        sb.append(',');
        sb.append("searchDescription");
        sb.append('=');
        sb.append(((this.searchDescription == null)?"<null>":this.searchDescription));
        sb.append(',');
        sb.append("projectManager");
        sb.append('=');
        sb.append(((this.projectManager == null)?"<null>":this.projectManager));
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
        result = ((result* 31)+((this.projectManager == null)? 0 :this.projectManager.hashCode()));
        result = ((result* 31)+((this.no == null)? 0 :this.no.hashCode()));
        result = ((result* 31)+((this.billToCustomerNo == null)? 0 :this.billToCustomerNo.hashCode()));
        result = ((result* 31)+((this.personResponsible == null)? 0 :this.personResponsible.hashCode()));
        result = ((result* 31)+((this.odataEtag == null)? 0 :this.odataEtag.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.searchDescription == null)? 0 :this.searchDescription.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Job) == false) {
            return false;
        }
        Job rhs = ((Job) other);
        return (((((((((this.projectManager == rhs.projectManager)||((this.projectManager!= null)&&this.projectManager.equals(rhs.projectManager)))&&((this.no == rhs.no)||((this.no!= null)&&this.no.equals(rhs.no))))&&((this.billToCustomerNo == rhs.billToCustomerNo)||((this.billToCustomerNo!= null)&&this.billToCustomerNo.equals(rhs.billToCustomerNo))))&&((this.personResponsible == rhs.personResponsible)||((this.personResponsible!= null)&&this.personResponsible.equals(rhs.personResponsible))))&&((this.odataEtag == rhs.odataEtag)||((this.odataEtag!= null)&&this.odataEtag.equals(rhs.odataEtag))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.searchDescription == rhs.searchDescription)||((this.searchDescription!= null)&&this.searchDescription.equals(rhs.searchDescription))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
