package majharul.soft.pkss.model;

public class MemberHelper {

    String id;
    String team;
    String member;
    String name;
    String father;
    String village;
    String phone;
    String category;
    String userphone;
    String savings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
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

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }

    public MemberHelper(String id, String team, String member, String name, String father, String village, String phone, String category, String userphone, String savings) {
        this.id = id;
        this.team = team;
        this.member = member;
        this.name = name;
        this.father = father;
        this.village = village;
        this.phone = phone;
        this.category = category;
        this.userphone = userphone;
        this.savings = savings;
    }

    public MemberHelper() {
    }
}
