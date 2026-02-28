package com.latihan.java.multiple.database.model.hts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "m_user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -5610739203816307275L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String name;

    @Column(unique = true)
    private String email;

    @Column(name = "sales_group_id")
    private long salesGroupId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getSalesGroupId() {
        return salesGroupId;
    }

    public void setSalesGroupId(long salesGroupId) {
        this.salesGroupId = salesGroupId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", salesGroupId=" + salesGroupId +
                '}';
    }
}
