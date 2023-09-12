package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;
import sn.ept.git.dic2.projetjee.entities.Stock;

@XmlRootElement(name = "stockResponse")
public class StockResponse {

    private String msg;

    public StockResponse(String msg) {
        this.msg = msg;
    }

    public StockResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
