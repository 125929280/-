/*寝室类
**
**2019-7-6
*/
package Dormitory;

public class Dormitory {
    private String building, room, score, repair;

    public Dormitory() {

    }

    public Dormitory(String building, String room, String score, String repair) {
        this.building = building;
        this.room = room;
        this.score = score;
        this.repair = repair;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuilding() {
        return this.building;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return this.room;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return this.score;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public String getRepair() {
        return this.repair;
    }
}