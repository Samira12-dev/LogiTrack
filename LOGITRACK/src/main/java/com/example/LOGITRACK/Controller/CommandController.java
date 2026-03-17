package com.example.LOGITRACK.Controller;

import com.example.LOGITRACK.ENTITY.Command;
import com.example.LOGITRACK.Service.ClientService;
import com.example.LOGITRACK.Service.CommandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commands")
public class CommandController {
    private final CommandService commandService;
    private final ClientService clientService;

    public CommandController(CommandService commandService,ClientService clientService){
        this.clientService=clientService;
        this.commandService= commandService;
    }

    @PostMapping
    public Command CreateCommand(@RequestParam Long clientId){
        return commandService.createCommand(clientId);
    }
    @GetMapping
    public List<Command> getAllCommands(){
        return  commandService.getAllCommands();
    }
    @GetMapping("/{id}")
    public Command getCommand(@PathVariable  Long id){
        return commandService.getCommandById(id);
    }
    @PutMapping("/{id}/status")
    public Command updateCommand(@PathVariable Long id, @RequestParam String status){
        return commandService.updateStatusCommand(id,status);
    }
    @GetMapping("/client/clientId")
    public List<Command> getCommandById(@PathVariable Long clientId){
        return commandService.getCommandByClient(clientId);
    }
    @GetMapping("/count")
    public long totalCommand(){
        return commandService.totalCommands();
    }
}
