package gov.ca.dir.acct.calosha.adf.usecase024.view.backing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.AttributeBinding;
import oracle.adf.model.BindingContext;

import oracle.adf.model.DataControl;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import oracle.jbo.server.ApplicationModuleImpl;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class eddFunctionsBean {
    public eddFunctionsBean() {
    }

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
    private oracle.jbo.domain.Number ThirdPartyCollectionId;
    private oracle.jbo.domain.Number PartyAgencyFundMapId;
    private oracle.jbo.domain.Number PartyAgencyFundMapIdCollectFee;
    private java.sql.Date IssueDateSqlDate;
    private java.sql.Date SubmissionDateSqlDate;
    private java.sql.Date LiabilityFinalityDateSqlDate;
    private java.sql.Date DemandLetterDateSqlDate;
    private java.sql.Date JudgmentDateSqlDate;
    private java.sql.Date InterestThroughDateSqlDate;
    private oracle.jbo.domain.Number ThirdPartyParentId;
    private String ReportingId;
    private oracle.jbo.domain.Number zeroNumber = new Number(0);

    private ArrayList phoneNumbers = new ArrayList<String>();

    private String currentlySelectedInspectionNumber;

    private boolean validNRecord = false;
    private boolean validRRecord = false;
    private boolean validORecord = false;
    private boolean validDRecord = false;

    private final String REFERRAL_SEARCH_APP_MODULE =
        "ReferralSearchInvoiceAMDataControl";

    private final String VALIDATE_DEBTOR_NUMBER_SQL_N =
        "SELECT COUNT(DEBTOR_NUMBER) N_RECORD_COUNT FROM THIRD_PARTY_COLLECTION " +
        "INNER JOIN THIRD_PARTY_COLLECT_HEADER ON THIRD_PARTY_COLLECTION.TP_COLLECTION_HEADER_ID " +
        "= THIRD_PARTY_COLLECT_HEADER.TP_COLLECTION_HEADER_ID " +
        "WHERE THIRD_PARTY_COLLECTION.RECORD_TYPE = 'N'  AND THIRD_PARTY_COLLECT_HEADER.STATUS <> 'ERROR' " +
        "AND (THIRD_PARTY_COLLECTION.STATUS IS NULL or THIRD_PARTY_COLLECTION.STATUS <> 'INVALID') " +
        "AND DEBTOR_NUMBER = ?";

    private final String VALIDATE_DEBTOR_NUMBER_SQL_R =
        "SELECT FUNC_IS_ACTIVE_TPC_REF( ? ) R_RECORD_COUNT FROM DUAL";

    // Getters and Setters here, there are some ghetto setters to cast SQL Dates
    // to Oracle Date Conversion below

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

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getIssueDate() {
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

    public void setValidNRecord(boolean validNRecord) {
        this.validNRecord = validNRecord;
    }

    public boolean isValidNRecord() {
        return validNRecord;
    }

    public void setValidRRecord(boolean validRRecord) {
        this.validRRecord = validRRecord;
    }

    public boolean isValidRRecord() {
        return validRRecord;
    }

    public void setValidORecord(boolean validORecord) {
        this.validORecord = validORecord;
    }

    public boolean isValidORecord() {
        return validORecord;
    }

    public void setValidDRecord(boolean validDRecord) {
        this.validDRecord = validDRecord;
    }

    public boolean isValidDRecord() {
        return validDRecord;
    }

    public void setPhoneNumbers(ArrayList phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ArrayList getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setIssueDateSqlDate(java.sql.Date issueDateSqlDate) {
        IssueDate = new Date(issueDateSqlDate);
        this.IssueDateSqlDate = issueDateSqlDate;
    }

    public java.sql.Date getIssueDateSqlDate() {
        return IssueDateSqlDate;
    }

    public void setSubmissionDateSqlDate(java.sql.Date submissionDateSqlDate) {
        SubmissionDate = new Date(submissionDateSqlDate);
        this.SubmissionDateSqlDate = submissionDateSqlDate;
    }

    public java.sql.Date getSubmissionDateSqlDate() {
        return SubmissionDateSqlDate;
    }

    public void setLiabilityFinalityDateSqlDate(java.sql.Date liabilityFinalityDateSqlDate) {
        LiabilityFinalityDate = new Date(liabilityFinalityDateSqlDate);
        this.LiabilityFinalityDateSqlDate = liabilityFinalityDateSqlDate;
    }

    public java.sql.Date getLiabilityFinalityDateSqlDate() {
        return DemandLetterDateSqlDate;
    }

    public void setDemandLetterDateSqlDate(java.sql.Date demandLetterDateSqlDate) {
        DemandLetterDate = new Date(demandLetterDateSqlDate);
        this.DemandLetterDateSqlDate = demandLetterDateSqlDate;
    }

    public java.sql.Date getDemandLetterDateSqlDate() {
        return DemandLetterDateSqlDate;
    }

    public void setJudgmentDateSqlDate(java.sql.Date judgmentDateSqlDate) {
        JudgmentDate = new Date(judgmentDateSqlDate);
        this.JudgmentDateSqlDate = judgmentDateSqlDate;
    }

    public java.sql.Date getJudgmentDateSqlDate() {
        return JudgmentDateSqlDate;
    }

    public void setInterestThroughDateSqlDate(java.sql.Date judgmentDateSqlDate) {
        InterestThroughDate = new Date(judgmentDateSqlDate);
        this.InterestThroughDateSqlDate = judgmentDateSqlDate;
    }

    public java.sql.Date InterestThroughDateSqlDate() {
        return InterestThroughDateSqlDate;
    }

    public void setPartyAgencyFundMapIdCollectFee(Number PartyAgencyFundMapIdCollectFee) {
        this.PartyAgencyFundMapIdCollectFee = PartyAgencyFundMapIdCollectFee;
    }

    public Number getPartyAgencyFundMapIdCollectFee() {
        return PartyAgencyFundMapIdCollectFee;
    }

    public void setThirdPartyParentId(Number ThirdPartyParentId) {
        this.ThirdPartyParentId = ThirdPartyParentId;
    }

    public Number getThirdPartyParentId() {
        return ThirdPartyParentId;
    }

    public void setReportingId(String ReportingId) {
        this.ReportingId = ReportingId;
    }

    public String getReportingId() {
        return ReportingId;
    }

    public Number getZeroNumber() {
        return zeroNumber;
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

    public void setCurrentlySelectedInspectionNumber(String currentlySelectedInspectionNumber) {
        this.currentlySelectedInspectionNumber =
                currentlySelectedInspectionNumber;
    }

    public String getCurrentlySelectedInspectionNumber() {
        return currentlySelectedInspectionNumber;
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

    //Create a new Referral record
    public void newReferral() {
        OperationBinding newReferral =
            getBindings().getOperationBinding("CreateReferral");
        newReferral.execute();
        if (!newReferral.getErrors().isEmpty()) {
            System.out.println(newReferral.getErrors().toString());
        }
    }
    //Method to translate CalOSHA phone type codes into EDD phone types
    public String getEddPhoneType(String contactMethodTypeCode) {
        String eddContactMethodType = "";
        if (contactMethodTypeCode.equalsIgnoreCase("PHONE") ||
            contactMethodTypeCode.equalsIgnoreCase("WORK_PHONE")) {
            eddContactMethodType = "BSN";
        } else if (contactMethodTypeCode.equalsIgnoreCase("CELLPHONE")) {
            eddContactMethodType = "CELL";
        }
        return eddContactMethodType;
    }

    //Method to populate Phone Numbers for EDD transmittal
    public void populateEddPhoneNumbers() {

        //Get row array for phone nubmers for employer
        Row[] phoneRows =
            getDcBindings("ContactPhoneView1Iterator").getAllRowsInRange();

        //Populate Phones if available
        if (phoneRows.length > 0) {
            Row phoneRow = phoneRows[0];
            String formattedPhone =
                phoneRow.getAttribute("ContactMethodValue").toString().replaceAll("\\D+",
                                                                                  "");
            Number formattedPhoneAreaCodeNumber = new Number(0);
            Number formattedPhoneNumber = new Number(0);

            //Cast Phone String to oracle Number
            try {
                formattedPhoneAreaCodeNumber =
                        new Number(formattedPhone.substring(0, 3));
                formattedPhoneNumber =
                        new Number(formattedPhone.substring(3, 10));
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                //Took out nondigits and db column is 100 chars max, shouldn't have a exception here
            }

            //Populate first phone number
            setPhoneType(getEddPhoneType(phoneRow.getAttribute("ContactMethodTypeCode").toString()));
            setPhoneAreaCode(formattedPhoneAreaCodeNumber);
            setPhoneNumber(formattedPhoneNumber);

            //Populate second phone number if available
            if (phoneRows.length > 1) {
                Row phoneRow2 = phoneRows[1];
                String formattedPhone2 =
                    phoneRow.getAttribute("ContactMethodValue").toString().replaceAll("\\D+",
                                                                                      "");

                Number formattedPhoneAreaCodeNumber2 = new Number(0);
                Number formattedPhoneNumber2 = new Number(0);

                //Cast Phone String to oracle Number
                try {
                    formattedPhoneAreaCodeNumber2 =
                            new Number(formattedPhone2.substring(0, 3));
                    formattedPhoneNumber2 =
                            new Number(formattedPhone2.substring(3, 10));
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    //Took out nondigits and db column is 100 chars max, shouldn't have a exception here
                }

                setPhoneType1(getEddPhoneType(phoneRow2.getAttribute("ContactMethodTypeCode").toString()));
                setPhoneAreaCode1(formattedPhoneAreaCodeNumber2);
                setPhoneNumber1(formattedPhoneNumber2);
            }
        }
        phoneNumbers.clear();
    }
    
    //Method that runs a page binding operation method to do a db commit
    public void commit() {
        OperationBinding commit = getBindings().getOperationBinding("Commit");
        commit.execute();
        if (!commit.getErrors().isEmpty()) {
            System.out.println(commit.getErrors().isEmpty());
        }
    }
    
    //Method that runs a page binding operation method to do a db rollback
    public String rollback() {
        OperationBinding operationBinding =
            getBindings().getOperationBinding("Rollback");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println(operationBinding.getErrors().isEmpty());
        }
        return null;
    }
    
    //Selection Listener for 
    public void collectionReferralSelectionListener(SelectionEvent selectionEvent) {
        ADFUtil.invokeEL("#{bindings.InvoiceView1.collectionModel.makeCurrent}",
                         new Class[] { SelectionEvent.class },
                         new Object[] { selectionEvent });
        DCIteratorBinding InvoiceIterator =
            getDcBindings("InvoiceView1Iterator");

        Row currentInvoiceIteratorRow = InvoiceIterator.getCurrentRow();

        String debtorNumber =
            currentInvoiceIteratorRow.getAttribute("InspectionNumber").toString();

        Date currentDate =
            new Date((java.sql.Date)currentInvoiceIteratorRow.getAttribute("CurrentGroovyDate"));

        if (validNRecord(debtorNumber) == true &&
            currentInvoiceIteratorRow.getAttribute("EddEligible") != null &&
            currentDate.compareTo(new Date((java.sql.Date)currentInvoiceIteratorRow.getAttribute("EddEligible"))) ==
            1) {
            setValidNRecord(true);
        } else {
            setValidNRecord(false);
        }

        /*
        if (validRRecord(debtorNumber) == true) {
            setValidRRecord(true);
        }*/

    }
    
    /**
     * Returns whether N Third Party Collection Record is valid to be created
     * Method uses a prepared statement to see if a nonerrored N Record already
     * exists in the TPC Table.
     * 
     * @param   debtorNumber    invoice number of the debt
     * @return                  whether N Record creation is valid or not
     */
    public boolean validNRecord(String debtorNumber) {
        boolean isNValid = false;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            prepStmt =
                getReferralAppModule().getDBTransaction().createPreparedStatement(VALIDATE_DEBTOR_NUMBER_SQL_N,
                                                                                  0);
            prepStmt.setString(1, debtorNumber);
            prepStmt.execute();
            rs = prepStmt.getResultSet();
            rs.next();
            int nRecordCount = rs.getInt("N_RECORD_COUNT");
            rs.close();
            prepStmt.close();
            if (nRecordCount < 1) {
                isNValid = true;
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
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        
        AttributeBinding appealTypeCodeAttr =
            (AttributeBinding)bindings.getControlBinding("AppealTypeCode");
        
        //Checks if Appeal is under Reconsider or Writ of Mandamus
        if(appealTypeCodeAttr.getInputValue() != null && !appealTypeCodeAttr.toString().equalsIgnoreCase("REGULAR")){
            isNValid = false;
        }
        
        return isNValid;
    }
    
    /**
     * Returns whether O or D Third Party Collection Record is valid to be created
     * Method uses a prepared statement to see if a nonerrored current valid N 
     * or R Record already exists in the TPC Table.A valid N or R Record would
     * make it valid to create an O or D Record.
     * 
     * @param   debtorNumber    invoice number of the debt
     * @return                  whether O or D Record creation is valid or not
     */
    public boolean validODRecord(String debtorNumber) {
        boolean isODValid = false;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            prepStmt =
                getReferralAppModule().getDBTransaction().createPreparedStatement(VALIDATE_DEBTOR_NUMBER_SQL_R,
                                                                                  0);
            prepStmt.setString(1, debtorNumber);
            prepStmt.execute();
            rs = prepStmt.getResultSet();
            rs.next();
            int rRecordCount = rs.getInt("R_RECORD_COUNT");
            rs.close();
            prepStmt.close();
            if (rRecordCount > 0) {
                isODValid = true;
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
        return isODValid;
    }
    
    /**
     * Returns whether R Third Party Collection Record is valid to be created
     * Method uses a prepared statement to see if a nonerrored current valid N 
     * or R Record already exists in the TPC Table. A valid N or R Record would
     * make it invalid to create an R Record.
     * 
     * @param   debtorNumber    invoice number of the debt
     * @return                  whether R Record creation is valid or not
     */
    public boolean validRRecord(String debtorNumber) {
        boolean isNExists = false;
        boolean isRValid = false;
        boolean invalidR = true;
        
        PreparedStatement prepStmt = null;
        ResultSet rs = null;

        try {
            prepStmt =
                getReferralAppModule().getDBTransaction().createPreparedStatement(VALIDATE_DEBTOR_NUMBER_SQL_N,
                                                                                  0);
            prepStmt.setString(1, debtorNumber);
            prepStmt.execute();
            rs = prepStmt.getResultSet();
            rs.next();
            int nRecordCount = rs.getInt("N_RECORD_COUNT");
            rs.close();
            prepStmt.close();
            if (nRecordCount > 0) {
                isNExists = true;
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

        try {
            prepStmt =
                getReferralAppModule().getDBTransaction().createPreparedStatement(VALIDATE_DEBTOR_NUMBER_SQL_R,
                                                                                  0);
            prepStmt.setString(1, debtorNumber);
            prepStmt.execute();
            rs = prepStmt.getResultSet();
            rs.next();
            int rRecordCount = rs.getInt("R_RECORD_COUNT");
            rs.close();
            prepStmt.close();
            if (rRecordCount == 0) {
                invalidR = false;
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

        if (isNExists == true) {
            if (invalidR == false) {
                isRValid = true;
            }
        }


        return isRValid;
    }
    
    /**
     * This method returns navigation. It will allow navigation to the ADF library
     * taskflow if N record Creation is valid.
     * 
     * @return  navigation  whether to navigate to allow N Record creation
     */
    public String newEddNRecordMenuItemNav() {

        boolean isNRecordValid = false;
        boolean isInspectionEddEligible = true;
        String notEddEligible =
            "Record is not Eligible for EDD N Record Referral";
        String notValidNRecord = "N Record is not valid due to existing record";
        ArrayList<String> errorMessages = new ArrayList<String>();
        String errorMessage = "";
        String navToEddNRecord = "";
        

        OperationBinding ExecuteWithParamsTpcLastNrCeasedRecall =
            getBindings().getOperationBinding("ExecuteWithParamsTpcLastNrCeasedRecall");
        ExecuteWithParamsTpcLastNrCeasedRecall.execute();
        if (!ExecuteWithParamsTpcLastNrCeasedRecall.getErrors().isEmpty()) {
            System.out.println(ExecuteWithParamsTpcLastNrCeasedRecall.getErrors().toString());
        }
        
        DCIteratorBinding tpcLastNrCeasedRecallIter = 
            getDcBindings("ThirdPartyCollectionLastNRCeasedRecallIterator");        
        
        //Use Last Finality Date
        //LiabilityFinalityDate = 
        //    (Date) tpcLastNrCeasedRecallIter.getCurrentRow().getAttribute("LiabilityFinalityDate");
        
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding inspectionNumberAttr =
            (AttributeBinding)bindings.getControlBinding("InspectionNumber");

        if (inspectionNumberAttr.getInputValue() != null) {
            isNRecordValid =
                    validNRecord(inspectionNumberAttr.getInputValue().toString());
        }

        AttributeBinding currentDateAttr =
            (AttributeBinding)bindings.getControlBinding("CurrentDate");

        AttributeBinding eddEligibleDateAttr =
            (AttributeBinding)bindings.getControlBinding("EddEligible");

        AttributeBinding statusAttr =
            (AttributeBinding)bindings.getControlBinding("Status");
                         
        String status = (String)statusAttr.getInputValue();
        
        AttributeBinding pmtplanstatusAttr =
            (AttributeBinding)bindings.getControlBinding("Pmtplanstatus");
        
        String pmtplanstatus = (String) pmtplanstatusAttr.getInputValue();
                
        AttributeBinding isAppealOpenAttr =
            (AttributeBinding)bindings.getControlBinding("IsAppealOpen");
        
        String isAppealOpen = (String)isAppealOpenAttr.getInputValue();

        AttributeBinding balanceAttr =
            (AttributeBinding)bindings.getControlBinding("Invoicebalance");

        String invoiceBalanceString = (String)balanceAttr.getInputValue();
        
        Number invoiceBalance = new Number(0);

        try {
            invoiceBalance =
                    new Number(invoiceBalanceString.replace("$", "").replace(",",
                                                                             ""));
        } catch (SQLException sqlException) {
            //Generally shouldn't need an exception handler here
            sqlException.printStackTrace();
        }

        Date currentDate = (Date)currentDateAttr.getInputValue();

        if (eddEligibleDateAttr.getInputValue() != null &&
            currentDate.compareTo(new Date((java.sql.Date)eddEligibleDateAttr.getInputValue())) ==
            1) {
        } else{
            errorMessages.add("<p>" + "Not yet reached referrable date." + "</p>");
            isInspectionEddEligible = false;
        }
        
        if (invoiceBalance.compareTo(new Number(25)) == 1) {
        } else{
            errorMessages.add("<p>" + "Not sufficient remaining balance." + "</p>");
            isInspectionEddEligible = false;
        }
        
        //Check Referral status is NEW, only NEW can be referred to EDD
        if (!status.equalsIgnoreCase("NEW")) {
            isInspectionEddEligible = false;
            errorMessages.add("<p>" + "Referral Status is not NEW." + "</p>");
        } 
        
        //Check Payment Plan Status PAID or NEW pplans cannot be referred to EDD
        if (pmtplanstatus.equalsIgnoreCase("NEW")) {
            isInspectionEddEligible = false;
            errorMessages.add("<p>" + "Active Payment Plan Exists" + "</p>");
        } else if (pmtplanstatus.equalsIgnoreCase("PAID")) {
            isInspectionEddEligible = false;
            errorMessages.add("<p>" + "Existing Payment Plan is Paid" + "</p>");
        }
        
        //Check Appeal Status, OPEN appeals cannot be referred to EDD
        if (isAppealOpen.equalsIgnoreCase("OPEN")) {
            isInspectionEddEligible = false;
            errorMessages.add("<p>" + "Appeal is currently OPEN" + "</p>");
        } 
        
        if (isInspectionEddEligible == false) {
            errorMessage = notEddEligible;
        } else if (isNRecordValid == false) {
            errorMessage = notValidNRecord;
        } else {
            navToEddNRecord = "newEddNRecord";
        }
        
        if (isInspectionEddEligible == false || isNRecordValid == false) {

            StringBuilder message = new StringBuilder("<html><body>");

            message.append("<p><b>Invalid Inspection for an N Record</b></p>");
            message.append("<p>" + errorMessage + "</p>");
            message.append("</body></html>");
            FacesMessage fm = new FacesMessage(message.toString());
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null, fm);
        }

        return navToEddNRecord;
    }
    
    /**
     * This method returns navigation. It will allow navigation to the ADF library
     * taskflow if R record Creation is valid.
     * 
     * @return  navigation  whether to navigate to allow R Record creation
     */
    public String newEddRRecordMenuItemNav() {
        boolean isRRecordValid = false;
        String errorMessage = "Inspection is not valid for an R Record";
        String navToEddRRecord = "";

        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding inspectionNumberAttr =
            (AttributeBinding)bindings.getControlBinding("InspectionNumber");

        if (inspectionNumberAttr.getInputValue() != null) {
            isRRecordValid = validRRecord(getDebtorNumber());
        }

        if (isRRecordValid == true) {
            navToEddRRecord = "newEddRRecord";
        } else if (isRRecordValid == false) {

            StringBuilder message = new StringBuilder("<html><body>");

            message.append("<p><b>Invalid Inspection for an R Record</b></p>");
            message.append("<p>" + errorMessage + "</p>");
            message.append("</body></html>");
            FacesMessage fm = new FacesMessage(message.toString());
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null, fm);
        }

        return navToEddRRecord;
    }
    
    /**
     * This method returns navigation. It will allow navigation to the ADF library
     * taskflow if O record Creation is valid.
     * 
     * @return  navigation  whether to navigate to allow O Record creation
     */
    public String newEddORecordMenuItemNav() {
        boolean isORecordValid = false;
        String errorMessage = "Active N or R Record does not exist";
        String navToEddORecord = "";

        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding inspectionNumberAttr =
            (AttributeBinding)bindings.getControlBinding("InspectionNumber");

        if (inspectionNumberAttr.getInputValue() != null) {
            isORecordValid =
                    validODRecord(inspectionNumberAttr.getInputValue().toString());
        }

        if (isORecordValid == true) {
            navToEddORecord = "newEddORecord";
        } else if (isORecordValid == false) {
            StringBuilder message = new StringBuilder("<html><body>");

            message.append("<p><b>Invalid Inspection for an O Record</b></p>");
            message.append("<p>" + errorMessage + "</p>");
            message.append("</body></html>");
            FacesMessage fm = new FacesMessage(message.toString());
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null, fm);
        }

        return navToEddORecord;
    }
    
    /**
     * This method returns navigation. It will allow navigation to the ADF library
     * taskflow if N record Creation is valid.
     * 
     * @return  navigation  whether to navigate to allow D Record creation
     */
    public String newEddDRecordMenuItemNav() {
        boolean isDRecordValid = false;
        String errorMessage = "Active N or R Record does not exist";
        String navToEddDRecord = "";

        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding inspectionNumberAttr =
            (AttributeBinding)bindings.getControlBinding("InspectionNumber");

        if (inspectionNumberAttr.getInputValue() != null) {
            isDRecordValid =
                    validODRecord(inspectionNumberAttr.getInputValue().toString());
        }

        if (isDRecordValid == true) {
            navToEddDRecord = "newEddDRecord";
        } else if (isDRecordValid == false) {
            StringBuilder message = new StringBuilder("<html><body>");

            message.append("<p><b>Invalid Inspection for a D Record</b></p>");
            message.append("<p>" + errorMessage + "</p>");
            message.append("</body></html>");
            FacesMessage fm = new FacesMessage(message.toString());
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null, fm);
        }

        return navToEddDRecord;
    }
}

