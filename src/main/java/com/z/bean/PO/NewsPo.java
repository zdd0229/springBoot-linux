package com.z.bean.PO;

import com.z.bean.BasePO;

public class NewsPo extends BasePO {
    private int id;//
    private String title;//标题
    private String content;//内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
