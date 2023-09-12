package sn.ept.git.dic2.projetjee.rest.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gpsResponse")
public class GPSResponse {

    private String msg;

    public GPSResponse(String msg) {
        this.msg = msg;
    }

    public GPSResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
