package com.triwahyuprasetyo.sqliteservice.database;

import java.util.ArrayList;

/**
 * Created by why on 7/31/16.
 */

public class AnggotaDbService {
    private AnggotaDbDao anggotaDbDao;

    public AnggotaDbService(AnggotaDbDao anggotaDbDao) {
        this.anggotaDbDao = anggotaDbDao;
    }

    public void insertAnggota(Anggota anggota) {
        anggotaDbDao.insertAnggota(anggota);
    }

    public ArrayList<Anggota> getAllAnggota() {
        ArrayList<Anggota> anggotaList = anggotaDbDao.getAllAnggota();
        return anggotaList;
    }

    public Anggota getAnggotaById(int anggId) {
        Anggota anggota = anggotaDbDao.getAnggotaById(anggId);
        return anggota;
    }

    public void updateAnggota(Anggota anggota) {
        anggotaDbDao.updateAnggota(anggota);
    }

    public void deleteAnggota(int anggotaId) {
        anggotaDbDao.deleteAnggota(anggotaId);
    }
}
