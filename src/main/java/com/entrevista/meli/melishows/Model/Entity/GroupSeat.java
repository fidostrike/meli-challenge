package com.entrevista.meli.melishows.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SQLDelete(sql = "UPDATE GroupSeat SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
public class GroupSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "groupSeat")
    private Set<Price> lstPrice = new HashSet<>();

    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;
}
