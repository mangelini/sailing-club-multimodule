module backend {
    requires java.sql;
    requires java.sql.rowset;
    exports messageManagement to frontend;
    exports entities to frontend;
    opens entities to javafx.base;
}