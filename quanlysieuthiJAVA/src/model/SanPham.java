package model;

import java.util.Date;

public class SanPham {
    private int idSp;
    private String tenSp;
    private double giaSp;
    private int soLuongSp;
    private int idLoai;
    private String donViTinh;
    private String moTa;
    private Date hanSuDung;
    private Date ngayNhap;
    private String trangThai;

    public SanPham () {
    }

    public SanPham 	(int idSp, String tenSp, double giaSp, int soLuongSp, int idLoai, 
                   String donViTinh, String moTa, Date hanSuDung, Date ngayNhap, String trangThai) {
        this.idSp = idSp;
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.soLuongSp = soLuongSp;
        this.idLoai = idLoai;
        this.donViTinh = donViTinh;
        this.moTa = moTa;
        this.hanSuDung = hanSuDung;
        this.ngayNhap = ngayNhap;
        this.trangThai = trangThai;
    }

    public int getIdSp() {
        return idSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public double getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(double giaSp) {
        this.giaSp = giaSp;
    }

    public int getSoLuongSp() {
        return soLuongSp;
    }

    public void setSoLuongSp(int soLuongSp) {
        this.soLuongSp = soLuongSp;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "idSp=" + idSp +
                ", tenSp='" + tenSp + '\'' +
                ", giaSp=" + giaSp +
                ", soLuongSp=" + soLuongSp +
                ", idLoai=" + idLoai +
                ", donViTinh='" + donViTinh + '\'' +
                ", moTa='" + moTa + '\'' +
                ", hanSuDung=" + hanSuDung +
                ", ngayNhap=" + ngayNhap +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}