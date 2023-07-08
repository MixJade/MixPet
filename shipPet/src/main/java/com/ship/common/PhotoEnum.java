package com.ship.common;

public enum PhotoEnum {
    PET("defaultPet.jpg"), CLIENT("zs.jpg"), DOCTOR("defaultDoctor.jpg");
    private final String photoName;

    PhotoEnum(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoName() {
        return photoName;
    }
}
