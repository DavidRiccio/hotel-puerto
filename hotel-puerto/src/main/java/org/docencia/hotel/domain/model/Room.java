package org.docencia.hotel.domain.model;

public class Room {
   private String id;
   private Integer number;
   private String type;
   private float price_per_night;
   private String hotelId;

    /**
     * Constructor con todos los parametros
     * 
     * @param id              id de la habitacion
     * @param number          numero de la habitacion
     * @param type            tipo de la habitacion
     * @param price_per_night precio de la habitacion
     * @param hotel           id del hotel al que pertenece
     */
    public Room(String id, Integer number, String type, float price_per_night, String hotelId) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.price_per_night = price_per_night;
        this.hotelId = hotelId;
    }

    /**
     * Constructor con el id
     * 
     * @param id id de la habitacion
     */
    public Room(String id) {
        this.id = id;
    }

    /**
     * Constructor vacio
     */
    public Room() {
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

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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
        Room other = (Room) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
