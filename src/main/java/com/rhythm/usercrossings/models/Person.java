package com.rhythm.usercrossings.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rhythm.usercrossings.models.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "people")
@Getter
@Setter
public class Person extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "passport_id")
    private Long passportId;

    @Column(name = "nationality")
    private String nationality;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Crossing> crossings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(passportId, person.passportId) &&
                Objects.equals(nationality, person.nationality);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), firstName, lastName, passportId, nationality);
    }
}
