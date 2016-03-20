package com.mvpdemo.beans;

import java.io.Serializable;

/**
 * @创建者 陆辉
 * @创建时间 2016/3/209:43
 * @描述 ${TODO}
 * @更新者 $Atuthor$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ImageBean implements Serializable{
    private String title;
    private String thumburl;
    private String sourceurl;
    private int height;
    private int width;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
