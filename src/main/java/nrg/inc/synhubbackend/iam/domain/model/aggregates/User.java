package nrg.inc.synhubbackend.iam.domain.model.aggregates;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String imgUrl;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getImgUrl() { return imgUrl; }

    // setters for testing / placeholder
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
}
