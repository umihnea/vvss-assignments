package service;

import org.junit.*;
import domain.*;
import validation.*;
import repository.*;

public class BlackBoxTest {
    Service service;

    @Before
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
     * TC 1. Equivalent class testing for 'id'
     */
    @Test
    public void studentIdShouldBeNonNull() {
        Assert.assertEquals(
                0,
                service.saveStudent("id0", "s0", 937)
        );
        Assert.assertEquals(
                1,
                service.saveStudent(null, "s0", 937)
        );
    }

    /**
     * TC 2. Equivalent class testing for 'name'
     */
    @Test
    public void studentNameShouldBeNonNull() {
        Assert.assertEquals(
                0,
                service.saveStudent("id1", "s0", 937)
        );
        Assert.assertEquals(
                1,
                service.saveStudent("id2", null, 937)
        );
    }

    /**
     * TC 3. Trying to add a student with a group number
     * outside range [110, 938) should fail (failure code is 1).
     */
    @Test
    public void studentGroupBoundaryValueTesting() {
        Assert.assertEquals(
                1,
                service.saveStudent("id3", "s0", 109)
        );
        Assert.assertEquals(
                1,
                service.saveStudent("id4", "s1", 110)
        );
        Assert.assertEquals(
                0,
                service.saveStudent("id5", "s0", 111)
        );
        Assert.assertEquals(
                0,
                service.saveStudent("id6", "s1", 937)
        );
        Assert.assertEquals(
                1,
                service.saveStudent("id7", "s1", 938)
        );
        Assert.assertEquals(
                1,
                service.saveStudent("id8", "s1", 939)
        );
    }
}