package com.example.javacode.model;

import com.google.gson.annotations.SerializedName;

public class JavacodeModel {

    private String id;
    private String title;
    private String about;
    private String code;
    private String androidv4_supported;
    private String androidx_supported;
    private String category;
    private String source;
    private String uid;
    private String username;
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setAndroidv4_supported(String androidv4_supported) {
        this.androidv4_supported = androidv4_supported;
    }

    public String getAndroidv4_supported() {
        return androidv4_supported;
    }

    public void setAndroidx_supported(String androidx_supported) {
        this.androidx_supported = androidx_supported;
    }

    public String getAndroidx_supported() {
        return androidx_supported;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
