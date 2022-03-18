package entities;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Abstract parent class of every fee
 */
public abstract class Fee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected Integer ID;
    protected String paymentType;
    protected Timestamp date;
    protected Double fee;
}
