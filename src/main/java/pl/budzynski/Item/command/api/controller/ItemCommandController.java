package pl.budzynski.Item.command.api.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.budzynski.Item.command.api.commands.AddItemCommand;
import pl.budzynski.Item.command.api.model.ItemRestModel;

import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemCommandController {

    private CommandGateway commandGateway;

    public ItemCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addItem(@RequestBody ItemRestModel itemRestModel){
        AddItemCommand addItemCommand=
            AddItemCommand.builder()
                    .itemId(UUID.randomUUID().toString())
                    .name(itemRestModel.getName())
                    .build();
        String result = commandGateway.sendAndWait(addItemCommand);
        return result;
    }
}
