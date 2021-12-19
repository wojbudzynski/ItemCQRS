package pl.budzynski.Item.query.api.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import pl.budzynski.Item.command.api.data.Item;
import pl.budzynski.Item.command.api.data.ItemRepository;
import pl.budzynski.Item.command.api.model.ItemRestModel;
import pl.budzynski.Item.query.api.queries.GetItemQuery;
import pl.budzynski.Item.query.api.queries.GetItemsQuery;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemProjection {

    private ItemRepository itemRepository;

    public ItemProjection(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @QueryHandler
    public List<ItemRestModel> handle(GetItemsQuery getItemsQuery){
        List<Item> items=
                itemRepository.getItems();

        List<ItemRestModel> itemRestModels=
                items.stream()
                        .map(item->ItemRestModel
                                .builder()
                                .name(item.getName())
                                .itemId(item.getItemId())
                                .build())
                        .collect(Collectors.toList());

        return itemRestModels;
    }

    @QueryHandler
    public ItemRestModel handle(GetItemQuery getItemQuery){
        Item item=
                itemRepository.getItem(getItemQuery.getItemId());

        ItemRestModel itemRestModel=ItemRestModel
                                .builder()
                                .name(item.getName())
                                .itemId(item.getItemId())
                                .build();

        return itemRestModel;
    }
}
