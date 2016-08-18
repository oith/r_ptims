package eims.controller;

import eims.model.acad.ProcOutCourseSchedule;
import eims.dto._SearchDTO;
import eims.exception.ProcOutCourseScheduleNotFoundException;
import eims.service.ProcOutCourseScheduleService;
import eims.service.CourseFoundedService;
import eims.model.acad.CourseFounded;
import eims.service.RoomService;
import eims.model.acad.Room;

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
@RequestMapping(value = "/procOutCourseSchedule")
@SessionAttributes({"courseFoundeds","rooms"}) 
public class ProcOutCourseScheduleController extends _OithController {

    protected static final String MODEL = "procOutCourseSchedule";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private ProcOutCourseScheduleService procOutCourseScheduleService;

    @Autowired
    private CourseFoundedService courseFoundedService;
    @Autowired
    private RoomService roomService;




    @ModelAttribute("courseFoundeds")
    public Iterable<CourseFounded> courseFoundeds() {
        return courseFoundedService.findAll();
    }
    @ModelAttribute("rooms")
    public Iterable<Room> rooms() {
        return roomService.findAll();
    }
 

    private void commonPost(ProcOutCourseSchedule currObject) {
            try {
                currObject.setCourseFounded(courseFoundedService.findById(currObject.getCourseFounded().getId()));
            } catch (Exception exp) {
                currObject.setCourseFounded(null);
            }
            try {
                currObject.setRoom(roomService.findById(currObject.getRoom().getId()));
            } catch (Exception exp) {
                currObject.setRoom(null);
            }

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new ProcOutCourseSchedule());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid ProcOutCourseSchedule currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                ProcOutCourseSchedule procOutCourseSchedule = procOutCourseScheduleService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, procOutCourseSchedule.getId());
                return "redirect:/" + SHOW + "/" + procOutCourseSchedule.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        ProcOutCourseSchedule procOutCourseSchedule = procOutCourseScheduleService.findById(id);
        
        if (procOutCourseSchedule == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, procOutCourseSchedule);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid ProcOutCourseSchedule currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                ProcOutCourseSchedule procOutCourseSchedule = procOutCourseScheduleService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, procOutCourseSchedule.getId());
                return "redirect:/" + SHOW + "/" + procOutCourseSchedule.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ProcOutCourseSchedule procOutCourseSchedule = procOutCourseScheduleService.findById(id);

        if (procOutCourseSchedule == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, procOutCourseSchedule);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid ProcOutCourseSchedule currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               ProcOutCourseSchedule procOutCourseSchedule = procOutCourseScheduleService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, procOutCourseSchedule.getId());
               return "redirect:/" + SHOW + "/" + procOutCourseSchedule.getId();
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
        List<ProcOutCourseSchedule> procOutCourseSchedules;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            procOutCourseSchedules = procOutCourseScheduleService.search(searchCriteria);
        } else {
            procOutCourseSchedules = procOutCourseScheduleService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, procOutCourseSchedules);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<ProcOutCourseSchedule> procOutCourseSchedules = procOutCourseScheduleService.findAll();
        model.addAttribute(MODELS, procOutCourseSchedules);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<ProcOutCourseSchedule> procOutCourseSchedules = procOutCourseScheduleService.findAll(searchCriteria);

        model.addAttribute(MODELS, procOutCourseSchedules);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<ProcOutCourseSchedule> procOutCourseSchedules = procOutCourseScheduleService.findAll();
        model.addAttribute(MODELS, procOutCourseSchedules);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        ProcOutCourseSchedule procOutCourseSchedule = procOutCourseScheduleService.findById(id);

        if (procOutCourseSchedule == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, procOutCourseSchedule);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            ProcOutCourseSchedule deleted = procOutCourseScheduleService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
        } catch (ProcOutCourseScheduleNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
