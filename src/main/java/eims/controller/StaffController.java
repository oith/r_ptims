package eims.controller;

import eims.model.acad.Staff;
import eims.dto._SearchDTO;
import eims.exception.StaffNotFoundException;
import eims.service.StaffService;

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
@RequestMapping(value = "/staff")
 
public class StaffController extends _OithController {

    protected static final String MODEL = "staff";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private StaffService staffService;



    @RequestMapping(value = "/getCodableDTO", method = RequestMethod.GET)
    public @ResponseBody
    String getCodableDTO(@RequestParam(value="code") String code) {
        Staff codable = staffService.findByCode(code);
       
        if (codable != null) {
            return codable.getFullName();
        }
        return "Not Found";
    }

 

    private void commonPost(Staff currObject) {

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new Staff());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid Staff currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

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
                Staff staff = staffService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, staff.getCode());
                return "redirect:/" + SHOW + "/" + staff.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Staff staff = staffService.findById(id);
        
        if (staff == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, staff);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid Staff currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

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
                Staff staff = staffService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, staff.getCode());
                return "redirect:/" + SHOW + "/" + staff.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        Staff staff = staffService.findById(id);

        if (staff == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, staff);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid Staff currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

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
               Staff staff = staffService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, staff.getCode());
               return "redirect:/" + SHOW + "/" + staff.getId();
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
        List<Staff> staffs;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            staffs = staffService.search(searchCriteria);
        } else {
            staffs = staffService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, staffs);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Staff> staffs = staffService.findAll();
        model.addAttribute(MODELS, staffs);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<Staff> staffs = staffService.findAll(searchCriteria);

        model.addAttribute(MODELS, staffs);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Staff> staffs = staffService.findAll();
        model.addAttribute(MODELS, staffs);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Staff staff = staffService.findById(id);

        if (staff == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, staff);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            Staff deleted = staffService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (StaffNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
