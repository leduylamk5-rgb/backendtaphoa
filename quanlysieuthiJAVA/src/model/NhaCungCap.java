package model;

public class NhaCungCap {
    private int id_ncc;
    private String ten_ncc;
    private String so_dien_thoai;
    private String dia_chi;
    private String email;

    public NhaCungCap() {
    }

    public NhaCungCap(int id_ncc, String ten_ncc, String so_dien_thoai, String dia_chi, String email) {
        this.id_ncc = id_ncc;
        this.ten_ncc = ten_ncc;
        this.so_dien_thoai = so_dien_thoai;
        this.dia_chi = dia_chi;
        this.email = email;
    }

    public int getId_ncc() {
        return id_ncc;
    }

    public void setId_ncc(int id_ncc) {
        this.id_ncc = id_ncc;
    }

    public String getTen_ncc() {
        return ten_ncc;
    }

    public void setTen_ncc(String ten_ncc) {
        this.ten_ncc = ten_ncc;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "NhaCungCap{" +
                "id_ncc=" + id_ncc +
                ", ten_ncc='" + ten_ncc + '\'' +
                ", so_dien_thoai='" + so_dien_thoai + '\'' +
                ", dia_chi='" + dia_chi + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}