package gov.ca.dir.acct.calosha.adf.usecase011.view;

import java.util.Map;

import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;

import oracle.binding.BindingContainer;

public class ModifyPaymentValidationBean {


    private boolean disableReversalButton;
    private boolean disableNsfButton;
    private boolean disableRefundButton;
    private boolean disableNsfPaymentButton;
    private boolean disableTransferButton;
    
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
     * argument - name of request scope #{pageFlowScope.pageFlowScopeName}
     * returns String pageFlowScopeValue
     */
    private String getPageFlowScope(String pageFlowScopeName){
        ADFContext adfContext = ADFContext.getCurrent();
        Map pageFlowScope = adfContext.getPageFlowScope();
        String pageFlowScopeValue = (String) pageFlowScope.get(pageFlowScopeName);
        return pageFlowScopeValue;
    }
    
    //Helper Method to get bindings
    private BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public boolean isDisableReversalButton() {
        return disableReversalButton;
    }

    public void setDisableReversalButton(boolean disableReversalButton) {
        this.disableReversalButton = disableReversalButton;
    }

    public boolean isDisableNsfButton() {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        return disableNsfButton;
    }

    public void setDisableNsfButton(boolean disableNsfButton) {
        this.disableNsfButton = disableNsfButton;
    }

    public boolean isDisableRefundButton() {
        return disableRefundButton;
    }

    public void setDisableRefundButton(boolean disableRefundButton) {
        this.disableRefundButton = disableRefundButton;
    }

    public boolean isDisableNsfPaymentButton() {
        return disableNsfPaymentButton;
    }

    public void setDisableNsfPaymentButton(boolean disableNsfPaymentButton) {
        this.disableNsfPaymentButton = disableNsfPaymentButton;
    }

    public boolean isDisableTransferButton() {
        return disableTransferButton;
    }

    public void setDisableTransferButton(boolean disableTransferButton) {
        this.disableTransferButton = disableTransferButton;
    }
    
    
    
    
}
