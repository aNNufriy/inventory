package ru.testfield.ansible.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Data
@Setter
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JstreeNode implements Comparable<JstreeNode> {
    private String id;
    private String parent;
    private String text;
    private boolean leafNode;
    private String type;

    @Override
    public int compareTo(JstreeNode jstreeNode) {
        if(!this.isLeafNode()&&jstreeNode.isLeafNode()){
            return 1;
        }else{
            if(this.getText()!=null) {
                return getUniqueId(this).compareTo(getUniqueId(jstreeNode));
            } else {
                return -1;
            }
        }
    }

    private String getUniqueId(JstreeNode jstreeNode) {
        return jstreeNode.getText() + jstreeNode.getId();
    }
}
