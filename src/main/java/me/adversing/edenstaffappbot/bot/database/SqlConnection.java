package me.adversing.edenstaffappbot.bot.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlConnection {

    private Connection connection;

    public SqlConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            /* TODO you have to edit this with your db's credentials */
            this.connection = DriverManager.getConnection("jdbc:mysql://" + "127.0.0.1" +"/" + "edenbot" + "?" + "user=" + "root" + "&password= " + "");
            createTables();
            System.out.println("[EdenMine Bot] Connected to the database successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("[EdenMine Bot] There was an error while trying to connect to the provided database.");
            e.printStackTrace();
        }
    }

    private void createTables() {
        try (Statement st = connection.createStatement()){
            String sql = "create table if not exists `users` " +
                    "(" +
                    "id bigint primary key not null, " +
                    "staff boolean default false, " +
                    "admin boolean default false" +
                    ")";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Long> selectAdmins() {
        List<Long> admins = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement("select * from users where admin = true")){
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    admins.add(resultSet.getLong("id"));
                }
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Long> selectStaff() {
        List<Long> staff = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement("select * from users where staff = true")){
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    staff.add(resultSet.getLong("id"));
                }
            }
            return staff;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean isAdmin(long id) {
        try (PreparedStatement st = connection.prepareStatement("select id from users where id = ? and admin = true")){
            st.setLong(1, id);
            try (ResultSet resultSet = st.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isStaff(long id) {
        try (PreparedStatement st = connection.prepareStatement("select id from users where id = ? and staff = true")){
            st.setLong(1, id);
            try (ResultSet resultSet = st.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertUser(long id) {
        if (userExists(id)) return;
        try (PreparedStatement st = connection.prepareStatement("insert into users (id) values (?)")){
            System.out.println(id + " added to the database");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean userExists(long id) {
        try (PreparedStatement st = connection.prepareStatement("select id from users where id = ?")){
            st.setLong(1, id);
            try (ResultSet resultSet = st.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateAdmin(long id, boolean add) {
        try (PreparedStatement st = connection.prepareStatement("update users set admin = ? where id = ?")){
            st.setBoolean(1, add);
            st.setLong(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStaff(long id, boolean add) {
        try (PreparedStatement st = connection.prepareStatement("update users set staff = ? where id = ?")){
            st.setBoolean(1, add);
            st.setLong(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
