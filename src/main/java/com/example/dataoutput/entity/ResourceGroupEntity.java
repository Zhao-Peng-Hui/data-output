package com.example.dataoutput.entity;

import com.example.dataoutput.enums.ResourceGroupType;
import com.example.dataoutput.iam.BaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 * 组织结构
 * <pre>
 *     构建企业组织机构树
 * </pre>
 *
 * @author ryvius 资源组
 */
@Document
public class ResourceGroupEntity extends BaseEntity {

    private static final long serialVersionUID = 5311048781900930142L;

    /**
     * 父资源组ID
     */
    @Indexed
    private String parentId;

    /**
     * 父资源路径
     * 以 , 逗号分隔
     */
    private String parentPath;


    private String province;

    /**
     * 资源组名称
     */
    private String name;

    @Indexed(unique = true)
    private String code;

    private String description;

    private int order;

    private String owner;

    @Indexed(unique = false)
    private boolean removed = false;

    /**
     * 组织机构是否有效，默认有效
     */
    private boolean enabled = true;


    /**
     * 组织机构类型，默认为外部组织机构
     */
    private ResourceGroupType orgType = ResourceGroupType.EXTERNAL_GROUP;

    /**
     * 组织机构内嵌属性
     */
    private Map<String, Object> attrs = new HashMap<>();

    // 范围
    private List<Double[]> coordinates = new ArrayList<>();

    private Set<String> ips;

    public static Set<String> attributes() {
        return Collections.unmodifiableSet(
                new HashSet<>(
                        Arrays.asList("name",
                                "code",
                                "parentCode",
                                "description",
                                "order",
                                "编码",
                                "机构名",
                                "上级机构",
                                "省份",
                                "描述",
                                "创建者",
                                "创建日期")));
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 序号
     */
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * 创建者
     */
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 组织机构编码
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 是否已删除 （软删除标记）
     */
    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public Set<String> getIps() {
        return ips;
    }

    public void setIps(Set<String> ips) {
        this.ips = ips;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public void setOrgType(ResourceGroupType orgType) {
        this.orgType = orgType;
    }

    public ResourceGroupType getOrgType() {
        return this.orgType;
    }
}
