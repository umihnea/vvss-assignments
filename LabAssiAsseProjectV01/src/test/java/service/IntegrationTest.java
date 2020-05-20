package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.util.Arrays;

public class IntegrationTest {
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
     * Wrap over the call to the service to write tests more easily.
     *
     * @param args: in order: id, description, deadline,
     *              startline (all as strings).
     * @return 0 on success, -1 on failure.
     */
    private int addAssignment(String[] args) {
        return service.saveTema(
                args[0],
                args[1],
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
        );
    }

    @Test
    public void testAssignment() {
        String[] trueArgs = {"valid-id", "desc", "14", "1"};
        String[] falseArgs = {null, "desc", "14", "1"};
        String[] falseArgs1 = {"", "desc", "14", "1"};

        Assert.assertEquals(0, addAssignment(trueArgs));
        Arrays.asList(falseArgs, falseArgs1).forEach(args -> {
            Assert.assertEquals(-1, addAssignment(falseArgs));
        });
    }

    @Test
    public void testStudent() {
        Assert.assertEquals(
                0,
                service.saveStudent("id0", "s0", 937)
        );
        Assert.assertEquals(
                1,
                service.saveStudent(null, "s0", 937)
        );
    }

    @Test
    public void testGrade() {
        Assert.assertEquals(
                0,
                service.saveNota("1", "1", 8, 10, "good")
        );
        Assert.assertEquals(
                -1,
                service.saveNota("-1", "1", 10, 10, "good")
        );
    }

    @Test
    public void testAll() {
        testAssignment();
        testStudent();
        testGrade();
    }

    @Test
    public void testAddStudent(){
        Assert.assertEquals(
                0,
                service.saveStudent("id0", "name1", 935)
        );
        Assert.assertEquals(
                1,
                service.saveStudent(null, "name2", 935)
        );
    }

    @Test
    public void testAddAssignment(){
        testAddStudent();
        String[] trueArgs = {"valid-id", "desc", "14", "1"};
        String[] falseArgs = {null, "desc", "14", "1"};
        String[] falseArgs1 = {"", "desc", "14", "1"};

        Assert.assertEquals(0, addAssignment(trueArgs));
        Arrays.asList(falseArgs, falseArgs1).forEach(args -> {
            Assert.assertEquals(-1, addAssignment(falseArgs));
        });
    }

    @Test
    public void testAddGrade(){
        testAddAssignment();
        Assert.assertEquals(
                0,
                service.saveNota("1", "1", 8, 10, "Good")
        );
        Assert.assertEquals(
                -1,
                service.saveNota("-1", "1", 10, 10, "Good")
        );
    }

}
