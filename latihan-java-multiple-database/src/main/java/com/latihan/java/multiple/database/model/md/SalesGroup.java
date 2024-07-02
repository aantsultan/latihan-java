package com.latihan.java.multiple.database.model.md;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "m_sales_group")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalesGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_group_id")
    private long id;
    private String name;
}
