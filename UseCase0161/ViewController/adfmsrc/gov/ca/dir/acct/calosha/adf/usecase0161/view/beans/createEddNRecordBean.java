package gov.ca.dir.acct.calosha.adf.usecase0161.view.beans;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

import oracle.adf.model.BindingContext;

import oracle.adf.model.DataControl;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;

public class createEddNRecordBean {

    private oracle.jbo.domain.Number TpCollectionHeaderId;
    private java.lang.String RecordType;
    private java.lang.String DebtId;
    private java.lang.String DebtorNumber;
    private oracle.jbo.domain.Number ReferralId;
    private java.lang.String DebtorType;
    private java.lang.String DebtorId;
    private java.lang.String EmployerName;
    private java.lang.String EntityLegalName;
    private java.lang.String EntityFirstName;
    private java.lang.String EntityLastName;
    private java.lang.String IndexCode;
    private java.lang.String ReportingId;
    private java.lang.String RevenueSourceCode;
    private java.lang.String AgencySourceCode;
    private oracle.jbo.domain.Number PcaCode;
    private java.lang.String FundId;
    private java.lang.String FiscalYearName;
    private java.lang.String City;
    private java.lang.String StreetAddress;
    private java.lang.String StateCode;
    private java.lang.String ZipCode;
    private java.lang.String City1;
    private java.lang.String StreetAddress1;
    private java.lang.String StateCode1;
    private java.lang.String ZipCode1;
    private oracle.jbo.domain.Date IssueDate;
    private oracle.jbo.domain.Date SubmissionDate;
    private oracle.jbo.domain.Date LiabilityFinalityDate;
    private oracle.jbo.domain.Date DemandLetterDate;
    private oracle.jbo.domain.Date JudgmentDate;
    private oracle.jbo.domain.Date InterestThroughDate;
    private oracle.jbo.domain.Number CollectionFeeBalance;
    private oracle.jbo.domain.Number CourtFilingFeeBalance;
    private oracle.jbo.domain.Number InterestBalance;
    private oracle.jbo.domain.Number NsfFeeBalance;
    private oracle.jbo.domain.Number FinalPenalty;
    private oracle.jbo.domain.Number FinalTotalAmountDue;
    private java.lang.String PhoneType;
    private java.lang.String PhoneType1;
    private oracle.jbo.domain.Number PhoneAreaCode;
    private oracle.jbo.domain.Number PhoneAreaCode1;
    private oracle.jbo.domain.Number PhoneNumber;
    private oracle.jbo.domain.Number PhoneNumber1;
    private java.lang.String DataSourceCode;
    private oracle.jbo.domain.Number ThirdPartyParentId;
    private oracle.jbo.domain.Number ThirdPartyCollectionId;
    private oracle.jbo.domain.Number ThirdPartyCollectionAssocToId;
    private oracle.jbo.domain.Number PartyAgencyFundMapId;
    private java.lang.String Navigate;
    private oracle.jbo.domain.Number testNumber = new Number(514);
    private oracle.jbo.domain.Number testNumber2 = new Number(601);
    private oracle.jbo.domain.Number zeroNumber = new Number(0);
    private oracle.jbo.domain.Number calculatedFinalTotalAmountDue =
        new Number(0);

    private final String VALIDATE_DEBTOR_NUMBER_SQL_N =
        "SELECT COUNT(DEBTOR_NUMBER) N_RECORD_COUNT FROM THIRD_PARTY_COLLECTION " +
        "INNER JOIN THIRD_PARTY_COLLECT_HEADER ON THIRD_PARTY_COLLECTION.TP_COLLECTION_HEADER_ID " +
        "= THIRD_PARTY_COLLECT_HEADER.TP_COLLECTION_HEADER_ID " +
        "WHERE THIRD_PARTY_COLLECTION.RECORD_TYPE = 'N'  AND THIRD_PARTY_COLLECT_HEADER.STATUS <> 'ERROR' " +
        "AND (THIRD_PARTY_COLLECTION.STATUS IS NULL or THIRD_PARTY_COLLECTION.STATUS <> 'INVALID') " +
        "AND DEBTOR_NUMBER = ?";

    private final String REFERRAL_SEARCH_APP_MODULE =
        "ReferralSearchInvoiceAMDataControl";
    private RichInputText entityLegalEntityIt;
    private RichInputText entityFirstNameIt;
    private RichInputText entityLastNameIt;
    private RichInputText streetAddress2;
    private RichInputText city2;
    private RichSelectOneChoice stateCode2;
    private RichInputText zipCode2;
    private RichInputText debtorIdIt;
    private Boolean addOptionalAddress = false;

    public void setTpCollectionHeaderId(Number TpCollectionHeaderId) {
        this.TpCollectionHeaderId = TpCollectionHeaderId;
    }

    public Number getTpCollectionHeaderId() {
        return TpCollectionHeaderId;
    }

    public void setRecordType(String RecordType) {
        this.RecordType = RecordType;
    }

    public String getRecordType() {
        return RecordType;
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

    public void setReferralId(Number ReferralId) {
        this.ReferralId = ReferralId;
    }

    public Number getReferralId() {
        return ReferralId;
    }

    public void setDebtorType(String DebtorType) {
        this.DebtorType = DebtorType;
    }

    public String getDebtorType() {
        return DebtorType;
    }

    public void setDebtorId(String DebtorId) {
        this.DebtorId = DebtorId;
    }

    public String getDebtorId() {
        return DebtorId;
    }

    public void setEmployerName(String EmployerName) {
        this.EmployerName = EmployerName;
    }

    public String getEmployerName() {
        return EmployerName;
    }

    public void setEntityLegalName(String EntityLegalName) {
        this.EntityLegalName = EntityLegalName;
    }

    public String getEntityLegalName() {
        return EntityLegalName;
    }

    public void setIndexCode(String IndexCode) {
        this.IndexCode = IndexCode;
    }

    public String getIndexCode() {
        return IndexCode;
    }

    public void setRevenueSourceCode(String RevenueSourceCode) {
        this.RevenueSourceCode = RevenueSourceCode;
    }

    public String getRevenueSourceCode() {
        return RevenueSourceCode;
    }

    public void setAgencySourceCode(String AgencySourceCode) {
        this.AgencySourceCode = AgencySourceCode;
    }

    public String getAgencySourceCode() {
        return AgencySourceCode;
    }

    public void setPcaCode(Number PcaCode) {
        this.PcaCode = PcaCode;
    }

    public Number getPcaCode() {
        return PcaCode;
    }

    public void setFundId(String FundId) {
        this.FundId = FundId;
    }

    public String getFundId() {
        return FundId;
    }

    public void setFiscalYearName(String FiscalYearName) {
        this.FiscalYearName = FiscalYearName;
    }

    public String getFiscalYearName() {
        return FiscalYearName;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCity() {
        return City;
    }

    public void setStreetAddress(String StreetAddress) {
        this.StreetAddress = StreetAddress;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStateCode(String StateCode) {
        this.StateCode = StateCode;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setCity1(String City1) {
        this.City1 = City1;
    }

    public String getCity1() {
        return City1;
    }

    public void setStreetAddress1(String StreetAddress1) {
        this.StreetAddress1 = StreetAddress1;
    }

    public String getStreetAddress1() {
        return StreetAddress1;
    }

    public void setStateCode1(String StateCode1) {
        this.StateCode1 = StateCode1;
    }

    public String getStateCode1() {
        return StateCode1;
    }

    public void setZipCode1(String ZipCode1) {
        this.ZipCode1 = ZipCode1;
    }

    public String getZipCode1() {
        return ZipCode1;
    }

    public void setIssueDate(oracle.jbo.domain.Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public oracle.jbo.domain.Date getIssueDate() {
        return IssueDate;
    }

    public void setSubmissionDate(Date SubmissionDate) {
        this.SubmissionDate = SubmissionDate;
    }

    public Date getSubmissionDate() {
        return SubmissionDate;
    }

    public void setLiabilityFinalityDate(Date LiabilityFinalityDate) {
        this.LiabilityFinalityDate = LiabilityFinalityDate;
    }

    public Date getLiabilityFinalityDate() {
        return LiabilityFinalityDate;
    }

    public void setDemandLetterDate(Date DemandLetterDate) {
        this.DemandLetterDate = DemandLetterDate;
    }

    public Date getDemandLetterDate() {
        return DemandLetterDate;
    }

    public void setJudgmentDate(Date JudgmentDate) {
        this.JudgmentDate = JudgmentDate;
    }

    public Date getJudgmentDate() {
        return JudgmentDate;
    }

    public void setInterestThroughDate(Date InterestThroughDate) {
        this.InterestThroughDate = InterestThroughDate;
    }

    public Date getInterestThroughDate() {
        return InterestThroughDate;
    }

    public void setCollectionFeeBalance(Number CollectionFeeBalance) {
        this.CollectionFeeBalance = CollectionFeeBalance;
    }

    public Number getCollectionFeeBalance() {
        return CollectionFeeBalance;
    }

    public void setCourtFilingFeeBalance(Number CourtFilingFeeBalance) {
        this.CourtFilingFeeBalance = CourtFilingFeeBalance;
    }

    public Number getCourtFilingFeeBalance() {
        return CourtFilingFeeBalance;
    }

    public void setInterestBalance(Number InterestBalance) {
        this.InterestBalance = InterestBalance;
    }

    public Number getInterestBalance() {
        return InterestBalance;
    }

    public void setNsfFeeBalance(Number NsfFeeBalance) {
        this.NsfFeeBalance = NsfFeeBalance;
    }

    public Number getNsfFeeBalance() {
        return NsfFeeBalance;
    }

    public void setFinalPenalty(Number FinalPenalty) {
        this.FinalPenalty = FinalPenalty;
    }

    public Number getFinalPenalty() {
        return FinalPenalty;
    }

    public void setFinalTotalAmountDue(Number FinalTotalAmountDue) {
        this.FinalTotalAmountDue = FinalTotalAmountDue;
    }

    public Number getFinalTotalAmountDue() {
        return FinalTotalAmountDue;
    }

    public void setPhoneType(String PhoneType) {
        this.PhoneType = PhoneType;
    }

    public String getPhoneType() {
        return PhoneType;
    }

    public void setPhoneType1(String PhoneType1) {
        this.PhoneType1 = PhoneType1;
    }

    public String getPhoneType1() {
        return PhoneType1;
    }

    public void setPhoneAreaCode(Number PhoneAreaCode) {
        this.PhoneAreaCode = PhoneAreaCode;
    }

    public Number getPhoneAreaCode() {
        return PhoneAreaCode;
    }

    public void setPhoneAreaCode1(Number PhoneAreaCode1) {
        this.PhoneAreaCode1 = PhoneAreaCode1;
    }

    public Number getPhoneAreaCode1() {
        return PhoneAreaCode1;
    }

    public void setPhoneNumber(Number PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public Number getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber1(Number PhoneNumber1) {
        this.PhoneNumber1 = PhoneNumber1;
    }

    public Number getPhoneNumber1() {
        return PhoneNumber1;
    }

    public void setDataSourceCode(String DataSourceCode) {
        this.DataSourceCode = DataSourceCode;
    }

    public String getDataSourceCode() {
        return DataSourceCode;
    }

    public void setThirdPartyParentId(Number ThirdPartyParentId) {
        this.ThirdPartyParentId = ThirdPartyParentId;
    }

    public Number getThirdPartyParentId() {
        return ThirdPartyParentId;
    }

    public void setThirdPartyCollectionId(Number ThirdPartyCollectionId) {
        this.ThirdPartyCollectionId = ThirdPartyCollectionId;
    }

    public Number getThirdPartyCollectionId() {
        return ThirdPartyCollectionId;
    }

    public void setPartyAgencyFundMapId(Number PartyAgencyFundMapId) {
        this.PartyAgencyFundMapId = PartyAgencyFundMapId;
    }

    public Number getPartyAgencyFundMapId() {
        return PartyAgencyFundMapId;
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

    public void setReportingId(String ReportingId) {
        this.ReportingId = ReportingId;
    }

    public String getReportingId() {
        return ReportingId;
    }

    public void setTestNumber2(Number testNumber2) {
        this.testNumber2 = testNumber2;
    }

    public Number getTestNumber2() {
        return testNumber2;
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

    public void setEntityLegalEntityIt(RichInputText entityLegalEntityIt) {
        this.entityLegalEntityIt = entityLegalEntityIt;
    }

    public RichInputText getEntityLegalEntityIt() {
        return entityLegalEntityIt;
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

    public Number getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(Number testNumber) {
        this.testNumber = testNumber;
    }

    public void setCalculatedFinalTotalAmountDue(Number calculatedFinalTotalAmountDue) {
        this.calculatedFinalTotalAmountDue = calculatedFinalTotalAmountDue;
    }

    public Number getCalculatedFinalTotalAmountDue() {
        return calculatedFinalTotalAmountDue;
    }

    public void setZeroNumber(Number zeroNumber) {
        this.zeroNumber = zeroNumber;
    }

    public Number getZeroNumber() {
        return zeroNumber;
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

    public Object resolveExpression(String el) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
            facesContext.getApplication().getExpressionFactory();
        ValueExpression valueExp =
            expressionFactory.createValueExpression(elContext, el,
                                                    Object.class);
        return valueExp.getValue(elContext);
    }

    public void setValueToEL(String el, Object val) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
            facesContext.getApplication().getExpressionFactory();
        ValueExpression exp =
            expressionFactory.createValueExpression(elContext, el,
                                                    Object.class);
        exp.setValue(elContext, val);
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


    //Update the Header Id using the latest New Tpc Header

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
            getDcBindings("ThirdPartyCollectionNView2Iterator");

        //Get ThirdPartyCollectionNView Iterator current row
        Row tpcNewRecordRow = tpcNewRecord.getCurrentRow();

        //Set current ThirdPartyCollectionHeader Id in the new Third Party Collection
        tpcNewRecordRow.setAttribute("TpCollectionHeaderId", tpcHeaderId);

        if (FinalTotalAmountDue == null) {
            tpcNewRecordRow.setAttribute("FinalTotalAmountDue",
                                         getCalculatedFinalTotalAmountDue());
        }
    }

    //Validate to make sure N Record is a valid and not a duplicate

    public void validNRecordDebtorNumber(FacesContext facesContext,
                                         UIComponent uIComponent,
                                         Object object) {
        if (object != null) {
            String debtorNumber = object.toString();
            try {
                PreparedStatement prepStmt =
                    getReferralAppModule().getDBTransaction().createPreparedStatement(VALIDATE_DEBTOR_NUMBER_SQL_N,
                                                                                      0);
                prepStmt.setString(1, debtorNumber);
                prepStmt.execute();


                ResultSet rs = prepStmt.getResultSet();
                rs.next();
                int nRecordCount = rs.getInt("N_RECORD_COUNT");
                rs.close();
                prepStmt.close();
                if (nRecordCount > 0) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                                  "Debtor Number already exists",
                                                                  "Duplicate Debtor Numbers not allowed"));
                }
            } catch (SQLException sqlException) {
                System.out.println(sqlException.toString());
            }
            ;
        }
    }
    //Validate to make sure Liability Finality Date is not a future date

    public void validNotFutureDate(FacesContext facesContext,
                                         UIComponent uIComponent,
                                         Object object) {
        
        Date currentDate = new Date(Date.getCurrentDate());
        
        if (object != null) {
            Date liabilityFinalityDate = (Date)object;

            if (liabilityFinalityDate.compareTo(currentDate) == 1) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                              "Invalid Date",
                                                              "Future not allowed for this field"));
            }
        }
    }
    
    /*
     * TODO: probably just need to remove this, calling TF will supply a valid
     * debtor number that will not be editable
     */

    public void validRRecordDebtorNumber(FacesContext facesContext,
                                         UIComponent uIComponent,
                                         Object object) {

        if (object != null) {
            String debtorNumber = object.toString();
            try {
                PreparedStatement prepStmt =
                    getReferralAppModule().getDBTransaction().createPreparedStatement(VALIDATE_DEBTOR_NUMBER_SQL_N,
                                                                                      0);
                prepStmt.setString(1, debtorNumber);
                prepStmt.execute();


                ResultSet rs = prepStmt.getResultSet();
                rs.next();
                int nRecordCount = rs.getInt("R_RECORD_COUNT");
                rs.close();
                prepStmt.close();
                if (nRecordCount > 0) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                  "Debtor Number already exists",
                                                                  "Duplicate Debtor Numbers not allowed"));
                }
            } catch (SQLException sqlException) {
                System.out.println(sqlException.toString());
            }
            ;
        }
    }

    //Check if Deletion of a TPC Row is valid, only NEW Header can be deleted

    public String checkIfEddDeleteIsValid() {
        String navigation = Navigate;
        if (ThirdPartyCollectionId != null) {
            //Get TPC Header Id for the current row
            DCIteratorBinding tpcIterator =
                getDcBindings("ThirdPartyCollectionNView2Iterator");
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
        return navigation;
    }

    //Delete children TPC Records for a TPC Record being deleted

    public void deleteChildrenPendingRecords() {
        //Get TPC Assoc Records for Children Iterator
        DCIteratorBinding tpcAssocIterator =
            getDcBindings("ThirdPartyCollAssocView6Iterator");
        Row[] tpcAssocRows = tpcAssocIterator.getAllRowsInRange();

        //Get Iterator for TPC
        DCIteratorBinding tpcIterator =
            getDcBindings("ThirdPartyCollectionAnyView1Iterator");

        //Iterate through child rows and delete children and associations
        for (Row tpcAssocRow : tpcAssocRows) {
            String tpcChildKey =
                tpcAssocRow.getAttribute("ThirdPartyCollToId").toString();
            tpcAssocRow.remove();
            tpcIterator.setCurrentRowWithKeyValue(tpcChildKey);
            tpcIterator.removeCurrentRow();
        }
        //ThirdPartyCollAssocView5IteratorThirdPartyCollectionNView2Iterator

        DCIteratorBinding tpcAssocIterator2 =
            getDcBindings("ThirdPartyCollAssocView5Iterator");

        if (tpcAssocIterator2.getCurrentRow() != null) {
            tpcAssocIterator2.getCurrentRow().remove();
        }

        DCIteratorBinding tpcIterator2 =
            getDcBindings("ThirdPartyCollectionNView2Iterator");
        tpcIterator2.getCurrentRow().remove();
    }

    //Recalculate Final Total Amt Due for legacy data

    public void recalculateFinalTotalAmtDue(ValueChangeEvent valueChangeEvent) {
        Number newAmount = new Number(0);
        Number oldAmount = new Number(0);

        if (valueChangeEvent.getNewValue() != null) {
            String newAmountString = (String)valueChangeEvent.getNewValue();
            try {
                newAmount =
                        new Number(newAmountString.replace("$", "").replace(",",
                                                                            ""));
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                // this should not throw an exception...
            }
        }

        if (valueChangeEvent.getOldValue() != null) {
            String oldAmountString = (String)valueChangeEvent.getOldValue();
            try {
                oldAmount =
                        new Number(oldAmountString.replace("$", "").replace(",",
                                                                            ""));
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                // this should not throw an exception...
            }
        }
        
        Number changeInAmount = newAmount.subtract(oldAmount);
        setCalculatedFinalTotalAmountDue(changeInAmount.add(calculatedFinalTotalAmountDue));
        

    }

    //Set Calculated Final Total Amount from an edit scenario

    public void setCalculatedFinalTotalAmount() {

        //Get ThirdPartyCollectionHeader Iterator
        DCIteratorBinding tpcHeader =
            getDcBindings("ThirdPartyCollectionNView2Iterator");

        //Get ThirdPartyCollectionHeader Iterator current row
        Row tpcHeaderCurrentRow = tpcHeader.getCurrentRow();

        setCalculatedFinalTotalAmountDue((Number)tpcHeaderCurrentRow.getAttribute("FinalTotalAmountDue"));

    }

    //Helper method to copy an attribute from an old row to the new row

    private void copyAtttribute(Row iteratorBindingOldCurrentRow,
                                Row iteratorBindingNewCurrentRow,
                                String attributeName) {

        if (iteratorBindingOldCurrentRow.getAttribute(attributeName) != null) {
            iteratorBindingNewCurrentRow.setAttribute(attributeName,
                                                      iteratorBindingOldCurrentRow.getAttribute(attributeName));
        }
    }

    //Copy the previous N/R Record to an R Record
    //TODO: This is pretty ghetto code, so maybe find an OOP way of refactoring this.

    public void copyPreviousRecord() {

        //Get ThirdPartyCollectionHeader Iterator
        DCIteratorBinding tpcNRRecordOld =
            getDcBindings("ThirdPartyCollectionLastNRCeasedRecallIterator");

        //Get ThirdPartyCollectionHeader Iterator
        DCIteratorBinding tpcRRecordNew =
            getDcBindings("ThirdPartyCollectionNView2Iterator");

        Row currentTpcRRecordNewRow = tpcRRecordNew.getCurrentRow();

        Row currentTpcNRRecordOldRow = tpcNRRecordOld.getCurrentRow();

        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "AgencySourceCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "City");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "City1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "CollectionFeeBalance");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "CourtFilingFeeBalance");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "DebtorId");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "DebtorType");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "DemandLetterDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "EmployerName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "EntityFirstName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "EntityLastName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "EntityLegalName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "FinalPenalty");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "FinalTotalAmountDue");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "FiscalYearName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "FundId");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "IndexCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "InterestBalance");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "InterestThroughDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "IssueDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "JudgmentDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "LegalEntityType");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "LiabilityFinalityDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "NsfFeeBalance");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PcaCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneAreaCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneAreaCode1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneNumber");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneNumber1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneType");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneType1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "ReportingId");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "RevenueSourceCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "StateCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "StateCode1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "StreetAddress");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "StreetAddress1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "ZipCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "ZipCode1");
        
        calculatedFinalTotalAmountDue = (Number) currentTpcNRRecordOldRow.getAttribute("FinalTotalAmountDue");
    }

    public void copyPreviousRecordCalold() {

        //Get ThirdPartyCollectionHeader Iterator
        DCIteratorBinding tpcNRRecordOld =
            getDcBindings("ThirdPartyCollectionAnyView1Iterator");

        //Get ThirdPartyCollectionHeader Iterator
        DCIteratorBinding tpcRRecordNew =
            getDcBindings("ThirdPartyCollectionNView2Iterator");

        Row currentTpcRRecordNewRow = tpcRRecordNew.getCurrentRow();

        Row currentTpcNRRecordOldRow = tpcNRRecordOld.getCurrentRow();

        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "AgencySourceCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "City");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "City1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "CollectionFeeBalance");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "CourtFilingFeeBalance");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "DebtorId");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "DebtorType");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "DemandLetterDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "EmployerName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "EntityFirstName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "EntityLastName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "EntityLegalName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "FinalPenalty");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "FinalTotalAmountDue");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "FiscalYearName");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "FundId");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "IndexCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "InterestBalance");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "InterestThroughDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "IssueDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "JudgmentDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "LegalEntityType");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "LiabilityFinalityDate");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "NsfFeeBalance");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PcaCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneAreaCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneAreaCode1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneNumber");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneNumber1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneType");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "PhoneType1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "ReportingId");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "RevenueSourceCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "StateCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "StateCode1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "StreetAddress");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "StreetAddress1");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "ZipCode");
        copyAtttribute(currentTpcNRRecordOldRow, currentTpcRRecordNewRow,
                       "ZipCode1");
        
        calculatedFinalTotalAmountDue = (Number) currentTpcNRRecordOldRow.getAttribute("FinalTotalAmountDue");
    }
    
    /**
     * Sets Entity Type field in the bean when select one choice on the 
     * jsff page is changed
     * 
     * @param   valueChangeEvent    Value change listener from jsff
     */
    public void legalEntityTypeValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Integer entityTypeInt = (Integer)valueChangeEvent.getNewValue();
        
            DCIteratorBinding tpcRRecord =
                getDcBindings("ThirdPartyCollectionNView2Iterator");
            Row tpcRRecordRow = tpcRRecord.getCurrentRow();

        if (entityTypeInt > 1) {

            //Allow Legal Entity to be editable
            entityLegalEntityIt.setReadOnly(false);
            entityLegalEntityIt.setRequired(true);

            //Make Legal First/Last Name read only & Clear
            
            tpcRRecordRow.setAttribute("EntityFirstName", "");
            tpcRRecordRow.setAttribute("EntityLastName", "");
            entityFirstNameIt.resetValue();
            entityLastNameIt.resetValue();
            entityFirstNameIt.setReadOnly(true);
            entityLastNameIt.setReadOnly(true);
            entityFirstNameIt.setRequired(false);
            entityLastNameIt.setRequired(false);

        } else if (entityTypeInt < 2) {

            //Make Legal Entity Type read only & Clear
            tpcRRecordRow.setAttribute("EntityLegalName", "");
            entityLegalEntityIt.resetValue();
            entityLegalEntityIt.setReadOnly(true);
            entityLegalEntityIt.setRequired(false);

            //Allow Legal First/Last Name to be editable
            entityFirstNameIt.setReadOnly(false);
            entityLastNameIt.setReadOnly(false);
            entityFirstNameIt.setRequired(true);
            entityLastNameIt.setRequired(true);
        }

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

    public void setStreetAddress2(RichInputText streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public RichInputText getStreetAddress2() {
        return streetAddress2;
    }

    public void setCity2(RichInputText city2) {
        this.city2 = city2;
    }

    public RichInputText getCity2() {
        return city2;
    }

    public void setStateCode2(RichSelectOneChoice stateCode2) {
        this.stateCode2 = stateCode2;
    }

    public RichSelectOneChoice getStateCode2() {
        return stateCode2;
    }

    public void setDebtorIdIt(RichInputText debtorIdIt) {
        this.debtorIdIt = debtorIdIt;
    }

    public RichInputText getDebtorIdIt() {
        return debtorIdIt;
    }

    public void setZipCode2(RichInputText zipCode2) {
        this.zipCode2 = zipCode2;
    }

    public RichInputText getZipCode2() {
        return zipCode2;
    }

    
    /**
     * Sets Debtor Type Id field in the bean when select one choice on the 
     * jsff page is changed
     * 
     * @param   valueChangeEvent    Value change listener from jsff
     */
    public void debtorTypeValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Integer debtorTypeInt = (Integer)valueChangeEvent.getNewValue();
        if (debtorTypeInt > 0) {
            debtorIdIt.setRequired(true);
        } else {
            debtorIdIt.setRequired(false);
        }
    }
    
    /**
     * Sets Optional Address fields to required orclear/read only in the bean 
     * when select boolean checkbox on the jsff page is changed
     * 
     * @param   valueChangeEvent    Value change listener from jsff
     */
    public void optionalAddressValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Boolean optionalAddress = (Boolean) valueChangeEvent.getNewValue();
        if(optionalAddress == true){
            streetAddress2.setRequired(true);
            city2.setRequired(true);
            stateCode2.setRequired(true);
            zipCode2.setRequired(true);
            streetAddress2.setReadOnly(false);
            city2.setReadOnly(false);
            stateCode2.setReadOnly(false);
            zipCode2.setReadOnly(false);
        } else if (optionalAddress == false){
            streetAddress2.setRequired(false);
            city2.setRequired(false);
            stateCode2.setRequired(false);
            zipCode2.setRequired(false);
            streetAddress2.resetValue();
            city2.resetValue();
            stateCode2.resetValue();
            zipCode2.resetValue();
            streetAddress2.setReadOnly(true);
            city2.setReadOnly(true);
            stateCode2.setReadOnly(true);
            zipCode2.setReadOnly(true);
            
        }
    }

    public void setAddOptionalAddress(boolean addOptionalAddress) {
        this.addOptionalAddress = addOptionalAddress;
    }

    public Boolean isAddOptionalAddress() {
        return addOptionalAddress;
    }
}
