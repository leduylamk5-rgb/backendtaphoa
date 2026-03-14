package service;

import dao.UserDAO;
import model.User;

public class DangNhapService {
    private UserDAO userDAO;

    public DangNhapService() {
        userDAO = new UserDAO();
    }

    public User dangNhap(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        return userDAO.login(username.trim(), password.trim());
    }

    public boolean laQuanLy(User user) {
        return user != null && "quanly".equalsIgnoreCase(user.getVai_tro());
    }

    public boolean laNhanVien(User user) {
        return user != null && "nhanvien".equalsIgnoreCase(user.getVai_tro());
    }
}