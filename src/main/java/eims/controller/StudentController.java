package eims.controller;

import eims.model.acad.Student;
import eims.dto._SearchDTO;
import eims.exception.StudentNotFoundException;
import eims.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;
import java.math.BigInteger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/student")
 
public class StudentController extends _OithController {

    protected static final String MODEL = "student";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private StudentService studentService;





 

    private void commonPost(Student currObject) {

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new Student());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid Student currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

        String picFile=multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }
        String addressPermanent_city=multipartImageFileHandler(request, "addressPermanent_city");
        if (addressPermanent_city != null && !addressPermanent_city.isEmpty()) {
            currObject.getAddressPermanent().setCity(addressPermanent_city);
        }
        String addressPresent_city=multipartImageFileHandler(request, "addressPresent_city");
        if (addressPresent_city != null && !addressPresent_city.isEmpty()) {
            currObject.getAddressPresent().setCity(addressPresent_city);
        }


    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                Student student = studentService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, student.getCode());
                return "redirect:/" + SHOW + "/" + student.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Student student = studentService.findById(id);
        
        if (student == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, student);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid Student currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

        String picFile=multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }
        String addressPermanent_city=multipartImageFileHandler(request, "addressPermanent_city");
        if (addressPermanent_city != null && !addressPermanent_city.isEmpty()) {
            currObject.getAddressPermanent().setCity(addressPermanent_city);
        }
        String addressPresent_city=multipartImageFileHandler(request, "addressPresent_city");
        if (addressPresent_city != null && !addressPresent_city.isEmpty()) {
            currObject.getAddressPresent().setCity(addressPresent_city);
        }


    commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                Student student = studentService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, student.getCode());
                return "redirect:/" + SHOW + "/" + student.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        Student student = studentService.findById(id);

        if (student == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, student);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid Student currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

        String picFile=multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }
        String addressPermanent_city=multipartImageFileHandler(request, "addressPermanent_city");
        if (addressPermanent_city != null && !addressPermanent_city.isEmpty()) {
            currObject.getAddressPermanent().setCity(addressPermanent_city);
        }
        String addressPresent_city=multipartImageFileHandler(request, "addressPresent_city");
        if (addressPresent_city != null && !addressPresent_city.isEmpty()) {
            currObject.getAddressPresent().setCity(addressPresent_city);
        }


    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               Student student = studentService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, student.getCode());
               return "redirect:/" + SHOW + "/" + student.getId();
            } catch (Exception e) {
               errorHandler(bindingResult, e);
            }
        } 
        return COPY;
    }
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String search(@ModelAttribute(SEARCH_CRITERIA) _SearchDTO searchCriteria, ModelMap model) {
        /*
        String searchTerm = searchCriteria.getSearchTerm();
        List<Student> students;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            students = studentService.search(searchCriteria);
        } else {
            students = studentService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, students);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Student> students = studentService.findAll();
        model.addAttribute(MODELS, students);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<Student> students = studentService.findAll(searchCriteria);

        model.addAttribute(MODELS, students);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Student> students = studentService.findAll();
        model.addAttribute(MODELS, students);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Student student = studentService.findById(id);

        if (student == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, student);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            Student deleted = studentService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (StudentNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
