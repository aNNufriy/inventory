package ru.testfield.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.ansible.model.AnsibleHostGroup;
import ru.testfield.ansible.model.JstreeNode;
import ru.testfield.ansible.repository.AnsibleHostGroupRepository;
import ru.testfield.ansible.service.InventoryService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class InventoryController extends AbstractWebController {

    private final InventoryService inventoryService;
    private final AnsibleHostGroupRepository ansibleHostGroupRepository;

    public InventoryController(InventoryService inventoryService, AnsibleHostGroupRepository ansibleHostGroupRepository) {
        this.inventoryService = inventoryService;
        this.ansibleHostGroupRepository = ansibleHostGroupRepository;
    }

    @RequestMapping("/inventory/list")
    public Map<String,Object> getInventory(){
        return inventoryService.getInventory();
    }

    @RequestMapping("/inventory/jstree")
    public Set<JstreeNode> getInventoryJstree(@RequestParam Optional<String> id) {
        Set<AnsibleHostGroup> groups = ansibleHostGroupRepository.findByParentId(null);
        Set<JstreeNode> nodes = groups.stream().map(this::getJstreeNode).collect(Collectors.toSet());
        for (JstreeNode node : Set.copyOf(nodes)) {
            if(node.getId()!=null) {
                Set<AnsibleHostGroup> ngr = ansibleHostGroupRepository.findByParentId(UUID.fromString(node.getId()));
                nodes.addAll(ngr.stream().map(this::getJstreeNode).collect(Collectors.toSet()));
            }
        }
        return nodes;
    }

    private JstreeNode getJstreeNode(AnsibleHostGroup host) {
        return new JstreeNode(
                host.getId().toString(),
                host.getParent() != null ? host.getParent().getId().toString() : "#",
                host.getName());
    }
}