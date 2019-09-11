/*???
**
**2019-7-3
*/
package Dormitory;

import java.sql.*;

public class Student extends User implements InterfaceSet {
    private String sno, sname, gender, building, room, bed, major, indate;
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public Student() {

    }

    public Student(String sno, String sname, String password, String gender, String building, String room, String bed,
            String major, String indate) {
        super(sno, password);
        this.sno = sno;
        this.sname = sname;
        this.gender = gender;
        this.building = building;
        this.room = room;
        this.bed = bed;
        this.major = major;
        this.indate = indate;
    }

    public Student(String sno, String sname, String gender, String building, String room, String bed, String major,
            String indate) {
        super(sno, "000000");
        this.sno = sno;
        this.sname = sname;
        this.gender = gender;
        this.building = building;
        this.room = room;
        this.bed = bed;
        this.major = major;
        this.indate = indate;
    }

    public boolean login(String sno, String password) {
        conn = GetConnection.connect();
        String sql = "SELECT * FROM STUDENT WHERE SNO = ? AND PASSWORD = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
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

    public String getPassword(String sno) {
        conn = GetConnection.connect();
        String sql = "SELECT PASSWORD FROM STUDENT WHERE SNO = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
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
        String sql = "UPDATE STUDENT SET PASSWORD = ? WHERE SNO = ?";
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

    public boolean updateRepair(String sno, String repair) {
        conn = GetConnection.connect();
        boolean flag = false;
        String sql = "UPDATE DORMITORY SET REPAIR = ? WHERE DORMITORY.BUILDING = (select BUILDING from student where sno = ?) and DORMITORY.ROOM = (select ROOM from student where sno = ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, repair);
            ps.setString(2, sno);
            ps.setString(3, sno);
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

    public ResultSet searchDormitory(String sno) {
        conn = GetConnection.connect();
        String sql = "SELECT D.BUILDING, D.ROOM, D.SCORE, D.REPAIR FROM DORMITORY AS D JOIN STUDENT AS S ON D.BUILDING = S.BUILDING AND D.ROOM = S.ROOM WHERE S.SNO = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public ResultSet searchRoommate(String sno) {
        conn = GetConnection.connect();
        String sql = "SELECT SNO, SNAME, BED, MAJOR, INDATE FROM DORMITORY AS D JOIN STUDENT AS S ON D.BUILDING = S.BUILDING AND D.ROOM = S.ROOM WHERE d.BUILDING = (SELECT BUILDING FROM STUDENT WHERE SNO = ?) AND D.ROOM = (SELECT ROOM FROM STUDENT WHERE SNO = ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            ps.setString(2, sno);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("search failed:" + e.getMessage());
            return null;
        } finally {
            GetConnection.close();
        }
    }

    public String getSno() {
        return this.sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return this.sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBuilding() {
        return this.building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBed() {
        return this.bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInDate() {
        return this.indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }
}