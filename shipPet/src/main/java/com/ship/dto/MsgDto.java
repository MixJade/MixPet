package com.ship.dto;

import com.ship.entity.Msg;

public class MsgDto extends Msg {
    /**
     * 用户姓名
     */
    private String clientName;
    /**
     * 用户照片名称
     */
    private String clientPhoto;
    /**
     * 医生姓名
     */
    private String doctorName;
    /**
     * 医生照片名称
     */
    private String doctorPhoto;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhoto() {
        return clientPhoto;
    }

    public void setClientPhoto(String clientPhoto) {
        this.clientPhoto = clientPhoto;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhoto() {
        return doctorPhoto;
    }

    public void setDoctorPhoto(String doctorPhoto) {
        this.doctorPhoto = doctorPhoto;
    }
}
