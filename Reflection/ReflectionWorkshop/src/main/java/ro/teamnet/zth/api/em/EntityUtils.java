package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntityUtils {

    private EntityUtils() throws UnsupportedOperationException{
    }

    public static String getTableName(Class t){
        Table table = (Table)t.getAnnotation(Table.class);
        if(table!=null){
            return table.name();
        }else{
            return t.getSimpleName();
        }
    }

    public static List<ColumnInfo> getColumns(Class t){
        Field[] allFields = t.getDeclaredFields();
        List<ColumnInfo> columnInfo = new ArrayList<>();
        for (Field field : allFields){
            ColumnInfo cInfo = new ColumnInfo();
            cInfo.setColumnName(field.getName());
            cInfo.setColumnType(field.getType());
            if(field.getAnnotation(Id.class) != null){
                cInfo.setIsId(true);
                cInfo.setDbName(field.getAnnotation(Id.class).name());
            }
            else{

                cInfo.setIsId(false);
                cInfo.setDbName(field.getAnnotation(Column.class).name());

            }
            columnInfo.add(cInfo);
        }
        return columnInfo;
    }

    public static Object castFromSqlType(Object value, Class wantedType){
        if(value.getClass().equals(BigDecimal.class) && wantedType.equals(Integer.class)){
            return ((BigDecimal) value).intValue();
        }
        if(value.getClass().equals(BigDecimal.class) && wantedType.equals(Long.class)){
            return ((BigDecimal) value).longValue();
        }
        if(value.getClass().equals(BigDecimal.class) && wantedType.equals(Float.class)){
            return ((BigDecimal) value).floatValue();
        }
        if(value.getClass().equals(BigDecimal.class) && wantedType.equals(Double.class)){
            return ((BigDecimal) value).doubleValue();
        }
        if(value.getClass().equals(BigDecimal.class) == false){
            return value;
        }
        return value;
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation){
        Field[] allFields = clazz.getDeclaredFields();
        List<Field> listField = new ArrayList<>();
        for (Field field : allFields){
            if (field.getAnnotation(annotation) != null){
                listField.add(field);
            }
        }
        return listField;
    }

    public static Object getSqlValue(Object object){
        if (object.getClass().getAnnotation(Table.class) != null){
            Field[] allFields = object.getClass().getDeclaredFields();
            for (Field field : allFields){
                if(field.getAnnotation(Id.class) != null){
                    field.setAccessible(true);
                    try {
                        return field.get(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return object;
        }
        else return object;
    }

}
