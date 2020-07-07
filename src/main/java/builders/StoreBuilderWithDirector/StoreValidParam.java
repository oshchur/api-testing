package builders.StoreBuilderWithDirector;

public class StoreValidParam extends StoreBuilder {


    @Override
    public void setId() {

        store.id = "1";
    }

    @Override
    public void setPetId() {

        store.petId = "2";
    }

    @Override
    public void setQuantity() {

        store.quantity = "3";
    }

    @Override
    public void setShipDate() {

        store.shipDate = "2020-06-16T11:34:55.145Z";
    }

    @Override
    public void setStatus() {

        store.status = "placed";

    }

    @Override
    public void setComplete() {

        store.complete = "true";
    }
}
