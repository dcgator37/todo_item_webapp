package database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoDatabaseTest {

    @Test
    void sanityTest() {
        assertTrue(true, "JUnit is working");
    }

    @Test
    void testAddAndRetrieve() throws Exception {
        TodoDatabase dao = new TodoDatabase();
        dao.add("Test Item");

        assertTrue(
                dao.findAll()
                        .stream()
                        .anyMatch(i -> i.getText().equals("Test Item"))
        );
    }

    @Test
    void testAddDeleteItem() throws Exception {
        TodoDatabase dao = new TodoDatabase();
        dao.add("Test Item");
        dao.delete(1);
        assertFalse(
                dao.findAll()
                        .stream()
                        .anyMatch(i -> i.getText().equals("Test Item"))
        );
    }
}