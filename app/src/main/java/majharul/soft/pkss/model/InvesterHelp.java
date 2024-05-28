package majharul.soft.pkss.model;

public class InvesterHelp {


    String id,team,name,father,village,phone,license;

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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public InvesterHelp(String id, String team, String name, String father, String village, String phone, String license) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.father = father;
        this.village = village;
        this.phone = phone;
        this.license = license;
    }

    public InvesterHelp() {
    }
}
