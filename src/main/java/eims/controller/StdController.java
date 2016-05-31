package eims.controller;

import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import eims.model.acad.Student;
import eims.service.StudentService;

@Controller
@RequestMapping("/std")
public class StdController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MessageSource messageSource;

    /*
     * This method will list all existing students.
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listStudents(ModelMap model) {

        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "std/index";
    }

    /*
     * This method will provide the medium to add a new student.
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newStudent(ModelMap model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("edit", false);
        return "std/registration";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving student in database. It also validates the user input
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveStudent(@Valid Student student, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "std/registration";
        }

        /*
         * Preferred way to achieve uniqueness of field [code] should be implementing custom @Unique annotation 
         * and applying it on field [code] of Model class [Student].
         * 
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         * 
         */
        if (!studentService.isStudentCodeUnique(student.getId(), student.getCode())) {
            FieldError codeError = new FieldError("student", "code", messageSource.getMessage("non.unique.code", new String[]{student.getCode()}, Locale.getDefault()));
            result.addError(codeError);
            return "std/registration";
        }

        studentService.saveStudent(student);

        model.addAttribute("success", "Student " + student.getName() + " registered successfully");
        return "std/success";
    }


    /*
     * This method will provide the medium to update an existing student.
     */
    @RequestMapping(value = {"/edit-{code}-student"}, method = RequestMethod.GET)
    public String editStudent(@PathVariable String code, ModelMap model) {
        Student student = studentService.findStudentByCode(code);
        model.addAttribute("student", student);
        model.addAttribute("edit", true);
        return "std/registration";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating student in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-{code}-student"}, method = RequestMethod.POST)
    public String updateStudent(@Valid Student student, BindingResult result,
            ModelMap model, @PathVariable String code) {

        if (result.hasErrors()) {
            return "std/registration";
        }

        if (!studentService.isStudentCodeUnique(student.getId(), student.getCode())) {
            FieldError codeError = new FieldError("student", "code", messageSource.getMessage("non.unique.code", new String[]{student.getCode()}, Locale.getDefault()));
            result.addError(codeError);
            return "std/registration";
        }

        studentService.updateStudent(student);

        model.addAttribute("success", "Student " + student.getName() + " updated successfully");
        return "std/success";
    }

    /*
     * This method will delete an student by it's SSN value.
     */
    @RequestMapping(value = {"/delete-{code}-student"}, method = RequestMethod.GET)
    public String deleteStudent(@PathVariable String code) {
        studentService.deleteStudentByCode(code);
        return "redirect:/std/index";
    }

}
