package sn.ept.git.dic2.projetjee.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
public class GPSLocation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double latitude;
    private double longitude;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    public GPSLocation() {
    }
    public GPSLocation(UUID id, double latitude, double longitude, Date timestamp) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
