package org.docencia.hotel.persistence.jpa.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "number")
    Integer number;

    @Column(name = "type")
    String type;

    @Column(name = "price_per_night")
    float price_per_night;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    HotelEntity hotel;

    @OneToMany(mappedBy = "room")
    List<BookingEntity> bookings;

    /**
     * Constructor con todos los parametros
     * 
     * @param id              id de la habitacion
     * @param number          numero de la habitacion
     * @param type            tipo de la habitacion
     * @param price_per_night precio de la habitacion
     * @param hotel           id del hotel al que pertenece
     */
    public RoomEntity(String id, Integer number, String type, float price_per_night, HotelEntity hotel) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.price_per_night = price_per_night;
        this.hotel = hotel;
    }

    /**
     * Constructor con el id
     * 
     * @param id id de la habitacion
     */
    public RoomEntity(String id) {
        this.id = id;
    }

    /**
     * Constructor vacio
     */
    public RoomEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice_per_night() {
        return price_per_night;
    }

    public void setPrice_per_night(float price_per_night) {
        this.price_per_night = price_per_night;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
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
        RoomEntity other = (RoomEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
