package pl.budzynski.Item.command.api.aggregate;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import pl.budzynski.Item.command.api.commands.AddItemCommand;
import pl.budzynski.Item.command.api.events.AddItemEvent;

@Aggregate
public class ItemAggregate {

    @AggregateIdentifier
    private String itemId;
    private String name;

    public ItemAggregate() {
    }
    @CommandHandler
    public ItemAggregate(AddItemCommand addItemCommand) {
        AddItemEvent addItemEvent =
                new AddItemEvent();

        BeanUtils.copyProperties(addItemCommand, addItemEvent);

        AggregateLifecycle.apply(addItemEvent);
    }

    @EventSourcingHandler
    public void on(AddItemEvent addItemEvent){
        this.name=addItemEvent.getName();
        this.itemId=addItemEvent.getItemId();
    }
}
