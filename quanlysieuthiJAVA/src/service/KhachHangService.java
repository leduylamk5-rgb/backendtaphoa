package service;

import dao.KhachHangDAO;
import model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangService {
    private KhachHangDAO khachHangDAO;

    public KhachHangService() {
        khachHangDAO = new KhachHangDAO();
    }

    public List<KhachHang> getAllKhachHang() {
        return khachHangDAO.getAll();
    }

    public KhachHang getKhachHangById(int id) {
        if (id <= 0) {
            return null;
        }
        return khachHangDAO.getById(id);
    }

    public boolean themKhachHang(KhachHang kh) {
        if (!kiemTraKhachHangHopLe(kh)) {
            return false;
        }
        return khachHangDAO.insert(kh);
    }

    public boolean suaKhachHang(KhachHang kh) {
        if (kh == null || kh.getId_kh() <= 0) {
            return false;
        }
        if (!kiemTraKhachHangHopLe(kh)) {
            return false;
        }
        return khachHangDAO.update(kh);
    }

    public boolean xoaKhachHang(int id) {
        if (id <= 0) {
            return false;
        }
        return khachHangDAO.delete(id);
    }

    public List<KhachHang> timKhachHangTheoTen(String tenCanTim) {
        List<KhachHang> ketQua = new ArrayList<>();
        List<KhachHang> ds = khachHangDAO.getAll();

        if (tenCanTim == null || tenCanTim.trim().isEmpty()) {
            return ketQua;
        }

        String key = tenCanTim.trim().toLowerCase();

        for (KhachHang kh : ds) {
            if (kh.getTen_kh() != null && kh.getTen_kh().toLowerCase().contains(key)) {
                ketQua.add(kh);
            }
        }

        return ketQua;
    }

    public KhachHang timTheoSoDienThoai(String soDienThoai) {
        List<KhachHang> ds = khachHangDAO.getAll();

        if (soDienThoai == null || soDienThoai.trim().isEmpty()) {
            return null;
        }

        String sdt = soDienThoai.trim();

        for (KhachHang kh : ds) {
            if (kh.getSo_dien_thoai() != null && kh.getSo_dien_thoai().equals(sdt)) {
                return kh;
            }
        }

        return null;
    }

    public boolean congDiemThuong(int idKh, int diemCong) {
        if (idKh <= 0 || diemCong < 0) {
            return false;
        }

        KhachHang kh = khachHangDAO.getById(idKh);
        if (kh == null) {
            return false;
        }

        kh.setDiem_thuong_kh(kh.getDiem_thuong_kh() + diemCong);
        return khachHangDAO.update(kh);
    }

    private boolean kiemTraKhachHangHopLe(KhachHang kh) {
        if (kh == null) {
            return false;
        }
        if (kh.getTen_kh() == null || kh.getTen_kh().trim().isEmpty()) {
            return false;
        }
        if (kh.getNam_sinh_kh() <= 0) {
            return false;
        }
        return true;
    }
}