package org.docencia.hotel.persistence.nosql.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "guest_preferences")
public class GuestPreferencesDocument {

    @Id
    private String id;

    private String guestId;

    private String roomType;

    private String specialRequests;

    /**
     * Constructor con todos los parametros
     * 
     * @param id              id de las preferencias
     * @param guestId         id del huesped
     * @param roomType        tipo de la habitacion
     * @param specialRequests peticiones especiales
     */
    public GuestPreferencesDocument(String id, String guestId, String roomType, String specialRequests) {
        this.id = id;
        this.guestId = guestId;
        this.roomType = roomType;
        this.specialRequests = specialRequests;
    }

    /**
     * Constructor con id
     * 
     * @param id id de las preferencias
     */
    public GuestPreferencesDocument(String id) {
        this.id = id;
    }

    /**
     * Constructor vacio
     */
    public GuestPreferencesDocument() {
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

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
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
        GuestPreferencesDocument other = (GuestPreferencesDocument) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
