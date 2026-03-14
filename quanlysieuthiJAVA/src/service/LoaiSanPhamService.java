package service;

import dao.LoaiSanPhamDAO;
import model.LoaiSanPham;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamService {
    private LoaiSanPhamDAO loaiSanPhamDAO;

    public LoaiSanPhamService() {
        loaiSanPhamDAO = new LoaiSanPhamDAO();
    }

    public List<LoaiSanPham> getAllLoaiSanPham() {
        return loaiSanPhamDAO.getAll();
    }

    public LoaiSanPham getLoaiSanPhamById(int id) {
        if (id <= 0) {
            return null;
        }
        return loaiSanPhamDAO.getById(id);
    }

    public boolean themLoaiSanPham(LoaiSanPham loai) {
        if (!kiemTraLoaiHopLe(loai)) {
            return false;
        }
        return loaiSanPhamDAO.insert(loai);
    }

    public boolean suaLoaiSanPham(LoaiSanPham loai) {
        if (loai == null || loai.getId_loai() <= 0) {
            return false;
        }
        if (!kiemTraLoaiHopLe(loai)) {
            return false;
        }
        return loaiSanPhamDAO.update(loai);
    }

    public boolean xoaLoaiSanPham(int id) {
        if (id <= 0) {
            return false;
        }
        return loaiSanPhamDAO.delete(id);
    }

    public List<LoaiSanPham> timTheoTenLoai(String tenCanTim) {
        List<LoaiSanPham> ketQua = new ArrayList<>();
        List<LoaiSanPham> ds = loaiSanPhamDAO.getAll();

        if (tenCanTim == null || tenCanTim.trim().isEmpty()) {
            return ketQua;
        }

        String key = tenCanTim.trim().toLowerCase();

        for (LoaiSanPham loai : ds) {
            if (loai.getTen_loai() != null && loai.getTen_loai().toLowerCase().contains(key)) {
                ketQua.add(loai);
            }
        }

        return ketQua;
    }

    private boolean kiemTraLoaiHopLe(LoaiSanPham loai) {
        if (loai == null) {
            return false;
        }
        return loai.getTen_loai() != null && !loai.getTen_loai().trim().isEmpty();
    }
}