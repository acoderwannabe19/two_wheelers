package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "commandeResponse")
public class CommandeResponse {

    private String msg;

    public CommandeResponse(String msg) {
        this.msg = msg;
    }

    public CommandeResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
