package gov.ca.dir.acct.calosha.adf.usecase011.view;

import oracle.adf.share.ADFContext;

import org.apache.myfaces.trinidad.model.RowKeySetImpl;

public class ExpandTreeTable {

    private RowKeySetImpl ps ;
    
    public void setPageFlowScopeExpandNodes() {
    if (ps == null) {
                    ps = new RowKeySetImpl(true);
                    //ps = new RowKeySetImpl(true);
                    //ADFContext.getCurrent().getPageFlowScope().put("expandedAllNodes", ps);
                }
    }
}
