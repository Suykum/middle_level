package ru.job4j.entity;

import java.sql.Date;

public class Item {
    private int id;
    private String desc;
    private Date created;
    private boolean done;

    public Item() {

    }
    public Item(String desc, Date created, boolean done) {
        setDesc(desc);
        setCreated(created);
        setDone(done);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
