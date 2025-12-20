package org.docencia.hotel.domain.model;

public class GuestPreferences {
    private String id;
    private String guestId;
    private String roomType;
    private String specialRequest;

    /**
     * Constructor con todos los parametros
     * 
     * @param id              id de las preferencias
     * @param guestId         id del huesped
     * @param roomType        tipo de la habitacion
     * @param specialRequests peticiones especiales
     */
    public GuestPreferences(String id, String guestId, String roomType, String specialRequest) {
        this.id = id;
        this.guestId = guestId;
        this.roomType = roomType;
        this.specialRequest = specialRequest;
    }

    /**
     * Constructor con id
     * 
     * @param id id de las preferencias
     */
    public GuestPreferences(String id) {
        this.id = id;
    }

    /**
     * Constructor vacio
     */
    public GuestPreferences() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
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
        GuestPreferences other = (GuestPreferences) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
