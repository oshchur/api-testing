package builders;

import model.Store;

public abstract class StoreBuilder {

    public Store store;

    public void createNewStore() {
        store = new Store();
    }

    public Store getMyStore() {
        return store;
    }

    public abstract void setId();

    public abstract void setPetId();

    public abstract void setQuantity();

    public abstract void setShipDate();

    public abstract void setStatus();

    public abstract void setComplete();
}
