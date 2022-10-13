package com.entrevista.meli.melishows.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@SQLDelete(sql = "UPDATE Price SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private Float price;

    @ManyToOne(cascade = CascadeType.ALL)
    Function function;

    @ManyToOne(cascade = CascadeType.ALL)
    GroupSeat groupSeat;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;

    public Price(Float price, Function function, GroupSeat groupSeat) {
        this.price = price;
        this.function = function;
        this.groupSeat = groupSeat;
    }
}
