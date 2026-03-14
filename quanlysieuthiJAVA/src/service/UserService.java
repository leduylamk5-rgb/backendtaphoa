package service;

import dao.UserDAO;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public List<User> getAllUser() {
        return userDAO.getAll();
    }

    public User getUserById(int id) {
        if (id <= 0) {
            return null;
        }
        return userDAO.getById(id);
    }

    public boolean themUser(User user) {
        if (!kiemTraUserHopLe(user)) {
            return false;
        }

        if (kiemTraUsernameTonTai(user.getUsername())) {
            return false;
        }

        return userDAO.insert(user);
    }

    public boolean suaUser(User user) {
        if (user == null || user.getId_user() <= 0) {
            return false;
        }

        if (!kiemTraUserHopLe(user)) {
            return false;
        }

        List<User> ds = userDAO.getAll();
        for (User u : ds) {
            if (u.getUsername() != null
                    && user.getUsername() != null
                    && u.getUsername().equalsIgnoreCase(user.getUsername())
                    && u.getId_user() != user.getId_user()) {
                return false;
            }
        }

        return userDAO.update(user);
    }

    public boolean xoaUser(int id) {
        if (id <= 0) {
            return false;
        }
        return userDAO.delete(id);
    }

    public boolean doiMatKhau(int idUser, String matKhauMoi) {
        if (idUser <= 0 || matKhauMoi == null || matKhauMoi.trim().isEmpty()) {
            return false;
        }

        User user = userDAO.getById(idUser);
        if (user == null) {
            return false;
        }

        user.setPassword(matKhauMoi.trim());
        return userDAO.update(user);
    }

    public boolean kiemTraUsernameTonTai(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        List<User> ds = userDAO.getAll();
        for (User u : ds) {
            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(username.trim())) {
                return true;
            }
        }

        return false;
    }

    public List<User> timTheoUsername(String usernameCanTim) {
        List<User> ketQua = new ArrayList<>();
        List<User> ds = userDAO.getAll();

        if (usernameCanTim == null || usernameCanTim.trim().isEmpty()) {
            return ketQua;
        }

        String key = usernameCanTim.trim().toLowerCase();

        for (User u : ds) {
            if (u.getUsername() != null && u.getUsername().toLowerCase().contains(key)) {
                ketQua.add(u);
            }
        }

        return ketQua;
    }

    private boolean kiemTraUserHopLe(User user) {
        if (user == null) {
            return false;
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return false;
        }
        if (user.getVai_tro() == null || user.getVai_tro().trim().isEmpty()) {
            return false;
        }
        if (!user.getVai_tro().equalsIgnoreCase("quanly")
                && !user.getVai_tro().equalsIgnoreCase("nhanvien")) {
            return false;
        }
        return true;
    }
}