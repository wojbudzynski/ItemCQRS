package pl.budzynski.Item.query.api.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import pl.budzynski.Item.command.api.data.Item;
import pl.budzynski.Item.command.api.data.ItemRepository;
import pl.budzynski.Item.command.api.model.ItemRestModel;
import pl.budzynski.Item.query.api.queries.GetItemQuery;

@Component
public class ItemProjection {

    private ItemRepository itemRepository;

    public ItemProjection(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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
