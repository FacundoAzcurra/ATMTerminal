package com.solvd.atm.DAO.impl;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.IUserDAO;
import com.solvd.atm.bin.Account;
import com.solvd.atm.bin.User;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl extends AbstractDAO implements IUserDAO {
    private final static String INSERT = "INSERT INTO user(id_user, username, password) VALUES (?,?,?) ";
    private final static String UPDATE = "UPDATE user SET username = ?, password = ? WHERE id_user = ?";
    private final static String DELETE = "DELETE FROM user where id_user = ?";
    private final static String GET_ALL = "SELECT * FROM user";
    private final static String GET_ONE = "SELECT * FROM user WHERE id_user = ?";

    @Override
    public void insert(User a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getUserId());
            stat.setString(2, a.getUsername());
            stat.setString(3, a.getPassword());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have saved");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL ERROR", e);
        } finally {
            returnConnection(conn);
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL ERROR", e);
                }
            }
        }
    }

    @Override
    public void update(User a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getUsername());
            stat.setString(2, a.getPassword());
            stat.setInt(3, a.getUserId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have saved");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL ERROR", e);
        } finally {
            returnConnection(conn);
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL ERROR", e);
                }
            }
        }

    }

    @Override
    public void delete(int a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, a);

            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have saved");
            }

        } catch (SQLException e) {
            throw new DAOException("SQL ERROR", e);
        } finally {
            returnConnection(conn);
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL ERROR", e);
                }
            }
        }

    }

    private User convert(ResultSet rs) throws SQLException {
        int userId = rs.getInt("id_user");
        String username = rs.getString("username");
        String pass = rs.getString("password");
        User user = new User(userId, username, pass);
        return user;
    }

    @Override
    public List<User> getList() throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(GET_ALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                userList.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error.", e);
        } finally {
            returnConnection(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL Error.", e);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL Error.", e);
                }
            }
        }
        return userList;
    }

    @Override
    public User getObject(Integer id) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        User a = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(GET_ONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convert(rs);

            } else {
                throw new DAOException("Register not found.");
            }

        } catch (SQLException e) {
            throw new DAOException("SQL Error.", e);
        } finally {
            returnConnection(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL Error.", e);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL Error.", e);
                }
            }
        }
        return a;
    }
}

