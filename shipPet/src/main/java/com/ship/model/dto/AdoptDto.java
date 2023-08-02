package com.ship.model.dto;

import com.ship.model.entity.Adopt;

public class AdoptDto extends Adopt {
    /**
     * 宠物姓名
     */
    private String petName;
    /**
     * 用户姓名
     */
    private String clientName;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
