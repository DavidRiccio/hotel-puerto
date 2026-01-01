package org.docencia.hotel.persistence.jpa.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "guest")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name = "full_name")
    String full_name;
    @Column(name = "email")
    String email;
    @Column(name = "phone")
    String phone;
    @Column(name = "preferences_id")
    String preferencesId;
    @OneToMany(mappedBy = "guest")
    List<BookingEntity> bookings;

    /**
     * Constructor con todos los parametros
     * 
     * @param id            id del huesped
     * @param full_name     nombre completo del huesped
     * @param email         email de huesped
     * @param phone         telefono del huesped
     * @param preferencesId id de las preferencias del huesped
     * @param bookings      reservas del huesped
     */
    public GuestEntity(String id, String full_name, String email, String phone, String preferencesId,
            List<BookingEntity> bookings) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.preferencesId = preferencesId;
        this.bookings = bookings;
    }

    /**
     * Constructor con id
     * 
     * @param id id del huesped
     */
    public GuestEntity(String id) {
        this.id = id;
    }

    /**
     * Constructor vacio
     */
    public GuestEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPreferencesId() {
        return preferencesId;
    }

    public void setPreferencesId(String preferencesId) {
        this.preferencesId = preferencesId;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GuestEntity other = (GuestEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
