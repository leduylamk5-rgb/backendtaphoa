package model;

import java.sql.Timestamp;

public class HoaDon {
    private int id_hoa_don;
    private int id_kh;
    private int id_nhan_vien;
    private Timestamp ngay_lap;
    private double tong_tien;
    private double giam_gia;
    private double thue;
    private String phuong_thuc_thanh_toan;
    private String trang_thai;
    private String ghi_chu;

    public HoaDon() {
    }

    public HoaDon(int id_hoa_don, int id_kh, int id_nhan_vien, Timestamp ngay_lap,
                  double tong_tien, double giam_gia, double thue,
                  String phuong_thuc_thanh_toan, String trang_thai, String ghi_chu) {
        this.id_hoa_don = id_hoa_don;
        this.id_kh = id_kh;
        this.id_nhan_vien = id_nhan_vien;
        this.ngay_lap = ngay_lap;
        this.tong_tien = tong_tien;
        this.giam_gia = giam_gia;
        this.thue = thue;
        this.phuong_thuc_thanh_toan = phuong_thuc_thanh_toan;
        this.trang_thai = trang_thai;
        this.ghi_chu = ghi_chu;
    }

    public int getId_hoa_don() {
        return id_hoa_don;
    }

    public void setId_hoa_don(int id_hoa_don) {
        this.id_hoa_don = id_hoa_don;
    }

    public int getId_kh() {
        return id_kh;
    }

    public void setId_kh(int id_kh) {
        this.id_kh = id_kh;
    }

    public int getId_nhan_vien() {
        return id_nhan_vien;
    }

    public void setId_nhan_vien(int id_nhan_vien) {
        this.id_nhan_vien = id_nhan_vien;
    }

    public Timestamp getNgay_lap() {
        return ngay_lap;
    }

    public void setNgay_lap(Timestamp ngay_lap) {
        this.ngay_lap = ngay_lap;
    }

    public double getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(double tong_tien) {
        this.tong_tien = tong_tien;
    }

    public double getGiam_gia() {
        return giam_gia;
    }

    public void setGiam_gia(double giam_gia) {
        this.giam_gia = giam_gia;
    }

    public double getThue() {
        return thue;
    }

    public void setThue(double thue) {
        this.thue = thue;
    }

    public String getPhuong_thuc_thanh_toan() {
        return phuong_thuc_thanh_toan;
    }

    public void setPhuong_thuc_thanh_toan(String phuong_thuc_thanh_toan) {
        this.phuong_thuc_thanh_toan = phuong_thuc_thanh_toan;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "id_hoa_don=" + id_hoa_don +
                ", id_kh=" + id_kh +
                ", id_nhan_vien=" + id_nhan_vien +
                ", ngay_lap=" + ngay_lap +
                ", tong_tien=" + tong_tien +
                ", giam_gia=" + giam_gia +
                ", thue=" + thue +
                ", phuong_thuc_thanh_toan='" + phuong_thuc_thanh_toan + '\'' +
                ", trang_thai='" + trang_thai + '\'' +
                ", ghi_chu='" + ghi_chu + '\'' +
                '}';
    }
}