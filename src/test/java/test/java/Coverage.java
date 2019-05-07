package test.java;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class Coverage {

    @Test
    public void addAssignmentValid()
    {
        int assignmentsBefore = 0;
        int assignmentsAfter = 0;

        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        for (Tema assignment : service.findAllTeme())
        {
            assignmentsBefore += 1;
        }
        service.saveTema(Integer.toString(assignmentsBefore + 100), "Description", 4, 2);
        for (Tema assignment : service.findAllTeme())
        {

            assignmentsAfter += 1;
        }
        assert(assignmentsBefore + 1 == assignmentsAfter);
    }

    @Test
    public void addAssignmentInvalid()
    {
        //input data is invalid (the id is incorrect) the test should fail
        int count = 0;
        int countafter = 0;

        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        for (Tema assignment : service.findAllTeme())
        {
            count += 1;
        }
        service.saveTema("", "Test descr", 4, 2);
        for (Tema assignment : service.findAllTeme())
        {
            countafter += 1;
        }
        assert(count == countafter);
    }

    @Test
    public void addAssignmentInvalid2()
    {
        //input data is invalid (deadline date is before the starting date) so the test should fail
        int count = 0;
        int countafter = 0;

        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        for (Tema assignment : service.findAllTeme())
        {
            count += 1;
        }
        service.saveTema(Integer.toString(count + 100), "", 4, 2);
        for (Tema assignment : service.findAllTeme())
        {
            countafter += 1;
        }
        assert(count == countafter);
    }
}
