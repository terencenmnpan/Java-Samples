package gov.ca.dir.acct.calosha.adf.usecase010.view;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.DialogEvent.Outcome;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class ModifyPaymentBean {
    private boolean modifyButtonDisabled;
    private RichTable paymentTable;

    public ModifyPaymentBean() {
    }


    public boolean isModifyButtonDisabled() {

        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        String paymentTypeCode = bindings.getControlBinding("PaymentTypeCode").toString();
        
        
        return modifyButtonDisabled;
    }

    public void setModifyButtonDisabled(boolean modifyButtonDisabled) {
        this.modifyButtonDisabled = modifyButtonDisabled;
    }

    public void setPaymentTable(RichTable paymentTable) {
        this.paymentTable = paymentTable;
    }

    public RichTable getPaymentTable() {
        return paymentTable;
    }
    

    public void viewNotesDialogListener (DialogEvent dialogEvent){
        Outcome outcome = dialogEvent.getOutcome();
        if(outcome == Outcome.ok){

            BindingContainer bindings =  BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding operationBinding =
                bindings.getOperationBinding("Commit");

            Object result = operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                System.out.println(operationBinding.getErrors().toString());
            //handle error
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getPaymentTable());
        }
    }
}
