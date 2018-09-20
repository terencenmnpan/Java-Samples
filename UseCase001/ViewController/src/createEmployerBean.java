import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

public class createEmployerBean {


    public createEmployerBean() {
    }
    
    private String newPartyCategoryCode = "";
    private String newPartyClassificationCode = "OTHER";
    private String contactMethodValue;
    private String contactMethodTypeCode;
    private String contactMethodType;

    //Page component binding fields
    private RichPopup phoneTypePopup;
    private RichPopup emailTypePopup;
    private RichPopup websiteUrlTypePopup;
    
    /* These are unused booleans that we use to control visible properties on 
     * the update employer screen. Disabled property doesn't allow the use of 
     * methods to return boolean properties without having a field in the bean.
     */
    private boolean addressSiteExists;
    private boolean addressMailingExists;
    private boolean contactPhoneExists;
    private boolean contactWorkExists;
    private boolean contactFaxExists;
    private boolean contactCellExists;
    private boolean contactWebsiteExists;
    private boolean contactUrlExists;
    private boolean contactEmailExists;
    
    
    //Getters and setters
    public void setNewPartyCategoryCode(String newPartyCategoryCode) {
        this.newPartyCategoryCode = newPartyCategoryCode;
    }

    public String getNewPartyCategoryCode() {
        return newPartyCategoryCode;
    }

    public void setNewPartyClassificationCode(String newPartyClassificationCode) {
        this.newPartyClassificationCode = newPartyClassificationCode;
    }

    public String getNewPartyClassificationCode() {
        return newPartyClassificationCode;
    }

    public void setContactMethodValue(String contactMethodValue) {
        this.contactMethodValue = contactMethodValue;
    }

    public String getContactMethodValue() {
        return contactMethodValue;
    }

    public void setPhoneTypePopup(RichPopup phoneTypePopup) {
        this.phoneTypePopup = phoneTypePopup;
    }

    public RichPopup getPhoneTypePopup() {
        return phoneTypePopup;
    }

    public void setContactMethodTypeCode(String contactMethodTypeCode) {
        this.contactMethodTypeCode = contactMethodTypeCode;
    }

    public String getContactMethodTypeCode() {
        return contactMethodTypeCode;
    }

    public void setContactMethodType(String contactMethodType) {
        this.contactMethodType = contactMethodType;
    }

    public String getContactMethodType() {
        return contactMethodType;
    }

    public void setEmailTypePopup(RichPopup emailTypePopup) {
        this.emailTypePopup = emailTypePopup;
    }

    public RichPopup getEmailTypePopup() {
        return emailTypePopup;
    }

    public void setWebsiteUrlTypePopup(RichPopup websiteUrlTypePopup) {
        this.websiteUrlTypePopup = websiteUrlTypePopup;
    }

    public RichPopup getWebsiteUrlTypePopup() {
        return websiteUrlTypePopup;
    }

    public boolean isContactPhoneExists() {
        return checkContactMethodNotVisible("PHONE");
    }

    public boolean isContactWorkExists() {
        return checkContactMethodNotVisible("WORK_PHONE");
    }

    public boolean isContactFaxExists() {
        return checkContactMethodNotVisible("FAX");
    }

    public boolean isContactCellExists() {
        return checkContactMethodNotVisible("CELLPHONE");
    }

    public boolean isContactWebsiteExists() {
        return checkContactMethodNotVisible("WEB_SITE");
    }

    public boolean isContactUrlExists() {
        return checkContactMethodNotVisible("URL");
    }

    public boolean isContactEmailExists() {
        return checkContactMethodNotVisible("EMAIL");
    }

    public boolean isAddressSiteExists() {
        return checkAddressNotVisible("SITE");
    }

    public boolean isAddressMailingExists() {
        return checkAddressNotVisible("MAILING");
    }

    //Helper method to get bindings
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /*
     * Helper method to get request scope value in managed bean
     * argument - name of request scope #{requestScope.requestScopeName}
     * returns String requestScopeValue
     */
    private String getRequestScope(String requestScopeName){
        ADFContext adfContext = ADFContext.getCurrent();
        Map requestScope = adfContext.getRequestScope();
        String requestScopeValue = (String) requestScope.get(requestScopeName);
        return requestScopeValue;
    }
    
    /*
     * Helper method to get page flow scope value in managed bean
     * argument - name of page flow scope #{pageFlowScope.pageFlowScopeName}
     * returns String requestScopeValue
     */
    private String getPageFlowScope(String pageFlowScopeName){
        ADFContext adfContext = ADFContext.getCurrent();
        Map pageFlowScope = adfContext.getPageFlowScope();
        String pageFlowScopeValue = (String) pageFlowScope.get(pageFlowScopeName);
        return pageFlowScopeValue;
    }
    
    /*
    * Method checks for request scope values for contact methods to see 
    * if they have values and then inserts values into the database,
    * if empty, they will not insert and then commit and return to calling
    * task flow
    */
    public String sendBackEmployer() {
        BindingContainer bindings = getBindings();
        
        //Deletes Party Classification row if Party Category is blank
        if(getPageFlowScope("UseCase001EmployerPartyCategoryCode").equalsIgnoreCase("N/A")){
        OperationBinding operationBinding9 = bindings.getOperationBinding("DeletePartyClassification");
        operationBinding9.execute();
        if (!operationBinding9.getErrors().isEmpty()) {
            return null;
        }
        }
        
        
        if(getRequestScope("Url") != null && ! getRequestScope("Url").isEmpty()){
        OperationBinding operationBinding8 = bindings.getOperationBinding("UrlCreateInsert");
        operationBinding8.execute();
        if (!operationBinding8.getErrors().isEmpty()) {
            return null;
        }
        }
        
        if(getRequestScope("Website") != null && ! getRequestScope("Website").isEmpty()){
        OperationBinding operationBinding7 = bindings.getOperationBinding("WebsiteCreateInsert");
        operationBinding7.execute();
        if (!operationBinding7.getErrors().isEmpty()) {
            return null;
        }
        }
        
        if(getRequestScope("Fax") != null && ! getRequestScope("Fax").isEmpty()){
        OperationBinding operationBinding6 = bindings.getOperationBinding("FaxCreateInsert");
        operationBinding6.execute();
        if (!operationBinding6.getErrors().isEmpty()) {
            return null;
        }
        }
        
        if(getRequestScope("Email") != null && ! getRequestScope("Email").isEmpty()){
        OperationBinding operationBinding5 = bindings.getOperationBinding("EmailCreateInsert");
        operationBinding5.execute();
        if (!operationBinding5.getErrors().isEmpty()) {
            return null;
        }
        }
        
        if(getRequestScope("WorkPhoneNumber") != null && ! getRequestScope("WorkPhoneNumber").isEmpty()){
        OperationBinding operationBinding4 = bindings.getOperationBinding("WorkPhoneCreateInsert");
        operationBinding4.execute();
        if (!operationBinding4.getErrors().isEmpty()) {
            return null;
        }
        }
        
        if(getRequestScope("CellPhoneNumber") != null && ! getRequestScope("CellPhoneNumber").isEmpty()){
        OperationBinding operationBinding3 = bindings.getOperationBinding("CellPhoneCreateInsert");
        operationBinding3.execute();
        if (!operationBinding3.getErrors().isEmpty()) {
            return null;
        }
        }
        
        if(getRequestScope("PhoneNumber") != null && ! getRequestScope("PhoneNumber").isEmpty()){
        OperationBinding operationBinding2 = bindings.getOperationBinding("PhoneCreateInsert");
        operationBinding2.execute();
        if (!operationBinding2.getErrors().isEmpty()) {
            return null;
        }
        }
                
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        
        return "selectCreatedEmployer";
    }

    //Validates Party Category Code to make sure the correct combination is selected
    public void partyCategoryCodeValidator(FacesContext facesContext,
                                                 UIComponent uIComponent,
                                                 Object object) {
        if(object != null){
            

            BindingContext lBindingContext = BindingContext.getCurrent();
            BindingContainer lBindingContainer = lBindingContext.getCurrentBindingsEntry();
            JUCtrlListBinding list = (JUCtrlListBinding) lBindingContainer.get("PartyCategoryCode");
            int newindex = (Integer) object;
            Object row = list.getDisplayData();  // load the list
            Row lFromList = (Row)list.getValueFromList(newindex);
            Object lAttribute = lFromList.getAttribute("PartyCategoryCode");
            String newVal = (String) lAttribute;
            setNewPartyCategoryCode(newVal);
            
            String msgPolice = "Employers with " + "POLICE" +
                 " Party Category Code should have GOVN as their Party Classification";            
            String msgSchool = "Employers with " + "SCHOOL" +
                 " Party Category Code should have GOVN as their Party Classification";            
            String msgFire = "Employers with " + "FIRE" +
                 " Party Category Code should have GOVN as their Party Classification";      
            String msgPublic = "Employers with " + "PUBLIC" +
                 " Party Category Code should have CORP as their Party Classification";
            
            if(newPartyCategoryCode.equalsIgnoreCase("FIRE") && 
               !newPartyClassificationCode.equalsIgnoreCase("GOVN")){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msgFire,null));
            } else if(newPartyCategoryCode.equalsIgnoreCase("SCHOOL") && 
               !newPartyClassificationCode.equalsIgnoreCase("GOVN")){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msgSchool,null));
            } else if(newPartyCategoryCode.equalsIgnoreCase("POLICE") && 
               !newPartyClassificationCode.equalsIgnoreCase("GOVN")){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msgPolice,null));
            } else if(newPartyCategoryCode.equalsIgnoreCase("PUBLIC") && 
               !newPartyClassificationCode.equalsIgnoreCase("CORP")){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msgPublic,null));
            } else if (!newPartyCategoryCode.equalsIgnoreCase("PUBLIC")
                       &&!newPartyCategoryCode.equalsIgnoreCase("PRIVATE")
                       &&!newPartyCategoryCode.equalsIgnoreCase("SCHOOL")
                       &&!newPartyCategoryCode.equalsIgnoreCase("POLICE")
                       &&!newPartyCategoryCode.equalsIgnoreCase("FIRE")
                      )
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"OTHER should not be chosen",null));
        }
    }
    
    //Validates Party Classification Code to make sure the correct combination is selected
    public void partyClassificationCodeValidator(FacesContext facesContext,
                                                 UIComponent uIComponent,
                                                 Object object) {
        if(object != null){
            

            BindingContext lBindingContext = BindingContext.getCurrent();
            BindingContainer lBindingContainer = lBindingContext.getCurrentBindingsEntry();
            JUCtrlListBinding list = (JUCtrlListBinding) lBindingContainer.get("PartyClassficationCode");
            int newindex = (Integer) object;
            Object row = list.getDisplayData();  // load the list
            Row lFromList = (Row)list.getValueFromList(newindex);
            Object lAttribute = lFromList.getAttribute("PartyClassificationCode");
            String newVal = (String) lAttribute;
            setNewPartyClassificationCode(newVal);
            
            
            String msgPolice = "Employers with " + "POLICE" +
                 " Party Category Code should have GOVN as their Party Classification";            
            String msgSchool = "Employers with " + "SCHOOL" +
                 " Party Category Code should have GOVN as their Party Classification";            
            String msgFire = "Employers with " + "FIRE" +
                 " Party Category Code should have GOVN as their Party Classification";      
            String msgPublic = "Employers with " + "PUBLIC" +
                 " Party Category Code should have CORP as their Party Classification";
            
            if(newPartyCategoryCode.equalsIgnoreCase("FIRE") && 
               !newPartyClassificationCode.equalsIgnoreCase("GOVN")){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msgFire,null));
            } else if(newPartyCategoryCode.equalsIgnoreCase("SCHOOL") && 
               !newPartyClassificationCode.equalsIgnoreCase("GOVN")){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msgSchool,null));
            } else if(newPartyCategoryCode.equalsIgnoreCase("POLICE") && 
               !newPartyClassificationCode.equalsIgnoreCase("GOVN")){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msgPolice,null));
            } else if(newPartyCategoryCode.equalsIgnoreCase("PUBLIC") && 
               !newPartyClassificationCode.equalsIgnoreCase("CORP")){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msgPublic,null));
            }else if (!newPartyCategoryCode.equalsIgnoreCase("PUBLIC")
                       &&!newPartyCategoryCode.equalsIgnoreCase("PRIVATE")
                       &&!newPartyCategoryCode.equalsIgnoreCase("SCHOOL")
                       &&!newPartyCategoryCode.equalsIgnoreCase("POLICE")
                       &&!newPartyCategoryCode.equalsIgnoreCase("FIRE")
                      )
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"OTHER should not be chosen",null));
            
        }
    }
    
    //Value change listener for Party Category Code
    public void partyCategoryCodeValueChangeListener(ValueChangeEvent valueChangeEvent) {
        BindingContext lBindingContext = BindingContext.getCurrent();
        BindingContainer lBindingContainer = lBindingContext.getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) lBindingContainer.get("PartyCategoryCode");
        int newindex = (Integer) valueChangeEvent.getNewValue();
        Object row = list.getDisplayData();  // load the list
        Row lFromList = (Row)list.getValueFromList(newindex);
        Object lAttribute = lFromList.getAttribute("PartyCategoryCode");
        String newVal = (String) lAttribute;
        setNewPartyCategoryCode(newVal);
    }
    
    //Value change listener for Party Classification Code
    public void partyClassificationCodeValueChangeListener(ValueChangeEvent valueChangeEvent) {
        BindingContext lBindingContext = BindingContext.getCurrent();
        BindingContainer lBindingContainer = lBindingContext.getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) lBindingContainer.get("PartyClassficationCode");
        int newindex = (Integer) valueChangeEvent.getNewValue();
        Object row = list.getDisplayData();  // load the list
        Row lFromList = (Row)list.getValueFromList(newindex);
        Object lAttribute = lFromList.getAttribute("PartyClassificationCode");
        String newVal = (String) lAttribute;
        setNewPartyClassificationCode(newVal);
    }
    
    //Create Contact Method
    public void addContactMethod(DialogEvent dialogEvent) {
        String contactMethodValue;
        String contactMethodInput;
        
        if (getContactMethodTypeCode().equalsIgnoreCase("EMAIL")){
            contactMethodInput = "inputEmailMethodValue";
        } else if (getContactMethodTypeCode().equalsIgnoreCase("WEB_SITE") | 
                   getContactMethodTypeCode().equalsIgnoreCase("URL")){
            contactMethodInput = "inputWebsiteUrlMethodValue";
        } else {
            contactMethodInput = "inputContactMethodValue";
        }
        
        contactMethodValue = (String)
            dialogEvent.getComponent().findComponent(contactMethodInput).getAttributes().get("value");

        BindingContainer bindings = getBindings();

        OperationBinding operationBinding = bindings.getOperationBinding("CreateContactMethod");
        operationBinding.getParamsMap().put("ContactMethodValue",
                                            contactMethodValue);
        operationBinding.getParamsMap().put("ContactMethodTypeCode",
                                            getContactMethodTypeCode());
        operationBinding.execute();
        
        OperationBinding obCommit = bindings.getOperationBinding("Commit");
        obCommit.execute();
        
    }
    
    /* Show popup methods used for adding contact methods
     * popups don't like to play nice with action listeners
     * so it's easier to use an action listener to show popups
     * in order to pass values to dialog methods.
     */
     
    public void showFaxPopup(ActionEvent actionEvent){
        
        setContactMethodTypeCode("FAX");
        setContactMethodType("Fax");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        hints.add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID, "p1");
        getPhoneTypePopup().show(hints);
    }

    public void showPhonePopup(ActionEvent actionEvent) {
        
        setContactMethodTypeCode("PHONE");
        setContactMethodType("Phone");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        hints.add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID, "p1");
        getPhoneTypePopup().show(hints);
    }

    public void showCellPopup(ActionEvent actionEvent) {

        setContactMethodTypeCode("CELLPHONE");
        setContactMethodType("Cell Phone");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        hints.add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID, "p1");
        getPhoneTypePopup().show(hints);
    }

    public void showWorkPopup(ActionEvent actionEvent) {

        setContactMethodTypeCode("WORK_PHONE");
        setContactMethodType("Work Phone");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        hints.add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID, "p1");
        getPhoneTypePopup().show(hints);
    }

    public void showEmailPopup(ActionEvent actionEvent) {

        setContactMethodTypeCode("EMAIL");
        setContactMethodType("Email");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        hints.add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID, "p2");
        getEmailTypePopup().show(hints);
    }

    public void showUrlPopup(ActionEvent actionEvent) {

        setContactMethodTypeCode("URL");
        setContactMethodType("Url");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        hints.add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID, "p3");
        getWebsiteUrlTypePopup().show(hints);
    }

    public void showWebsitePopup(ActionEvent actionEvent) {

        setContactMethodTypeCode("WEB_SITE");
        setContactMethodType("Website");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        hints.add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID, "p3");
        getWebsiteUrlTypePopup().show(hints);
    }

    //
    public boolean checkContactMethodNotVisible(String contactMethodType) {
        ArrayList<String> contactMethodTypesUsed = new ArrayList<String>();
        boolean contactMethodTypeExists = true;
        
        //Get bindings
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();

        //Get contact method vo iter
        DCIteratorBinding contactMethodViewIter =
            bindings.findIteratorBinding("ContactMethodView1Iterator");

        Row[] contactMethodRows = contactMethodViewIter.getAllRowsInRange();

        for (Row contactMethodRow : contactMethodRows) {
            contactMethodTypesUsed.add((String)contactMethodRow.getAttribute("ContactMethodTypeCode"));
        }
        
        if(contactMethodTypesUsed.contains(contactMethodType)){
            contactMethodTypeExists = false;
        }
        
        return contactMethodTypeExists;
    }//checkContactMethodNotVisible

    //

    public boolean checkAddressNotVisible(String addressType) {
        ArrayList<String> addressTypesUsed = new ArrayList<String>();
        boolean addressTypeExists = true;

        //Get bindings
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();

        //Get contact method vo iter
        DCIteratorBinding addressViewIter =
            bindings.findIteratorBinding("AddressView2Iterator");

        Row[] addressRows = addressViewIter.getAllRowsInRange();

        for (Row addressRow : addressRows) {
            addressTypesUsed.add((String)addressRow.getAttribute("AddressTypeCode"));
        }

        if (addressTypesUsed.contains(addressType)) {
            addressTypeExists = false;
        }

        return addressTypeExists;
    } //checkAddressNotVisible
}
