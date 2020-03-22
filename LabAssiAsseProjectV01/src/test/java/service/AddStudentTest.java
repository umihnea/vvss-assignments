package service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import domain.*;
import org.junit.jupiter.api.TestInstance;
import validation.*;
import repository.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddStudentTest {
    Service service;

    @BeforeAll
    public void setUp() {
        Validator<Student> sv = new StudentValidator();
        Validator<Tema> tv = new TemaValidator();
        Validator<Nota> nv = new NotaValidator();

        StudentXMLRepository sr = new StudentXMLRepository(sv, "studenti.xml");
        TemaXMLRepository tr = new TemaXMLRepository(tv, "teme.xml");
        NotaXMLRepository nr = new NotaXMLRepository(nv, "note.xml");

        service = new Service(sr, tr, nr);
    }

    /**
     * Add a student. Trying to add a student with a group number
     * outside inclusive range [110, 938] should fail (fail = return code is 1).
     */
    @Test
    public void studentGroupShouldBeInRange() {
        Assert.assertEquals(
                1,
                service.saveStudent("id0", "s0", 99)
        );
        Assert.assertEquals(
                1,
                service.saveStudent("id1", "s1", 939)
        );

        Assert.assertEquals(
                0,
                service.saveStudent("id0", "s0", 111)
        );
        Assert.assertEquals(
                0,
                service.saveStudent("id1", "s1", 937)
        );
    }

    /**
     * Add a student. Trying to add a second student
     * with the same ID should fail.
     */
    @Test
    void studentIdShouldBeUnique() {
        Assert.assertEquals(
                0,
                service.saveStudent("non-unique", "st0", 937)
        );

        /* This case should fail. Return code should not be 0. */
        Assert.assertNotEquals(
                0,
                service.saveStudent("non-unique", "st1", 826)
        );
    }
}