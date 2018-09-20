package gov.ca.dir.acct.calosha.adf.usecase0102.view;

import java.sql.SQLException;

import java.util.Iterator;

import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.validator.ValidatorException;

import javax.faces.application.FacesMessage.Severity;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;
import oracle.adf.model.AttributeBinding;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adfdt.model.objects.IteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;
import oracle.jbo.server.SequenceImpl;

import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.context.RequestContext;

public class ProcessPaymentBean{


    private RichTable unbilledItemsTable;
    private RichInputText paymentAmountInputText;
    private RichInputText unappliedAmount;
    private RichTable billedItemsTable;
    private RichOutputText billedBalance;
    private RichOutputText unbilledBalance;
    private RichCommandToolbarButton commitAllocationButtonBinding;
    private RichPanelFormLayout paymentForm;
    private RichInputText depositSlipNumber;
    private RichInputDate transmittalDate;
    private RichInputComboboxListOfValues paymentType;
    private RichInputText transferAllocatedAmount;
    private RichInputText postJudgmentInterest;
    private RichInputText inspectionNumber;

    public ProcessPaymentBean() {
    }

 
    /* UI COMPONENT BINDINGS BEGIN */
    public void setUnbilledItemsTable(RichTable unbilledItemsTable) {
        this.unbilledItemsTable = unbilledItemsTable;
    }

    public RichTable getUnbilledItemsTable() {
        return unbilledItemsTable;
    }

    public void setPaymentAmountInputText(RichInputText paymentAmountInputText) {
        this.paymentAmountInputText = paymentAmountInputText;
    }

    public RichInputText getPaymentAmountInputText() {
        return paymentAmountInputText;
    }

    public void setTransferAllocatedAmount(RichInputText transferAllocatedAmount) {
        this.transferAllocatedAmount = transferAllocatedAmount;
    }

    public RichInputText getTransferAllocatedAmount() {
        return transferAllocatedAmount;
    }

    public void setPostJudgmentInterest(RichInputText postJudgmentInterest) {
        this.postJudgmentInterest = postJudgmentInterest;
    }

    public RichInputText getPostJudgmentInterest() {
        return postJudgmentInterest;
    }

    public void setInspectionNumber(RichInputText inspectionNumber) {
        this.inspectionNumber = inspectionNumber;
    }

    public RichInputText getInspectionNumber() {
        return inspectionNumber;
    }
    

    public void setDepositSlipNumber(RichInputText depositSlipNumber) {
        this.depositSlipNumber = depositSlipNumber;
    }

    public RichInputText getDepositSlipNumber() {
        return depositSlipNumber;
    }

    public void setTransmittalDate(RichInputDate transmittalDate) {
        this.transmittalDate = transmittalDate;
    }

    public RichInputDate getTransmittalDate() {
        return transmittalDate;
    }

    public void setPaymentType(RichInputComboboxListOfValues paymentType) {
        this.paymentType = paymentType;
    }

    public RichInputComboboxListOfValues getPaymentType() {
        return paymentType;
    }

    public void setUnappliedAmount(RichInputText unappliedAmount) {
        this.unappliedAmount = unappliedAmount;
    }

    public RichInputText getUnappliedAmount() {
        return unappliedAmount;
    }


    public void setBilledItemsTable(RichTable billedItemsTable) {
        this.billedItemsTable = billedItemsTable;
    }

    public RichTable getBilledItemsTable() {
        return billedItemsTable;
    }

    public void setBilledBalance(RichOutputText billedBalance) {
        this.billedBalance = billedBalance;
    }

    public RichOutputText getBilledBalance() {
        return billedBalance;
    }

    public void setUnbilledBalance(RichOutputText unbilledBalance) {
        this.unbilledBalance = unbilledBalance;
    }

    public RichOutputText getUnbilledBalance() {
        return unbilledBalance;
    }

    public void setCommitAllocationButtonBinding(RichCommandToolbarButton commitAllocationButtonBinding) {
        this.commitAllocationButtonBinding = commitAllocationButtonBinding;
    }

    public RichCommandToolbarButton getCommitAllocationButtonBinding() {
        return commitAllocationButtonBinding;
    }

    public void setPaymentForm(RichPanelFormLayout paymentForm) {
        this.paymentForm = paymentForm;
    }

    public RichPanelFormLayout getPaymentForm() {
        return paymentForm;
    }
 /* UI COMPONENT BINDINGS END */
 
 
    /**
     * Utility the shows Error Messages as pop-up on screen
     * @param message
     * @param severity
     */
    private void showErrorMessage(String message, Severity severity){
        FacesMessage fm = new FacesMessage(message.toString());
        fm.setSeverity(severity);
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.addMessage(null,fm); 
        //throw new ValidatorException(fm);
    }
    
    /**
     * Method to bound to the Commit button. 
     * @return
     */
    public String commitAllocationButton() {
        // Add event code here...
        System.out.println("Committing Payment Allocation..." + this.unappliedAmount.getValue());
        
        //Validate allocation to make sure that over allocation does not occur
        oracle.jbo.domain.Number balance = ((oracle.jbo.domain.Number)this.paymentAmountInputText.getValue()).subtract(this.calculateTotalAllocatedAmount()).subtract((oracle.jbo.domain.Number)this.unappliedAmount.getValue());
        if (balance.intValue() != 0){
            String msg = "Payment has been allocated incorrectly. Please review allocation.";
            showErrorMessage(msg,FacesMessage.SEVERITY_ERROR);
        }
        if (((Number)this.unappliedAmount.getValue()).compareTo(0)==-1){
            String msg = "Unapplied Amount cannot be negative.";
            showErrorMessage(msg,FacesMessage.SEVERITY_ERROR);          
        }
        
        BindingContext bindingContext = BindingContext.getCurrent();
        DCBindingContainer dcBindingContainer = (DCBindingContainer) bindingContext.getCurrentBindingsEntry();
        
        //run appmodule routine to create Deposit Transmittal records or delay for checks whose transmittal are done separately
        System.out.println("createPaymentAllocation is being invoked." );
        OperationBinding createDepositTransmittalOperationBinding = dcBindingContainer.getOperationBinding("ifCreateDepositTransmittal");
        
        //assign values to appmodule variables to be used by other sub-routines
        System.out.println("createPaymentAllocation commiting." );
        createDepositTransmittalOperationBinding.getParamsMap().put("depositSlipNumber", depositSlipNumber.getValue());
        createDepositTransmittalOperationBinding.getParamsMap().put("transmittalDate", transmittalDate.getValue());
        createDepositTransmittalOperationBinding.getParamsMap().put("paymentType", paymentType.getValue());
        createDepositTransmittalOperationBinding.execute();  
        
        //run process payment routines (populate payment allocation table)
        System.out.println("createPaymentAllocation is being invoked." );
        OperationBinding processPaymentOperationBinding = dcBindingContainer.getOperationBinding("processPayments");
        System.out.println("createPaymentAllocation commiting." );
        processPaymentOperationBinding.execute();      
        
        //commit all data changes
        System.out.println("createPaymentAllocation is being invoked." );
        OperationBinding commitOperationBinding = dcBindingContainer.getOperationBinding("commitProcessPayment");
        System.out.println("createPaymentAllocation commiting." );
        commitOperationBinding.execute();      
  
        return "commit";
    }

    /**
     *Utility method to refresh any give iterator
     * @param iter
     */
    private void refreshIterator(RowSetIterator iter){
        iter.reset();
        iter.closeRowSetIterator();
    }


    
    /**
     * Utility method that calculates the amount allocated to Billed or Unbilled items. 
     * @param iteratorBinding either Billed iterator or Unbilled iterator
     * @return totalAllocationAmount
     */
    private oracle.jbo.domain.Number calculateAllocatedAmount(DCIteratorBinding iteratorBinding){
    	//RowSetIterator rowIterator = iteratorBinding.getRowSetIterator();
    	ViewObject viewObject = iteratorBinding.getViewObject();
        RowSetIterator rowIterator = viewObject.createRowSetIterator(null);
        oracle.jbo.domain.Number _allocatedAmount = new oracle.jbo.domain.Number(0);
        //this.refreshIterator(rowIterator);
        rowIterator.reset();
        //for each items that have been allocated an amount, get amount and add to running sum
        while (rowIterator.hasNext()){
            Row next = rowIterator.next();
            try {
                Object amt = next.getAttribute("AllocatedAmount"); //the allocation amount field
                if (amt != null){//handle null values (ie null=0)
                    amt = new oracle.jbo.domain.Number(amt.toString());
                } 
                else{
                    amt = new oracle.jbo.domain.Number(0);
                }
                _allocatedAmount = _allocatedAmount.add((oracle.jbo.domain.Number)amt); //running sum of allocation amounts
            } catch (SQLException e) {
            }
        }
        rowIterator.closeRowSetIterator();
        
        return _allocatedAmount; //return amount
    }
 
 
   /**
     * Utility method that returns that total allocated amount including Billed, Unbilled and Post-Judgmnent Interest.
     * @return totalAllocatedAmount
     */
    private oracle.jbo.domain.Number calculateTotalAllocatedAmount(){
        BindingContext bindingContext = BindingContext.getCurrent();
        DCBindingContainer dcBindingContainer = (DCBindingContainer) bindingContext.getCurrentBindingsEntry();
        
        //get Post Judgment Interest amount
        oracle.jbo.domain.Number postJudgmentAmount = postJudgmentInterest.getValue() != null ? (oracle.jbo.domain.Number)postJudgmentInterest.getValue():new Number(0);
        
        //get Unbilled allocation amount
        DCIteratorBinding unbilledIteratorBinding;
        unbilledIteratorBinding = dcBindingContainer.findIteratorBinding("UnbilledItemsView1Iterator");
        
        //get Billed allocation amount
        DCIteratorBinding billedIteratorBinding;
        billedIteratorBinding = dcBindingContainer.findIteratorBinding("BilledItemsView1Iterator");
        
        //calculate and return total allocation
        return calculateAllocatedAmount(unbilledIteratorBinding).add(calculateAllocatedAmount(billedIteratorBinding)).add(postJudgmentAmount);
        
    }

   /**
     *Utility to calculate the remaining amount available for allocation (ie Unapplied Amount). The Unapplied Amount field will be updated with by this method.
     * @param deltaValue sum of old value and new value
     * @param oldValue old field value
     */
    private void calculateRemainingAmount(oracle.jbo.domain.Number deltaValue, oracle.jbo.domain.Number oldValue) {
        oracle.jbo.domain.Number totalAllocatedAmount = calculateTotalAllocatedAmount();
        oracle.jbo.domain.Number diff = deltaValue.subtract(deltaValue.subtract(oldValue));
        oracle.jbo.domain.Number _oldValue = oldValue != null ? oldValue : new oracle.jbo.domain.Number(0); //assign 0 if null
        //set unapplied based on calculated value
        if (this.getTransferAllocatedAmount().getValue() != null){//if processing transfer payments (ie module called from Transfer Function)
            this.getUnappliedAmount().setValue(((oracle.jbo.domain.Number)this.getTransferAllocatedAmount().getValue()).subtract(totalAllocatedAmount).subtract(deltaValue).add(_oldValue).add(_oldValue));
        }
        else{//else regular payments
            this.getUnappliedAmount().setValue(((oracle.jbo.domain.Number)this.getPaymentAmountInputText().getValue()).subtract(totalAllocatedAmount).subtract(deltaValue).add(_oldValue).add(_oldValue));
        }
    }

    /**
     * Listener method for the Unbilled AllocatedAmount field
     * @param valueChangeEvent
     */
    public void unbilledAllocatedAmountChangeListener(ValueChangeEvent valueChangeEvent) {
        BindingContext bindingContext = BindingContext.getCurrent();
        DCBindingContainer dcBindingContainer = (DCBindingContainer) bindingContext.getCurrentBindingsEntry();
        OperationBinding allocatePaymentAmount = dcBindingContainer.getOperationBinding("recalculateUnappliedAfterManualAllocation");
        allocatePaymentAmount.execute();  
        RequestContext.getCurrentInstance().addPartialTarget(getBilledItemsTable());    
        RequestContext.getCurrentInstance().addPartialTarget(getUnbilledItemsTable()); 
        oracle.jbo.domain.Number oldValue = valueChangeEvent.getOldValue() != null ? (oracle.jbo.domain.Number) valueChangeEvent.getOldValue(): new oracle.jbo.domain.Number(0) ;
        calculateRemainingAmount(oldValue.add((oracle.jbo.domain.Number) valueChangeEvent.getNewValue()), oldValue);
        
        
    }

    /**
     * Listener method for the Billed AllocatedAmount field
     * @param valueChangeEvent
     */    
    public void billedAllocatedAmountChangeListener(ValueChangeEvent valueChangeEvent) {        
        BindingContext bindingContext = BindingContext.getCurrent();
        DCBindingContainer dcBindingContainer = (DCBindingContainer) bindingContext.getCurrentBindingsEntry();
        OperationBinding allocatePaymentAmount = dcBindingContainer.getOperationBinding("recalculateUnappliedAfterManualAllocation");
        allocatePaymentAmount.execute();  
        RequestContext.getCurrentInstance().addPartialTarget(getBilledItemsTable());    
        RequestContext.getCurrentInstance().addPartialTarget(getUnbilledItemsTable()); 
        oracle.jbo.domain.Number oldValue = valueChangeEvent.getOldValue() != null ? (oracle.jbo.domain.Number) valueChangeEvent.getOldValue(): new oracle.jbo.domain.Number(0) ;
        oracle.jbo.domain.Number newValue = valueChangeEvent.getNewValue() != null ? (oracle.jbo.domain.Number) valueChangeEvent.getNewValue(): new oracle.jbo.domain.Number(0) ;
        calculateRemainingAmount(oldValue.add(newValue), oldValue);

    }

    /**
    *Listener method if Payment Amount field is updated. This triggers all payment allocation routines that will populate billed and unbilled allocation tables.
    * @param valueChangeEvent
    */
    public void paymentAmountChangeListener(ValueChangeEvent valueChangeEvent) {
       BindingContext bindingContext = BindingContext.getCurrent();
       DCBindingContainer dcBindingContainer = (DCBindingContainer) bindingContext.getCurrentBindingsEntry();
       System.out.println("Payment amount changed. Reallocating...");

       valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
       
       //prepare data for TPC referral in case the inspection has been referred to EDD (ie TPC Record Type P must be created)
       OperationBinding isTPCReferred = dcBindingContainer.getOperationBinding("isTPCReferred");
       isTPCReferred.getParamsMap().put("invoiceNumber",(String) this.getInspectionNumber().getValue());
       isTPCReferred.execute();  
       Boolean isReferred = (Boolean) isTPCReferred.getResult();
       
       String paymentTypeCode = (String) this.paymentType.getValue();
       
       //if isReferred then allocate to unapplied only and show warn dialog
       //if (isReferred && paymentTypeCode.compareToIgnoreCase("ACCOUNT_TRANSFER")!=0){
       //    this.unappliedAmount.setValue(this.paymentAmountInputText.getValue());
       //    String msg = "Invoice has been Referred to EDD. Payment Amount has been allocated to Unapplied by default.";
       //    showErrorMessage(msg,FacesMessage.SEVERITY_WARN);
       //}
       
       //perform initial allocations       
       System.out.println("allocatePaymentAmount is being invoked." );
       OperationBinding allocatePaymentAmount = dcBindingContainer.getOperationBinding("allocatePaymentAmount");
       allocatePaymentAmount.execute();  
       
       //TODO: need to refresh iterators for the table here..
       //ADFContext.getCurrent().getRequestScope().put("refreshBilledItemsNeeded", Boolean.TRUE); 
       //ADFContext.getCurrent().getRequestScope().put("refreshUnbilledItemsNeeded", Boolean.TRUE); 
       
       //DCIteratorBinding billedIteratorBinding = dcBindingContainer.findIteratorBinding("BilledItemsView1Iterator");
       //billedIteratorBinding.executeQuery();
       //display allocations in billed and unbilled tables
       RequestContext.getCurrentInstance().addPartialTarget(getBilledItemsTable());    
       RequestContext.getCurrentInstance().addPartialTarget(getUnbilledItemsTable());  
    
    }
    
    /**
     *Validator binding method for billed AllocatedAmount field
     * @param facesContext
     * @param uIComponent
     * @param object
     */
    public void billedAllocatedAmountValidator(FacesContext facesContext,
                                               UIComponent uIComponent,
                                               Object object) {
        allocatedAmountValidation(object);
    }

    /**
     *Validator binding method for unbilled AllocatedAmount field
     * @param facesContext
     * @param uIComponent
     * @param object
     */
    public void unbilledAllocatedAmountValidator(FacesContext facesContext,
                                               UIComponent uIComponent,
                                               Object object) {
        allocatedAmountValidation(object);
    }
    
   /**
     *Method used to validated fields called by the validator binding methods.
     * @param object
     */
    private void allocatedAmountValidation(Object object){
        oracle.jbo.domain.Number _billedBalance = (oracle.jbo.domain.Number) this.getUnbilledBalance().getValue();
        oracle.jbo.domain.Number _validatedAmount = (oracle.jbo.domain.Number) object;
        String msg;
        if (_billedBalance.compareTo(_validatedAmount) == -1){
            msg = "Allocation Amount cannot exceed Balance.";
            showErrorMessage(msg,FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        }
        
        if (this.getPaymentAmountInputText().getValue() == null){
            msg = "Payment Amount is blank.";
            showErrorMessage(msg,FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        }
    }


/**
     * Refresh Billed and Unbilled UI table components
     * @param actionEvent
     */
    public void refreshTables(ActionEvent actionEvent) {
        
        BindingContext bindingContext = BindingContext.getCurrent();
        DCBindingContainer dcBindingContainer = (DCBindingContainer) bindingContext.getCurrentBindingsEntry();
        System.out.println("refreshTables is being invoked." );

        DCIteratorBinding unappliedAmountIter = dcBindingContainer.findIteratorBinding("UnbilledItemsView1Iterator");
        unappliedAmountIter.executeQuery();
        DCIteratorBinding billedItemsView1Iter = dcBindingContainer.findIteratorBinding("BilledItemsView1Iterator");
        billedItemsView1Iter.executeQuery();
        
       
        RequestContext.getCurrentInstance().addPartialTarget(getBilledItemsTable()); 
    }


/**
     *Validator that checks if payment amount is a negative number.
     * @param facesContext
     * @param uIComponent
     * @param object
     */
    public void paymentAmountValidator(FacesContext facesContext,
                                       UIComponent uIComponent,
                                       Object object) {
        oracle.jbo.domain.Number _paymentAmount = (oracle.jbo.domain.Number) this.getPaymentAmountInputText().getValue();
        String msg;
        if (_paymentAmount.doubleValue() < 0){
            msg = "Payment Amount cannot be negative number.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        }

    }

/**
     *Set flag to indicate if Transaction is shared (set to true via taskflow if module is called by the Transfer Module)
     * @param share
     * @return
     */
    public Boolean setSharedTransaction(Boolean share){
        return share;
    }

    public void allocatePaymentAmount(ActionEvent actionEvent) {
        // Add event code here...
    }


/**
     * Calls a appmodule method that clears all payment allocation. This is used primarily for Transfer Function (if transaction is Cancelled)).
     * @return
     */
    public String undoPaymentAllocations() {
        BindingContext bindingContext = BindingContext.getCurrent();
        DCBindingContainer dcBindingContainer = (DCBindingContainer) bindingContext.getCurrentBindingsEntry();
        OperationBinding commitOperationBinding = dcBindingContainer.getOperationBinding("undoPaymentAllocations");
        System.out.println("undoing payment allocations" );
        commitOperationBinding.execute(); 
        return "cancel";
    }

/**
     *Listener method for the Payment Type field. This activate/deactivate fields appropriate to the payment type selected.
     * @param valueChangeEvent
     */
    public void paymentTypeChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue().toString().compareTo("VISA_MASTER_CREDIT_CARD") == 0 
            || valueChangeEvent.getNewValue().toString().compareTo("AMEX_CREDIT_CARD") == 0
            || valueChangeEvent.getNewValue().toString().compareTo("DISCOVER_CREDIT_CARD") == 0
            || valueChangeEvent.getNewValue().toString().compareTo("EFT") == 0
            || valueChangeEvent.getNewValue().toString().compareTo("ACCOUNT_TRANSFER") == 0
            || valueChangeEvent.getNewValue().toString().compareTo("USB_LOCK_BOX") == 0){
            this.getDepositSlipNumber().setDisabled(false); 
            this.getDepositSlipNumber().setRequired(true);
            this.getTransmittalDate().setDisabled(false);
            this.getTransmittalDate().setRequired(true);
        }
        else{
            this.getDepositSlipNumber().setDisabled(true); 
            this.getDepositSlipNumber().setRequired(false);
            this.getTransmittalDate().setDisabled(true);
            this.getDepositSlipNumber().setRequired(false);
        }
        
        //if (valueChangeEvent.getNewValue().toString().compareTo("ACCOUNT_TRANSFER") == 0){
        //    this.getPostJudgmentInterest().setDisabled(false);
        //    this.getPostJudgmentInterest().setRequired(true);
        //}
        //else{
        //   this.getPostJudgmentInterest().setDisabled(true);
        //    this.getPostJudgmentInterest().setRequired(false);
        //}

    }
    
    /**
     * Activate input fields for Account Transfer payment type
     */
    public void prepareInputFieldsForTPConSharedModule(){
       String paymentType = (String)this.getPaymentType().getValue();
        if (paymentType.compareTo("ACCOUNT_TRANSFER") == 0){
            this.getPostJudgmentInterest().setDisabled(false);
            this.getPostJudgmentInterest().setRequired(true);
        }
        else{
            this.getPostJudgmentInterest().setDisabled(true);
            this.getPostJudgmentInterest().setRequired(false);
        }
    }


    public void ATNumberValidation(FacesContext facesContext,
                                   UIComponent uIComponent, Object object) {
        String paymentType = (String)this.getPaymentType().getValue();
        if (paymentType.compareTo("ACCOUNT_TRANSFER") == 0){
            String ATNumber = (String) object;  
            String msg = null;
            if (!ATNumber.startsWith("AT")) {
                msg = "AT Nunber should start with prefix AT";  
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, "");  
                facesContext.addMessage(null, facesMsg);                        
            } else if (ATNumber.length() < 8) {
                msg = "AT Nunber should contain 6 digits following by AT.";  
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, "");  
                facesContext.addMessage(null, facesMsg);           
            } else {
                String checkingNumber = ATNumber.substring(2, ATNumber.length() - 2);
                String expression = "[-+]?[0-9]*\\.?[0-9]+$"; 
                CharSequence inputStr = checkingNumber;  
                Pattern pattern = Pattern.compile(expression);  
                Matcher matcher = pattern.matcher(inputStr);  
                if(!matcher.matches()){ 
                     msg = "AT Nunber should contain 6 numbers following by AT";
                     FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, "");  
                     facesContext.addMessage(null, facesMsg);            
                }
            }
        }
    }
}
