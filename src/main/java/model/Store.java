package model;


import com.google.gson.Gson;
import org.json.JSONObject;

public class Store {

    public String id;
    public String petId;
    public String quantity;
    public String shipDate;
    public String status;
    public String complete;

    public Store storeObj;

    public Store(String id, String petId, String quantity, String shipDate, String status, String complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public Store() {

    }

    // Getter Methods

    public String getId() {
        return id;
    }

    public String getPetId() {
        return petId;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public String getStatus() {
        return status;
    }

    public String getComplete() {
        return complete;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "{" + "\n" +
                "Id=" + id +",\n" +
                "petId=" + petId + ",\n" +
                "quantity=" + quantity + ",\n" +
                "shipDate=" + shipDate + ",\n" +
                "status=" + status + ",\n" +
                "complete=" + complete + "\n" +
                '}';
    }

    public Store toEntity(JSONObject jsObj) {
        Gson gson = new Gson();
        return storeObj = gson.fromJson(jsObj.toString(), Store.class);
    }
}
