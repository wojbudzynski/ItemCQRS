package pl.budzynski.Item.command.api.events;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import pl.budzynski.Item.command.api.data.Item;
import pl.budzynski.Item.command.api.data.ItemRepository;

@Component
public class ItemEventsHandler {

    private ItemRepository itemRepository;

    public ItemEventsHandler(ItemRepository itemRepository) {
        this.itemRepository=itemRepository;
    }

    @EventHandler
    public void on(AddItemEvent event){
        Item item=
                new Item();
        BeanUtils.copyProperties(event,item);
        itemRepository.save(item);

    }
}
