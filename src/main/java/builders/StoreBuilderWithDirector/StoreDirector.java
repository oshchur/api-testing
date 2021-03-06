package builders.StoreBuilderWithDirector;

import model.Store;

public class StoreDirector {

    private StoreBuilder storeBuilder;

    public void setStoreBuilder(StoreBuilder stBuild) {
        storeBuilder = stBuild;
    }

    public Store getStore() {
        return storeBuilder.getMyStore();
    }

    public void constructStore() {
        storeBuilder.createNewStore();
        storeBuilder.setId();
        storeBuilder.setPetId();
        storeBuilder.setQuantity();
        storeBuilder.setShipDate();
        storeBuilder.setStatus();
        storeBuilder.setComplete();

    }
}
