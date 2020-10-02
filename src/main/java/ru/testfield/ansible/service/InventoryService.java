package ru.testfield.ansible.service;

import org.springframework.stereotype.Service;
import ru.testfield.ansible.model.AnsibleHost;
import ru.testfield.ansible.model.AnsibleHostGroup;
import ru.testfield.ansible.repository.AnsibleHostGroupRepository;

import java.util.*;

@Service
public class InventoryService {

    private final AnsibleHostGroupRepository ansibleHostGroupRepository;

    public InventoryService(AnsibleHostGroupRepository ansibleHostGroupRepository) {
        this.ansibleHostGroupRepository = ansibleHostGroupRepository;
    }

    public Map<String, Object> getInventory() {
        Map<String, Object> groups = new TreeMap<>();
        Iterable<AnsibleHostGroup> allGroups = ansibleHostGroupRepository.findAll();
        for (AnsibleHostGroup hostGroup : allGroups) {
            Map<String, Object> groupMap = new TreeMap<>();
            groupMap.put("hosts", printHosts(hostGroup));
            groupMap.put("children", printChildren(hostGroup));
            groupMap.put("vars", printVars(hostGroup));
            groups.put(hostGroup.getName(), groupMap);
        }
        groups.put("_meta", Map.of("hostvars", printHostVars(allGroups)));
        return groups;
    }

    private Map<String,String> printVars(AnsibleHostGroup hostGroup) {
        return new TreeMap<>();
    }

    private Set<String> printChildren(AnsibleHostGroup hostGroup) {
        var children = new TreeSet<String>();
        if (hostGroup != null) {
            for (var childGroup : hostGroup.getChildren()) {
                children.add(childGroup.getName());
            }
        }
        return children;
    }

    private Set<String> printHosts(AnsibleHostGroup hostGroup) {
        var hosts = new TreeSet<String>();
        if (hostGroup != null) {
            for (var host : hostGroup.getHosts()) {
                hosts.add(host.getName());
            }
        }
        return hosts;
    }

    private TreeMap<String, Map<String, String>> printHostVars(Iterable<AnsibleHostGroup> hostGroups) {
        var hosts = new TreeMap<String, Map<String, String>>();
        if (hostGroups != null) {
            for (var group : hostGroups) {
                if (group != null) {
                    Set<AnsibleHostGroup> children = group.getChildren();
                    printHostVars(children);
                    for (AnsibleHost host : group.getHosts()) {
                        if(!hosts.containsKey(host.getName())){
                            hosts.put(host.getName(),new TreeMap<>());
                        }
                        Map<String, String> variables = host.getVariables();
                        if(variables !=null) {
                            for (String variableName : variables.keySet()) {
                                hosts.get(host.getName()).put(variableName, variables.get(variableName));
                            }
                        }
                    }
                }
            }
        }
        return hosts;
    }
}