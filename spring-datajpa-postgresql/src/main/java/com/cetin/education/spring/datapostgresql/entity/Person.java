package com.cetin.education.spring.datapostgresql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Person {
    @Id
    @SequenceGenerator(name = "seq_person", initialValue = 1, allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
    private Long id;

    @Column(name = "Name", length = 100, nullable = false)
    private String name;

    @Column(name = "Surname", length = 100, nullable = false)
    private String surname;

    @OneToMany
    @JoinColumn(name = "person_address_id")
    private List<Address> addressList;
}
