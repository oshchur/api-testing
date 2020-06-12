package model;

public class Tag {
    private Integer id;
    private String name;

    public Tag() {
    }


    public Tag(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "category{ " + "Id" + id +
                ",Name = '" + name + '}';
    }
}