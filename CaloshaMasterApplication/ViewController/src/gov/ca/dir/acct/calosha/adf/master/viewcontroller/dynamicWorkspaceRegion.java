package gov.ca.dir.acct.calosha.adf.master.viewcontroller;

import oracle.adf.controller.TaskFlowId;

public class dynamicWorkspaceRegion {
    private String taskFlowId;// = "/WEB-INF/test-module-1-tf.xml#test-module-1-tf";

    public dynamicWorkspaceRegion() {
    }

    public void setTaskFlowId(String _taskFlowId) {
        taskFlowId = _taskFlowId;
    }
    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }
}
