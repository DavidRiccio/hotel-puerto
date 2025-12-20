package org.docencia.hotel.domain.model;

public class Booking {
    private String id;
    private String roomId;
    private String guestId;
    private String check_in;
    private String check_out;

    /**
     * Constructor con todos los parametros
     * 
     * @param id        id de la reserva
     * @param room      habitacion reservada
     * @param guest     huesped que reserva
     * @param check_in  dia de check in
     * @param check_out dia de check out
     */
    public Booking(String id, String roomId, String guestId, String check_in, String check_out) {
        this.id = id;
        this.roomId = roomId;
        this.guestId = guestId;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    /**
     * Constructor con id
     * 
     * @param id id de la reserva
     */
    public Booking(String id) {
        this.id = id;
    }

    /**
     * Constructor vacio
     */
    public Booking() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
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
        Booking other = (Booking) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
