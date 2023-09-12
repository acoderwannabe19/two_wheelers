package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "articleCommandeResponse")
public class ArticleCommandeResponse {

    private String msg;

    public ArticleCommandeResponse(String msg) {
        this.msg = msg;
    }

    public ArticleCommandeResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
