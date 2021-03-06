package gov.ca.dir.acct.calosha.adf.usecase0102.model.appmodule.common;

import java.util.Map;

import oracle.jbo.ApplicationModule;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jun 12 19:59:37 PDT 2014
// ---------------------------------------------------------------------
public interface ProcessPaymentAM extends ApplicationModule {

    Number getUnbilledItemIdSequence();

    Number getUnappliedUnbilledItemId();

    Number getPaymentIdSequence();


    void createRelatedParty(Number paymentId);


    void commitProcessPayment();

    Number getPaymentId();

    void createUnappliedPaymentAllocation();

    void setPaymentId(Number paymentId);

    void setIsPaymentExist(boolean isPaymentExist);

    void processPayments();

    void allocatePaymentAmount();

    void undoPaymentAllocations();

    void allocatePaymentAmount(Double paymentAmount);

    Number ifCreateDepositTransmittal(String depositSlipNumber,
                                      Date transmittalDate,
                                      String paymentType);


    void createPaymentAllocation(String allocationTypeCode, Number citationId,
                                 Number citationItemId, Number inspectionId,
                                 Number paymentId, Number unbilledItemId,
                                 Date allocationDate, Number allocatedAmount,
                                 String comments, String itemType,
                                 String referenceNumber);

    void setVarReferenceNumber(String varReferenceNumber);

    boolean isTPCReferred(String invoiceNumber);

    String getPaymentTypeCode();

    void recalculateUnappliedAfterManualAllocation();
}
