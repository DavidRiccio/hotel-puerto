package org.docencia.hotel.persistence.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    GuestEntity guest;

    @Column(name = "check_in")
    String check_in;

    @Column(name = "check_out")
    String check_out;

    /**
     * Constructor con todos los parametros
     * 
     * @param id        id de la reserva
     * @param room      habitacion reservada
     * @param guest     huesped que reserva
     * @param check_in  dia de check in
     * @param check_out dia de check out
     */
    public BookingEntity(String id, RoomEntity room, GuestEntity guest, String check_in, String check_out) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    /**
     * Constructor con id
     * 
     * @param id id de la reserva
     */
    public BookingEntity(String id) {
        this.id = id;
    }

    /**
     * Constructor vacio
     */
    public BookingEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public GuestEntity getGuest() {
        return guest;
    }

    public void setGuest(GuestEntity guest) {
        this.guest = guest;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
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
        BookingEntity other = (BookingEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
