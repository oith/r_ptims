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
import eims.model.hr.Employee;
import eims.service.EmployeeService;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MessageSource messageSource;

    /*
     * This method will list all existing employees.
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listEmployees(ModelMap model) {

        List<Employee> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees);
        return "emp/index";
    }

    /*
     * This method will provide the medium to add a new employee.
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newEmployee(ModelMap model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("edit", false);
        return "emp/registration";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving employee in database. It also validates the user input
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveEmployee(@Valid Employee employee, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "emp/registration";
        }

        /*
         * Preferred way to achieve uniqueness of field [code] should be implementing custom @Unique annotation 
         * and applying it on field [code] of Model class [Employee].
         * 
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         * 
         */
        if (!employeeService.isEmployeeCodeUnique(employee.getId(), employee.getCode())) {
            FieldError codeError = new FieldError("employee", "code", messageSource.getMessage("non.unique.code", new String[]{employee.getCode()}, Locale.getDefault()));
            result.addError(codeError);
            return "emp/registration";
        }

        employeeService.saveEmployee(employee);

        model.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
        return "emp/success";
    }


    /*
     * This method will provide the medium to update an existing employee.
     */
    @RequestMapping(value = {"/edit-{code}-employee"}, method = RequestMethod.GET)
    public String editEmployee(@PathVariable String code, ModelMap model) {
        Employee employee = employeeService.findEmployeeByCode(code);
        model.addAttribute("employee", employee);
        model.addAttribute("edit", true);
        return "emp/registration";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating employee in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-{code}-employee"}, method = RequestMethod.POST)
    public String updateEmployee(@Valid Employee employee, BindingResult result,
            ModelMap model, @PathVariable String code) {

        if (result.hasErrors()) {
            return "emp/registration";
        }

        if (!employeeService.isEmployeeCodeUnique(employee.getId(), employee.getCode())) {
            FieldError codeError = new FieldError("employee", "code", messageSource.getMessage("non.unique.code", new String[]{employee.getCode()}, Locale.getDefault()));
            result.addError(codeError);
            return "emp/registration";
        }

        employeeService.updateEmployee(employee);

        model.addAttribute("success", "Employee " + employee.getName() + " updated successfully");
        return "emp/success";
    }

    /*
     * This method will delete an employee by it's SSN value.
     */
    @RequestMapping(value = {"/delete-{code}-employee"}, method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable String code) {
        employeeService.deleteEmployeeByCode(code);
        return "redirect:/emp/index";
    }

}
