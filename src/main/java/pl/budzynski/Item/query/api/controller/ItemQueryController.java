package pl.budzynski.Item.query.api.controller;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import pl.budzynski.Item.command.api.model.ItemRestModel;
import pl.budzynski.Item.query.api.queries.GetItemQuery;
import pl.budzynski.Item.query.api.queries.GetItemsQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/items")
public class ItemQueryController {

    private QueryGateway queryGateway;

    public ItemQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ItemRestModel> getAllItems(){
        GetItemsQuery getItemsQuery=
                new GetItemsQuery();

        List<ItemRestModel> itemRestModels=
        queryGateway.query(getItemsQuery,
                ResponseTypes.multipleInstancesOf(ItemRestModel.class)).join();
        return itemRestModels;
    }

    @GetMapping("/{itemId}")
    public ItemRestModel getItem(@PathVariable String itemId) throws ExecutionException, InterruptedException {
        GetItemQuery getItemsQuery=
                new GetItemQuery(itemId);

        ItemRestModel itemRestModel=
                queryGateway.query(getItemsQuery,
                        ResponseTypes.instanceOf(ItemRestModel.class)).get();
        return itemRestModel;
    }
}
