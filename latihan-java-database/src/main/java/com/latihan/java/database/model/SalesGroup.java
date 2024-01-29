package com.latihan.java.database.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_sales_group")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalesGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_group_id")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    private User user;

    public SalesGroup (String name, User user){
        this.name = name;
        this.user = user;
    }
}
