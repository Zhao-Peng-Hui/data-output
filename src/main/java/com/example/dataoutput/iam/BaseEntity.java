package com.example.dataoutput.iam;


import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.util.FastByteArrayOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ryvius
 * 基础实体类
 */
public abstract class BaseEntity implements Serializable, Persistable<String>, Cloneable {

    private static final long serialVersionUID = -7166979186411587807L;

    @Id
    private String id;

    @Version
    private Long version;

    @Indexed(unique = false)
    @CreatedDate
    private DateTime createdDate = new DateTime();

    @LastModifiedDate
    @Indexed
    private DateTime lastModifiedDate = new DateTime();

    /* 签名属性集合（签名算法、签名项、签名值、签名者标识、证书公钥值） */
    private Map<String, Object> signInfo = new HashMap<>();

    //实体类创建者
    private String entityCreator;

    //实体类修改者
    private String mender;

    //新增或修改者签名(username)
    private String signName;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == this.getId();
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getEntityCreator() {
        return entityCreator;
    }

    public void setEntityCreator(String entityCreator) {
        this.entityCreator = entityCreator;
    }

    public String getMender() {
        return mender;
    }

    public void setMender(String mender) {
        this.mender = mender;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }


    public Map<String, Object> getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(Map<String, Object> signInfo) {
        this.signInfo = signInfo;
    }

    public BaseEntity clone() throws CloneNotSupportedException {
        BaseEntity entity = null;
        // Write the object out to a byte array
        FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(fbos)) {

            out.writeObject(this);
            out.flush();
            out.close();

        } catch (IOException e) {
            // ignore
        }
        try (ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());) {
            // Retrieve an input stream from the byte array and read
            // a copy of the object back in.
            entity = (BaseEntity) in.readObject();
            fbos.close();
            in.close();
        } catch (IOException e) {
            // ignore
        } catch (ClassNotFoundException cnfe) {
            // ignore
        }
        return entity;
    }
}
