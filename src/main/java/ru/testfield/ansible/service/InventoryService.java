package ru.testfield.ansible.service;

import org.springframework.stereotype.Service;
import ru.testfield.ansible.model.AnsibleHostGroup;
import ru.testfield.ansible.repository.AnsibleHostGroupRepository;

import java.util.*;

@Service
public class InventoryService {

    private final AnsibleHostGroupRepository ansibleHostGroupRepository;

    public InventoryService(AnsibleHostGroupRepository ansibleHostGroupRepository) {
        this.ansibleHostGroupRepository = ansibleHostGroupRepository;
    }

    public Map<String,Object> getInventory(){
        Map<String,Object> groups = new TreeMap<>();
        Iterable<AnsibleHostGroup> all = ansibleHostGroupRepository.findAll();
        for (AnsibleHostGroup hostGroup : all) {
            Map<String,Object> groupMap = new TreeMap<>();
            groupMap.put("hosts",printHosts(hostGroup));
            groupMap.put("children",printChildren(hostGroup));
            groupMap.put("vars",printVars(hostGroup));
            groups.put(hostGroup.getName(), groupMap);
        }
        groups.put("_meta",Map.of("hostvars", new TreeMap<String,String>()));
        return groups;
    }

    private Set<String> printVars(AnsibleHostGroup hostGroup) {
        var vars = new TreeSet<String>();
        return vars;
    }

    private Set<String> printChildren(AnsibleHostGroup hostGroup) {
        var children = new TreeSet<String>();
        if(hostGroup!=null){
            for (var childGroup: hostGroup.getChildren()) {
                children.add(childGroup.getName());
            }
        }
        return children;
    }

    private Set<String> printHosts(AnsibleHostGroup hostGroup) {
        var hosts = new TreeSet<String>();
        if(hostGroup!=null){
            for (var host: hostGroup.getHosts()) {
                hosts.add(host.getName());
            }
        }
        return hosts;
    }
}