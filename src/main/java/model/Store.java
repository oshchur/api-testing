package model;


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONObject;

public class Store {

    private String id;
    private String petId;
    private String quantity;
    private String shipDate;
    private String status;
    private boolean complete;
    private Store storeObj;

    public Store(String id, String petId, String quantity, String shipDate, String status, boolean complete) {
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

    public boolean getComplete() {
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

    public void setComplete(boolean complete) {
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

    public JSONObject toJson() {
        JSONObject object = new JSONObject();

        object.put("id", this.getId());
        object.put("petId", this.getPetId());
        object.put("quantity", this.getQuantity());
        object.put("shipDate", this.getShipDate());
        object.put("status", this.getStatus());
        object.put("complete", this.getComplete());

        return object;
    }

    public Store toEntity(JSONObject jsObj) {
        Gson gson = new Gson();
        return storeObj = gson.fromJson(jsObj.toString(), Store.class);
    }
}
