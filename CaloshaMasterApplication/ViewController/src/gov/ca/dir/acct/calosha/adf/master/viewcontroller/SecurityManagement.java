package gov.ca.dir.acct.calosha.adf.master.viewcontroller;
import java.security.Principal;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.share.security.SecurityContext;
//import oracle.adf.share.security.identitymanagement.UserProfile;

import oracle.jbo.JboException;

//import oracle.security.idm.ComplexSearchFilter;
import oracle.security.idm.IMException;
//import oracle.security.idm.Identity;
import oracle.security.idm.IdentityStore;


import oracle.security.idm.IdentityStoreFactory;
import oracle.security.idm.IdentityStoreFactoryBuilder;
import oracle.security.idm.ModProperty;
//import oracle.security.idm.ObjectNotFoundException;
//import oracle.security.idm.OperationNotSupportedException;
import oracle.security.idm.Role;
import oracle.security.idm.RoleManager;
import oracle.security.idm.RoleProfile;
//import oracle.security.idm.SearchFilter;
import oracle.security.idm.SearchParameters;
import oracle.security.idm.SearchResponse;
import oracle.security.idm.SimpleSearchFilter;
import oracle.security.idm.User;
import oracle.security.idm.UserProfile;
import oracle.security.idm.UserManager;
import oracle.security.idm.providers.oid.OIDIdentityStoreFactory;

/**
 *This class can be used to perform operation on OID using OPSS API
 * @author Ramandeep Nanda
 */

public class SecurityManagement {
    public static final ADFLogger OIDLogger =
        ADFLogger.createADFLogger(SecurityManagement.class);
    private static final ResourceBundle rb =
        ResourceBundle.getBundle("yourresourcebundlelocation");

    /**
     *
     * @return The store instance for OID store
     */
    public static IdentityStore getStoreInstance() {
        return SecurityManagement.IdentityStoreConfigurator.initializeDefaultStore();
    }

    public static IdentityStoreFactory getIdentityStoreFactory() {
        return SecurityManagement.IdentityStoreConfigurator.idStoreFactory;
    }

    /**
     * Returns the logged in User if using ADF security
     * @return The logged in User
     */
    public static String getLoggedInUser() {
        ADFContext ctxt = ADFContext.getCurrent();
        SecurityContext sctxt = ctxt.getSecurityContext();
        return sctxt.getUserName();
    }

    /**
     * This method returns the user profile of currently logged in user if using ADF security
     * @return oracle.adf.share.security.identitymanagement.UserProfile;
     */
    public static UserProfile getLoggedInUserProfile() {
        ADFContext ctxt = ADFContext.getCurrent();
        SecurityContext sctxt = ctxt.getSecurityContext();
        return (UserProfile)sctxt.getUserProfile();
    }

    /**
     * Assigns the specified role to the user
     * @param roleName the role to assign
     * @param userName the user to assign role to
     */
    public static void assignRoleToUser(String roleName, String userName) {
        String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();
        IdentityStore store = SecurityManagement.getStoreInstance();
        try {
            Role role =
                store.searchRole(IdentityStore.SEARCH_BY_NAME, roleName);
            User user = store.searchUser(userName);
            RoleManager rm = store.getRoleManager();
            if (!rm.isGranted(role, user.getPrincipal())) {
                rm.grantRole(role, user.getPrincipal());
            }

        } catch (IMException e) {
            OIDLogger.severe("Exception in " + methodName +
                             "Could not assign role [" + roleName +
                             "] to the user [" + userName + "] because of " +
                             e.getMessage() + " ", e);
            throw new JboException("Could not assign role [" + roleName +
                                   "] to the user [" + userName + "] due to " +
                                   e.getMessage());

        } finally {
            try {
                store.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }
    }

    /**
     * Assigns the specified role to the user
     * @param roleNames the roles to assign
     * @param userName the user to assign role to
     * @return the set of users who are assigned roles
     */
    public static Set assignRolesToUser(Set roleNames, String userName) {
        Set rolesAssigned = new HashSet();

        String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();
        IdentityStore store = SecurityManagement.getStoreInstance();
        String roleName = null;
        try {
            User user = store.searchUser(userName);
            Principal userPrincipal = user.getPrincipal();
            RoleManager rm = store.getRoleManager();
            Iterator it = roleNames.iterator();
            while (it.hasNext()) {
                roleName = (String)it.next();
                Role role =
                    store.searchRole(IdentityStore.SEARCH_BY_NAME, roleName);
                if (!rm.isGranted(role, user.getPrincipal())) {
                    rm.grantRole(role, userPrincipal);
                    rolesAssigned.add(roleName);
                }
            }
        } catch (IMException e) {

            OIDLogger.severe("Exception in " + methodName +
                             "Could not assign role [" + roleName +
                             "] to the user [" + userName + "] because of " +
                             e.getMessage() + " ", e);
            throw new JboException("Could not assign role [" + roleName +
                                   "] to the user [" + userName + "] due to " +
                                   e.getMessage());


        } finally {
            try {
                store.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }

        return rolesAssigned;
    }

    /**
     * Assigns the specified role to the user
     * @param roleName the role to assign
     * @param users the users to assign role to
     * @return The users who are assigned the role
     */
    public static Set assignRoleToUsers(String roleName, Map users) {
        Set usersAssigned = new HashSet();
        String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();
        IdentityStore store = SecurityManagement.getStoreInstance();
        Set entrySet = users.entrySet();
        Iterator it = entrySet.iterator();
        String userName = null;

        try {
            Role role =
                store.searchRole(IdentityStore.SEARCH_BY_NAME, roleName);
            RoleManager rm = store.getRoleManager();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                userName = (String)entry.getKey();
                User user = store.searchUser(userName);
                if (!rm.isGranted(role, user.getPrincipal())) {
                    rm.grantRole(role, user.getPrincipal());
                    usersAssigned.add(user);
                }
            }
        } catch (IMException e) {
            OIDLogger.severe("Exception in " + methodName +
                             "Could not assign role [" + roleName +
                             "] to the user [" + userName + "] because of " +
                             e.getMessage() + " ", e);
        } finally {
            try {
                store.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }
        return usersAssigned;
    }

    //revoke sample below It is similar to the above mentioned assign case so mentioning a sample operation

    /**
     * To remove the role from user
     * @param roleName the role to remove/ revoke
     * @param userName the user from which to revoke role
     */
    public static void removeRoleFromUser(String roleName, String userName) {
        String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();
        IdentityStore store = SecurityManagement.getStoreInstance();
        try {
            Role role =
                store.searchRole(IdentityStore.SEARCH_BY_NAME, roleName);

            User user = store.searchUser(userName);
            RoleManager rm = store.getRoleManager();
            if (rm.isGranted(role, user.getPrincipal())) {
                rm.revokeRole(role, user.getPrincipal());
            }
        } catch (IMException e) {
            OIDLogger.severe("Exception in " + methodName +
                             "Could not revoke role [" + roleName +
                             "] from the user [" + userName + "] because of " +
                             e.getMessage() + " ", e);
            throw new JboException("Could not remove role [" + roleName +
                                   "] from the user [" + userName +
                                   "] due to " + e.getMessage());

        } finally {
            try {
                store.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }
    }

    public static void dropUserWithRoles(String userId) {
        UserManager um = null;
        IdentityStore store = null;
        //User newUser = null;
        try {
            store = SecurityManagement.getStoreInstance();
            User user = store.searchUser(IdentityStore.SEARCH_BY_NAME, userId);
            um = store.getUserManager();
            if (user != null) {
                //drop user if already present
                um.dropUser(user);
                RoleManager rm = store.getRoleManager();
                Principal userPrincipal = user.getPrincipal();
                SearchResponse resp = rm.getGrantedRoles(userPrincipal, true);
                while (resp.hasNext()) {
                    rm.revokeRole((Role)resp.next(), user.getPrincipal());
                }
            }
        } catch (IMException e) {
            OIDLogger.info("[dropUser]" + e);

        } finally {
            try {
                store.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }
    }

    public static void dropUser(String userId) {
        UserManager um = null;
        //User newUser = null;
        IdentityStore store = null;

        try {
            store = SecurityManagement.getStoreInstance();
            User user = store.searchUser(IdentityStore.SEARCH_BY_NAME, userId);
            um = store.getUserManager();
            if (user != null) {
                //drop user if already present
                um.dropUser(user);
            }
        } catch (IMException e) {
            OIDLogger.info("[dropUser]" + e);

        } finally {
            try {
                store.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }
    }

    /**
     * Gets the userProfile of the logged in user if using ADF security
     * @param approverUser
     * @return
     */
    public static oracle.security.idm.UserProfile getUserProfile(String approverUser) {
        IdentityStore store = SecurityManagement.getStoreInstance();
        oracle.security.idm.UserProfile profile = null;
        try {
            User user = store.searchUser(approverUser);
            profile = user.getUserProfile();

        } catch (IMException e) {
            OIDLogger.info("Could not find user in OID with supplied Id" +
                           approverUser);
            throw new JboException(e.getMessage());
        } finally {
            try {
                store.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }
        return profile;
    }

    /**
     * Gets all the roles
     * @return
     */
    public static List getAllRoles() {
        String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();
        List returnList = new ArrayList();
        IdentityStore store = SecurityManagement.getStoreInstance();

        try {
            SimpleSearchFilter filter =
                store.getSimpleSearchFilter(RoleProfile.NAME,
                                            SimpleSearchFilter.TYPE_EQUAL,
                                            null);
            String wildCardChar = filter.getWildCardChar();
            // Here the default_role is a property this is just a placeholder can be  any pattern you want to search
            filter.setValue(wildCardChar + rb.getString("DEFAULT_ROLE") +
                            wildCardChar);
            SearchParameters parameters =
                new SearchParameters(filter, SearchParameters.SEARCH_ROLES_ONLY);
            SearchResponse resp =
                store.searchRoles(Role.SCOPE_ANY, parameters);
            while (resp.hasNext()) {
                Role role = (Role)resp.next();
                String tempRole = role.getPrincipal().getName();
                returnList.add(tempRole);
            }
            store.close();
        } catch (IMException e) {
            OIDLogger.severe("Exception in " + methodName + " " +
                             e.getMessage() + " ", e);
            throw new JboException(e.getMessage());
        } finally {
            try {
                store.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }

        return returnList;
    }

    /**
     * Fetches all the roles assigned to the user
     * @param userName
     * @return
     */
    public static List getAllUserRoles(String userName, String searchPath) {
        String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();
        List returnList = new ArrayList();
        IdentityStoreFactory storeFactory = SecurityManagement.getIdentityStoreFactory();
        IdentityStore store = null;
        String[] userSearchBases = { rb.getString(searchPath) };
        String[] groupSearchBases = { rb.getString("group.search.bases") };
        Hashtable storeEnv = new Hashtable();
        storeEnv.put(OIDIdentityStoreFactory.ADF_IM_SUBSCRIBER_NAME,
                     rb.getString("oidsubscribername"));
        storeEnv.put(OIDIdentityStoreFactory.RT_USER_SEARCH_BASES,
                     userSearchBases);
        storeEnv.put(OIDIdentityStoreFactory.RT_GROUP_SEARCH_BASES,
                     groupSearchBases);

        try {
            store = storeFactory.getIdentityStoreInstance(storeEnv);
            User user =
                store.searchUser(IdentityStore.SEARCH_BY_NAME, userName);
            RoleManager mgr = store.getRoleManager();
            SearchResponse resp =
                mgr.getGrantedRoles(user.getPrincipal(), false);
            while (resp.hasNext()) {
                String name = resp.next().getName();
                returnList.add(name);
            }

        } catch (IMException e) {
            OIDLogger.severe("Exception in " + methodName + " " +
                             e.getMessage() + " ", e);
            throw new JboException(e.getMessage());
        } finally {
            try {
                store.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }

        return returnList;
    }

    /**
     *Use to change the passoword for logged in user It uses ADF Security Context to get logged in user
     *
     **/
    public static void changePasswordForUser(String oldPassword,
                                             String newPassword,
                                             String userName) {
        String methodName =
            java.lang.Thread.currentThread().getStackTrace()[1].getMethodName();
        SecurityContext securityContext =
            ADFContext.getCurrent().getSecurityContext();
        String user = securityContext.getUserName();
        IdentityStore oidStore = null;
        oidStore = SecurityManagement.getStoreInstance();
        try {
            UserManager uMgr = oidStore.getUserManager();
            User authUser =
                uMgr.authenticateUser(user, oldPassword.toCharArray());

            if (authUser != null) {
                UserProfile profile = authUser.getUserProfile();

                profile.setPassword(oldPassword.toCharArray(),
                                    newPassword.toCharArray());
            }
        } catch (IMException e) {
            if (OIDLogger.isLoggable(Level.SEVERE)) {
                OIDLogger.severe("[" + methodName +
                                 "]  Exception occured due to " + e.getCause(),
                                 e);
            }
            throw new JboException(e.getMessage());
        } finally {
            try {
                oidStore.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }


    }

    /**
     * Resets the password for user
     * Commented out
     *
    public static void resetPasswordForUser(String userId) {
        String methodName =
            java.lang.Thread.currentThread().getStackTrace()[1].getMethodName();
        IdentityStore oidStore = SecurityManagement.getStoreInstance();
        User user = null;
        try {
            user = oidStore.searchUser(userId);
            if (user != null) {
                UserProfile userProfile = user.getUserProfile();
                List passwordValues =
                    userProfile.getProperty("userpassword").getValues();
                ModProperty prop =
                    new ModProperty("PASSWORD", passwordValues.get(0),
                                    ModProperty.REMOVE);
                userProfile.setProperty(prop);
                String randomPassword = "welcome1";
                userProfile.setPassword(null, randomPassword.toCharArray());
            }
        } catch (IMException e) {
            OIDLogger.severe("[" + methodName + "]" +
                             "Exception occured due to ", e);

        } finally {
            try {
                oidStore.close();
            } catch (IMException e) {
                OIDLogger.severe("Exception occured in closing store");
            }
        }

    }
*/

    /**
     * This nested private class is used for configuring and initializing a store instance
     * @author Ramandeep Nanda
     */
    private static final class IdentityStoreConfigurator {
        private static final IdentityStoreFactory idStoreFactory =
            initializeFactory();


        private static IdentityStoreFactory initializeFactory() {
            String methodName =
                Thread.currentThread().getStackTrace()[1].getMethodName();
            IdentityStoreFactoryBuilder builder =
                new IdentityStoreFactoryBuilder();
            IdentityStoreFactory oidFactory = null;
            try {
                Hashtable factEnv = new Hashtable();
                factEnv.put(OIDIdentityStoreFactory.ST_SECURITY_PRINCIPAL,
                            rb.getString("oidusername"));
                factEnv.put(OIDIdentityStoreFactory.ST_SECURITY_CREDENTIALS,
                            rb.getString("oiduserpassword"));
                factEnv.put(OIDIdentityStoreFactory.ST_SUBSCRIBER_NAME,
                            rb.getString("oidsubscribername"));
                factEnv.put(OIDIdentityStoreFactory.ST_LDAP_URL,
                            rb.getString("ldap.url"));
                factEnv.put(OIDIdentityStoreFactory.ST_USER_NAME_ATTR,
                            rb.getString("username.attr"));
                oidFactory =
                        builder.getIdentityStoreFactory("oracle.security.idm.providers.oid.OIDIdentityStoreFactory",
                                                        factEnv);
            } catch (IMException e) {
                OIDLogger.severe("Exception in " + methodName + " " +
                                 e.getMessage() + " ", e);
                //re throw exception here
            }
            return oidFactory;
        }

        private static IdentityStore initializeDefaultStore() {
            IdentityStore store = null;
            String methodName =
                Thread.currentThread().getStackTrace()[1].getMethodName();
            String[] userSearchBases = { rb.getString("user.search.bases") };
            String[] groupCreateBases = { rb.getString("group.search.bases") };
            String[] usercreate = { rb.getString("user.create.bases") };
            String[] groupClass = { rb.getString("GROUP_CLASSES") };
            Hashtable storeEnv = new Hashtable();
            storeEnv.put(OIDIdentityStoreFactory.ADF_IM_SUBSCRIBER_NAME,
                         rb.getString("oidsubscribername"));
            storeEnv.put(OIDIdentityStoreFactory.RT_USER_SEARCH_BASES,
                         userSearchBases);
            storeEnv.put(OIDIdentityStoreFactory.RT_GROUP_SEARCH_BASES,
                         groupCreateBases);
            storeEnv.put(OIDIdentityStoreFactory.RT_USER_CREATE_BASES,
                         usercreate);
            storeEnv.put(OIDIdentityStoreFactory.RT_USER_SELECTED_CREATEBASE,
                         rb.getString("user.create.bases"));
            storeEnv.put(OIDIdentityStoreFactory.RT_GROUP_OBJECT_CLASSES,
                         groupClass);
            try {
                store = SecurityManagement.IdentityStoreConfigurator.idStoreFactory.getIdentityStoreInstance(storeEnv);
            } catch (IMException e) {
                OIDLogger.severe("Exception in " + methodName + " " +
                                 e.getMessage() + " ", e);
                // re throw exception here

            }
            return store;

        }
    }
}


