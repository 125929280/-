package Dormitory;

import java.sql.*;

public class SystemAdmin extends User implements InterfaceSet {

    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public SystemAdmin() {
        super("admin", "admin");
    }

    public boolean login(String username, String password) {
        conn = GetConnection.connect();
        String sql = "SELECT * FROM SYSTEMADMIN WHERE USERNAME = ? AND PASSWORD = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
            }
            if (i != 0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            System.out.println("login fail:" + e.getMessage());
            return false;
        } finally {
            GetConnection.close();
        }
    }

    public String getPassword(String username) {
        conn = GetConnection.connect();
        String sql = "SELECT PASSWORD FROM SYSTEMADMIN WHERE USERNAME = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            // System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public void updatePassword(String username, String password) {
        conn = GetConnection.connect();
        String sql = "UPDATE SYSTEMADMIN SET PASSWORD = ? WHERE USERNAME = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();
            // System.out.println("update success!");
        } catch (SQLException e) {
            // System.out.println("update fail:" + e.getMessage());
        } finally {
            GetConnection.close();
        }
    }

    public void addAdmin(String username, String password) {
        conn = GetConnection.connect();
        String sql = "INSERT INTO ADMIN (USERNAME, PASSWORD) VALUES(?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            // ps.setString(3, user.getType());

            ps.executeUpdate();

            // System.out.println("create success!");
            // return info.userInfo();
        } catch (Exception e) {
            // System.out.println("create fail:" + e.getMessage());
            // return info.userInfo();
        } finally {
            GetConnection.close();
        }
    }

    public void deleteAdmin(String username) {
        conn = GetConnection.connect();
        String sql = "DELETE FROM ADMIN WHERE USERNAME = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();
            // System.out.println("delete done!");
            // Info info = new Info();
            // return info.allStudentInfo();
        } catch (SQLException e) {
            // System.out.println("delete failed:" + e.getMessage());
            // Info info = new Info();
            // return info.allStudentInfo();
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchAllAdmin() {
        conn = GetConnection.connect();
        String sql = "SELECT * FROM ADMIN";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            // System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchAdmin(String username) {
        conn = GetConnection.connect();
        String sql = "SELECT * FROM ADMIN WHERE USERNAME = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs;
            } else
                return null;
        } catch (SQLException e) {
            // System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public void addDormitory(String building, String room) {
        conn = GetConnection.connect();
        String sql = "INSERT INTO DORMITORY (BUILDING, ROOM, SCORE, REPAIR) VALUES(?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, building);
            ps.setString(2, room);
            ps.setString(3, "0");
            ps.setString(4, "无");

            ps.executeUpdate();

            System.out.println("add successed!");
            // return info.allStudentInfo();
        } catch (Exception e) {
            System.out.println("add failed:" + e.getMessage());
            // return info.allStudentInfo();
        } finally {
            GetConnection.close();
        }
    }

    public void deleteDormitory(String building, String room) {
        conn = GetConnection.connect();
        String sql = "DELETE FROM DORMITORY WHERE BUILDING = ? AND ROOM = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, building);
            ps.setString(2, room);
            ps.executeUpdate();
            // System.out.println("delete success!");
        } catch (SQLException e) {
            // System.out.println("delete fail:" + e.getMessage());
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchDormitoryWithStudent(String building, String room) {
        conn = GetConnection.connect();
        String sql = "SELECT * FROM DORMITORY AS D JOIN STUDENT AS S ON D.BUILDING = S.BUILDING AND D.ROOM = S.ROOM WHERE D.BUILDING = ? AND D.ROOM = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, building);
            ps.setString(2, room);

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs;
            } else
                return null;
        } catch (SQLException e) {
            // System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchDormitory(String building, String room) {
        conn = GetConnection.connect();
        String sql = "SELECT * FROM DORMITORY WHERE BUILDING = ? AND ROOM = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, building);
            ps.setString(2, room);

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs;
            } else
                return null;
        } catch (SQLException e) {
            // System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchAllDormitory() {
        conn = GetConnection.connect();
        String sql = "SELECT * FROM DORMITORY";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            // System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public void addStudent(String sno, String sname, String gender, String building, String room, String bed,
            String major, String indate) {
        conn = GetConnection.connect();
        String sql = "INSERT INTO STUDENT (SNO, SNAME, PASSWORD, GENDER, BUILDING, ROOM, BED, MAJOR, INDATE) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            ps.setString(2, sname);
            ps.setString(3, "000000");
            ps.setString(4, gender);
            ps.setString(5, building);
            ps.setString(6, room);
            ps.setString(7, bed);
            ps.setString(8, major);
            ps.setString(9, indate);

            ps.executeUpdate();

            // System.out.println("add success!");
        } catch (Exception e) {
            // System.out.println("add failed:" + e.getMessage());
        } finally {
            GetConnection.close();
        }
    }

    public void deleteStudent(String sno) {
        conn = GetConnection.connect();
        String sql = "DELETE FROM STUDENT WHERE SNO = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            ps.executeUpdate();
            // System.out.println("delete success!");
        } catch (SQLException e) {
            // System.out.println("delete fail:" + e.getMessage());
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchStudent(String sno) {
        conn = GetConnection.connect();
        String sql = "SELECT * FROM STUDENT WHERE SNO = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs;
            } else
                return null;
        } catch (SQLException e) {
            // System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchAllStudent() {
        conn = GetConnection.connect();
        String sql = "SELECT SNO, SNAME, GENDER, BUILDING, ROOM, BED, MAJOR, INDATE FROM STUDENT";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            // System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchBed(String building, String room, String bed) {
        conn = GetConnection.connect();
        String sql = "SELECT SNO FROM STUDENT WHERE BUILDING = ? AND ROOM = ? AND BED = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, building);
            ps.setString(2, room);
            ps.setString(3, bed);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs;
            } else
                return null;
        } catch (SQLException e) {
            // System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

}