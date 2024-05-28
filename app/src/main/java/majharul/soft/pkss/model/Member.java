package majharul.soft.pkss.model;

public class Member {

    String team,name,father,village,phone,category;

    public Member(String team, String name, String father, String village, String phone, String category) {
        this.team = team;
        this.name = name;
        this.father = father;
        this.village = village;
        this.phone = phone;
        this.category = category;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Member() {
    }
}
