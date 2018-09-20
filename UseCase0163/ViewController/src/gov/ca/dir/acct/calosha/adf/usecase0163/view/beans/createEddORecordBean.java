package gov.ca.dir.acct.calosha.adf.usecase0163.view.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControl;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;

public class createEddORecordBean {
    private oracle.jbo.domain.Number    TpCollectionHeaderId;
    private java.lang.String            DebtId;
    private java.lang.String            DebtorNumber;
    private java.lang.String            EntityLegalName;
    private java.lang.String            EntityFirstName;
    private java.lang.String            EntityLastName;
    private oracle.jbo.domain.Number    ThirdPartyParentId;
    private oracle.jbo.domain.Number    ThirdPartyCollectionId;
    private oracle.jbo.domain.Number    ThirdPartyCollectionAssocToId;
    private oracle.jbo.domain.Number    ReferralId;
    private java.lang.String            DataSourceCode;
    private java.lang.String            Navigate;
    private oracle.jbo.domain.Number    testNumber = new Number(456);

    private final String VALIDATE_DEBTOR_NUMBER_SQL_O = 
        "SELECT COUNT(THIRD_PARTY_COLLECTION.DEBTOR_NUMBER) NR_RECORD_COUNT FROM THIRD_PARTY_COLLECTION \n" +
        "LEFT OUTER JOIN THIRD_PARTY_COLL_ASSOC ON THIRD_PARTY_COLLECTION.THIRD_PARTY_COLLECT_ID = THIRD_PARTY_COLL_ASSOC.THIRD_PARTY_COLL_FROM_ID\n" +
        "LEFT OUTER JOIN THIRD_PARTY_COLLECTION ThirdPartyTo ON THIRD_PARTY_COLL_ASSOC.THIRD_PARTY_COLL_TO_ID = ThirdPartyTo.THIRD_PARTY_COLLECT_ID\n" +
        "WHERE THIRD_PARTY_COLLECTION.RECORD_TYPE IN ('N','R') AND (ThirdPartyTo.RECORD_TYPE <> 'D' OR ThirdPartyTo.RECORD_TYPE IS NULL) \n" +
        "AND THIRD_PARTY_COLLECTION.STATUS IS NULL AND THIRD_PARTY_COLLECTION.DEBTOR_NUMBER = ?";

    private final String REFERRAL_SEARCH_APP_MODULE =
        "ReferralSearchInvoiceAMDataControl";
    
    private RichInputText entityLegalNameIt;
    private RichInputText entityFirstNameIt;
    private RichInputText entityLastNameIt;


    public void setTpCollectionHeaderId(Number TpCollectionHeaderId) {
        this.TpCollectionHeaderId = TpCollectionHeaderId;
    }

    public Number getTpCollectionHeaderId() {
        return TpCollectionHeaderId;
    }

    public void setDebtId(String DebtId) {
        this.DebtId = DebtId;
    }

    public String getDebtId() {
        return DebtId;
    }

    public void setDebtorNumber(String DebtorNumber) {
        this.DebtorNumber = DebtorNumber;
    }

    public String getDebtorNumber() {
        return DebtorNumber;
    }

    public void setThirdPartyCollectionId(Number ThirdPartyCollectionId) {
        this.ThirdPartyCollectionId = ThirdPartyCollectionId;
    }

    public Number getThirdPartyCollectionId() {
        return ThirdPartyCollectionId;
    }

    public void setThirdPartyParentId(Number ThirdPartyParentId) {
        this.ThirdPartyParentId = ThirdPartyParentId;
    }

    public Number getThirdPartyParentId() {
        return ThirdPartyParentId;
    }

    public void setDataSourceCode(String DataSourceCode) {
        this.DataSourceCode = DataSourceCode;
    }

    public String getDataSourceCode() {
        return DataSourceCode;
    }

    public void setThirdPartyCollectionAssocToId(Number ThirdPartyCollectionAssocToId) {
        this.ThirdPartyCollectionAssocToId = ThirdPartyCollectionAssocToId;
    }

    public Number getThirdPartyCollectionAssocToId() {
        return ThirdPartyCollectionAssocToId;
    }

    public void setNavigate(String Navigate) {
        this.Navigate = Navigate;
    }

    public String getNavigate() {
        return Navigate;
    }

    public void setTestNumber(Number testNumber) {
        this.testNumber = testNumber;
    }

    public Number getTestNumber() {
        return testNumber;
    }
    
    //Helper Method to get bindings

    private BindingContainer getBindings() {
        BindingContext bindingContext = BindingContext.getCurrent();
        BindingContainer bindings = bindingContext.getCurrentBindingsEntry();
        return bindings;
    }

    //Helper Method to get DC Iterator Binding, Iterator needs to exist in page binding

    private DCIteratorBinding getDcBindings(String iteratorName) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIteratorBinding =
            bindings.findIteratorBinding(iteratorName);
        return dcIteratorBinding;
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

    public void updateHeaderIdForNewTpcRecord() {

        //Get ThirdPartyCollectionHeader Iterator
        DCIteratorBinding tpcHeader =
            getDcBindings("ThirdPartyCollectionHeaderView2Iterator");

        //Get ThirdPartyCollectionHeader Iterator current row
        Row tpcHeaderCurrentRow = tpcHeader.getCurrentRow();

        //Get current ThirdPartyCollectionHeader Id
        oracle.jbo.domain.Number tpcHeaderId =
            (oracle.jbo.domain.Number)tpcHeaderCurrentRow.getAttribute("TpCollectionHeaderId");

        //Get ThirdPartyCollectionNView Iterator
        DCIteratorBinding tpcNewRecord =
            getDcBindings("ThirdPartyCollectionOView1Iterator");

        //Get ThirdPartyCollectionNView Iterator current row
        Row tpcNewRecordRow = tpcNewRecord.getCurrentRow();

        //Set current ThirdPartyCollectionHeader Id in the new Third Party Collection
        tpcNewRecordRow.setAttribute("TpCollectionHeaderId", tpcHeaderId);

    }

    public void validORecordDebtorNumber(FacesContext facesContext,
                                         UIComponent uIComponent,
                                         Object object) {
        String preparedStmtSql = "";

        if (object != null) {
            String debtorNumber = object.toString();
            try {
                PreparedStatement prepStmt =
                    getReferralAppModule().getDBTransaction().createPreparedStatement(VALIDATE_DEBTOR_NUMBER_SQL_O,
                                                                                      0);
                prepStmt.setString(1, debtorNumber);
                prepStmt.execute();
                ResultSet rs = prepStmt.getResultSet();
                rs.next();
                int nrRecordCount = rs.getInt("NR_RECORD_COUNT");
                rs.close();
                prepStmt.close();
                if (nrRecordCount < 1) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                  "Debtor Number is not valid",
                                                                  "Please check and reenter"));
                }
            } catch (SQLException sqlException) {
                System.out.println(sqlException.toString());
            }
            ;
        }
    }
    public String checkIfEddDeleteIsValid() {
        String navigation = Navigate;
        if (ThirdPartyCollectionId != null) {
            //Get TPC Header Id for the current row
            DCIteratorBinding tpcIterator =
                getDcBindings("ThirdPartyCollectionOView1Iterator");
            String tpcHeaderId =
                tpcIterator.getCurrentRow().getAttribute("TpCollectionHeaderId").toString();

            //Get TPC Header Iterator & find out if it's been processed
            DCIteratorBinding tpcHeaderIterator =
                getDcBindings("ThirdPartyCollectionHeaderView1Iterator");
            tpcHeaderIterator.executeQuery();
            tpcHeaderIterator.setCurrentRowWithKeyValue(tpcHeaderId);
            String headerStatus =
                tpcHeaderIterator.getCurrentRow().getAttribute("Status").toString();
            if (!headerStatus.equalsIgnoreCase("NEW")) {
                navigation = "invalidHeaderStatus";
            }
        }
        navigation = additionalNameNotValid();
        
        return navigation;
    }

    public void setReferralId(Number ReferralId) {
        this.ReferralId = ReferralId;
    }

    public Number getReferralId() {
        return ReferralId;
    }

    public void createPersonRelatedParty() {
        
        //Get DCIteratorBindings
        DCIteratorBinding partyIter =
            getDcBindings("PartyView1Iterator");
        DCIteratorBinding personIter =
            getDcBindings("PersonView2Iterator");
        DCIteratorBinding relatedPartyIter =
            getDcBindings("RelatedPartyView1Iterator");
        
        Row newPersonRow = personIter.getViewObject().createRow();
        Row newPartyRow = partyIter.getViewObject().createRow();
        Row newRelatedPartyRow = relatedPartyIter.getViewObject().createRow();
        
        Number partyId = (Number)newPersonRow.getAttribute("PartyId");
        
        newPartyRow.setAttribute("PartyId", partyId);
        newPersonRow.setAttribute("FirstName", EntityFirstName);
        newPersonRow.setAttribute("LastName", EntityLastName);
        newRelatedPartyRow.setAttribute("PartyId", partyId);
        newRelatedPartyRow.setAttribute("ReferralId", ReferralId);
        
        partyIter.getViewObject().insertRow(newPartyRow);
        personIter.getViewObject().insertRow(newPersonRow);
        relatedPartyIter.getViewObject().insertRow(newRelatedPartyRow);
    }

    public void setEntityFirstName(String EntityFirstName) {
        this.EntityFirstName = EntityFirstName;
    }

    public String getEntityFirstName() {
        return EntityFirstName;
    }

    public void setEntityLastName(String EntityLastName) {
        this.EntityLastName = EntityLastName;
    }

    public String getEntityLastName() {
        return EntityLastName;
    }

    public void setEntityLegalName(String EntityLegalName) {
        this.EntityLegalName = EntityLegalName;
    }

    public String getEntityLegalName() {
        return EntityLegalName;
    }

    public void createOrganizationRelatedParty() {
        
        //Get DCIteratorBindings
        DCIteratorBinding partyIter =
            getDcBindings("PartyView1Iterator");
        DCIteratorBinding organizationIter =
            getDcBindings("OrganizationView1Iterator");
        DCIteratorBinding relatedPartyIter =
            getDcBindings("RelatedPartyView1Iterator");
        
        Row newOrganizationRow = organizationIter.getViewObject().createRow();
        Row newPartyRow = partyIter.getViewObject().createRow();
        Row newRelatedPartyRow = relatedPartyIter.getViewObject().createRow();
        
        Number partyId = (Number)newOrganizationRow.getAttribute("PartyId");
        
        newPartyRow.setAttribute("PartyId", partyId);
        newOrganizationRow.setAttribute("LegalName", EntityLegalName);
        newRelatedPartyRow.setAttribute("PartyId", partyId);
        newRelatedPartyRow.setAttribute("ReferralId", ReferralId);
        
        partyIter.getViewObject().insertRow(newPartyRow);
        organizationIter.getViewObject().insertRow(newOrganizationRow);
        relatedPartyIter.getViewObject().insertRow(newRelatedPartyRow);
    }
    
    private String additionalNameNotValid(){
        
        String navigation = Navigate;
        
        String entityLegalNameValue =  "";
        String entityFirstNameValue =  "";
        String entityLastNameValue  =  "";        
        String errorMsg = "First and Last Name should be blank if Legal Name is filled out.";
        String errorMsg2 = "Both First/Last Name fields should be filled out if Legal Name is blank.";
        String errorMsg3 = "Legal Name or Both First/Last fields should be filled out";
        
        BindingContainer bindings = getBindings();
        
        AttributeBinding entityLegalNameAttr = (AttributeBinding)bindings.getControlBinding("EntityLegalName");
        AttributeBinding entityFirstNameValueAttr = (AttributeBinding)bindings.getControlBinding("EntityFirstName");
        AttributeBinding entityLastNameValueAttr = (AttributeBinding)bindings.getControlBinding("EntityLastName");

        if (entityLegalNameAttr != null) {
            entityLegalNameValue = (String)entityLegalNameAttr.getInputValue();
        }
        if (entityFirstNameValueAttr != null) {
            entityFirstNameValue =
                    (String)entityFirstNameValueAttr.getInputValue();
        }
        if (entityLastNameValueAttr != null) {
            entityLastNameValue =
                    (String)entityLastNameValueAttr.getInputValue();
        }


        if (entityLegalNameValue != null && !entityLegalNameValue.isEmpty()) {
            if (entityFirstNameValue != null ||
                entityLastNameValue != null ) {
                navigation = " ";
                FacesMessage fm =
                    new FacesMessage(FacesMessage.SEVERITY_INFO, errorMsg,
                                     null);
                FacesContext fctx = FacesContext.getCurrentInstance();
                fctx.addMessage(null, fm);
            }
        }

        if (entityLegalNameValue == null || entityLegalNameValue.isEmpty()) {
            if (entityFirstNameValue == null ||
                entityLastNameValue == null ) {
                navigation = " ";
                FacesMessage fm =
                    new FacesMessage(FacesMessage.SEVERITY_INFO, errorMsg2,
                                     null);
                FacesContext fctx = FacesContext.getCurrentInstance();
                fctx.addMessage(null, fm);

            }
        }

        if (entityLegalNameValue == null || entityLegalNameValue.isEmpty()) {
            if (entityFirstNameValue == null ||
                entityFirstNameValue.isEmpty()) {
                if (entityLastNameValue == null ||
                    entityLastNameValue.isEmpty()) {
                    navigation = " ";
                    FacesMessage fm =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, errorMsg3,
                                         null);
                    FacesContext fctx = FacesContext.getCurrentInstance();
                    fctx.addMessage(null, fm);
                }
            }
        }
        
        return navigation;
    }

    public void validEntityLegalName(FacesContext facesContext,
                                     UIComponent uIComponent, Object object) {
        String entityLegalNameValue =   object.toString();
        String entityFirstNameValue =   (String)entityFirstNameIt.getValue();
        String entityLastNameValue  =   (String)entityLastNameIt.getValue();
        
        
        
    }    
    
    public void validEntityFirstName(FacesContext facesContext,
                                     UIComponent uIComponent, Object object) {
        String entityLegalNameValue =   (String)entityLegalNameIt.getValue();
        String entityFirstNameValue =   object.toString();
        String entityLastNameValue  =   (String)entityLastNameIt.getValue();
    }

    public void setEntityLegalNameIt(RichInputText entityLegalNameIt) {
        this.entityLegalNameIt = entityLegalNameIt;
    }

    public RichInputText getEntityLegalNameIt() {
        return entityLegalNameIt;
    }

    public void setEntityFirstNameIt(RichInputText entityFirstNameIt) {
        this.entityFirstNameIt = entityFirstNameIt;
    }

    public RichInputText getEntityFirstNameIt() {
        return entityFirstNameIt;
    }

    public void setEntityLastNameIt(RichInputText entityLastNameIt) {
        this.entityLastNameIt = entityLastNameIt;
    }

    public RichInputText getEntityLastNameIt() {
        return entityLastNameIt;
    }
}
