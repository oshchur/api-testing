package builders;

import model.Store;

public class StoreBuilder {

    public String id;
    public String petId;
    public String quantity;
    public String shipDate;
    public String status;
    public String complete;


    public StoreBuilder setId(String id) {
        this.id = id;

        return this;
    }

    public StoreBuilder setPetId(String petId) {
        this.petId = petId;

        return this;
    }

    public StoreBuilder setQuantity(String quantity) {
        this.quantity = quantity;

        return this;
    }

    public StoreBuilder setShipDate(String shipDate) {
        this.shipDate = shipDate;

        return this;
    }

    public StoreBuilder setStatus(String status) {
        this.status = status;

        return this;
    }

    public StoreBuilder setComplete(String complete) {
        this.complete = complete;

        return this;
    }

    public Store build() {
        Store store = new Store();
        store.setId(id);
        store.setPetId(petId);
        store.setQuantity(quantity);
        store.setShipDate(shipDate);
        store.setStatus(status);
        store.setComplete(complete);

        return store;
    }
}
