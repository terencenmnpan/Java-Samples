package gov.ca.dir.acct.calosha.adf.master.viewcontroller;

import java.text.SimpleDateFormat;

import oracle.adf.view.rich.event.DialogEvent;

import gov.ca.dir.acct.calosha.adf.common.code.utils.OracleReportBean;
import gov.ca.dir.acct.calosha.adf.common.code.utils.PropertiesLoader;

import java.util.Properties;

import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlListBinding;
//import java.util.Map;



public class ReportParameterBean {
    private OracleReportBean reportBean = null;
    
    public ReportParameterBean() {
        PropertiesLoader _props = new PropertiesLoader();
        Properties _properties = _props.getProperties();
        String _reportServer = _properties.getProperty("gov.ca.dir.acct.calosha.adf.common.code.ReportServer");
        reportBean = new OracleReportBean(_reportServer,"8888","/reports/rwservlet");
        reportBean.setReportServerParam(reportBean.RS_PARAM_DESFORMAT, "pdf");
        reportBean.setReportServerParam(reportBean.RS_PARAM_DESTYPE, "cache");
        //reportBean.setReportServerParam(reportBean.RS_PARAM_USERID, "CALOSHA/CALOSHA@CALODEV");
        reportBean.setReportNamedParam("caloshaparam");
    }


    
    public void okActionListener02(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "calosha02_report.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate2").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate2").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    
    public void okActionListener03(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "calosha051_report.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate3").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate3").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    
    public void okActionListener04(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "calosha052_report.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate4").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate4").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    
    public void okActionListener05(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "paid_in_full.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate5").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate5").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    
    public void okActionListener06(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "calosha01a_report.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate1").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate1").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    
    public void okActionListener07(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "calosha071_report.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate7").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate7").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    public void okActionListener08(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "calosha072_report.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate8").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate8").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    public void okActionListener09(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "calosha091_report.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate9").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate9").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    public void okActionListener10(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "calosha092_report.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate10").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate10").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    public void okActionListener11(DialogEvent dialogEvent) {
        // Add event code here...
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();;
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("CollectionStaffName"); 
        list.getDisplayData();
        Row selectedValue = (Row) list.getValueFromList(Integer.parseInt(dialogEvent.getComponent().findComponent("soc1").getAttributes().get("value").toString()));
        String accountingCollectionStaff = selectedValue.getAttribute("CollectionStaffName").toString();
        
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "coe_followup.rdf");
        reportBean.setReportParameter("P_ANALYST", accountingCollectionStaff);
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    public void okActionListener12(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "edd_referred_list.rdf");
        reportBean.setReportParameter("P_StartDate", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate11").getAttributes().get("value")));
        reportBean.setReportParameter("P_EndDate", dateFormatter.format(dialogEvent.getComponent().findComponent("edate11").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    public void okActionListener13(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "edd_referred_summary.rdf");
        reportBean.setReportParameter("P_StartDate", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate12").getAttributes().get("value")));
        reportBean.setReportParameter("P_EndDate", dateFormatter.format(dialogEvent.getComponent().findComponent("edate12").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
   
    public void okActionListener14(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "Judgment_Filing_Report.rdf");
        reportBean.setReportParameter("P_START", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate13").getAttributes().get("value")));
        reportBean.setReportParameter("P_END", dateFormatter.format(dialogEvent.getComponent().findComponent("edate13").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
        
    public void okActionListener15(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "collection_staff_statistics.rdf");
        reportBean.setReportParameter("P_START_DATE", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate14").getAttributes().get("value")));
        reportBean.setReportParameter("P_END_DATE", dateFormatter.format(dialogEvent.getComponent().findComponent("edate14").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }


        
    public void okActionListener16(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "collection_report_new.rdf");
        reportBean.setReportParameter("p_start_date", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate15").getAttributes().get("value")));
        reportBean.setReportParameter("p_end_date", dateFormatter.format(dialogEvent.getComponent().findComponent("edate15").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }

    
    public void okActionListener17(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "Judgment_Satisfied_Report.rdf");
        reportBean.setReportParameter("P_START", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate16").getAttributes().get("value")));
        reportBean.setReportParameter("P_END", dateFormatter.format(dialogEvent.getComponent().findComponent("edate16").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
   
    public void okActionListener18(DialogEvent dialogEvent) {
        // Add event code here...

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "Judgment_Vacate_Report.rdf");
        reportBean.setReportParameter("P_START", dateFormatter.format(dialogEvent.getComponent().findComponent("bdate17").getAttributes().get("value")));
        reportBean.setReportParameter("P_END", dateFormatter.format(dialogEvent.getComponent().findComponent("edate17").getAttributes().get("value")));
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
    public void okActionListener19(DialogEvent dialogEvent) {
        // Add event code here...
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();;
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("CollectionStaffName"); 
        list.getDisplayData();
        Row selectedValue = (Row) list.getValueFromList(Integer.parseInt(dialogEvent.getComponent().findComponent("soc2").getAttributes().get("value").toString()));
        String accountingCollectionStaff = selectedValue.getAttribute("CollectionStaffName").toString();
        
        reportBean.setReportServerParam(reportBean.RS_PARAM_REPORT, "coe_followup2.rdf");
        reportBean.setReportParameter("P_ANALYST", accountingCollectionStaff);
        reportBean.openUrlInNewWindow(reportBean.getReportServerURL());
        System.out.println(reportBean.getReportServerURL());
    }
}
