package com.barry.po;

/**
 * Created by Barry on 2018/2/4.
 */
public class UserFIle {
    private String user_id;
    private String file_name;
    private byte[] file_content;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public byte[] getFile_content() {
        return file_content;
    }

    public void setFile_content(byte[] file_content) {
        this.file_content = file_content;
    }
}
