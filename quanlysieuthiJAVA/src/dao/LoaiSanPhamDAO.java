package dao;

import model.LoaiSanPham;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamDAO implements BaseDAO<LoaiSanPham> {

    @Override
    public List<LoaiSanPham> getAll() {
        List<LoaiSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM loai_san_pham";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new LoaiSanPham(
                        rs.getInt("id_loai"),
                        rs.getString("ten_loai")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public LoaiSanPham getById(int id) {
        String sql = "SELECT * FROM loai_san_pham WHERE id_loai = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new LoaiSanPham(
                        rs.getInt("id_loai"),
                        rs.getString("ten_loai")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(LoaiSanPham loai) {
        String sql = "INSERT INTO loai_san_pham(ten_loai) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, loai.getTen_loai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(LoaiSanPham loai) {
        String sql = "UPDATE loai_san_pham SET ten_loai=? WHERE id_loai=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, loai.getTen_loai());
            ps.setInt(2, loai.getId_loai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM loai_san_pham WHERE id_loai=?";

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