package dao;

import model.ChiTietPhieuNhap;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapDAO implements BaseDAO<ChiTietPhieuNhap> {

    @Override
    public List<ChiTietPhieuNhap> getAll() {
        List<ChiTietPhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_phieu_nhap";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ChiTietPhieuNhap(
                        rs.getInt("id_chi_tiet_nhap"),
                        rs.getInt("id_phieu_nhap"),
                        rs.getInt("id_sp"),
                        rs.getInt("so_luong"),
                        rs.getDouble("don_gia_nhap"),
                        rs.getDouble("thanh_tien")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ChiTietPhieuNhap getById(int id) {
        String sql = "SELECT * FROM chi_tiet_phieu_nhap WHERE id_chi_tiet_nhap = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ChiTietPhieuNhap(
                        rs.getInt("id_chi_tiet_nhap"),
                        rs.getInt("id_phieu_nhap"),
                        rs.getInt("id_sp"),
                        rs.getInt("so_luong"),
                        rs.getDouble("don_gia_nhap"),
                        rs.getDouble("thanh_tien")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ChiTietPhieuNhap> getByPhieuNhapId(int idPhieuNhap) {
        List<ChiTietPhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_phieu_nhap WHERE id_phieu_nhap = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPhieuNhap);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new ChiTietPhieuNhap(
                        rs.getInt("id_chi_tiet_nhap"),
                        rs.getInt("id_phieu_nhap"),
                        rs.getInt("id_sp"),
                        rs.getInt("so_luong"),
                        rs.getDouble("don_gia_nhap"),
                        rs.getDouble("thanh_tien")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean insert(ChiTietPhieuNhap ct) {
        String sql = "INSERT INTO chi_tiet_phieu_nhap(id_phieu_nhap, id_sp, so_luong, don_gia_nhap, thanh_tien) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ct.getId_phieu_nhap());
            ps.setInt(2, ct.getId_sp());
            ps.setInt(3, ct.getSo_luong());
            ps.setDouble(4, ct.getDon_gia_nhap());
            ps.setDouble(5, ct.getThanh_tien());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(ChiTietPhieuNhap ct) {
        String sql = "UPDATE chi_tiet_phieu_nhap SET id_phieu_nhap = ?, id_sp = ?, so_luong = ?, don_gia_nhap = ?, thanh_tien = ? WHERE id_chi_tiet_nhap = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ct.getId_phieu_nhap());
            ps.setInt(2, ct.getId_sp());
            ps.setInt(3, ct.getSo_luong());
            ps.setDouble(4, ct.getDon_gia_nhap());
            ps.setDouble(5, ct.getThanh_tien());
            ps.setInt(6, ct.getId_chi_tiet_nhap());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM chi_tiet_phieu_nhap WHERE id_chi_tiet_nhap = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteByPhieuNhapId(int idPhieuNhap) {
        String sql = "DELETE FROM chi_tiet_phieu_nhap WHERE id_phieu_nhap = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPhieuNhap);
            return ps.executeUpdate() >= 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}