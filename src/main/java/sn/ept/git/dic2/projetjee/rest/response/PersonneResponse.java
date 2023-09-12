package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personneResponse")
public class PersonneResponse {

    private String msg;

    public PersonneResponse(String msg) {
        this.msg = msg;
    }

    public PersonneResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
