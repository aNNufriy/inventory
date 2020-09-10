package ru.testfield.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.ansible.model.AnsibleHost;
import ru.testfield.ansible.model.AnsibleHostGroup;
import ru.testfield.ansible.model.JstreeNode;
import ru.testfield.ansible.repository.AnsibleHostGroupRepository;
import ru.testfield.ansible.repository.AnsibleHostRepository;
import ru.testfield.ansible.service.InventoryService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class InventoryController extends AbstractWebController {

    private final InventoryService inventoryService;
    private final AnsibleHostRepository ansibleHostRepository;
    private final AnsibleHostGroupRepository ansibleHostGroupRepository;

    public InventoryController(InventoryService inventoryService, AnsibleHostRepository ansibleHostRepository, AnsibleHostGroupRepository ansibleHostGroupRepository) {
        this.inventoryService = inventoryService;
        this.ansibleHostRepository = ansibleHostRepository;
        this.ansibleHostGroupRepository = ansibleHostGroupRepository;
    }

    @RequestMapping("/inventory/list")
    public Map<String,Object> getInventory(){
        return inventoryService.getInventory();
    }

    @RequestMapping("/inventory/jstree")
    public Set<JstreeNode> getInventoryJstree(@RequestParam String id) {
        Iterable<AnsibleHostGroup> allGroups = ansibleHostGroupRepository.findAll();
        Set<JstreeNode> nodes = new TreeSet<>();
        nodes.addAll(StreamSupport.stream(allGroups.spliterator(), false)
                .map(this::getJSTreeNode)
                .collect(Collectors.toSet()));
        Iterable<AnsibleHost> allHosts = ansibleHostRepository.findAll();
        nodes.addAll(StreamSupport.stream(allHosts.spliterator(), false)
                .map(this::getJSTreeNode)
                .collect(Collectors.toSet()));
        return nodes;
    }

    private JstreeNode getJSTreeNode(AnsibleHost ansibleHost) {
        return new JstreeNode(
                ansibleHost.getId().toString(),
                ansibleHost.getGroup() != null ? ansibleHost.getGroup().getId().toString() : "#",
                ansibleHost.getName(),false,
                "server");
    }

    private JstreeNode getJSTreeNode(AnsibleHostGroup group) {
        return new JstreeNode(
                group.getId().toString(),
                group.getParent() != null ? group.getParent().getId().toString() : "#",
                group.getName(),true,
                "group");
    }
}