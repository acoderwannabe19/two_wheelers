package sn.ept.git.dic2.projetjee.rest.response;


import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "produitResponse")
public class ProduitResponse {

    private String msg;

    public ProduitResponse(String msg) {
        this.msg = msg;
    }

    public ProduitResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
