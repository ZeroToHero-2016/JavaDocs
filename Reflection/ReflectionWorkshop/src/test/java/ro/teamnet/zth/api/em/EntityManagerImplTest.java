package ro.teamnet.zth.api.em;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EntityManagerImplTest {
    EntityManagerImpl entity = new EntityManagerImpl();
    @Test
    public void testFindAllMethod() throws ClassNotFoundException, SQLException {
        List<Department> l = entity.findAll(Department.class);
        assertEquals("",27,l.size());
    }

    @Test
    public void testGetNextIdVal() {
        EntityManagerImpl EMI = new EntityManagerImpl();
        Long result = EMI.getNextIdVal("departments", "department_id");
        assertEquals(271, result.longValue());
    }

    @Test
    public void testInsert() {
        Department toBeInserted = new Department();
        toBeInserted.setDepartmentName("Sport");
        toBeInserted.setLocation(1000L);
        Department departamentInserat = (Department)entity.insert(toBeInserted);
        assertEquals (toBeInserted.getDepartmentName(),departamentInserat.getDepartmentName());
    }
    @Test
    public void testInsertMethod() {
        Location toBeInserted = new Location();
        toBeInserted.setId((long) 1000);
        toBeInserted.setCity("Bucharest");
        toBeInserted.setPostalCode("235468");
        toBeInserted.setStateProvince("Bucharest");
        toBeInserted.setStreetAddress("Bucharest");
        assertNotNull(entity.insert(toBeInserted));
    }

    @Test
    public void testFindByIdMethod() {
        Department depTest = new Department();
        try {
            Object test = entity.findById(depTest.getClass(), new Long(270));
            Assert.assertTrue(test != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        Department toBeUpdate = new Department();
        toBeUpdate.setId((long) 130);
        toBeUpdate.setDepartmentName("Sport");
        toBeUpdate.setLocation(1000L);
        entity.deleteById(toBeUpdate);
    }
}

