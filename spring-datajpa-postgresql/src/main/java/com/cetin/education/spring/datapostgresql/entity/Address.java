package com.cetin.education.spring.datapostgresql.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_person_address", initialValue = 1, allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person_address")
    private Long id;

    @Column(name = "Address", length = 500, nullable = false)
    private String address;

    @Enumerated
    private AddressTp addressTp;

    @Column(name = "Is_Active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_address_id")
    private Person person;
}
