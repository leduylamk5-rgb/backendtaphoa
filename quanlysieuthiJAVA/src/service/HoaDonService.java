package service;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.SanPhamDAO;
import model.ChiTietHoaDon;
import model.HoaDon;
import model.KhachHang;
import model.SanPham;

import java.util.List;

public class HoaDonService {
    private HoaDonDAO hoaDonDAO;
    private ChiTietHoaDonDAO chiTietHoaDonDAO;
    private SanPhamDAO sanPhamDAO;
    private KhachHangDAO khachHangDAO;

    public HoaDonService() {
        hoaDonDAO = new HoaDonDAO();
        chiTietHoaDonDAO = new ChiTietHoaDonDAO();
        sanPhamDAO = new SanPhamDAO();
        khachHangDAO = new KhachHangDAO();
    }

    public List<HoaDon> getAllHoaDon() {
        return hoaDonDAO.getAll();
    }

    public HoaDon getHoaDonById(int idHoaDon) {
        if (idHoaDon <= 0) {
            return null;
        }
        return hoaDonDAO.getById(idHoaDon);
    }

    public List<ChiTietHoaDon> getChiTietHoaDon(int idHoaDon) {
        if (idHoaDon <= 0) {
            return null;
        }
        return chiTietHoaDonDAO.getByHoaDonId(idHoaDon);
    }

    public double tinhTongTien(List<ChiTietHoaDon> dsChiTiet) {
        double tong = 0;

        if (dsChiTiet == null || dsChiTiet.isEmpty()) {
            return tong;
        }

        for (ChiTietHoaDon ct : dsChiTiet) {
            tong += ct.getThanh_tien();
        }

        return tong;
    }

    public boolean kiemTraDanhSachChiTiet(List<ChiTietHoaDon> dsChiTiet) {
        if (dsChiTiet == null || dsChiTiet.isEmpty()) {
            return false;
        }

        for (ChiTietHoaDon ct : dsChiTiet) {
            if (ct == null) {
                return false;
            }

            if (ct.getId_sp() <= 0 || ct.getSo_luong() <= 0 || ct.getDon_gia() < 0 || ct.getThanh_tien() < 0) {
                return false;
            }

            SanPham sp = sanPhamDAO.getById(ct.getId_sp());
            if (sp == null) {
                return false;
            }

            if (sp.getSoLuongSp() < ct.getSo_luong()) {
                return false;
            }
        }

        return true;
    }

    public boolean taoHoaDon(HoaDon hoaDon, List<ChiTietHoaDon> dsChiTiet) {
        if (hoaDon == null) {
            return false;
        }

        if (!kiemTraDanhSachChiTiet(dsChiTiet)) {
            return false;
        }

        double tongTien = tinhTongTien(dsChiTiet);
        hoaDon.setTong_tien(tongTien);

        int idHoaDon = hoaDonDAO.insertAndGetId(hoaDon);
        if (idHoaDon == -1) {
            return false;
        }

        for (ChiTietHoaDon ct : dsChiTiet) {
            ct.setId_hoa_don(idHoaDon);

            boolean themChiTiet = chiTietHoaDonDAO.insert(ct);
            if (!themChiTiet) {
                return false;
            }

            SanPham sp = sanPhamDAO.getById(ct.getId_sp());
            if (sp == null) {
                return false;
            }

            int soLuongMoi = sp.getSoLuongSp() - ct.getSo_luong();
            boolean capNhatKho = sanPhamDAO.updateSoLuong(sp.getIdSp(), soLuongMoi);

            if (!capNhatKho) {
                return false;
            }
        }

        if (hoaDon.getId_kh() > 0) {
            congDiemThuongKhachHang(hoaDon.getId_kh(), tongTien);
        }

        return true;
    }

    public boolean xoaHoaDon(int idHoaDon) {
        if (idHoaDon <= 0) {
            return false;
        }

        List<ChiTietHoaDon> dsChiTiet = chiTietHoaDonDAO.getByHoaDonId(idHoaDon);

        if (dsChiTiet != null) {
            for (ChiTietHoaDon ct : dsChiTiet) {
                SanPham sp = sanPhamDAO.getById(ct.getId_sp());
                if (sp != null) {
                    int soLuongMoi = sp.getSoLuongSp() + ct.getSo_luong();
                    sanPhamDAO.updateSoLuong(sp.getIdSp(), soLuongMoi);
                }
            }
        }

        chiTietHoaDonDAO.deleteByHoaDonId(idHoaDon);
        return hoaDonDAO.delete(idHoaDon);
    }

    public boolean congDiemThuongKhachHang(int idKh, double tongTienHoaDon) {
        if (idKh <= 0 || tongTienHoaDon <= 0) {
            return false;
        }

        KhachHang kh = khachHangDAO.getById(idKh);
        if (kh == null) {
            return false;
        }

        int diemCong = (int) (tongTienHoaDon / 10000);
        kh.setDiem_thuong_kh(kh.getDiem_thuong_kh() + diemCong);

        return khachHangDAO.update(kh);
    }
}