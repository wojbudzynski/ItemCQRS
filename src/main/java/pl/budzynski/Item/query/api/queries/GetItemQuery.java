package pl.budzynski.Item.query.api.queries;

public class GetItemQuery {
    private final String itemId;

    public GetItemQuery(String itemId) {
        this.itemId=itemId;
    }

    public String getItemId() {
        return itemId;
    }
}
