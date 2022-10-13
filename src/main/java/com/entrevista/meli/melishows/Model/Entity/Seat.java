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

@SQLDelete(sql = "UPDATE Seat SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    @ManyToOne(optional = false , cascade = CascadeType.ALL)
    Auditorium auditorium;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    GroupSeat groupSeat;
    /*
    @OneToMany(mappedBy = "seat")
    private Set<ReservationTicket_Seat> lstReservationTicket_Seat = new HashSet<>();
*/
    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;

    public Seat(Auditorium auditorium, GroupSeat groupSeat) {
        this.auditorium = auditorium;
        this.groupSeat = groupSeat;
    }
}
