package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "magasinResponse")
public class MagasinResponse {

    private String msg;

    public MagasinResponse(String msg) {
        this.msg = msg;
    }

    public MagasinResponse() {
    }

    public String getMsg() {
        return msg;
    }
    public String setMsg() {
        return msg;
    }
}
