package gov.ca.dir.acct.calosha.adf.usecase011.model.appmodule;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 04 08:04:11 PDT 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ModifyPaymentAMImpl extends ApplicationModuleImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ModifyPaymentAMImpl() {
    }

    /**
     * Container's getter for PaymentView1.
     * @return PaymentView1
     */
    public ViewObjectImpl getPaymentView1() {
        return (ViewObjectImpl)findViewObject("PaymentView1");
    }

    /**
     * Container's getter for DepositTransmittalView1.
     * @return DepositTransmittalView1
     */
    public ViewObjectImpl getDepositTransmittalView1() {
        return (ViewObjectImpl)findViewObject("DepositTransmittalView1");
    }

    /**
     * Container's getter for PaymentAllocationInspectionView1.
     * @return PaymentAllocationInspectionView1
     */
    public ViewObjectImpl getPaymentAllocationInspectionView1() {
        return (ViewObjectImpl)findViewObject("PaymentAllocationInspectionView1");
    }


    /**
     * Container's getter for PaymentAllocationView1.
     * @return PaymentAllocationView1
     */
    public ViewObjectImpl getPaymentAllocationView1() {
        return (ViewObjectImpl)findViewObject("PaymentAllocationView1");
    }

    /**
     * Container's getter for PaymentDepositTransmittalLink1.
     * @return PaymentDepositTransmittalLink1
     */
    public ViewLinkImpl getPaymentDepositTransmittalLink1() {
        return (ViewLinkImpl)findViewLink("PaymentDepositTransmittalLink1");
    }

    /**
     * Container's getter for PaymentPaymentAllocationPaymentIdLink1.
     * @return PaymentPaymentAllocationPaymentIdLink1
     */
    public ViewLinkImpl getPaymentPaymentAllocationPaymentIdLink1() {
        return (ViewLinkImpl)findViewLink("PaymentPaymentAllocationPaymentIdLink1");
    }


    /**
     * Container's getter for PaymentPaymentAllocationLink1.
     * @return PaymentPaymentAllocationLink1
     */
    public ViewLinkImpl getPaymentPaymentAllocationLink1() {
        return (ViewLinkImpl)findViewLink("PaymentPaymentAllocationLink1");
    }


    /**
     * Container's getter for CitationPaymentIdView1.
     * @return CitationPaymentIdView1
     */
    public ViewObjectImpl getCitationPaymentIdView1() {
        return (ViewObjectImpl)findViewObject("CitationPaymentIdView1");
    }

    /**
     * Container's getter for PaInspectCitationPaymentIdLink1.
     * @return PaInspectCitationPaymentIdLink1
     */
    public ViewLinkImpl getPaInspectCitationPaymentIdLink1() {
        return (ViewLinkImpl)findViewLink("PaInspectCitationPaymentIdLink1");
    }


    /**
     * Container's getter for CitationItemPaymentIdView1.
     * @return CitationItemPaymentIdView1
     */
    public ViewObjectImpl getCitationItemPaymentIdView1() {
        return (ViewObjectImpl)findViewObject("CitationItemPaymentIdView1");
    }

    /**
     * Container's getter for CitationPayIdCitationItemPayIdLink1.
     * @return CitationPayIdCitationItemPayIdLink1
     */
    public ViewLinkImpl getCitationPayIdCitationItemPayIdLink1() {
        return (ViewLinkImpl)findViewLink("CitationPayIdCitationItemPayIdLink1");
    }
}