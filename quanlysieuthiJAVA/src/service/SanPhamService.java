package service;

import dao.SanPhamDAO;
import model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamService {
    private SanPhamDAO sanPhamDAO;

    public SanPhamService() {
        sanPhamDAO = new SanPhamDAO();
    }

    public List<SanPham> getAllSanPham() {
        return sanPhamDAO.getAll();
    }

    public SanPham getSanPhamById(int idSp) {
        if (idSp <= 0) {
            return null;
        }
        return sanPhamDAO.getById(idSp);
    }

    public boolean themSanPham(SanPham sp) {
        if (!kiemTraSanPhamHopLe(sp)) {
            return false;
        }

        return sanPhamDAO.insert(sp);
    }

    public boolean suaSanPham(SanPham sp) {
        if (sp == null || sp.getIdSp() <= 0) {
            return false;
        }

        if (!kiemTraSanPhamHopLe(sp)) {
            return false;
        }

        return sanPhamDAO.update(sp);
    }

    public boolean xoaSanPham(int idSp) {
        if (idSp <= 0) {
            return false;
        }

        return sanPhamDAO.delete(idSp);
    }

    public boolean capNhatSoLuong(int idSp, int soLuongMoi) {
        if (idSp <= 0 || soLuongMoi < 0) {
            return false;
        }

        return sanPhamDAO.updateSoLuong(idSp, soLuongMoi);
    }

    public boolean kiemTraTonKho(int idSp, int soLuongCanMua) {
        if (idSp <= 0 || soLuongCanMua <= 0) {
            return false;
        }

        SanPham sp = sanPhamDAO.getById(idSp);
        if (sp == null) {
            return false;
        }

        return sp.getSoLuongSp() >= soLuongCanMua;
    }

    public List<SanPham> timTheoTen(String tenCanTim) {
        List<SanPham> ketQua = new ArrayList<>();
        List<SanPham> ds = sanPhamDAO.getAll();

        if (tenCanTim == null || tenCanTim.trim().isEmpty()) {
            return ketQua;
        }

        String key = tenCanTim.trim().toLowerCase();

        for (SanPham sp : ds) {
            if (sp.getTenSp() != null && sp.getTenSp().toLowerCase().contains(key)) {
                ketQua.add(sp);
            }
        }

        return ketQua;
    }

    public List<SanPham> locTheoLoai(int idLoai) {
        List<SanPham> ketQua = new ArrayList<>();
        List<SanPham> ds = sanPhamDAO.getAll();

        for (SanPham sp : ds) {
            if (sp.getIdLoai() == idLoai) {
                ketQua.add(sp);
            }
        }

        return ketQua;
    }

    private boolean kiemTraSanPhamHopLe(SanPham sp) {
        if (sp == null) {
            return false;
        }

        if (sp.getTenSp() == null || sp.getTenSp().trim().isEmpty()) {
            return false;
        }

        if (sp.getGiaSp() < 0) {
            return false;
        }

        if (sp.getSoLuongSp() < 0) {
            return false;
        }

        if (sp.getIdLoai() <= 0) {
            return false;
        }

        return true;
    }
}