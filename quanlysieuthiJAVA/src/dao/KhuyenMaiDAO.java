package dao;

import model.KhuyenMai;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO implements BaseDAO<KhuyenMai> {

    @Override
    public List<KhuyenMai> getAll() {
        List<KhuyenMai> list = new ArrayList<>();
        String sql = "SELECT * FROM khuyen_mai";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new KhuyenMai(
                        rs.getInt("id_khuyen_mai"),
                        rs.getString("ten_khuyen_mai"),
                        rs.getDouble("phan_tram_giam"),
                        rs.getDate("ngay_bat_dau"),
                        rs.getDate("ngay_ket_thuc"),
                        rs.getString("mo_ta")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public KhuyenMai getById(int id) {
        String sql = "SELECT * FROM khuyen_mai WHERE id_khuyen_mai = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new KhuyenMai(
                        rs.getInt("id_khuyen_mai"),
                        rs.getString("ten_khuyen_mai"),
                        rs.getDouble("phan_tram_giam"),
                        rs.getDate("ngay_bat_dau"),
                        rs.getDate("ngay_ket_thuc"),
                        rs.getString("mo_ta")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(KhuyenMai km) {
        String sql = "INSERT INTO khuyen_mai(ten_khuyen_mai, phan_tram_giam, ngay_bat_dau, ngay_ket_thuc, mo_ta) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, km.getTen_khuyen_mai());
            ps.setDouble(2, km.getPhan_tram_giam());
            ps.setDate(3, km.getNgay_bat_dau());
            ps.setDate(4, km.getNgay_ket_thuc());
            ps.setString(5, km.getMo_ta());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(KhuyenMai km) {
        String sql = "UPDATE khuyen_mai SET ten_khuyen_mai=?, phan_tram_giam=?, ngay_bat_dau=?, ngay_ket_thuc=?, mo_ta=? WHERE id_khuyen_mai=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, km.getTen_khuyen_mai());
            ps.setDouble(2, km.getPhan_tram_giam());
            ps.setDate(3, km.getNgay_bat_dau());
            ps.setDate(4, km.getNgay_ket_thuc());
            ps.setString(5, km.getMo_ta());
            ps.setInt(6, km.getId_khuyen_mai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM khuyen_mai WHERE id_khuyen_mai=?";

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