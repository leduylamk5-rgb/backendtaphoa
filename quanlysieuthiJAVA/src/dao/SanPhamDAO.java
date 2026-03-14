package dao;

import model.SanPham;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {

    public List<SanPham> getAll() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM san_pham";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new SanPham(
                        rs.getInt("id_sp"),
                        rs.getString("ten_sp"),
                        rs.getDouble("gia_sp"),
                        rs.getInt("so_luong_sp"),
                        rs.getInt("id_loai"),
                        rs.getString("don_vi_tinh"),
                        rs.getString("mo_ta"),
                        rs.getDate("han_su_dung"),
                        rs.getDate("ngay_nhap"),
                        rs.getString("trang_thai")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public SanPham getById(int id) {
        String sql = "SELECT * FROM san_pham WHERE id_sp = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new SanPham(
                        rs.getInt("id_sp"),
                        rs.getString("ten_sp"),
                        rs.getDouble("gia_sp"),
                        rs.getInt("so_luong_sp"),
                        rs.getInt("id_loai"),
                        rs.getString("don_vi_tinh"),
                        rs.getString("mo_ta"),
                        rs.getDate("han_su_dung"),
                        rs.getDate("ngay_nhap"),
                        rs.getString("trang_thai")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(SanPham sp) {
        String sql = "INSERT INTO san_pham(ten_sp, gia_sp, so_luong_sp, id_loai, don_vi_tinh, mo_ta, han_su_dung, ngay_nhap, trang_thai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSp());
            ps.setDouble(2, sp.getGiaSp());
            ps.setInt(3, sp.getSoLuongSp());
            ps.setInt(4, sp.getIdLoai());
            ps.setString(5, sp.getDonViTinh());
            ps.setString(6, sp.getMoTa());
            ps.setDate(7, new java.sql.Date(sp.getHanSuDung().getTime()));
            ps.setDate(8, new java.sql.Date(sp.getNgayNhap().getTime()));
            ps.setString(9, sp.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(SanPham sp) {
        String sql = "UPDATE san_pham SET ten_sp=?, gia_sp=?, so_luong_sp=?, id_loai=?, don_vi_tinh=?, mo_ta=?, han_su_dung=?, ngay_nhap=?, trang_thai=? WHERE id_sp=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSp());
            ps.setDouble(2, sp.getGiaSp());
            ps.setInt(3, sp.getSoLuongSp());
            ps.setInt(4, sp.getIdLoai());
            ps.setString(5, sp.getDonViTinh());
            ps.setString(6, sp.getMoTa());
            ps.setDate(7, new java.sql.Date(sp.getHanSuDung().getTime()));
            ps.setDate(8, new java.sql.Date(sp.getNgayNhap().getTime()));
            ps.setString(9, sp.getTrangThai());
            ps.setInt(10, sp.getIdSp());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM san_pham WHERE id_sp=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSoLuong(int idSp, int soLuongMoi) {
        String sql = "UPDATE san_pham SET so_luong_sp=? WHERE id_sp=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, soLuongMoi);
            ps.setInt(2, idSp);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}