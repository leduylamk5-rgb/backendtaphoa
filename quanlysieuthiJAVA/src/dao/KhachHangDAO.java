package dao;

import model.KhachHang;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO implements BaseDAO<KhachHang> {

    @Override
    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new KhachHang(
                        rs.getInt("id_kh"),
                        rs.getString("ten_kh"),
                        rs.getInt("nam_sinh_kh"),
                        rs.getInt("diem_thuong_kh"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"),
                        rs.getString("gioi_tinh")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public KhachHang getById(int id) {
        String sql = "SELECT * FROM khach_hang WHERE id_kh = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new KhachHang(
                        rs.getInt("id_kh"),
                        rs.getString("ten_kh"),
                        rs.getInt("nam_sinh_kh"),
                        rs.getInt("diem_thuong_kh"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"),
                        rs.getString("gioi_tinh")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(KhachHang kh) {
        String sql = "INSERT INTO khach_hang(ten_kh, nam_sinh_kh, diem_thuong_kh, so_dien_thoai, dia_chi, gioi_tinh) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kh.getTen_kh());
            ps.setInt(2, kh.getNam_sinh_kh());
            ps.setInt(3, kh.getDiem_thuong_kh());
            ps.setString(4, kh.getSo_dien_thoai());
            ps.setString(5, kh.getDia_chi());
            ps.setString(6, kh.getGioi_tinh());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(KhachHang kh) {
        String sql = "UPDATE khach_hang SET ten_kh=?, nam_sinh_kh=?, diem_thuong_kh=?, so_dien_thoai=?, dia_chi=?, gioi_tinh=? WHERE id_kh=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kh.getTen_kh());
            ps.setInt(2, kh.getNam_sinh_kh());
            ps.setInt(3, kh.getDiem_thuong_kh());
            ps.setString(4, kh.getSo_dien_thoai());
            ps.setString(5, kh.getDia_chi());
            ps.setString(6, kh.getGioi_tinh());
            ps.setInt(7, kh.getId_kh());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM khach_hang WHERE id_kh = ?";

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