package com.rhythm.usercrossings.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rhythm.usercrossings.models.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "crossings")
@Getter
@Setter
public class Crossing extends BaseEntity {

    @Column(name = "time")
    private Long time;

    @Column(name = "location")
    private String location;

    @Column(name = "enter")
    private Boolean enter;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Crossing)) return false;
        if (!super.equals(o)) return false;
        Crossing crossing = (Crossing) o;
        return Objects.equals(time, crossing.time) &&
                Objects.equals(location, crossing.location) &&
                Objects.equals(enter, crossing.enter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), time, location, enter);
    }
}
