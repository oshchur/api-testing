package builders.StoreBuilderWithDirector;

public class StoreInvalidParam extends StoreBuilder {
    @Override
    public void setId() {

        store.id = "1b";
    }

    @Override
    public void setPetId() {

        store.petId = "2b";
    }

    @Override
    public void setQuantity() {

        store.quantity = "3b";
    }

    //returns 500 as a response
    @Override
    public void setShipDate() {

        store.shipDate = "2020-06-1611:34:55";
    }

    //ignores enumed options
    @Override
    public void setStatus() {

        store.status = "bug";
    }

    @Override
    public void setComplete() {

        store.complete = "fal";
    }
}
