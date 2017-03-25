package eims.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import eims.model.com.AdmParam;
import eims.model.com.AdmProcess;
import eims.model.com.AdmProcessDetail;
import eims.model.com.AdmReport;
import eims.model.com.AdmReportDetail;
import eims.model.com.AdmReportFormat;
import eims.model.com.AdmWidgetType;
import eims.model.com.AdmZoneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AdmUtil {

    private static final String SELECTED = "SELECTED";

    private static EntityManagerFactory emf;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void initStaticDao() {
        System.out.println("uuuuuuuuuuuuuuuyyy");
        emf = this.entityManagerFactory;
    }

    public AdmUtil() {
    }

    public static List<Map<String, String>> getListFromDB(final String query) {
        EntityManager em = emf.createEntityManager();
        List<Map<String, String>> ret = new ArrayList<>();
        try {

            List koto = em.createQuery(query).getResultList();
            if (!koto.isEmpty()) {
                if (koto.get(0) instanceof Object[]) {

                    for (Object object : koto) {
                        Object[] objects = (Object[]) object;
                        Map<String, String> mm = new LinkedHashMap();
                        mm.put("id", objects[0] + "");
                        mm.put("show", objects[1] + "");
                        ret.add(mm);
                    }

                } else {
                }
            }
            System.out.println("macsay: ok db menu drop: " + koto);
        } catch (Exception e) {
            System.out.println("macsay: err db menu drop: " + e);
        } finally {

            em.close();

        }
        return ret;
    }

    public static String getSingleValFromDB(final String coll) {

//        DBObject dopp = dbS.getCollection(coll).findOne(new BasicDBObject(key, val));
//
//        if (dopp != null) {
//            Object kkk = dopp.get(outCol);
//            return kkk.toString();
//        } else {
//            return null;
//        }
        return null;
    }

    private static List<Map<String, String>> cmdHandller(String cmd) {
        List<Map<String, String>> strdef = new ArrayList();
        if (cmd != null && !(cmd = cmd.trim()).isEmpty()) {

            if ((cmd.toLowerCase().startsWith("select"))) {
                strdef = getListFromDB(cmd);
            } else if ((cmd.startsWith("{") && cmd.endsWith("}"))) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    strdef = mapper.readValue(cmd, new TypeReference<List<Map<String, String>>>() {
                    });
                } catch (Exception e) {
                }
            } else if ((cmd.startsWith("[") && cmd.endsWith("]"))) {

                ObjectMapper mapper = new ObjectMapper();
                try {
                    strdef = mapper.readValue(cmd, new TypeReference<List<String>>() {
                    });
                } catch (Exception e) {
                }

            } else if ((cmd.contains(","))) {
                String[] lstFixed = cmd.split(",");

                for (String xxx : lstFixed) {
                    Map<String, String> dd = new LinkedHashMap();

                    if (xxx.contains("~")) {
                        String[] tld = xxx.split("~");

                        dd.put("id", tld[0]);
                        dd.put("show", tld[1]);

                    } else {
                        dd.put("id", xxx);
                        dd.put("show", xxx);
                    }
                    strdef.add(dd);
                }
            }
        }
        return strdef;
    }

    private static String defaultValueHandller(String defaultValue) {
        String strdef = "";
        if (defaultValue != null && !(defaultValue = defaultValue.trim()).isEmpty()) {

            if ((defaultValue.toLowerCase().startsWith("select"))) {
                strdef = getSingleValFromDB(defaultValue);
            } else {
                strdef = defaultValue;
            }
        }
        return strdef;
    }

    public static Map<String, String> getTableOnly(final AdmProcess admProcess, final Map<String, Object> objMap) { //, String currSprId, String empId, String deptId) {

        Map<String, String> tblMap = new HashMap();
        String tableHead = new String();
        String tableRow = new String();
        String queryCustomized = null;
        String queryAlias = null;

        List<String> invisColParm = new ArrayList();
        List<String> inputColParm = new ArrayList();
        List<String> colParma = new ArrayList();

        try {
            queryCustomized = admProcess.getQuery();
            queryAlias = admProcess.getQueryAlias();
            for (AdmProcessDetail resultSet : admProcess.getAdmProcessDetails()) {

                if (resultSet.getAdmZoneType() == AdmZoneType.PARAM_QU) {
                    continue;
                }
                String widgetIdentity = resultSet.getAdmParam().getParamName();
                AdmWidgetType widgetType = resultSet.getAdmParam().getAdmWidgetType();

                colParma.add(widgetIdentity);

                if (widgetType.equals(AdmWidgetType.HIDE)) {
                    invisColParm.add(widgetIdentity);
                } else if (widgetType.equals(AdmWidgetType.INPUT)) {
                    inputColParm.add(widgetIdentity);
                }
            }
        } catch (Exception e) {
            System.out.println("MacDynamo Err 0149:" + invisColParm);
        }

        for (String jkjk : objMap.keySet()) {

            Object vvv = objMap.get(jkjk);
            String orpn = "null";
            if (vvv != null) {

                if (vvv instanceof String) {
                    if (vvv.toString().trim().length() == 0) {
                        orpn = "null";
                    } else {
                        orpn = "'" + vvv + "'";
                    }
                } else if (vvv instanceof Date) {
                    // System.out.println("rrrrrrrrrrrrrrrr vvv"+((Date) vvv));
                    SimpleDateFormat klkl = new SimpleDateFormat("dd/MM/yyyy");
                    orpn = "TO_DATE('" + klkl.format((Date) vvv) + "','DDMMYYYY')";
                } else if (vvv instanceof Number) {
                    orpn = vvv + "";
                }
            }
            queryCustomized = queryCustomized.replaceAll(":" + jkjk, orpn);

        }
        //queryCustomized = queryCustomized.replaceAll(":P_CURR_USER_ID", userId.toString());

        tableHead += "<th class='center bold'>Select All<br><input type='checkbox' id='checkAll'/></th>";

        SortedSet<String> colAll = new TreeSet<>();
        Map<String, Integer> mapColIndx = new LinkedHashMap<>();

        if (queryAlias != null) {
            int cntt = 0;
            for (String colxd : queryAlias.split(",")) {
                colAll.add(colxd);
                mapColIndx.put(colxd, cntt);
                cntt++;
            }
        }

        for (String colx : colAll) {
            if (invisColParm.contains(colx)) {
                continue;
            }
            tableHead += "<th class='center bold'>" + colx + "</th>";
        }

        int j = 1;

        EntityManager em = emf.createEntityManager();
        Query jjkk = em.createQuery(queryCustomized);
        for (Object colxd : jjkk.getResultList()) {

            Object[] colx = (Object[]) colxd;

            tableRow
                    += "<tr>"
                    + "   <td class='center'>"
                    + "       <input type='checkbox' name='row." + j + "' class='checkBoxTouchAll' value='row." + j + "'/>"
                    + "   </td>";
            for (String colname : colAll) {

                int index = mapColIndx.get(colname);

                String isHedn = "";
                String hdnOrtxt = "text";
                if (invisColParm.contains(colname)) {
                    isHedn = "style='display:none'";
                    hdnOrtxt = "hidden";
                }

                String ident = "";
                String valx = null;
                if (colParma.contains(colname)) {
                    ident = "id='" + colname + j + "'";
                    valx = " value='" + colx[index] + "'";
                }

                if (inputColParm.contains(colname)) {
                    tableRow += "<td " + isHedn + ">" + "<input " + ident + valx + " type='" + hdnOrtxt + "'></input></td>";
                } else if (valx != null) {
                    tableRow += "<td style=' display:none'>" + "<input " + ident + valx + " type='hidden'></input></td>";
                } else if (colname.toUpperCase().equals("URL")) {
                    String dd = "/" + colx[index];
                    String urlLink = "<a href='${request.contextPath}" + dd + "' target='_blank'>" + "Show Details" + "</a>";
                    tableRow += "<td " + isHedn + " " + ident + " >" + urlLink + "</td>";
                } else {
                    Object tdValue = colx[index];
                    if (tdValue == null) {
                        tdValue = "";
                    }
                    tableRow += "<td " + isHedn + " " + ident + " >" + tdValue + "</td>";
                }
            }
            tableRow += "</tr>";

            j++;
        }
        em.close();
        String dataTable = "<table id='procTable' class='dataListTable table-bordered table-striped table-hover' style='width:50%'> " + tableHead + tableRow + "</table>";

        tblMap.put("TOTAL_RECORD", Integer.toString(j - 1));
        tblMap.put("DATA_TABLE", dataTable);

        return tblMap;
    }

    public Map<String, String> getReportPageMap(final BigInteger reportId, final AdmReportService admReportService) {

        Map<String, String> objMap = new LinkedHashMap();

        AdmReport admReport = null;
        try {
            admReport = admReportService.findById(reportId);
        } catch (Exception e) {
            System.out.println("err AdmReport 849: " + e);
        }

        if (admReport == null) {
            return objMap;
        }

        for (AdmReportDetail admProcDtl : admReport.getAdmReportDetails()) {

            String defaultValue = admProcDtl.getAdmParam().getDefaultVal();
            String widgetIdentity = admProcDtl.getAdmParam().getParamName();

            objMap.put(widgetIdentity, defaultValueHandller(defaultValue));
        }

        decoReportPageMap(admReport, objMap);

//        StringBuilder sb = new StringBuilder();
//        for (AdmReportFormat bbb : admReport.getSupportFormats()) {
//            sb.append("<option value='").append(bbb).append("'>").append(bbb).append("</option>");
//        }
//        objMap.put("reportFormat", sb.toString());
        objMap.put("reportName", admReport.getFileName());

        return objMap;
    }

    private void decoReportPageMap(final AdmReport statement, final Map<String, String> mapper) {

        String fixedParam = "";

        for (AdmReportDetail resultSet : statement.getAdmReportDetails()) {

            Boolean isMandatory = resultSet.getAdmParam().getIsMandatory();
            String widgetLabel = resultSet.getAdmParam().getTitle();
            String helpText = resultSet.getAdmParam().getHelpText();
            String defaultValue = resultSet.getAdmParam().getDefaultVal();
            AdmWidgetType widgetType = resultSet.getAdmParam().getAdmWidgetType();
            String paramCmd = resultSet.getAdmParam().getCmd();
            String widgetIdentity = resultSet.getAdmParam().getParamName();
            String reqIndication = "";
            String req = "";
            String reqlab = "";

            if (widgetType == AdmWidgetType.UUID
                    || widgetType == AdmWidgetType.HIDE
                    || widgetType == AdmWidgetType.INPUT) {
                continue;
            }

            String strdef = defaultValueHandller(defaultValue);

            if (isMandatory != null) {
                if (isMandatory) {
                    reqIndication = "*";
                    req = " required='required' ";
                }
            }

            if (widgetLabel != null) {
                reqlab = widgetLabel;
            }

            if (helpText == null) {
                helpText = reqlab;
            }

            String rrrrrr;
            if (reqIndication.equals("*")) {
                rrrrrr = "<span class='required-indicator'> " + reqlab + reqIndication + "</span>";
            } else {
                rrrrrr = reqlab;
            }

            fixedParam += "<div class='col-xs-12 col-sm-4 col-md-4 col-lg-4'><div class='form-group'>" + "<label for='" + reqlab + "'>" + rrrrrr + "</label>";

            if (widgetType == AdmWidgetType.PASSWORD) {
                fixedParam += "<input class='form-control' type='" + widgetType + "' name='" + widgetIdentity + "' id='" + widgetIdentity + "' value='" + strdef + "' " + req + "/>";
            } else if (widgetType == AdmWidgetType.TEXT) {
                fixedParam += "<input class='form-control' type='" + widgetType + "' name='" + widgetIdentity + "' id='" + widgetIdentity + "' value='" + strdef + "' " + req + "/>";
            } else if (widgetType == AdmWidgetType.DATE) {
                fixedParam += "<input class='form-control dtp-date' ";
                fixedParam += " name='";
                fixedParam += widgetIdentity;
                fixedParam += "' id='";
                fixedParam += widgetIdentity;
                fixedParam += "' value='";
                fixedParam += strdef;
                fixedParam += "'/>";
            } else if (paramCmd != null) {

                if (widgetType == AdmWidgetType.LIST) {

                    List<Map<String, String>> optionList = cmdHandller(paramCmd);

                    String optSb = new String();
                    optSb += "<option value='${null}'>Select One</option>";
                    if (optionList != null) {
                        for (Map<String, String> p : optionList) {
                            String idx = p.get("id");
                            String showx = p.get("show");

                            if (strdef.equals(idx)) {
                                optSb += "<option " + SELECTED + " value='" + idx + "'>" + showx + "</option>";
                            } else {
                                optSb += "<option value='" + idx + "'>" + showx + "</option>";
                            }
                        }
                    }

                    fixedParam += "<Select class='form-control' name='" + widgetIdentity + "' id='" + widgetIdentity + "' " + req + ">" + optSb + "</select>";
                }
            }
            fixedParam += "</div></div>";
        }

        mapper.put("fixedParam", fixedParam);
    }

    public static Map<String, String> getProcPageMap(final AdmProcess admProcess) {

        Map<String, String> objMap = new HashMap();

        for (AdmProcessDetail admProcDtl : admProcess.getAdmProcessDetails()) {

            if (admProcDtl.getAdmZoneType() != AdmZoneType.SEARCH || !admProcDtl.getAdmParam().getIsActive()) {
                continue;
            }
            String defaultValue = admProcDtl.getAdmParam().getDefaultVal();
            String widgetIdentity = admProcDtl.getAdmParam().getParamName();

            objMap.put(widgetIdentity, defaultValueHandller(defaultValue));
        }

        decoProcPageMap(admProcess, objMap);

        return objMap;
    }

    public static void decoProcPageMap(final AdmProcess admProcess, final Map<String, String> mapper) {

        Map<String, String> rootMap = new HashMap();
        String searchContent = "";
        String fixedParam = "";
        String processButton = "";
        String queryParam = "";

        ObjectMapper mapperx = new ObjectMapper();

        try {
            Map<String, Object> procBtn = mapperx.readValue(admProcess.getProcessBtns(), new TypeReference<Map<String, Object>>() {
            });

            for (Object object : procBtn.keySet()) {
                processButton += "<button type='button' class='btn btn-primary' name='" + object + "' id='" + object + "' " + " onclick='executeProcess(this.id)'> " + procBtn.get(object) + "</button>";
                //processButton += "<button type='button' class='btn btn-warning' name='" + paramName + "' id='" + paramName + "' title='" + helpText + "' " + disable + " onclick='executeProcess(this.id)'> " + paramTitle + "</button>";
            }

        } catch (Exception e) {
            System.out.println("jjrye64356hjfhs6:" + e);
        }

        for (AdmProcessDetail admProcessDetail : admProcess.getAdmProcessDetails()) {
            AdmParam admParam = admProcessDetail.getAdmParam();
            AdmZoneType admZoneType = admProcessDetail.getAdmZoneType();
            Boolean isMandatory = admParam.getIsMandatory();

            String paramTitle = admParam.getTitle();
            String helpText = admParam.getHelpText();
            String defaultValue = admParam.getDefaultVal();
            AdmWidgetType admWidgetType = admParam.getAdmWidgetType();
            String paramCmd = admParam.getCmd();
            String paramName = admParam.getParamName();
            String reqIndication = "";
            String req = "";
            String reqlab = "";
            String strdef = defaultValueHandller(defaultValue);

            try {
                if (admZoneType == AdmZoneType.PARAM_QU) {
                    queryParam += paramName + ",";
                }
            } catch (Exception ec) {
            }

            if (isMandatory != null) {
                if (isMandatory) {
                    reqIndication = "*";
                    req = " required='required' ";
                }
            }

            if (paramTitle != null) {
                reqlab = paramTitle;
            }

            if (helpText == null) {
                helpText = reqlab;
            }

            String rrrrrr;
            if (reqIndication.equals("*")) {
                rrrrrr = "<span class='required-indicator'> " + reqlab + reqIndication + "</span>";
            } else {
                rrrrrr = reqlab;
            }

            if (admZoneType == AdmZoneType.SEARCH && admWidgetType != null) {
                rootMap.put(paramName, admWidgetType.name());
                searchContent += "<div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'><div class='form-group'>" + "<label for='" + reqlab + "'>" + "<span'>" + reqlab + reqIndication + "</span></label>";
            } else if (admZoneType == AdmZoneType.PARAM_FIXED && admWidgetType != AdmWidgetType.UUID) {// && !(widgetType.equals(AdmWidgetType.QU_PARAM_INVISIBLE.toString()) || widgetType.equals(AdmWidgetType.QU_PARAM_VISIBLE.toString()))) {
                fixedParam += "<div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'><div class='form-group'>" + "<label for='" + reqlab + "'>" + rrrrrr + "</label>";
            }

            if (admWidgetType == AdmWidgetType.PASSWORD) {
                if (admZoneType == AdmZoneType.SEARCH) {
                    searchContent += "<input class='form-control' type='" + admWidgetType + "' name='" + paramName + "' id='" + paramName + "' value='" + strdef + "' " + req + "/>";
                } else if (admZoneType == AdmZoneType.PARAM_FIXED || admZoneType == AdmZoneType.PARAM_QU) {
                    fixedParam += "<input class='form-control' type='" + admWidgetType + "' name='" + paramName + "' id='" + paramName + "' value='" + strdef + "' " + req + "/>";
                }
            } else if (admWidgetType == AdmWidgetType.TEXT) {
                if (admZoneType == AdmZoneType.SEARCH) {
                    searchContent += "<input class='form-control' type='" + admWidgetType + "' name='" + paramName + "' id='" + paramName + "' value='" + strdef + "' " + req + "/>";
                } else if (admZoneType == AdmZoneType.PARAM_FIXED || admZoneType == AdmZoneType.PARAM_QU) {
                    fixedParam += "<input class='form-control' type='" + admWidgetType + "' name='" + paramName + "' id='" + paramName + "' value='" + strdef + "' " + req + "/>";
                }
            } else if (admWidgetType == AdmWidgetType.DATE) {
                if (admZoneType == AdmZoneType.SEARCH) {
                    searchContent += "<input class='form-control dtp-date' ";
                    searchContent += "name='";
                    searchContent += paramName;
                    searchContent += "' id='";
                    searchContent += paramName;
                    searchContent += "' value='";
                    searchContent += strdef;
                    searchContent += "'/>";
                } else if (admZoneType == AdmZoneType.PARAM_FIXED || admZoneType == AdmZoneType.PARAM_QU) {
                    fixedParam += "<input class='form-control dtp-date' ";
                    fixedParam += " name='";
                    fixedParam += paramName;
                    fixedParam += "' id='";
                    fixedParam += paramName;
                    fixedParam += "' value='";
                    fixedParam += strdef;
                    fixedParam += "'/>";
                }
            } else if (paramCmd != null) {
                if (admWidgetType == AdmWidgetType.LIST || admWidgetType == AdmWidgetType.LIST_MULTI_SELECT) {
                    List<Map<String, String>> optionList = cmdHandller(paramCmd);

                    System.out.println("optionList:" + optionList + "kkkkkkkkk:" + strdef);
                    String optSb = new String();
                    optSb += "<option value=''>Select</option>";

                    List mayy = Arrays.asList(strdef.split(","));

                    if (optionList != null) {
                        for (Map p : optionList) {
                            Object idx = p.get("id");
                            Object showx = p.get("show");

                            if (mayy.contains(idx)) {
                                optSb += "<option " + SELECTED + " value='" + idx + "'>" + showx + "</option>";
                            } else {
                                optSb += "<option value='" + idx + "'>" + showx + "</option>";
                            }
                        }
                    }

                    String hhhh = "";
                    if (admWidgetType == AdmWidgetType.LIST_MULTI_SELECT) {
                        hhhh = " multiple='multiple' ";
                    }

                    if (admZoneType.equals(AdmZoneType.SEARCH)) {
                        searchContent += "<select class='form-control' name='" + paramName + "' id='" + paramName + "' " + req + hhhh + ">" + optSb + "</select>";
                    } else if (admZoneType.equals(AdmZoneType.PARAM_FIXED) || admZoneType.equals(AdmZoneType.PARAM_QU)) {
                        fixedParam += "<select class='form-control' name='" + paramName + "' id='" + paramName + "' " + req + hhhh + ">" + optSb + "</select>";
                    }
                }
            }

            if (admZoneType == AdmZoneType.SEARCH) {
                searchContent += "</div></div>";
            } else if (!fixedParam.isEmpty() && (admZoneType.equals(AdmZoneType.PARAM_FIXED) || admZoneType.equals(AdmZoneType.PARAM_QU))) {
                fixedParam += "</div></div>";
            }
        }

        if (!searchContent.isEmpty()) {
            searchContent = "<label for='searchParameter'>\n"
                    + "<span><h4><spring:message code='searchParameter' text='Search Parameter'/></h4></span>\n"
                    + "</label>\n"
                    + searchContent;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String searcherIdsxSTR = "";
        try {
            searcherIdsxSTR = objectMapper.writeValueAsString(rootMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapper.put("searcherIds", searcherIdsxSTR);
        mapper.put("searchContent", searchContent);
        mapper.put("fixedParam", fixedParam);
        mapper.put("processButton", processButton);

        try {
            queryParam = queryParam.substring(0, queryParam.length() - 1);
            mapper.put("qparams", queryParam);
        } catch (Exception e) {
        }
    }

    public static String getInitCap(final String dfg) {
        String sss = "";
        String strUpper = dfg.toUpperCase();
        if (strUpper.contains("DOB") || strUpper.contains("DOJ")) {
            return strUpper;
        }

        if (dfg.contains("_")) {
            String hhhh[] = dfg.split("_");

            for (String jjj : hhhh) {
                jjj = jjj.trim();
                sss += getInitCapWhile(jjj) + " ";
            }
        } else {
            return getInitCapWhile(dfg);
        }

        sss = sss.trim();
        return sss;
    }

    public static String getInitCapWhile(final String dfg) {
        if (dfg.length() >= 2) {
            return Character.toUpperCase(dfg.charAt(0)) + dfg.substring(1).toLowerCase();
        }
        return dfg;
    }
}
