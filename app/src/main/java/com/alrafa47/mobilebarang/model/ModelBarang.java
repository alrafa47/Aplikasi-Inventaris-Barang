package com.alrafa47.mobilebarang.model;

public class ModelBarang {

    private String nama;
    private String jumlah;
    private String harga;
    private String status;
    private String idBarang;

    public ModelBarang(String idBarang, String nama, String jumlah, String harga, String status) {
        this.idBarang = idBarang;
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
        this.status = status;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
