package dao;

import model.NhaCungCap;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO implements BaseDAO<NhaCungCap> {

    @Override
    public List<NhaCungCap> getAll() {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM nha_cung_cap";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new NhaCungCap(
                        rs.getInt("id_ncc"),
                        rs.getString("ten_ncc"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"),
                        rs.getString("email")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public NhaCungCap getById(int id) {
        String sql = "SELECT * FROM nha_cung_cap WHERE id_ncc = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new NhaCungCap(
                        rs.getInt("id_ncc"),
                        rs.getString("ten_ncc"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"),
                        rs.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(NhaCungCap ncc) {
        String sql = "INSERT INTO nha_cung_cap(ten_ncc, so_dien_thoai, dia_chi, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ncc.getTen_ncc());
            ps.setString(2, ncc.getSo_dien_thoai());
            ps.setString(3, ncc.getDia_chi());
            ps.setString(4, ncc.getEmail());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(NhaCungCap ncc) {
        String sql = "UPDATE nha_cung_cap SET ten_ncc=?, so_dien_thoai=?, dia_chi=?, email=? WHERE id_ncc=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ncc.getTen_ncc());
            ps.setString(2, ncc.getSo_dien_thoai());
            ps.setString(3, ncc.getDia_chi());
            ps.setString(4, ncc.getEmail());
            ps.setInt(5, ncc.getId_ncc());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM nha_cung_cap WHERE id_ncc=?";

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