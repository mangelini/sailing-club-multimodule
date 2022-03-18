package messageManagement;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Reply sent by server to respond to clients request
 */
public class Reply implements Serializable {
    private ReplyType responseCode;
    private ArrayList<Serializable> results;
    @Serial
    private static final long serialVersionUID = 1L;

    public Reply() {
        this.results = new ArrayList<>();
    }

    public Reply(ReplyType responseCode, ArrayList<Serializable> results) {
        this.responseCode = responseCode;
        this.results = results;
    }

    public Reply(ReplyType responseCode, Serializable obj) {
        this.responseCode = responseCode;
        this.results = new ArrayList<Serializable>();
        this.results.add(obj);
    }

    public Reply(ReplyType responseCode) {
        this.responseCode = responseCode;
        this.results = new ArrayList<Serializable>();
    }

    public ReplyType getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ReplyType responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<Serializable> getResults() {
        return results;
    }

    public void setResults(ArrayList<Serializable> results) {
        this.results = results;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public boolean wasOk() {
        return this.responseCode == ReplyType.OK;
    }

    public void addResult(Serializable obj){
        this.results.add(obj);
    }
}
