package dao;

import model.ChiTietHoaDon;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO implements BaseDAO<ChiTietHoaDon> {

    @Override
    public List<ChiTietHoaDon> getAll() {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_hoa_don";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ChiTietHoaDon(
                        rs.getInt("id_chi_tiet"),
                        rs.getInt("id_hoa_don"),
                        rs.getInt("id_sp"),
                        rs.getInt("so_luong"),
                        rs.getDouble("don_gia"),
                        rs.getDouble("thanh_tien")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ChiTietHoaDon getById(int id) {
        String sql = "SELECT * FROM chi_tiet_hoa_don WHERE id_chi_tiet = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ChiTietHoaDon(
                        rs.getInt("id_chi_tiet"),
                        rs.getInt("id_hoa_don"),
                        rs.getInt("id_sp"),
                        rs.getInt("so_luong"),
                        rs.getDouble("don_gia"),
                        rs.getDouble("thanh_tien")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ChiTietHoaDon> getByHoaDonId(int idHoaDon) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_hoa_don WHERE id_hoa_don = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new ChiTietHoaDon(
                        rs.getInt("id_chi_tiet"),
                        rs.getInt("id_hoa_don"),
                        rs.getInt("id_sp"),
                        rs.getInt("so_luong"),
                        rs.getDouble("don_gia"),
                        rs.getDouble("thanh_tien")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean insert(ChiTietHoaDon ct) {
        String sql = "INSERT INTO chi_tiet_hoa_don(id_hoa_don, id_sp, so_luong, don_gia, thanh_tien) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ct.getId_hoa_don());
            ps.setInt(2, ct.getId_sp());
            ps.setInt(3, ct.getSo_luong());
            ps.setDouble(4, ct.getDon_gia());
            ps.setDouble(5, ct.getThanh_tien());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(ChiTietHoaDon ct) {
        String sql = "UPDATE chi_tiet_hoa_don SET id_hoa_don = ?, id_sp = ?, so_luong = ?, don_gia = ?, thanh_tien = ? WHERE id_chi_tiet = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ct.getId_hoa_don());
            ps.setInt(2, ct.getId_sp());
            ps.setInt(3, ct.getSo_luong());
            ps.setDouble(4, ct.getDon_gia());
            ps.setDouble(5, ct.getThanh_tien());
            ps.setInt(6, ct.getId_chi_tiet());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM chi_tiet_hoa_don WHERE id_chi_tiet = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteByHoaDonId(int idHoaDon) {
        String sql = "DELETE FROM chi_tiet_hoa_don WHERE id_hoa_don = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idHoaDon);
            return ps.executeUpdate() >= 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}