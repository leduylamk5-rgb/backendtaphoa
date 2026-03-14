package service;

import dao.NhanVienDAO;
import model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienService {
    private NhanVienDAO nhanVienDAO;

    public NhanVienService() {
        nhanVienDAO = new NhanVienDAO();
    }

    public List<NhanVien> getAllNhanVien() {
        return nhanVienDAO.getAll();
    }

    public NhanVien getNhanVienById(int id) {
        if (id <= 0) {
            return null;
        }
        return nhanVienDAO.getById(id);
    }

    public boolean themNhanVien(NhanVien nv) {
        if (!kiemTraNhanVienHopLe(nv)) {
            return false;
        }
        return nhanVienDAO.insert(nv);
    }

    public boolean suaNhanVien(NhanVien nv) {
        if (nv == null || nv.getId_nhan_vien() <= 0) {
            return false;
        }
        if (!kiemTraNhanVienHopLe(nv)) {
            return false;
        }
        return nhanVienDAO.update(nv);
    }

    public boolean xoaNhanVien(int id) {
        if (id <= 0) {
            return false;
        }
        return nhanVienDAO.delete(id);
    }

    public List<NhanVien> timTheoTen(String tenCanTim) {
        List<NhanVien> ketQua = new ArrayList<>();
        List<NhanVien> ds = nhanVienDAO.getAll();

        if (tenCanTim == null || tenCanTim.trim().isEmpty()) {
            return ketQua;
        }

        String key = tenCanTim.trim().toLowerCase();

        for (NhanVien nv : ds) {
            if (nv.getHo_ten() != null && nv.getHo_ten().toLowerCase().contains(key)) {
                ketQua.add(nv);
            }
        }

        return ketQua;
    }

    private boolean kiemTraNhanVienHopLe(NhanVien nv) {
        if (nv == null) {
            return false;
        }
        if (nv.getHo_ten() == null || nv.getHo_ten().trim().isEmpty()) {
            return false;
        }
        if (nv.getTuoi() <= 0) {
            return false;
        }
        if (nv.getLuong() < 0) {
            return false;
        }
        return true;
    }
}