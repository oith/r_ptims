package eims.controller;

import eims.model.acad.CourseFounded;
import eims.dto._SearchDTO;
import eims.exception.CourseFoundedNotFoundException;
import eims.service.CourseFoundedService;
import eims.service.CourseService;
import eims.model.acad.Course;
import eims.service.InstructorService;
import eims.service.RoomService;
import eims.model.acad.Room;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/courseFounded")
@SessionAttributes({"courses", "rooms"})
public class CourseFoundedController extends _OithController {

    protected static final String MODEL = "courseFounded";

    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private CourseFoundedService courseFoundedService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private RoomService roomService;

    @ModelAttribute("courses")
    public Iterable<Course> courses() {
        return courseService.findAll();
    }

    @ModelAttribute("rooms")
    public Iterable<Room> rooms() {
        return roomService.findAll();
    }

    private void commonPost(CourseFounded currObject) {
        try {
            currObject.setCourse(courseService.findById(currObject.getCourse().getId()));
        } catch (Exception exp) {
            currObject.setCourse(null);
        }
        currObject.setInstructor(instructorService.findByCode(currObject.getInstructor().getCode()));
        try {
            Set<Room> kk = new LinkedHashSet();
            for (Room object : currObject.getRooms()) {
                kk.add(roomService.findById(object.getId()));
            }
            currObject.setRooms(kk);
        } catch (Exception exp) {
            currObject.setRooms(null);
        }

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new CourseFounded());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid CourseFounded currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                CourseFounded courseFounded = courseFoundedService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, courseFounded.getId());
                return "redirect:/" + SHOW + "/" + courseFounded.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        CourseFounded courseFounded = courseFoundedService.findById(id);

        if (courseFounded == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, courseFounded);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid CourseFounded currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                CourseFounded courseFounded = courseFoundedService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, courseFounded.getId());
                return "redirect:/" + SHOW + "/" + courseFounded.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        CourseFounded courseFounded = courseFoundedService.findById(id);

        if (courseFounded == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, courseFounded);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid CourseFounded currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                CourseFounded courseFounded = courseFoundedService.copy(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, courseFounded.getId());
                return "redirect:/" + SHOW + "/" + courseFounded.getId();
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
        List<CourseFounded> courseFoundeds;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            courseFoundeds = courseFoundedService.search(searchCriteria);
        } else {
            courseFoundeds = courseFoundedService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, courseFoundeds);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
         */
        Iterable<CourseFounded> courseFoundeds = courseFoundedService.findAll();
        model.addAttribute(MODELS, courseFoundeds);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<CourseFounded> courseFoundeds = courseFoundedService.findAll(searchCriteria);

        model.addAttribute(MODELS, courseFoundeds);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
         */
        Iterable<CourseFounded> courseFoundeds = courseFoundedService.findAll();
        model.addAttribute(MODELS, courseFoundeds);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        CourseFounded courseFounded = courseFoundedService.findById(id);

        if (courseFounded == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, courseFounded);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {

        try {
            CourseFounded deleted = courseFoundedService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
        } catch (CourseFoundedNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
