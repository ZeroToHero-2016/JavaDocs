package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;


    public String getValueForQuery(Object value){
        if (value.getClass().equals(String.class)){
            return (String)value;
        }
        if (value.getClass().equals(Date.class)){
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy"); 
            return "TO_DATE('" + dateFormat.format((Date)value) + "','mm-dd-yyyy'";
        }
        return "";
    }

    public QueryBuilder addCondition(Condition condition){
        QueryBuilder query = new QueryBuilder();

    }
}
