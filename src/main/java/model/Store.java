package model;


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONObject;

public class Store {

    private float id;
    private float petId;
    private float quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public Store(float id, float petId, float quantity, String shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    // Getter Methods

    public float getId() {
        return id;
    }

    public float getPetId() {
        return petId;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public String getStatus() {
        return status;
    }

    public boolean getComplete() {
        return complete;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setPetId(float petId) {
        this.petId = petId;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Store{" +
                "Id=" + id +
                ", petId='" + petId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", shipDate='" + shipDate + '\'' +
                ", status='" + status + '\'' +
                ", complete='" + complete +
                '}';
    }


    public Store toEntity(JSONObject jsObj) {
        Gson gson = new Gson();
        Store storeObj = gson.fromJson(jsObj.toString(), Store.class);
        return storeObj;
    }
}
