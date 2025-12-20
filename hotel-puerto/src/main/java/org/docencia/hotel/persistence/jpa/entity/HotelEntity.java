package org.docencia.hotel.persistence.jpa.entity;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "address")
    String address;

    @OneToMany(mappedBy = "hotel")
    List<RoomEntity> rooms;

    /**
     * Constructor con todos los parametros
     * 
     * @param id      id del hotel
     * @param name    nombre del hotel
     * @param address direccion del hotel
     */
    public HotelEntity(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    /**
     * Constructor con id
     * 
     * @param id id del hotel
     */
    public HotelEntity(String id) {
        this.id = id;
    }

    /**
     * Constructor vacio
     */
    public HotelEntity() {
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
        HotelEntity other = (HotelEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
