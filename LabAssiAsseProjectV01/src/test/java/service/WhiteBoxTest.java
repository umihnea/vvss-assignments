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
import java.util.List;

public class WhiteBoxTest {
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
    public void testIdConditional() {
        String[] trueArgs = {"valid-id", "desc", "14", "1"};
        String[] falseArgs = {null, "desc", "14", "1"};
        String[] falseArgs1 = {"", "desc", "14", "1"};

        Assert.assertEquals(0, addAssignment(trueArgs));
        Arrays.asList(falseArgs, falseArgs1).forEach(args -> {
            Assert.assertEquals(-1, addAssignment(falseArgs));
        });
    }

    @Test
    public void testDescriptionConditional() {
        String[] trueArgs = {"valid-id", "desc", "14", "1"};
        String[] falseArgs = {"valid-id", null, "14", "1"};
        String[] falseArgs1 = {"valid-id", "", "14", "1"};

        Assert.assertEquals(0, addAssignment(trueArgs));
        Arrays.asList(falseArgs, falseArgs1).forEach(args -> {
            Assert.assertEquals(-1, addAssignment(falseArgs));
        });
    }

    @Test
    public void testDeadlineConditional() {
        String[] trueArgs = {"id", "desc", "14", "1"};
        Assert.assertEquals(0, addAssignment(trueArgs));

        String[] falseArgs = {"id", "desc", "-2", "1"};
        String[] falseArgs1 = {"id", "desc", "16", "1"};
        String[] falseArgs2 = {"id", "desc", "1", "14"};

        Arrays.asList(falseArgs, falseArgs1, falseArgs2).forEach(args -> {
            Assert.assertEquals(-1, addAssignment(args));
        });
    }

    @Test
    public void testStartlineConditional() {
        String[] trueArgs = {"id", "desc", "14", "1"};
        Assert.assertEquals(0, addAssignment(trueArgs));

        String[] falseArgs = {"id", "desc", "14", "-1"};
        String[] falseArgs1 = {"id", "desc", "14", "15"};
        String[] falseArgs2 = {"id", "desc", "1", "14"};

        Arrays.asList(falseArgs, falseArgs1, falseArgs2).forEach(args -> {
            Assert.assertEquals(-1, addAssignment(args));
        });
    }

}