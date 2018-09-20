package gov.ca.dir.acct.calosha.adf.master.viewcontroller;

//import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.ui.pattern.dynamicShell.TabContext;
import oracle.ui.pattern.dynamicShell.Tab;
//import oracle.ui.pattern.dynamicShell.Tabs;


//import oracle.ui.pattern.dynamicShell.Tabs;


public class TabManager {

    public void closeTab(ActionEvent action) {
        TabContext tc = new TabContext().getCurrentInstance();
        tc.removeTab(tc.getSelectedTabIndex());
    
    }

    public void closeAllTabs(ActionEvent action) {
        TabContext tc = new TabContext().getCurrentInstance();
        List<Tab> tabsToRemove = tc.getTabs();
        for (Tab t : tabsToRemove) {
            tc.removeTab(t.getIndex());
        }
    }

    public void closeOtherTabs(ActionEvent action) {
        TabContext tc = new TabContext().getCurrentInstance();
        List<Tab> tabsToRemove = tc.getTabs();
        for (Tab t : tabsToRemove) {
            if (t.getIndex() != tc.getSelectedTabIndex()) {
                tc.removeTab(t.getIndex());
            }
        }
    }


}
