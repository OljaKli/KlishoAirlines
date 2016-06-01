package dao;

import com.klisho.airlines.Employee;
import com.klisho.airlines.Profession;
import dao.interfaces.EmployeeDao;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.cert.PKIXRevocationChecker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by Ola-Mola on 01/06/16.
 */
public class MySQLDaoTest {

    private static Connection connection;

    @BeforeClass
    public static void prepare() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        connection = DriverManager.getConnection("jdbc:mysql://localhost/airlines?" +
                                                    "user=root&password=n45v7845");
    }

    @Test
    public void testEmployeeDao() {
        EmployeeDao dao = new EmployeeDao(connection);

        {
            Optional<Employee> e1 = dao.getEmployeeById(1);
            Assert.assertTrue(e1.isPresent());

            System.out.println("employee 1: " + e1.get());

            Assert.assertEquals(e1.get().getFirstName(), "Алексей");
            Assert.assertEquals(e1.get().getLastName(), "Маресьев");
            Assert.assertEquals(e1.get().getProfession(), Profession.PILOT);
        }

        {
            Optional<Employee> e1 = dao.getEmployeeById(2);
            Assert.assertTrue(e1.isPresent());

            System.out.println("employee 2: " + e1.get());

            Assert.assertEquals(e1.get().getFirstName(), "Иван");
            Assert.assertEquals(e1.get().getLastName(), "Кожедуб");
            Assert.assertEquals(e1.get().getProfession(), Profession.PILOT);
        }
    }

    @Test
    public void testAllEmployeesDao() {
        EmployeeDao dao = new EmployeeDao(connection);

        {
            Collection<Employee> emps = dao.getAll();

            System.out.println("Total " + emps.size() + " employees");

            Assert.assertTrue(emps.size() >= 3);

            Employee e1 = emps.iterator().next();
            Assert.assertEquals(e1.getFirstName(), "Алексей");
            Assert.assertEquals(e1.getLastName(), "Маресьев");
            Assert.assertEquals(e1.getProfession(), Profession.PILOT);
        }
    }

    @AfterClass
    public static void finish() throws Exception {
        if (connection !=  null) {
            connection.close();
        }
    }
}