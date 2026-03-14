package dao;

import model.PhieuNhap;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO implements BaseDAO<PhieuNhap> {

    @Override
    public List<PhieuNhap> getAll() {
        List<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM phieu_nhap";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new PhieuNhap(
                        rs.getInt("id_phieu_nhap"),
                        rs.getInt("id_ncc"),
                        rs.getInt("id_nhan_vien"),
                        rs.getTimestamp("ngay_nhap"),
                        rs.getDouble("tong_tien"),
                        rs.getString("ghi_chu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public PhieuNhap getById(int id) {
        String sql = "SELECT * FROM phieu_nhap WHERE id_phieu_nhap = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new PhieuNhap(
                        rs.getInt("id_phieu_nhap"),
                        rs.getInt("id_ncc"),
                        rs.getInt("id_nhan_vien"),
                        rs.getTimestamp("ngay_nhap"),
                        rs.getDouble("tong_tien"),
                        rs.getString("ghi_chu")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(PhieuNhap pn) {
        String sql = "INSERT INTO phieu_nhap(id_ncc, id_nhan_vien, ngay_nhap, tong_tien, ghi_chu) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pn.getId_ncc());
            ps.setInt(2, pn.getId_nhan_vien());
            ps.setTimestamp(3, pn.getNgay_nhap());
            ps.setDouble(4, pn.getTong_tien());
            ps.setString(5, pn.getGhi_chu());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(PhieuNhap pn) {
        String sql = "UPDATE phieu_nhap SET id_ncc=?, id_nhan_vien=?, ngay_nhap=?, tong_tien=?, ghi_chu=? WHERE id_phieu_nhap=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pn.getId_ncc());
            ps.setInt(2, pn.getId_nhan_vien());
            ps.setTimestamp(3, pn.getNgay_nhap());
            ps.setDouble(4, pn.getTong_tien());
            ps.setString(5, pn.getGhi_chu());
            ps.setInt(6, pn.getId_phieu_nhap());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM phieu_nhap WHERE id_phieu_nhap=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int insertAndGetId(PhieuNhap pn) {
        String sql = "INSERT INTO phieu_nhap(id_ncc, id_nhan_vien, ngay_nhap, tong_tien, ghi_chu) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pn.getId_ncc());
            ps.setInt(2, pn.getId_nhan_vien());
            ps.setTimestamp(3, pn.getNgay_nhap());
            ps.setDouble(4, pn.getTong_tien());
            ps.setString(5, pn.getGhi_chu());

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