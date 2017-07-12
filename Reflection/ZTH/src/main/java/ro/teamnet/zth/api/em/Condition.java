package ro.teamnet.zth.api.em;

/**
 * Created by Radu.Furculesteanu on 7/12/2017.
 */
public class Condition {
    private String colimnName;
    private Object value;

    public String getColimnName() {
        return colimnName;
    }

    public void setColimnName(String colimnName) {
        this.colimnName = colimnName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
