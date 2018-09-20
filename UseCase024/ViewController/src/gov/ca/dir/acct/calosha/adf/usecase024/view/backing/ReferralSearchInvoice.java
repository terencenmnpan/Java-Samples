package gov.ca.dir.acct.calosha.adf.usecase024.view.backing;

import java.sql.SQLException;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.model.AttributeBinding;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.QueryOperationEvent;
import oracle.adf.view.rich.model.FilterableQueryDescriptor;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import oracle.adf.model.DataControl;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.DialogEvent.Outcome;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.server.ApplicationModuleImpl;

public class ReferralSearchInvoice {

    private RichTable resId1;
    private RichPopup cancelReferralPopup;

    private final String REFERRAL_SEARCH_APP_MODULE =
        "ReferralSearchInvoiceAMDataControl";
    
    private final String VALIDATE_RECOMMEND_WRITE_OFF = 
        "SELECT COUNT(*) RECOMMEND_WO_COUNT FROM RECOMMENDED_WRITE_OFF " +
        "WHERE INSPECTION_ID = ? ;" ;
    
    public void setResId1(RichTable resId1) {
        this.resId1 = resId1;
    }

    public RichTable getResId1() {
        return resId1;
    }    

    //Helper Method to get AppModule
    private ApplicationModuleImpl getReferralAppModule() {
        BindingContext bindings = BindingContext.getCurrent();

        DataControl referralSearchDC =
            bindings.findDataControl(REFERRAL_SEARCH_APP_MODULE);

        ApplicationModuleImpl referralSearchAM =
            (ApplicationModuleImpl)referralSearchDC.getDataProvider();

        return referralSearchAM;
    }
    //Helper Method to get DC Iterator Binding, Iterator needs to exist in page binding
    private DCIteratorBinding getDcBindings(String iteratorName) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIteratorBinding =
            bindings.findIteratorBinding(iteratorName);
        return dcIteratorBinding;
    }
    
    /*Vinay Patil: Custom method to reset the table filter criteria*/
    public void resetTableFilter() {
        System.out.println("Clear the table filter criteria values");
    
        // Cast the Table Filter Model to query descriptor
        FilterableQueryDescriptor qd;
        qd = (FilterableQueryDescriptor)getResId1().getFilterModel();

        // check if query descriptor and filter critera exists
        if (qd != null && qd.getFilterCriteria() != null){
            qd.getFilterCriteria().clear();   
        }
    }

    /* Vinay Patil: When the user presses the RESET button the query result is flushed form the screen  */
    public void queryOperationListener(QueryOperationEvent queryOperationEvent) {

        /* SearchInvoice.Jsff , af:query , Property - Behavior - queryOperationListener value*/
        String expr         = "#{bindings.SearchInvoiceCriteriaQuery.processQueryOperation}";
        String rsMethod     = "doQueryResultReset";
        String actionBbtn   = "RESET"; 
        
        resetTableFilter();
        
        System.out.println("Reset query results using doQueryResultReset method ");

        invokeMethodExpression(expr,
                               Object.class, 
                               QueryOperationEvent.class,
                               queryOperationEvent);

        System.out.println("Original EL about Query operation listener " +expr);
        
        if (queryOperationEvent.getOperation().name().toUpperCase().equals(actionBbtn)) {
            BindingContainer bindings = getBindings();
            
            System.out.println(actionBbtn+" button pressed to invoke method "+rsMethod);
            
            OperationBinding operationBinding =
                bindings.getOperationBinding(rsMethod);
            
            operationBinding.execute();

            System.out.println("Refresh the ADF page"+getResId1());
            
            // Refresh the Table component
            AdfFacesContext.getCurrentInstance().addPartialTarget(resId1);
        }
    }

    public Object invokeMethodExpression(String expr, Class returnType,
                                          Class[] argTypes, Object[] args) {
         FacesContext fc = FacesContext.getCurrentInstance();
         ELContext elctx = fc.getELContext();
         ExpressionFactory elFactory =
             fc.getApplication().getExpressionFactory();
         MethodExpression methodExpr =
             elFactory.createMethodExpression(elctx, expr, returnType,
                                              argTypes);
         System.out.println("Set Faces, EL and Method Expression invokeMethodExpression ");
         return methodExpr.invoke(elctx, args);
     }

    /* The invokeMethodExpression has to be instantiated*/
    public Object invokeMethodExpression(String expr, Class returnType,
                                         Class argType, Object argument) {
        System.out.println("Instantiate invokeMethodExpression ");

        return invokeMethodExpression(expr, returnType,
                                      new Class[] { argType },
                                      new Object[] { argument });
    }
    
    //Helper Method to get page def bindings
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public boolean activateEddFunction() {
        boolean activateButton = false;
        
        
        
        return activateButton;
    }

    public void setCancelReferralPopup(RichPopup cancelReferralPopup) {
        this.cancelReferralPopup = cancelReferralPopup;
    }

    public RichPopup getCancelReferralPopup() {
        return cancelReferralPopup;
    }
    
    /**
     * Returns whether an active EDD referral exists Method uses a prepared 
     * statement to see if a nonerrored current valid N or R Record 
     * already exists in the TPC Table. 
     * 
     * @param   debtorNumber    invoice number of the debt
     * @return                  whether EDD Referral is active
     */
    
    public boolean validEddRecord(String debtorNumber) {
        boolean isValidEdd = false;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            BindingContext bindings = BindingContext.getCurrent();

            DataControl referralSearchDC =
                bindings.findDataControl("ReferralSearchInvoiceAMDataControl");

            ApplicationModuleImpl referralSearchAM =
                (ApplicationModuleImpl)referralSearchDC.getDataProvider();
            
            prepStmt =
                referralSearchAM.getDBTransaction().createPreparedStatement(
                    "SELECT FUNC_IS_ACTIVE_TPC_REF( ? ) EDD_RECORD_COUNT FROM DUAL",
                                                                                  0);
            prepStmt.setString(1, debtorNumber);
            prepStmt.execute();
            rs = prepStmt.getResultSet();
            rs.next();
            int rRecordCount = rs.getInt("EDD_RECORD_COUNT");
            rs.close();
            prepStmt.close();
            if (rRecordCount > 0) {
                isValidEdd = true;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.toString());
        } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException logOrIgnore) {
                        System.out.println(logOrIgnore.toString());
                    }
                }
                if (prepStmt != null) {
                    try {
                        prepStmt.close();
                    } catch (SQLException logOrIgnore) {
                        System.out.println(logOrIgnore.toString());
                    }
                }
            }
        ;
        return isValidEdd;
    }
    
    //ActionListener method used to cancel a referral and check for validity
//TODO
    public void cancelReferral(ActionEvent actionEvent){
        
        boolean                     validEdd                = false;
        boolean                     isNotValidCancellation  = false;
        ArrayList<String>           errorMessages           = new ArrayList<String>();
        oracle.jbo.domain.Number    inspectionId;
        String                      inspectionNumber        = "";
        boolean                     appealDateExists        = false;
        boolean                     closedDateExists        = false;
        boolean                     onePLetterExists        = false;
        boolean                     collLetterExists        = false;
        oracle.jbo.domain.Number    invoiceBalance          = new oracle.jbo.domain.Number(0);
            
        //Get page bindings to subsequently get InspectionId and Inspection Number
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        //Get binding attribute
        AttributeBinding inspectionIdAttr =
            (AttributeBinding)bindings.getControlBinding("InspectionId");
        
        //Get inspection Id
        if (inspectionIdAttr.getInputValue() != null) {
            try{
            inspectionId =
                    new oracle.jbo.domain.Number(inspectionIdAttr.getInputValue().toString());
            } catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
        
        //Get binding attribute
        AttributeBinding inspectionNumAttr =
            (AttributeBinding)bindings.getControlBinding("InspectionNumber");
        
        //Get inspection Number
        if (inspectionNumAttr.getInputValue() != null) {
            inspectionNumber =
                    inspectionNumAttr.getInputValue().toString();
        }
        
        //check for active edd referrals
        validEdd = validEddRecord(inspectionNumber);
        
        if(validEdd == true){
            errorMessages.add("EDD Referral is currently Active.");
            isNotValidCancellation = true;
        }
        
        //check for valid judgments
        DCIteratorBinding validJudgmentViewIter = getDcBindings("ValidJudgmentViewIterator");
        long validJudgmentsLong = validJudgmentViewIter.getEstimatedRowCount();
        
        if(validJudgmentsLong > 0 ){
            errorMessages.add("Judgment is currently Active.");
            isNotValidCancellation = true;
        }
        
        //Invoicebalance
        DCIteratorBinding delinquentPayPlanViewIter = getDcBindings("DelinquentPayPlanViewIterator");
        long delinquentPayPlansLong = 0;
        
        delinquentPayPlansLong = delinquentPayPlanViewIter.getEstimatedRowCount();
        
        
        //Get invoice balance
        AttributeBinding invoiceBalanceAttr =
            (AttributeBinding)bindings.getControlBinding("Invoicebalance");
        
        String invoiceBalanceString = 
            invoiceBalanceAttr.getInputValue().toString();
        
        String invoiceBalanceStringFormatted = 
            invoiceBalanceString.replace("$", "").replace(",", "").replace("(", "").replace(")", "");

        DCIteratorBinding validPayPlanViewIter = 
            getDcBindings("ValidPaymentPlanViewIterator");
        
        long validPayPlansLong = 
            validPayPlanViewIter.getViewObject().getEstimatedRowCount();

        try{ 
            invoiceBalance = new oracle.jbo.domain.Number(invoiceBalanceStringFormatted);
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        //Get binding AppealDate
        AttributeBinding appealDateAttr =
            (AttributeBinding)bindings.getControlBinding("AppealDate");
        
        //Get appeal date 
        if (appealDateAttr.getInputValue() != null) {
            appealDateExists = true;
        }
        
        //Get 1P Letter Date
        AttributeBinding onePLetterDateAttr =
            (AttributeBinding)bindings.getControlBinding("OnepLetter");
        if(onePLetterDateAttr.getInputValue() != null){
            onePLetterExists = true;
        }
        
        //Get Collection Letter Date
        AttributeBinding collLetterDateAttr =
            (AttributeBinding)bindings.getControlBinding("CollLetter");
        if(collLetterDateAttr.getInputValue() != null){
            collLetterExists = true;
        }
        //Get appeal date
        if (appealDateAttr.getInputValue() != null) {
            appealDateExists = true;
        }
        
        //Get binding ClosedDate
        AttributeBinding closedDateAttr =
            (AttributeBinding)bindings.getControlBinding("ClosedDate");
        
        //Get appeal closed date
        if (closedDateAttr.getInputValue() != null){
            closedDateExists = true;
        }
        
        //Check if appeal is closed and if 1P letter has been voided
        if(appealDateExists == true && closedDateExists == true &&
        collLetterExists == true && invoiceBalance.compareTo(0) == 1){
            errorMessages.add("Appeal is closed and has not paid with a Collection Letter Sent and not voided");
            isNotValidCancellation = true;
        }
        
        //Check if Collection Letter has been sent and needs to be voided
        if(appealDateExists == false && invoiceBalance.compareTo(0) == 1
            &&  collLetterExists == true ){
            errorMessages.add("Collection Letter has been sent and not voided");
            isNotValidCancellation = true;
        }
        
        /*/Check for payment plans
        if(invoiceBalance.compareTo(0) == 1 && validPayPlansLong < 1 
           && appealDateExists == false) {
            errorMessages.add("There is a balance and no valid payment plan exists");
            isNotValidCancellation = true;
        } else if(invoiceBalance.compareTo(0) == 1 && validPayPlansLong < 1 
           && appealDateExists == true && closedDateExists == true) {
            errorMessages.add("There is a balance and no valid payment plan exists");
            isNotValidCancellation = true;
        }*/
        
        //Do not allow cancel if PAID_IN_FULL or DELETED
        AttributeBinding statusAttr =
            (AttributeBinding)bindings.getControlBinding("Status");
        
        if(statusAttr.getInputValue().toString().equalsIgnoreCase("PAID_IN_FULL")){
            errorMessages.add("Invoice is currently Paid in Full.");
            isNotValidCancellation = true;
        } else if (statusAttr.getInputValue().toString().equalsIgnoreCase("DELETED")){
            errorMessages.add("Referral is already Cancelled.");
            isNotValidCancellation = true;
        }
        
        //Check appeal type code
        AttributeBinding appealTypeCodeAttr =
                    (AttributeBinding)bindings.getControlBinding("AppealTypeCode");
        
                
        //Checks if Appeal is under Reconsider or Writ of Mandamus
        if(appealTypeCodeAttr.getInputValue() != null 
           && !appealTypeCodeAttr.getInputValue().toString().equalsIgnoreCase("REGULAR")){
                isNotValidCancellation = false;
        }
        
        //Cancel Referral is not valid, so show faces message and errors
        if(isNotValidCancellation == true){
            
            //Generate error message for invalid cancellation
            StringBuilder message = new StringBuilder("<html><body>");
        
            message.append("<p><b>Unable to cancel referral due to: </b></p>");
        
            //Build list of error messages
            for(String errorMessage : errorMessages){
                message.append("<p>" + errorMessage + "</p>");
            }
        
            message.append("</body></html>");
            FacesMessage fm = new FacesMessage(message.toString());
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null, fm);
            
        } else if (isNotValidCancellation == false){
        
            //Get referral view iterator
            DCIteratorBinding referralViewIter = getDcBindings("ReferralView1Iterator");
            //Get current referral row
            Row currentReferralViewRow = referralViewIter.getCurrentRow();
            //Set Status of current referral row as DELETED
            currentReferralViewRow.setAttribute("Status", "DELETED");
        System.out.println(currentReferralViewRow.getAttribute("ReferralId").toString());
            //Create a new call log for cancelling referral
            OperationBinding newCallLogNoteForCancelReferral =
                getBindings().getOperationBinding("CreateCallLogNote");
            newCallLogNoteForCancelReferral.execute();
            if (!newCallLogNoteForCancelReferral.getErrors().isEmpty()) {
                System.out.println(newCallLogNoteForCancelReferral.getErrors().toString());
            }
        
            //Show Cancel Referral popup for cancellation notes
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.cancelReferralPopup.show(hints);
        }
    }
    
    /*  TODO:Change this to use a client listener for Cancel
    *   Handling Cancel will need javascript and client listener
    */
    public void cancelReferralDialogListener(DialogEvent dialogEvent){
        Outcome outcome = dialogEvent.getOutcome();
        
        if(outcome == Outcome.yes){
        OperationBinding commit =
            getBindings().getOperationBinding("Commit");
        commit.execute();
        System.out.println("Commit");
            
        } else if (outcome == Outcome.no){
        OperationBinding rollback =
            getBindings().getOperationBinding("Rollback");
        rollback.execute();
            
        }
        
    }
    
    /**
     * Returns whether a Recommended Write Off Record is valid
     * 
     * @param   inspectionId    inspectionId of the debt
     * @return                  Recommended Write Off Record is valid or not
     */
    public boolean validRecommendWriteOff(Number inspectionId) {
        boolean isValidRecommendWriteOff = false;

        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            prepStmt =
                getReferralAppModule().getDBTransaction().createPreparedStatement(VALIDATE_RECOMMEND_WRITE_OFF,
                                                                                  0);
            prepStmt.setString(1, inspectionId.toString());
            prepStmt.execute();
            rs = prepStmt.getResultSet();
            rs.next();
            int nRecommendWoCount = rs.getInt("RECOMMEND_WO_COUNT");
            rs.close();
            prepStmt.close();
            if (nRecommendWoCount < 1) {
                isValidRecommendWriteOff = true;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.toString());
        } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException logOrIgnore) {
                        System.out.println(logOrIgnore.toString());
                    }
                }
                if (prepStmt != null) {
                    try {
                        prepStmt.close();
                    } catch (SQLException logOrIgnore) {
                        System.out.println(logOrIgnore.toString());
                    }
                }
            }
        ;
        
        return isValidRecommendWriteOff;
    }
    
    public String recommendWriteOffNavigation(){
        String navigation = "";
        

        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        
        AttributeBinding citationIssueDateAttr =
            (AttributeBinding)bindings.getControlBinding("Citationissuedate");
        
        return navigation;
    }
    
    public String judgmentNavigation(){
        String navigation = "";
        

        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        
        
        return navigation;
    }

    
    public String coeProcessNavigation(){
        String navigation = "";
        

        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        
        
        return navigation;
    }
}
