package dao;

import model.HoaDon;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO implements BaseDAO<HoaDon> {

    @Override
    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new HoaDon(
                        rs.getInt("id_hoa_don"),
                        rs.getInt("id_kh"),
                        rs.getInt("id_nhan_vien"),
                        rs.getTimestamp("ngay_lap"),
                        rs.getDouble("tong_tien"),
                        rs.getDouble("giam_gia"),
                        rs.getDouble("thue"),
                        rs.getString("phuong_thuc_thanh_toan"),
                        rs.getString("trang_thai"),
                        rs.getString("ghi_chu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public HoaDon getById(int id) {
        String sql = "SELECT * FROM hoa_don WHERE id_hoa_don = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new HoaDon(
                        rs.getInt("id_hoa_don"),
                        rs.getInt("id_kh"),
                        rs.getInt("id_nhan_vien"),
                        rs.getTimestamp("ngay_lap"),
                        rs.getDouble("tong_tien"),
                        rs.getDouble("giam_gia"),
                        rs.getDouble("thue"),
                        rs.getString("phuong_thuc_thanh_toan"),
                        rs.getString("trang_thai"),
                        rs.getString("ghi_chu")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(HoaDon hd) {
        String sql = "INSERT INTO hoa_don(id_kh, id_nhan_vien, ngay_lap, tong_tien, giam_gia, thue, phuong_thuc_thanh_toan, trang_thai, ghi_chu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hd.getId_kh());
            ps.setInt(2, hd.getId_nhan_vien());
            ps.setTimestamp(3, hd.getNgay_lap());
            ps.setDouble(4, hd.getTong_tien());
            ps.setDouble(5, hd.getGiam_gia());
            ps.setDouble(6, hd.getThue());
            ps.setString(7, hd.getPhuong_thuc_thanh_toan());
            ps.setString(8, hd.getTrang_thai());
            ps.setString(9, hd.getGhi_chu());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(HoaDon hd) {
        String sql = "UPDATE hoa_don SET id_kh=?, id_nhan_vien=?, ngay_lap=?, tong_tien=?, giam_gia=?, thue=?, phuong_thuc_thanh_toan=?, trang_thai=?, ghi_chu=? WHERE id_hoa_don=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hd.getId_kh());
            ps.setInt(2, hd.getId_nhan_vien());
            ps.setTimestamp(3, hd.getNgay_lap());
            ps.setDouble(4, hd.getTong_tien());
            ps.setDouble(5, hd.getGiam_gia());
            ps.setDouble(6, hd.getThue());
            ps.setString(7, hd.getPhuong_thuc_thanh_toan());
            ps.setString(8, hd.getTrang_thai());
            ps.setString(9, hd.getGhi_chu());
            ps.setInt(10, hd.getId_hoa_don());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM hoa_don WHERE id_hoa_don=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int insertAndGetId(HoaDon hd) {
        String sql = "INSERT INTO hoa_don(id_kh, id_nhan_vien, ngay_lap, tong_tien, giam_gia, thue, phuong_thuc_thanh_toan, trang_thai, ghi_chu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, hd.getId_kh());
            ps.setInt(2, hd.getId_nhan_vien());
            ps.setTimestamp(3, hd.getNgay_lap());
            ps.setDouble(4, hd.getTong_tien());
            ps.setDouble(5, hd.getGiam_gia());
            ps.setDouble(6, hd.getThue());
            ps.setString(7, hd.getPhuong_thuc_thanh_toan());
            ps.setString(8, hd.getTrang_thai());
            ps.setString(9, hd.getGhi_chu());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}