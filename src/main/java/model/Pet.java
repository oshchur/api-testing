package model;

import org.json.JSONObject;

import java.util.List;

public class Pet {

    private String id;
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private String status;


    public Pet() {
    }


    public Pet(String id, Category category, String name, List<String> photoUrls, List<Tag> tags, String status) {
        super();
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    //Getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    //Setters
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JSONObject getJson(Pet pet) {
        JSONObject object = new JSONObject();
        object.put("id", pet.getId());
        object.put("category", pet.getCategory());
        object.put("name", pet.getName());
        object.put("photoUrls", pet.getPhotoUrls());
        object.put("tags", pet.getTags());
        object.put("status", pet.getStatus());
        return object;
    }

    @Override
    public String toString() {
        return "pet{" + "Id" + id +
                ", Category = '" + category + '\'' +
                ", Name = '" + name + '\'' +
                ",Status = ' " + status + '}';


    }
}


