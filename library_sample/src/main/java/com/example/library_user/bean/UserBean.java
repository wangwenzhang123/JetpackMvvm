package com.example.library_user.bean;

public class UserBean {

    /**
     * type : 1
     * url : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590985623286&di=be06323444b064b32e28f6440199530e&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F91529822720e0cf313d90a430146f21fbe09aa58.jpg
     */

    private int type;
    private String url;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
