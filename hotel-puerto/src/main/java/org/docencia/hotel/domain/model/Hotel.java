package org.docencia.hotel.domain.model;

public class Hotel {

    private String id;
    private String name;
    private String address;

    /**
     * Constructor vacio
     */
    public Hotel() {
    }

    /**
     * Constructor con id
     * 
     * @param id id del hotel
     */
    public Hotel(String id) {
        this.id = id;
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id      id del hotel
     * @param name    nombre del hotel
     * @param address direccion del hotel
     */
    public Hotel(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        Hotel other = (Hotel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
