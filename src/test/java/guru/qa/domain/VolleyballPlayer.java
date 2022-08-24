package guru.qa.domain;


public class VolleyballPlayer {
    private String sport;
    private String name;
    private String growth;
    private Integer age;
    private String[] position;

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[]  getPosition() {
        return position;
    }

    public void setPosition(String[]  position) {
        this.position = position;
    }
}
