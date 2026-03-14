package dao;

import model.NhanVien;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO implements BaseDAO<NhanVien> {

    @Override
    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM nhan_vien";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new NhanVien(
                        rs.getInt("id_nhan_vien"),
                        rs.getString("ho_ten"),
                        rs.getString("gioi_tinh"),
                        rs.getInt("tuoi"),
                        rs.getDouble("luong"),
                        rs.getInt("thoi_gian_gan_bo"),
                        rs.getInt("diem_thuong"),
                        rs.getInt("diem_danh"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"),
                        rs.getString("chuc_vu"),
                        rs.getDate("ngay_vao_lam")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public NhanVien getById(int id) {
        String sql = "SELECT * FROM nhan_vien WHERE id_nhan_vien = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new NhanVien(
                        rs.getInt("id_nhan_vien"),
                        rs.getString("ho_ten"),
                        rs.getString("gioi_tinh"),
                        rs.getInt("tuoi"),
                        rs.getDouble("luong"),
                        rs.getInt("thoi_gian_gan_bo"),
                        rs.getInt("diem_thuong"),
                        rs.getInt("diem_danh"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"),
                        rs.getString("chuc_vu"),
                        rs.getDate("ngay_vao_lam")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO nhan_vien(ho_ten, gioi_tinh, tuoi, luong, thoi_gian_gan_bo, diem_thuong, diem_danh, so_dien_thoai, dia_chi, chuc_vu, ngay_vao_lam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nv.getHo_ten());
            ps.setString(2, nv.getGioi_tinh());
            ps.setInt(3, nv.getTuoi());
            ps.setDouble(4, nv.getLuong());
            ps.setInt(5, nv.getThoi_gian_gan_bo());
            ps.setInt(6, nv.getDiem_thuong());
            ps.setInt(7, nv.getDiem_danh());
            ps.setString(8, nv.getSo_dien_thoai());
            ps.setString(9, nv.getDia_chi());
            ps.setString(10, nv.getChuc_vu());
            ps.setDate(11, nv.getNgay_vao_lam());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(NhanVien nv) {
        String sql = "UPDATE nhan_vien SET ho_ten=?, gioi_tinh=?, tuoi=?, luong=?, thoi_gian_gan_bo=?, diem_thuong=?, diem_danh=?, so_dien_thoai=?, dia_chi=?, chuc_vu=?, ngay_vao_lam=? WHERE id_nhan_vien=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nv.getHo_ten());
            ps.setString(2, nv.getGioi_tinh());
            ps.setInt(3, nv.getTuoi());
            ps.setDouble(4, nv.getLuong());
            ps.setInt(5, nv.getThoi_gian_gan_bo());
            ps.setInt(6, nv.getDiem_thuong());
            ps.setInt(7, nv.getDiem_danh());
            ps.setString(8, nv.getSo_dien_thoai());
            ps.setString(9, nv.getDia_chi());
            ps.setString(10, nv.getChuc_vu());
            ps.setDate(11, nv.getNgay_vao_lam());
            ps.setInt(12, nv.getId_nhan_vien());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM nhan_vien WHERE id_nhan_vien = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}