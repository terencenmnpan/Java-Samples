package gov.ca.dir.acct.calosha.adf.usecase024.model.view;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 17 10:26:59 PDT 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ThirdPartyCollectionOViewRowImpl extends ViewRowImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ThirdPartyCollectionOViewRowImpl() {
    }

    /**
     * Gets ThirdPartyCollection entity object.
     * @return the ThirdPartyCollection
     */
    public EntityImpl getThirdPartyCollection() {
        return (EntityImpl)getEntity(0);
    }

    public Number getSequenceNumber(String sequenceName) {
        Number sequenceValue = new Number(0);
        if (sequenceName != null && !sequenceName.isEmpty()) {

            SequenceImpl sequenceImpl =
                new SequenceImpl(sequenceName, getDBTransaction());
            sequenceValue = sequenceImpl.getSequenceNumber();
        }
        return sequenceValue;
    }
}