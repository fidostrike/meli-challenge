package com.entrevista.meli.melishows.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SQLDelete(sql = "UPDATE Function SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
public class Function {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    Auditorium auditorium;

    @ManyToOne
    Show show;

    private Date dateFrom;

    private Date dateTo;

    @OneToMany(mappedBy = "auditorium")
    private Set<Seat> lstSeat = new HashSet<>();

    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;

    public Function(Auditorium auditorium, Show show) {
        this.auditorium = auditorium;
        this.show = show;
    }
}
