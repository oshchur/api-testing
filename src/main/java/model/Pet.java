package model;

import java.util.List;

public class Pet {

    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private String status;


    public Pet() {
    }


    public Pet(Integer id, Category category, String name, List<String> photoUrls, List<Tag> tags, String status) {
        super();
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }


    public List<Tag> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    //Setters
    public void setCategory(Category category) {
        this.category = category;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "pet{" + "Id" + id +
                ", Category = '" + category + '\'' +
                ", Name = '" + name + '\'' +
                ",Status = ' " + status + '}';


    }
}


