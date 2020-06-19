package builders;

import model.Store;

public class StoreBuilderNew {

    public void constructStore(Store store) {
        store.setId()
        store.setPetId()
        store.setQuantity()
        store.setShipDate()
        store.setStatus()
        store.setComplete()
        .build();
    }
}
