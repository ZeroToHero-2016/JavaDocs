package ro.teamnet.zth.api.em;



import java.util.Objects;

public class ColumnInfo {

    private String columnName;
    private Class columnType;
    private String dbName;
    private boolean idId;
    private Object value;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Class getColumnType() {
        return columnType;
    }

    public void setColumnType(Class columnType) {
        this.columnType = columnType;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public boolean isId() {
        return idId;
    }

    public void setIsId(boolean idId) {
        this.idId = idId;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
