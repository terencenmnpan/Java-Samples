package gov.ca.dir.acct.calosha.adf.master.viewcontroller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
//import javax.faces.context.ExternalContext;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.faces.validator.ValidatorException;

import javax.security.auth.Subject;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.view.rich.event.DialogEvent.Outcome;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.security.idm.IMException;
import oracle.security.idm.IdentityStore;
import oracle.security.idm.User;
import oracle.security.idm.UserProfile;
import oracle.security.jps.JpsContext;
import oracle.security.jps.JpsContextFactory;
import oracle.security.jps.JpsException;
import oracle.security.jps.service.idstore.IdentityStoreService;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import weblogic.security.URLCallbackHandler;
import weblogic.security.services.Authentication;

import weblogic.servlet.security.ServletAuthentication;

public class SecurityBean {
    private String _username;
    private String _password;
    private RichInputText _newPassword;
    private RichInputText _confirmPassword;
    public static final String DEFAULT_PASSWORD = "welcome1";

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getUsername() {
        return _username;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getPassword() {
        return _password;
    }
    
    public void set_newPassword(RichInputText _newPassword) {
        this._newPassword = _newPassword;
    }

    public RichInputText get_newPassword() {
        return _newPassword;
    }

    public void set_confirmPassword(RichInputText _confirmPassword) {
        this._confirmPassword = _confirmPassword;
    }

    public RichInputText get_confirmPassword() {
        return _confirmPassword;
    }


    /**
     * Login logic that initiaites user session upon successful credential validation. Also checks if default password has been updated.
     * @return
     * @throws LoginException
     */
    public String doLogin() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        AdfFacesContext.getCurrentInstance().getViewScope().put("var_username", _username);
        AdfFacesContext.getCurrentInstance().getViewScope().put("var_password", _password);
        //check if default password is not updated yet.
        if (_password.compareTo(DEFAULT_PASSWORD) == 0) {
            this.DisplayPopup("p1");
            //System.out.println("Password ok, proceeding to update password.");
        } else {
            try {
                HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
                processLogin(request, _username, _password);
                String loginUrl;
                loginUrl = "/adfAuthentication?success_url=/faces/mainScreen";
                //if everything is good, proceed to main application screen
                RequestDispatcher dispatcher = request.getRequestDispatcher(loginUrl);
                HttpServletResponse response = (HttpServletResponse)ctx.getExternalContext().getResponse();
                if (!response.isCommitted()) {
                    dispatcher.forward(request, response);
                    ctx.responseComplete();
                }
            } catch (FailedLoginException e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Username or Password", "Invalid Username or Password");
                ctx.addMessage(null, msg);
            } catch (LoginException e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Username or Password", "Invalid Username or Password");
                ctx.addMessage(null, msg);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error authenticating user.");
            }
        }
        return null;
    }


    /**
     * Processes user credentials for authentication.
     * @param request
     * @param username
     * @param password
     * @return
     * @throws LoginException
     * @throws FailedLoginException
     */
    private HttpServletRequest processLogin(HttpServletRequest request, String username, String password) throws LoginException, FailedLoginException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String un = username;
        byte[] pw = password.getBytes();
        Subject login;
        login = Authentication.login(new URLCallbackHandler(un, pw));
        ServletAuthentication.runAs(login, request);
        ServletAuthentication.generateNewSessionID(request);
        return request;
    }

    /**
     * Logic for logging ending session upon logout by user
     * @return
     */
    public String doLogout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String logoutUrl = ctx.getExternalContext().getRequestContextPath() + "/adfAuthentication?logout=true&end_url=/faces/appLogin.jspx";
        HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();

        try {
            ServletAuthentication.logout(request);
            ServletAuthentication.invalidateAll(request);
            ServletAuthentication.killCookie(request);
            ctx.getExternalContext().redirect(logoutUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ctx.responseComplete();
        return null;
    }

    /**
     * Displays Change Default Password Dialog UI
     * @param popupId
     */
    public void DisplayPopup(String popupId) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExtendedRenderKitService extRenderKitSrvc = Service.getRenderKitService(context, ExtendedRenderKitService.class);
        extRenderKitSrvc.addScript(context, "AdfPage.PAGE.findComponent(\"" + popupId + "\").show();");
    }



    /**
     * Event Listener for the Change Default Password Dialog UI
     * @param dialogEvent
     */
    public void updatePasswordDialogListener(DialogEvent dialogEvent) {
        Outcome outcome = dialogEvent.getOutcome();
        String newPassword = null;
        if (_newPassword.getValue() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password was empty, your password has not been updated.", null);
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            return;
        } else {
            newPassword = _newPassword.getValue().toString();
        }
        if (outcome == Outcome.ok && newPassword != null) {
            String username = (String)AdfFacesContext.getCurrentInstance().getViewScope().get("var_username");
            String password = (String)AdfFacesContext.getCurrentInstance().getViewScope().get("var_password");
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
            try {

                processLogin(request, username, password);
                ChangePasswordBean cpb = new ChangePasswordBean();
                cpb.changePassword(username, password, newPassword);
            } catch (LoginException e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Username or Password", "Invalid Username or Password");
                msg.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, msg);
            }

            doLogout();
        }
    }

    /**
     * Validator for the Confirm Password field in the Change Default Password UI
     * @param facesContext
     * @param uIComponent
     * @param object
     */
    public void confirmPassword(FacesContext facesContext, UIComponent uIComponent, Object object) {

    }

    /**
     * Listener if Update Password Dialog is canceled
     * @param popupCanceledEvent
     */
    public void updatePasswordPopupCanceledListener(PopupCanceledEvent popupCanceledEvent) {
        doLogout();
    }

    /**
     * Validator for Confirm Password field in the Change Default Password popup.
     * @param facesContext
     * @param uIComponent
     * @param object
     */
    public void confirmPasswordValidator(FacesContext facesContext, UIComponent uIComponent, Object object) {
        ChangePasswordBean cpb = new ChangePasswordBean();
        if (_newPassword.getValue() == null) {
            return;
        }
        String newPassword = _newPassword.getValue().toString();
        String confirmPassword = object.toString();
        if (newPassword.compareTo(confirmPassword) != 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords does not match. Please re-enter passwords.", null));
        }
    }
}
