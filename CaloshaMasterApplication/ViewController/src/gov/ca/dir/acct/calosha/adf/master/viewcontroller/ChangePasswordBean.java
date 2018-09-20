package gov.ca.dir.acct.calosha.adf.master.viewcontroller;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
//import javax.faces.event.ActionEvent;


import javax.faces.validator.ValidatorException;

import oracle.adf.share.ADFContext;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.security.idm.IMException;
import oracle.security.idm.IdentityStore;
//import oracle.security.idm.Property;
import oracle.security.idm.User;
//import oracle.security.idm.UserManager;
import oracle.security.idm.UserManager;
import oracle.security.idm.UserProfile;
import oracle.security.jps.JpsContext;
import oracle.security.jps.JpsContextFactory;
import oracle.security.jps.JpsException;
import oracle.security.jps.service.idstore.IdentityStoreService;

import utils.system;


public class ChangePasswordBean {
    private RichInputText _oldPassword;
    private RichInputText _newPassword;
    private RichInputText _confirmPassword;
    
    public static final String VALIDATION_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    

    public ChangePasswordBean() {
    }

    public void set_oldPassword(RichInputText _oldPassword) {
        this._oldPassword = _oldPassword;
    }

    public RichInputText get_oldPassword() {
        return _oldPassword;
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
     * Listener for Change Default Password popup
     * @param dialogEvent
     */
    public void okActionListener(DialogEvent dialogEvent) {
    //public void okActionListener() {
        String oldPassword = _oldPassword.getValue().toString();
        String newPassword = _newPassword.getValue().toString();
        changePassword(oldPassword, newPassword);
    }

    /**
     * Updates user password
     * @param oldPassword
     * @param newPassword
     */
    public void changePassword(String oldPassword, String newPassword) {
      
        System.out.println("DEBUG: IN CHANGE PASSWORD 1");
        FacesContext ctx = FacesContext.getCurrentInstance();
        JpsContext jc;
        try {
            jc = JpsContextFactory.getContextFactory().getContext();
            IdentityStoreService ids =
                jc.getServiceInstance(IdentityStoreService.class);
            IdentityStore is = ids.getIdmStore();
            //UserManager um = is.getUserManager();
            User user;
            user = is.searchUser(ADFContext.getCurrent().getSecurityContext().getUserName());
        //                (User)is.searchUser(ADFContext.getCurrent().getSecurityContext().getUserName());
            UserProfile userProfile = user.getUserProfile();
            //Property prop = userProfile.getProperty(userProfile.PASSWORD);
            System.out.println("DEBUG: userProfile name: "+userProfile.getUserName());
            System.out.println("DEBUG: userProfile password: "+userProfile.PASSWORD);
            userProfile.setPassword(oldPassword.toCharArray(),
                                    newPassword.toCharArray());
        } catch (JpsException e) {
            System.out.println("DEBUG: IN CHANGE PASSWORD 1 EXCEPTION 1: ");
            System.out.println("DEBUG: oldPassword: "+oldPassword);
            System.out.println("DEBUG: newPassword: "+newPassword);
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Password",
                                 "Invalid Password");
            ctx.addMessage(null, msg);
        } catch (IMException e) {
          System.out.println("DEBUG: IN CHANGE PASSWORD 1 EXCEPTION 2: ");
          System.out.println("DEBUG: oldPassword: "+oldPassword);
          System.out.println("DEBUG: newPassword: "+newPassword);
            System.out.println(e.getMessage() + ' ' +ADFContext.getCurrent().getSecurityContext().getUserName().toString());
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating Password",
                                 "Error updating Password");
            ctx.addMessage(null, msg);
        } catch (Exception e) {
          System.out.println("DEBUG: IN CHANGE PASSWORD 1 EXCEPTION 3: ");
          System.out.println("DEBUG: oldPassword: "+oldPassword);
          System.out.println("DEBUG: newPassword: "+newPassword);
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating Password",
                                 "Error updating Password");
            ctx.addMessage(null, msg);
        }
    }
    
    public void changePassword(String userName, String oldPassword, String newPassword){
      
        System.out.println("DEBUG: IN CHANGE PASSWORD 2");
        FacesContext ctx = FacesContext.getCurrentInstance();
        JpsContext jc;
        try {
            jc = JpsContextFactory.getContextFactory().getContext();
            IdentityStoreService ids =
                jc.getServiceInstance(IdentityStoreService.class);
            IdentityStore is = ids.getIdmStore();
            UserManager um = is.getUserManager();
            
            User user;
            user = is.searchUser(userName);
        //                (User)is.searchUser(ADFContext.getCurrent().getSecurityContext().getUserName());
            UserProfile userProfile = user.getUserProfile();
            //Property prop = userProfile.getProperty(userProfile.PASSWORD);
            System.out.println(userProfile.PASSWORD);
            userProfile.setPassword(oldPassword.toCharArray(),
                                    newPassword.toCharArray());
        } catch (JpsException e) {
          System.out.println("DEBUG: IN CHANGE PASSWORD 2 EXCEPTION 1: ");
          System.out.println("DEBUG: oldPassword: "+oldPassword);
          System.out.println("DEBUG: newPassword: "+newPassword);
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Password",
                                 "Invalid Password");
            ctx.addMessage(null, msg);
        } catch (IMException e) {
          System.out.println("DEBUG: IN CHANGE PASSWORD 2 EXCEPTION 2: ");
          System.out.println("DEBUG: oldPassword: "+oldPassword);
          System.out.println("DEBUG: newPassword: "+newPassword);
            System.out.println(e.getMessage() + ' ' +ADFContext.getCurrent().getSecurityContext().getUserName().toString());
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating Password",
                                 "Error updating Password");
            ctx.addMessage(null, msg);
        } catch (Exception e) {
          System.out.println("DEBUG: IN CHANGE PASSWORD 2 EXCEPTION 3: ");
          System.out.println("DEBUG: oldPassword: "+oldPassword);
          System.out.println("DEBUG: newPassword: "+newPassword);
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating Password",
                                 "Error updating Password");
            ctx.addMessage(null, msg);
        }
    }
    
    /**
     * Validates pasword format to ensure compliance to minimum password requirements.
     * @param facesContext
     * @param uIComponent
     * @param object
     */
    public void passwordValidator(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object != null) {
            String passwordString = object.toString();
            String regex = VALIDATION_PATTERN;
            Pattern pattern = Pattern.compile(regex);
            CharSequence stringInput = passwordString;
            Matcher matcher = pattern.matcher(stringInput);
            if (!matcher.matches()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Password, must be minimum of 8 characters with at least one uppercase char, one lowercase char, one number and one of special char (#?!@$%^&*-).",
                                                              null));
            }
        }
    }
    
    /**
     * Shows error message if newPassword is not same as confirmPassword. Can be called inside Validator Event Listener on a text field
     * @param newPassword
     * @param confirmPassword
     */
    public void confirmPassword(String newPassword, String confirmPassword){
        if (newPassword.compareTo(confirmPassword) !=0){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords does not match. Please re-enter passwords.",
                                                          null));
        }
    }
}
