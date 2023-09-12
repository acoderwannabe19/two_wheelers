package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "marqueResponse")
public class MarqueResponse {
    private String msg;

    public MarqueResponse(String msg) {
        this.msg = msg;
    }

    public MarqueResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
