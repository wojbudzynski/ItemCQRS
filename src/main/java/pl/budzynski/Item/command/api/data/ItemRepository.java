package pl.budzynski.Item.command.api.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemRepository {

    private List<Item> items;

    public ItemRepository() {
        items= new ArrayList<>();
    }

    public void save(Item item){
        items.add(item);
    }

    public void showItems(){
        for(Item i:items){
            System.out.println(i.getItemId()+" "+i.getName());
        };
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(String itemId) {
        for (Item i :items)
            if(i.getItemId()==itemId)
                return i;
        return null;
    }
}
