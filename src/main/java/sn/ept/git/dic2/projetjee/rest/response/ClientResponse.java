package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clientResponse")
public class ClientResponse {

    private String msg;

    public ClientResponse(String msg) {
        this.msg = msg;
    }

    public ClientResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
