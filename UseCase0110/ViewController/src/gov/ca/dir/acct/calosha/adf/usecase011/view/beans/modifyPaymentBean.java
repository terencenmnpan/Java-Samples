package gov.ca.dir.acct.calosha.adf.usecase011.view.beans;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.DialogEvent.Outcome;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.server.ApplicationModuleImpl;

public class modifyPaymentBean {
    
    private String warrantNumber = "";
    private Date warrantDate = new Date(Date.getCurrentDate());
    private static final String CHECK_PAYMENT_TYPE = "CHECK";
    private static final String EDF_PAYMENT_TYPE = "EDF";

    public void setWarrantNumber(String warrantNumber) {
        this.warrantNumber = warrantNumber;
    }

    public String getWarrantNumber() {
        return warrantNumber;
    }

    public void setWarrantDate(Date warrantDate) {
        this.warrantDate = warrantDate;
    }

    public Date getWarrantDate() {
        return warrantDate;
    }

    public static void navigate(String outcome) {
    FacesContext context = FacesContext.getCurrentInstance();
    NavigationHandler nh = context.getApplication().getNavigationHandler();
    nh.handleNavigation(context, null, outcome);
    }
    
    public void partialRefundDialogListener(DialogEvent dialogEvent){
        Outcome outcome = dialogEvent.getOutcome();
        if(outcome == outcome.ok){
            navigate("PartialRefund");
        }
    }

    /**
     * validate whether the CHECK/EDF payment already process or not. If not, then pop up
     * warning message.
     * 
     * @return  String
     */
    public String transferProcess() {
        // Add event code here...
        //get current payment
        DCBindingContainer dCBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        ApplicationModuleImpl appModule = 
            (ApplicationModuleImpl)dCBinding.getDataControl().getApplicationModule();  
        ViewObject paymentVO = appModule.findViewObject("PaymentView1");
        Row currentPayment = paymentVO.getCurrentRow();
        if (currentPayment != null) {
            String paymentType = (String)currentPayment.getAttribute("PaymentTypeCode");
            oracle.jbo.domain.Number depositTransmittalId = (oracle.jbo.domain.Number)currentPayment.getAttribute("DepositTransmittalId");
            if (depositTransmittalId == null &&
                    (paymentType.equalsIgnoreCase(CHECK_PAYMENT_TYPE) || 
                     paymentType.equalsIgnoreCase(EDF_PAYMENT_TYPE))) {
                String msg = "Payment cannot Transfer because " + paymentType + " Payment has not processed yet.";
                FacesContext ctx = FacesContext.getCurrentInstance(); 
                FacesMessage faceMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "", msg);
                ctx.addMessage(null, faceMsg);
                
                return null;
            }
        }
        
        return "Transfer";
    }
    /**
     * validate whether the CHECK/EDF payment already process or not. If not, then pop up
     * warning message.
     * 
     * @return  String
     */
    public String transferUnappliedProcess() {
        // Add event code here...
        //get current payment
        DCBindingContainer dCBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        ApplicationModuleImpl appModule = 
            (ApplicationModuleImpl)dCBinding.getDataControl().getApplicationModule();  
        ViewObject paymentVO = appModule.findViewObject("PaymentView1");
        Row currentPayment = paymentVO.getCurrentRow();
        if (currentPayment != null) {
            String paymentType = (String)currentPayment.getAttribute("PaymentTypeCode");
            oracle.jbo.domain.Number depositTransmittalId = (oracle.jbo.domain.Number)currentPayment.getAttribute("DepositTransmittalId");
            if (depositTransmittalId == null &&
                    (paymentType.equalsIgnoreCase(CHECK_PAYMENT_TYPE) || 
                     paymentType.equalsIgnoreCase(EDF_PAYMENT_TYPE))) {
                String msg = "Payment cannot Transfer because " + paymentType + " Payment has not processed yet.";
                FacesContext ctx = FacesContext.getCurrentInstance(); 
                FacesMessage faceMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "", msg);
                ctx.addMessage(null, faceMsg);
                
                return null;
            }
        }
        
        return "TransferUnapplied";
    }
}
