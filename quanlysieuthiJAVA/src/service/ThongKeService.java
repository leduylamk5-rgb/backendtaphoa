package service;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import model.ChiTietHoaDon;
import model.HoaDon;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ThongKeService {
    private HoaDonDAO hoaDonDAO;
    private ChiTietHoaDonDAO chiTietHoaDonDAO;

    public ThongKeService() {
        hoaDonDAO = new HoaDonDAO();
        chiTietHoaDonDAO = new ChiTietHoaDonDAO();
    }

    public double tinhTongDoanhThu() {
        double tong = 0;
        List<HoaDon> dsHoaDon = hoaDonDAO.getAll();

        for (HoaDon hd : dsHoaDon) {
            tong += hd.getTong_tien();
        }

        return tong;
    }

    public int demSoLuongHoaDon() {
        return hoaDonDAO.getAll().size();
    }

    public int demTongSoLuongSanPhamDaBan() {
        int tong = 0;
        List<HoaDon> dsHoaDon = hoaDonDAO.getAll();

        for (HoaDon hd : dsHoaDon) {
            List<ChiTietHoaDon> dsChiTiet = chiTietHoaDonDAO.getByHoaDonId(hd.getId_hoa_don());
            for (ChiTietHoaDon ct : dsChiTiet) {
                tong += ct.getSo_luong();
            }
        }

        return tong;
    }

    public double tinhDoanhThuTheoNgay(int ngay, int thang, int nam) {
        double tong = 0;
        List<HoaDon> dsHoaDon = hoaDonDAO.getAll();

        for (HoaDon hd : dsHoaDon) {
            Timestamp ts = hd.getNgay_lap();
            if (ts != null) {
                @SuppressWarnings("deprecation")
                int d = ts.getDate();
                @SuppressWarnings("deprecation")
                int m = ts.getMonth() + 1;
                @SuppressWarnings("deprecation")
                int y = ts.getYear() + 1900;

                if (d == ngay && m == thang && y == nam) {
                    tong += hd.getTong_tien();
                }
            }
        }

        return tong;
    }

    public double tinhDoanhThuTheoThang(int thang, int nam) {
        double tong = 0;
        List<HoaDon> dsHoaDon = hoaDonDAO.getAll();

        for (HoaDon hd : dsHoaDon) {
            Timestamp ts = hd.getNgay_lap();
            if (ts != null) {
                @SuppressWarnings("deprecation")
                int m = ts.getMonth() + 1;
                @SuppressWarnings("deprecation")
                int y = ts.getYear() + 1900;

                if (m == thang && y == nam) {
                    tong += hd.getTong_tien();
                }
            }
        }

        return tong;
    }

    public List<HoaDon> layHoaDonTheoThang(int thang, int nam) {
        List<HoaDon> ketQua = new ArrayList<>();
        List<HoaDon> dsHoaDon = hoaDonDAO.getAll();

        for (HoaDon hd : dsHoaDon) {
            Timestamp ts = hd.getNgay_lap();
            if (ts != null) {
                @SuppressWarnings("deprecation")
                int m = ts.getMonth() + 1;
                @SuppressWarnings("deprecation")
                int y = ts.getYear() + 1900;

                if (m == thang && y == nam) {
                    ketQua.add(hd);
                }
            }
        }

        return ketQua;
    }
}