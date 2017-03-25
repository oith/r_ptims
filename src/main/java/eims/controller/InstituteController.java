package eims.controller;

import eims.model.acad.Institute;
import eims.dto._SearchDTO;
import eims.exception.InstituteNotFoundException;
import eims.service.InstituteService;

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
@RequestMapping(value = "/institute")
 
public class InstituteController extends _OithController {

    protected static final String MODEL = "institute";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private InstituteService instituteService;





 

    private void commonPost(Institute currObject) {

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new Institute());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid Institute currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

        String address_city=multipartImageFileHandler(request, "address_city");
        if (address_city != null && !address_city.isEmpty()) {
            currObject.getAddress().setCity(address_city);
        }


    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                Institute institute = instituteService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, institute.getCode());
                return "redirect:/" + SHOW + "/" + institute.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Institute institute = instituteService.findById(id);
        
        if (institute == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, institute);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid Institute currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

        String address_city=multipartImageFileHandler(request, "address_city");
        if (address_city != null && !address_city.isEmpty()) {
            currObject.getAddress().setCity(address_city);
        }


    commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                Institute institute = instituteService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, institute.getCode());
                return "redirect:/" + SHOW + "/" + institute.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        Institute institute = instituteService.findById(id);

        if (institute == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, institute);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid Institute currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

        String address_city=multipartImageFileHandler(request, "address_city");
        if (address_city != null && !address_city.isEmpty()) {
            currObject.getAddress().setCity(address_city);
        }


    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               Institute institute = instituteService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, institute.getCode());
               return "redirect:/" + SHOW + "/" + institute.getId();
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
        List<Institute> institutes;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            institutes = instituteService.search(searchCriteria);
        } else {
            institutes = instituteService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, institutes);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Institute> institutes = instituteService.findAll();
        model.addAttribute(MODELS, institutes);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<Institute> institutes = instituteService.findAll(searchCriteria);

        model.addAttribute(MODELS, institutes);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Institute> institutes = instituteService.findAll();
        model.addAttribute(MODELS, institutes);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Institute institute = instituteService.findById(id);

        if (institute == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, institute);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            Institute deleted = instituteService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (InstituteNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
