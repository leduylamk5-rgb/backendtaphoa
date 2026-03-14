package service;

import dao.ChiTietPhieuNhapDAO;
import dao.PhieuNhapDAO;
import dao.SanPhamDAO;
import model.ChiTietPhieuNhap;
import model.PhieuNhap;
import model.SanPham;

import java.util.List;

public class PhieuNhapService {
    private PhieuNhapDAO phieuNhapDAO;
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;
    private SanPhamDAO sanPhamDAO;

    public PhieuNhapService() {
        phieuNhapDAO = new PhieuNhapDAO();
        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
        sanPhamDAO = new SanPhamDAO();
    }

    public List<PhieuNhap> getAllPhieuNhap() {
        return phieuNhapDAO.getAll();
    }

    public PhieuNhap getPhieuNhapById(int idPhieuNhap) {
        if (idPhieuNhap <= 0) {
            return null;
        }
        return phieuNhapDAO.getById(idPhieuNhap);
    }

    public List<ChiTietPhieuNhap> getChiTietPhieuNhap(int idPhieuNhap) {
        if (idPhieuNhap <= 0) {
            return null;
        }
        return chiTietPhieuNhapDAO.getByPhieuNhapId(idPhieuNhap);
    }

    public double tinhTongTienNhap(List<ChiTietPhieuNhap> dsChiTiet) {
        double tong = 0;

        if (dsChiTiet == null || dsChiTiet.isEmpty()) {
            return tong;
        }

        for (ChiTietPhieuNhap ct : dsChiTiet) {
            tong += ct.getThanh_tien();
        }

        return tong;
    }

    public boolean kiemTraDanhSachChiTietNhap(List<ChiTietPhieuNhap> dsChiTiet) {
        if (dsChiTiet == null || dsChiTiet.isEmpty()) {
            return false;
        }

        for (ChiTietPhieuNhap ct : dsChiTiet) {
            if (ct == null) {
                return false;
            }

            if (ct.getId_sp() <= 0 || ct.getSo_luong() <= 0 || ct.getDon_gia_nhap() < 0 || ct.getThanh_tien() < 0) {
                return false;
            }

            SanPham sp = sanPhamDAO.getById(ct.getId_sp());
            if (sp == null) {
                return false;
            }
        }

        return true;
    }

    public boolean taoPhieuNhap(PhieuNhap phieuNhap, List<ChiTietPhieuNhap> dsChiTiet) {
        if (phieuNhap == null) {
            return false;
        }

        if (!kiemTraDanhSachChiTietNhap(dsChiTiet)) {
            return false;
        }

        double tongTien = tinhTongTienNhap(dsChiTiet);
        phieuNhap.setTong_tien(tongTien);

        int idPhieuNhap = phieuNhapDAO.insertAndGetId(phieuNhap);
        if (idPhieuNhap == -1) {
            return false;
        }

        for (ChiTietPhieuNhap ct : dsChiTiet) {
            ct.setId_phieu_nhap(idPhieuNhap);

            boolean themChiTiet = chiTietPhieuNhapDAO.insert(ct);
            if (!themChiTiet) {
                return false;
            }

            SanPham sp = sanPhamDAO.getById(ct.getId_sp());
            if (sp == null) {
                return false;
            }

            int soLuongMoi = sp.getSoLuongSp() + ct.getSo_luong();
            boolean capNhatKho = sanPhamDAO.updateSoLuong(sp.getIdSp(), soLuongMoi);

            if (!capNhatKho) {
                return false;
            }
        }

        return true;
    }

    public boolean xoaPhieuNhap(int idPhieuNhap) {
        if (idPhieuNhap <= 0) {
            return false;
        }

        List<ChiTietPhieuNhap> dsChiTiet = chiTietPhieuNhapDAO.getByPhieuNhapId(idPhieuNhap);

        if (dsChiTiet != null) {
            for (ChiTietPhieuNhap ct : dsChiTiet) {
                SanPham sp = sanPhamDAO.getById(ct.getId_sp());
                if (sp != null) {
                    int soLuongMoi = sp.getSoLuongSp() - ct.getSo_luong();
                    if (soLuongMoi < 0) {
                        soLuongMoi = 0;
                    }
                    sanPhamDAO.updateSoLuong(sp.getIdSp(), soLuongMoi);
                }
            }
        }

        chiTietPhieuNhapDAO.deleteByPhieuNhapId(idPhieuNhap);
        return phieuNhapDAO.delete(idPhieuNhap);
    }
}