package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categorieResponse")
public class CategorieResponse {
    private String msg;

    public CategorieResponse(String msg) {
        this.msg = msg;
    }

    public CategorieResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
