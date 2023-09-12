package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;
import sn.ept.git.dic2.projetjee.entities.Employe;

@XmlRootElement(name = "employeResponse")
public class EmployeResponse {

    private String msg;

    public EmployeResponse(String msg) {
        this.msg = msg;
    }

    public EmployeResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
