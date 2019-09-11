/*用户类
**
**2019-7-2
*/
package Dormitory;

import java.sql.*;

public class Admin extends User implements InterfaceSet {

    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public boolean login(String username, String password) {

        conn = GetConnection.connect();
        String sql = "SELECT * FROM ADMIN WHERE USERNAME = ? AND PASSWORD = ?";
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
        String sql = "SELECT PASSWORD FROM ADMIN WHERE USERNAME = ?";
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
        String sql = "UPDATE ADMIN SET PASSWORD = ? WHERE USERNAME = ?";
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

    public void finishRepair(String building, String room) {
        conn = GetConnection.connect();
        String sql = "UPDATE DORMITORY SET REPAIR = '无' WHERE BUILDING = ? AND ROOM = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, building);
            ps.setString(2, room);

            ps.executeUpdate();
            System.out.println("update success!");
        } catch (SQLException e) {
            System.out.println("update fail:" + e.getMessage());
        } finally {
            GetConnection.close();
        }
    }

    public boolean updateScore(String building, String room, String score) {
        boolean flag = false;
        conn = GetConnection.connect();
        String sql = "UPDATE DORMITORY SET SCORE = ? WHERE BUILDING = ? AND ROOM = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, score);
            ps.setString(2, building);
            ps.setString(3, room);

            ps.executeUpdate();
            System.out.println("update success!");
            flag = true;
        } catch (SQLException e) {
            System.out.println("update fail:" + e.getMessage());
        } finally {
            GetConnection.close();
        }
        return flag;
    }

    public boolean searchRepair(String building, String room) {
        conn = GetConnection.connect();
        String sql = "SELECT REPAIR FROM DORMITORY WHERE BUILDING = ? AND ROOM = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, building);
            ps.setString(2, room);
            rs = ps.executeQuery();
            rs.next();
            if (rs.getString(1).equals("无")) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            System.out.println("search failed:" + e.getMessage());
            return false;
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchStudent(String sno) {
        conn = GetConnection.connect();
        String sql = "SELECT D.BUILDING, D.ROOM, D.SCORE, D.REPAIR FROM STUDENT AS S JOIN DORMITORY AS D ON S.BUILDING = D.BUILDING AND S.ROOM = D.ROOM WHERE SNO = ?";
        try {
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, sno);
            rs = ps.executeQuery();
            if (rs.next()) {
                rs.previous();
                return rs;
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchAllStudent() {
        conn = GetConnection.connect();
        String sql = "SELECT * FROM STUDENT";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchDormitory(String building, String room) {
        conn = GetConnection.connect();
        String sql = "SELECT S.SNO, S.SNAME, S.BED, S.MAJOR, S.INDATE FROM DORMITORY AS D JOIN STUDENT AS S ON D.BUILDING = S.BUILDING AND D.ROOM = S.ROOM WHERE D.BUILDING = ? AND D.ROOM = ?";
        try {
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, building);
            ps.setString(2, room);
            rs = ps.executeQuery();

            if (rs.next()) {
                rs.previous();
                return rs;
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("search failed:" + e.getMessage());
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
            System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public boolean changeDormitory(String sno, String building, String room, String bed) {
        conn = GetConnection.connect();
        String tempSno = "", tempBuilding = "", tempRoom = "", tempBed = "";
        String sql = "SELECT SNO FROM STUDENT AS S JOIN DORMITORY AS D ON S.BUILDING = D.BUILDING AND S.ROOM = D.ROOM WHERE S.BUILDING = ? AND S.ROOM = ? AND S.BED = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, building);
            ps.setString(2, room);
            ps.setString(3, bed);
            rs = ps.executeQuery();
            rs.next();
            tempSno = rs.getString("SNO");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (tempSno.equals("") == false) {
            sql = "SELECT BUILDING, ROOM, BED FROM STUDENT WHERE SNO = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, sno);
                rs = ps.executeQuery();
                rs.next();
                tempBuilding = rs.getString(1);
                tempRoom = rs.getString(2);
                tempBed = rs.getString(3);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            sql = "UPDATE STUDENT SET BUILDING = ?, ROOM = ?, BED = ? WHERE SNO = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, tempBuilding);
                ps.setString(2, tempRoom);
                ps.setString(3, tempBed);
                ps.setString(4, tempSno);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                GetConnection.close();
                return false;
            }
        }
        try {
            sql = "UPDATE STUDENT SET BUILDING = ?, ROOM = ?, BED = ? WHERE SNO = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, building);
            ps.setString(2, room);
            ps.setString(3, bed);
            ps.setString(4, sno);
            ps.executeUpdate();
            System.out.println("change success");
            GetConnection.close();
            return true;
        } catch (SQLException e) {
            System.out.println("change failed:" + e.getMessage());
            GetConnection.close();
            return false;
        }
    }

}
