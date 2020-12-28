package com.example.dataoutput.iam;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.*;

/**
 * @author ryvius
 * 资源对象
 */
public abstract class ResourceItemEntity extends BaseEntity {

    private static final long serialVersionUID = 7888764492268022207L;

    /**
     * 资源对象所在的组ID
     */
    @Indexed
    private LinkedHashSet<String> groupIds = new LinkedHashSet<String>();

    @Indexed
    private Set<String> groupChain = new HashSet<>();

    public LinkedHashSet<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(LinkedHashSet<String> groupIds) {
        this.groupIds = groupIds;
    }

    public Set<String> getGroupChain() {
        return groupChain;
    }

    public void setGroupChain(Set<String> groupChain) {
        this.groupChain = groupChain;
    }
}
