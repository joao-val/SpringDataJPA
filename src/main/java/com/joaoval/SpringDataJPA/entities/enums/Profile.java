package com.joaoval.SpringDataJPA.entities.enums;

public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private int cod;
    private String description;

    Profile(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Profile toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Profile x : Profile.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid ID: " + cod);
    }

}
