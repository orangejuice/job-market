package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Settings implements Serializable {
    @Id
    private String dKey;

    private String dValue;


    public String getDKey() {
        return dKey;
    }

    public void setDKey(String key) {
        this.dKey = key;
    }

    public String getDValue() {
        return dValue;
    }

    public void setDValue(String value) {
        this.dValue = value;
    }


}
