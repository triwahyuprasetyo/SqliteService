package com.triwahyuprasetyo.sqliteservice.database;

/**
 * Created by why on 7/31/16.
 */

public class Anggota {
    private int anggotaId;
    private String anggotaName;
    private String anggotaAddress;
    private int anggotaAge;

    public int getAnggotaId() {
        return anggotaId;
    }

    public void setAnggotaId(int anggotaId) {
        this.anggotaId = anggotaId;
    }

    public String getAnggotaName() {
        return anggotaName;
    }

    public void setAnggotaName(String anggotaName) {
        this.anggotaName = anggotaName;
    }

    public String getAnggotaAddress() {
        return anggotaAddress;
    }

    public void setAnggotaAddress(String anggotaAddress) {
        this.anggotaAddress = anggotaAddress;
    }

    public int getAnggotaAge() {
        return anggotaAge;
    }

    public void setAnggotaAge(int anggotaAge) {
        this.anggotaAge = anggotaAge;
    }
}
