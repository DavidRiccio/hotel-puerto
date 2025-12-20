package org.docencia.hotel.domain.model;

public class Guest {
    private String id;
    private String full_name;
    private String email;
    private String phone;
    private String preferencesId;

    /**
     * Constructor con todos los parametros
     * 
     * @param id            id del huesped
     * @param full_name     nombre completo del huesped
     * @param email         email del huesped
     * @param phone         telefono del huesped
     * @param preferencesId id del las preferencias del huesped
     */
    public Guest(String id, String full_name, String email, String phone, String preferencesId) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.preferencesId = preferencesId;
    }

    /**
     * Constructor con id
     * 
     * @param id id del huesped
     */
    public Guest(String id) {
        this.id = id;
    }

    /**
     * Constructor vacio
     */
    public Guest() {
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
        Guest other = (Guest) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
