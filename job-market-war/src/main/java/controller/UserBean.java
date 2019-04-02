package controller;

import constant.Constant;
import dao.UserDao;
import dao.UserFreelancerDao;
import dao.UserProviderDao;
import entity.User;
import entity.UserAdministrator;
import entity.UserFreelancer;
import entity.UserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HTTPUtil;
import util.MD5Util;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.io.Serializable;


@Named
@RequestScoped
public class UserBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(UserBean.class);

    @EJB
    private UserDao userDao;

    @EJB
    private UserFreelancerDao freelancerDao;

    @EJB
    private UserProviderDao providerDao;

    private String username;
    private String password;
    private String oldPassword;
    private String newPassword;
    private String email;
    private String skills;
    private User.UserRole userRole;
    private String selfIntroduction;

    //message
    private String loginMsg;
    private String profileMsg;


    public void loadCurrentUser(ComponentSystemEvent componentSystemEvent) {
        username = getCurrentUser().getUsername();
        email = getCurrentUser().getEmail();
        userRole = getCurrentUser().getRole();
        log.debug("load user profile page, role:{}", userRole);

        if (userRole == User.UserRole.Freelancer) {
            skills = ((UserFreelancer) getCurrentUser()).getSkills();
            selfIntroduction = ((UserFreelancer) getCurrentUser()).getDescriptionMessage();
        }
    }

    public void validateUsername(FacesContext ctx, UIComponent ui, Object o) throws ValidatorException {
        String name = o.toString();
        User u = userDao.findByUsername(name);
        if (u != null) {
            throw new ValidatorException(new FacesMessage("sorry", "username already existed."));
        }
    }

    public void validatePassword(FacesContext ctx, UIComponent ui, Object o) throws ValidatorException {
        String password = o.toString();
        String passwordMsg = "";
        if (password.length() < 8) {
            passwordMsg += "require at least 8 digital.";
        }
        if (!password.matches(".*([a-zA-Z]+).*") || !password.matches(".*([0-9]+).*")) {
            passwordMsg += "require digital and alphanumeric combination.";
            throw new ValidatorException(new FacesMessage("sorry", passwordMsg));
        }
    }

    public void validateEmail(FacesContext context, UIComponent uiComponent, Object o) throws ValidatorException {
        String email = o.toString();
        if (!email.matches("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")) {
            throw new ValidatorException(new FacesMessage("sorry", "please check your email address format"));
        }
    }

    public String register() {
        User user;

        if (userRole == User.UserRole.Administrator) {
            user = new UserAdministrator();
            user.setRole(User.UserRole.Administrator);
        } else if (userRole == User.UserRole.Provider) {
            user = new UserProvider();
            user.setRole(User.UserRole.Provider);
        } else {
            user = new UserFreelancer();
            user.setRole(User.UserRole.Freelancer);
        }

        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(MD5Util.md5(username + password));

        user = userDao.save(user);
        setCurrentUser(user);

        log.debug("new user registered, User={}, id={}", user, user.getId());

        return HTTPUtil.returnUrlBack();
    }

    public String login() {
        User user = userDao.findByUsername(username);
        if (user == null) {
            loginMsg = "username is not registered.";
            return HTTPUtil.toUrl("/login", false);
        }
        user = userDao.findByUsernameAndPassword(username, MD5Util.md5(username + password));
        if (user != null) {
            log.debug("user [{}] {} login successful", username, user.getClass());
            loginMsg = "";
            HTTPUtil.getSession().setAttribute(Constant.CURRENT_USER, user);
            return HTTPUtil.returnUrlBack();
        } else {
            log.debug("user [{}] login fail due to password [{}, encrypted: {}] error", username, password, MD5Util.md5(username + password));
            loginMsg = "password is not correct.";
            return HTTPUtil.toUrl("/login", false);
        }
    }

    public String logout() {
        HTTPUtil.getSession().setAttribute(Constant.CURRENT_USER, null);
        return HTTPUtil.returnUrlBack();
    }

    public void setCurrentUser(User user) {
        HTTPUtil.setCurrentUser(user);
    }

    public User getCurrentUser() {
        return HTTPUtil.getCurrentUser();
    }

    public String getEmailHash() {
        if (getCurrentUser() != null) {
            return MD5Util.md5(getCurrentUser().getEmail());
        }
        return "";
    }

    public String getHashByEmail(String email) {
        return MD5Util.md5(email);
    }

    public User getAdmin() {
        return userDao.findAdmin();
    }

    public void setLoginMsg(String loginMsg) {
        this.loginMsg = loginMsg;
    }

    public void update() {
        if (email != null) {
            if (email.equals(getCurrentUser().getEmail())) {
                profileMsg = "";
            } else if (email.isEmpty()) {
                profileMsg = "email cannot be empty. ";
            } else {
                try {
                    validateEmail(null, null, email);
                    getCurrentUser().setEmail(email);
                    profileMsg = "email address modified. ";
                } catch (Exception e) {
                    profileMsg = "email format error. ";
                }
            }
        }
        if (oldPassword != null && !oldPassword.isEmpty() && newPassword != null && !newPassword.isEmpty()) {
            if (!oldPassword.equals(getCurrentUser().getPassword())) {
                profileMsg += "old password is not matched.";
            } else {
                try {
                    validatePassword(null, null, newPassword);
                    getCurrentUser().setPassword(newPassword);
                    profileMsg += "password modified successful. ";
                } catch (Exception e) {
                    profileMsg += "password format is wrong. ";
                }
            }
        }
        if (skills != null) {
            if (!skills.equals(((UserFreelancer) getCurrentUser()).getSkills())) {
                ((UserFreelancer) getCurrentUser()).setSkills(skills);
                profileMsg += "skills modified successful. ";
            }
        }

        if (selfIntroduction != null) {
            if (!selfIntroduction.equals(((UserFreelancer) getCurrentUser()).getDescriptionMessage())) {
                ((UserFreelancer) getCurrentUser()).setDescriptionMessage(selfIntroduction);
                profileMsg += "self introduction modified successful. ";
            }
        }

        log.debug("user id:{} is requesting to change profile. message:{}", getCurrentUser().getId(), profileMsg);

        if (userRole == User.UserRole.Provider) {
            setCurrentUser(providerDao.update((UserProvider) getCurrentUser()));
        } else {
            setCurrentUser(freelancerDao.update((UserFreelancer) getCurrentUser()));
        }

    }

    public String getLoginMsg() {
        return loginMsg;
    }

    public String getProfileMsg() {
        String m = profileMsg;
        profileMsg = "";
        return m;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User.UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(User.UserRole userRole) {
        this.userRole = userRole;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
