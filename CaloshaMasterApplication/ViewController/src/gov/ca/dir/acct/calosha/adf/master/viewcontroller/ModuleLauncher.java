package gov.ca.dir.acct.calosha.adf.master.viewcontroller;

import javax.faces.event.ActionEvent;

import oracle.ui.pattern.dynamicShell.TabContext;
//import gov.ca.dir.acct.calosha.adf.master.viewcontroller.dynamicWorkspaceRegion;
import oracle.adf.controller.TaskFlowId;

public class ModuleLauncher {
    //private dynamicWorkspaceRegion dwr = new dynamicWorkspaceRegion();
    private String taskFlowId;// = "/WEB-INF/test-module-1-tf.xml#test-module-1-tf";

    public void multipleInstanceActivity(ActionEvent actionEvent) {
        /**
    * Example method when called repeatedly, will open another instance as
    * oppose to selecting a previously opened tab instance. Note the boolean
    * to create another tab instance is set to true.
    */

        _launchActivity("A New Activity", "/WEB-INF/flows/new.xml#new", true);
    }

  public void launchManageLockboxInbound(ActionEvent actionEvent) {
    //UseCase0107
    _launchActivity("Manage Lockbox Inbound",
            "/WEB-INF/Manage-Locbox-tf.xml#Manage-Locbox-tf",
            true);
    
  }
  
  public void launchManageException(ActionEvent actionEvent){
    //UseCase0106
    _launchActivity("Manage Exception",
            "/WEB-INF/Manage-Exception-tf.xml#Manage-Exception-tf",
            true);
  }
  

    public void launchIMISInbound(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("DOSH Inbound",
                        "/WEB-INF/taskflow/IMISInbound-tf.xml#IMISInbound-tf",
                        true);

    }
    
    public void launchOSHABInbound(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("OSHAB Inbound",
                        "/WEB-INF/search-oshab-data-tf.xml#search-oshab-data-tf",
                        true);

    }
    
        
    public void launchCollectionReferral(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Collection Referral",
                        "/WEB-INF/referral-search-invoice-tf.xml#referral-search-invoice-tf",
                        true);

    }    
    public void launchSetupInvoice(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Setup Invoice",
                        "/WEB-INF/setup-invoice-tf.xml#setup-invoice-tf",
                        true);
        /*        
        _launchActivity("CALSTARS Batch",
                "/WEB-INF/taskflow/IMISInbound-tf.xml#CalstarsBatch",
                true);
        */
    }   
    
    public void launchSetupFTAInvoice(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Setup FTA Invoice",
                        "/WEB-INF/fta-setup-invoice-tf.xml#fta-setup-invoice-tf",
                        true);
        /*        
        _launchActivity("CALSTARS Batch",
                "/WEB-INF/taskflow/IMISInbound-tf.xml#CalstarsBatch",
                true);
        */
    }  
    
    public void launchSearchInvoice(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Search Invoice",
                        "/WEB-INF/search-invoice-tf.xml#search-invoice-tf",
                        true);
        /*        
        _launchActivity("CALSTARS Batch",
                "/WEB-INF/taskflow/IMISInbound-tf.xml#CalstarsBatch",
                true);
        */
    }  
    
    public void launchWriteoffSummary(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Writeoff Summary",
                        "/WEB-INF/writeoff-summary-tf.xml#writeoff-summary-tf",
                        true);
        /*        
        _launchActivity("CALSTARS Batch",
                "/WEB-INF/taskflow/IMISInbound-tf.xml#CalstarsBatch",
                true);
        */
    } 
    
    public void launchGenerateWriteoffRequest(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("Generate Writeoff Request",
                        "/WEB-INF/create-writeoff-summary-tf.xml#create-writeoff-summary-tf",
                        true); 
    }   
    
    
    public void launchManagePayment(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Manage Payment",
                        "/WEB-INF/manage-payments-tf.xml#manage-payments-tf",
                        true);
    }  
           
    public void manageIMISInbound(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Manage DOSH Inbound",
                        "/WEB-INF/search-imis-inbound-tf.xml#search-imis-inbound-tf",
                        true);
    }  

    public void launchManageEDDTransmittal(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Manage EDD Transmittal",
                        "/WEB-INF/manage-third-party-records-tf.xml#manage-third-party-records-tf",
                        true);
    }
    
    public void launchSearchNSFPayments(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Search NSF Payment",
                        "/WEB-INF/nsf-payment-tf.xml#nsf-payment-tf",
                        true);
    }  
    
    
    public void launchEndOfDayProcess(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Process End-Of-Day",
                        "/WEB-INF/end-of-day-process-tf.xml#end-of-day-process-tf",
                        true);
    }  
    
    
    public void launchSearchAppealInformation(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Search Appeal Information",
                        "/WEB-INF/search-appeal-data-tf.xml#search-appeal-data-tf",
                        true);
    }  
           
    public void launchAllocatePayment(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Allocate Payment",
                        "/WEB-INF/transfer-payment-tf.xml#transfer-payment-tf",
                        true);
    } 
    
    public void launchManagePaymentPlan(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Manage Payment Plan",
                        "/WEB-INF/SearchPaymentPlan-tf.xml#SearchPaymentPlan-tf",
                        false);
    } 
    
    public void launchCOERequest(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("COE Request",
                        "/WEB-INF/coe-request-letter-tf.xml#coe-request-letter-tf",
                        true);
    } 
    
        
    public void launchEditCourtFees(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Edit Court Fees",
                        "/WEB-INF/SearchCourt_tf.xml#SearchCourt_tf",
                        true);
    } 
    
        
    public void launchSearchCollectionStaff(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */

                _launchActivity("Search Collection Staff",
                        "/WEB-INF/search-collectionunit-staff-tf.xml#search-collectionunit-staff-tf",
                        true);
    }     
    /******Reports Caller Begin*****************/
   
    public void launchCaloReport01(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        System.out.println("01a invoked");
        _launchActivity("AR Setup Report",
                        "/WEB-INF/report01-definition.xml#report01-definition",
                        false); 
    }
    
    
    public void launchCaloReport01a(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("01A Report",
                        "/WEB-INF/calosha01a_report-tf.xml#calosha01a_report-tf",
                        false); 
    }
    
    public void launchCaloReport03(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("03 AR Ammendments/Adjustments",
                        "/WEB-INF/calosha03_report-tf.xml#calosha03_report-tf",
                        false); 
    }
    
    
    public void launchCaloReport04(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("04 Detail Cash Receipts - Check",
                        "/WEB-INF/calosha04_report-tf.xml#calosha04_report-tf",
                        false); 
    }
    
    
    public void launchCaloReport06(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("06 Detail Cash Receipts - CC",
                        "/WEB-INF/calosha06_report-tf.xml#calosha06_report-tf",
                        false); 
    }
    
    public void launchCaloReport08(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("08 Detail Cash Receipts - EFT",
                        "/WEB-INF/calosha08_report-tf.xml#calosha08_report-tf",
                        false); 
    }
    
    public void launchCaloReport10(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("10 Detail Cash Receipts - AT",
                        "/WEB-INF/calosha10_report-tf.xml#calosha10_report-tf",
                        false); 
    }
    
    public void launchCaloReport11(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("AR Aging Report",
                        "/WEB-INF/calosha11_report-tf.xml#calosha11_report-tf",
                        false); 
    }
    
    public void launchCaloReport12a(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("Delinquent Payment Plans",
                        "/WEB-INF/calosha12a_report-tf.xml#calosha12a_report-tf",
                        false); 
    }
    
    public void launchCaloReport12b(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("Open Payment Plans",
                        "/WEB-INF/calosha12b_report-tf.xml#calosha12b_report-tf",
                        false); 
    }
    
    public void launchCaloReport14(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("14 AR Paid-In-Full Report",
                        "/WEB-INF/calosha14_report-tf.xml#calosha14_report-tf",
                        false); 
    }
 
 
    public void launchCaloReport13(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("13 SCO Write-Off Report",
                        "/WEB-INF/calosha13_report-tf.xml#calosha13_report-tf",
                        false); 
    }
    
         
    public void launchCaloReport15(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("15 AR Appealed Report",
                        "/WEB-INF/calosha15_report-tf.xml#calosha15_report-tf",
                        false); 
    }

 
    public void launchCaloReport28(ActionEvent actionEvent) {
        /**
      * Example method to call a single instance task flow. Note the boolean
      * to create another tab instance is set to false. The taskflow ID is used
      * to track whether to create a new tab or select an existing one.
      */
        _launchActivity("IMIS Inbound Data",
                        "/WEB-INF/calosha_report28.xml#calosha_report28",
                        false); 
    }
     /******Reports Caller End*****************/
     
    public void closeCurrentActivity(ActionEvent actionEvent) {
        TabContext tabContext = TabContext.getCurrentInstance();
        int tabIndex = tabContext.getSelectedTabIndex();
        if (tabIndex != -1) {
            tabContext.removeTab(tabIndex);
        }
    }

    public void currentTabDirty(ActionEvent e) {
        /**
        * When called, marks the current tab "dirty". Only at the View level
        * is it possible to mark a tab dirty since the model level does not
        * track to which tab data belongs.
        */
        TabContext tabContext = TabContext.getCurrentInstance();
        tabContext.markCurrentTabDirty(true);
    }

    public void currentTabClean(ActionEvent e) {
        TabContext tabContext = TabContext.getCurrentInstance();
        tabContext.markCurrentTabDirty(false);
    }

    private void _launchActivity(String title, String taskflowId,
                                 boolean newTab) {
        try {
            if (newTab) {
                TabContext.getCurrentInstance().addTab(title, taskflowId);
            } else {
                TabContext.getCurrentInstance().addOrSelectTab(title,
                                                               taskflowId);
            }
        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }

    public void launchFirstReplaceNPlace(ActionEvent actionEvent) {
        TabContext tabContext = TabContext.getCurrentInstance();
        try {
            tabContext.setMainContent("/WEB-INF/flows/first.xml#first");
        } catch (TabContext.TabContentAreaDirtyException toe) {
            // TODO: warn user TabContext api needed for this use case.
        }
    }

    public void launchSecondReplaceNPlace(ActionEvent actionEvent) {
        TabContext tabContext = TabContext.getCurrentInstance();
        try {
            tabContext.setMainContent("/WEB-INF/flows/second.xml#second");
        } catch (TabContext.TabContentAreaDirtyException toe) {
            // TODO: warn user TabContext api needed for this use case.
        }
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }
}
