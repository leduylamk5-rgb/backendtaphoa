package model;

import java.sql.Timestamp;

public class PhieuNhap {
    private int id_phieu_nhap;
    private int id_ncc;
    private int id_nhan_vien;
    private Timestamp ngay_nhap;
    private double tong_tien;
    private String ghi_chu;

    public PhieuNhap() {
    }

    public PhieuNhap(int id_phieu_nhap, int id_ncc, int id_nhan_vien, Timestamp ngay_nhap, double tong_tien, String ghi_chu) {
        this.id_phieu_nhap = id_phieu_nhap;
        this.id_ncc = id_ncc;
        this.id_nhan_vien = id_nhan_vien;
        this.ngay_nhap = ngay_nhap;
        this.tong_tien = tong_tien;
        this.ghi_chu = ghi_chu;
    }

    public int getId_phieu_nhap() {
        return id_phieu_nhap;
    }

    public void setId_phieu_nhap(int id_phieu_nhap) {
        this.id_phieu_nhap = id_phieu_nhap;
    }

    public int getId_ncc() {
        return id_ncc;
    }

    public void setId_ncc(int id_ncc) {
        this.id_ncc = id_ncc;
    }

    public int getId_nhan_vien() {
        return id_nhan_vien;
    }

    public void setId_nhan_vien(int id_nhan_vien) {
        this.id_nhan_vien = id_nhan_vien;
    }

    public Timestamp getNgay_nhap() {
        return ngay_nhap;
    }

    public void setNgay_nhap(Timestamp ngay_nhap) {
        this.ngay_nhap = ngay_nhap;
    }

    public double getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(double tong_tien) {
        this.tong_tien = tong_tien;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    @Override
    public String toString() {
        return "PhieuNhap{" +
                "id_phieu_nhap=" + id_phieu_nhap +
                ", id_ncc=" + id_ncc +
                ", id_nhan_vien=" + id_nhan_vien +
                ", ngay_nhap=" + ngay_nhap +
                ", tong_tien=" + tong_tien +
                ", ghi_chu='" + ghi_chu + '\'' +
                '}';
    }
}