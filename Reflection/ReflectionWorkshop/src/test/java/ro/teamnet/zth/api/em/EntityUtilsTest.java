package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EntityUtilsTest {


    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetColumnsMethod() {
        List<ColumnInfo> tableName = EntityUtils.getColumns(Department.class);
        assertEquals("List size should be 3!", 3, tableName.size());
    }

    @Test
    public void testcastFromSqlTypeMethod() {
        BigDecimal value = new BigDecimal(1.0);
        Object o = EntityUtils.castFromSqlType(value, Integer.class);
        assertEquals("Table name should be departments!", Integer.class, o.getClass());
    }

    @Test
    public void testgetFieldsByAnnotationsMethod() {
        List<Field> tableName = EntityUtils.getFieldsByAnnotations(Department.class, Column.class);
        assertEquals("Departments has 2 columns.", 2, tableName.size());
    }

    @Test
    public void testgetSqlValueMethod() {
        Department d = new Department();
        d.setId(12);
        Object tableName = EntityUtils.getSqlValue(d);
        assertEquals("Departments has 2 columns.", (long)12, tableName);
    }

}
