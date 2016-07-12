package ro.teamnet.zth.api.em;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.api.database.DBManager;

import java.sql.SQLException;

public class DBManagerTest {
        @Test
        public void testGetConnectionMethod() throws ClassNotFoundException, SQLException {
            Assert.assertNotNull(DBManager.getConnection());
        }
        @Test
        public void testCheckConnectionMethod() throws ClassNotFoundException, SQLException {
            boolean connected = DBManager.checkConnection(DBManager.getConnection());
            Assert.assertTrue(connected);
        }
}
