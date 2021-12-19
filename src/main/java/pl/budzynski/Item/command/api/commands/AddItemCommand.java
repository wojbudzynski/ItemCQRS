package pl.budzynski.Item.command.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class AddItemCommand {

    @TargetAggregateIdentifier
    private String itemId;
    private String name;
}
