package eims.controller;

import eims.model.com.AdmProcess;
import eims.model.security.AuthUser;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import eims.service.ProcServiceDef;
import java.util.Enumeration;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class _OithHomeController {

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private ProcServiceDef procServiceDef;

    private final SortedSet<String> list = new TreeSet<>();

    @RequestMapping(value = {"/refresh"}, method = RequestMethod.GET)
    public String refresh(ModelMap model, HttpServletRequest httpServletRequest) {
        HttpSession uuu = httpServletRequest.getSession();

        String omi[] = {"authRoles",
            "javax.servlet.jsp.jstl.fmt.request.charset",
            "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN",
            "SPRING_SECURITY_CONTEXT"
        };
        for (Enumeration<String> gg = uuu.getAttributeNames(); gg.hasMoreElements();) {
            String next = gg.nextElement();
            System.out.println("Attribute: " + next + " val: " + uuu.getAttribute(next));

            if (!(next.equals(omi[0])
                    || next.equals(omi[1])
                    || next.equals(omi[2])
                    || next.equals(omi[3]))) {
                uuu.removeAttribute(next);
            }
        }

        return "layouts/homeSecure";
    }

    @RequestMapping(value = "/makeSchedule/{code}", method = RequestMethod.GET)
    public String makeSchedule(@PathVariable("code") String code, ModelMap model) {
        procServiceDef.makeSchedule(code);
        return "index";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "index";
    }

    @RequestMapping(value = {"/homeSecure"}, method = RequestMethod.GET)
    public String homeSecure(ModelMap model) {
        model.addAttribute("controllerList", list);
        //model.addAttribute("loggedinuser", getPrincipal());
        return "layouts/homeSecure";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "layouts/login";
        } else {
            return "redirect:/index";
        }
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
//            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
//        return "redirect:/index?logout";
        return "redirect:/login?logout";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof AuthUser) {
            userName = ((AuthUser) principal).getFullName();
        }

        return userName;
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "layouts/accessDenied";
    }

    @PostConstruct
    public void init() {

        procServiceDef.dummyUserData();

        Map<RequestMappingInfo, HandlerMethod> handlerMethods
                = this.requestMappingHandlerMapping.getHandlerMethods();

        // index(null, null);
        for (Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            RequestMappingInfo mapping = item.getKey();
            HandlerMethod method = item.getValue();

            for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
                //System.out.println(method.getBeanType().getName() + "#" + method.getMethod().getName()+ " <-- " + urlPattern);

                if (urlPattern.endsWith("index")
                        || urlPattern.endsWith("excelFileUpload")
                        || urlPattern.endsWith("reportCenter")
                        || urlPattern.endsWith("processCenter")
                        || urlPattern.endsWith("queryCenter")) {
                    System.out.println(method.getBeanType().getName() + "#" + method.getMethod().getName() + " <-- " + urlPattern);

                    list.add(urlPattern);
                }
                //if (urlPattern.equals("some specific url")) {
                //add to list of matching METHODS
                //}
            }
        }
        procServiceDef.fastMenuGen(list);
    }
}
