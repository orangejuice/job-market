/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybeans;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Reiner
 */
@Named(value = "navigationBean")
@ApplicationScoped
public class NavigationBean {


    public String nextPage() {
        return "page2";
    }

    
    public String userPage1() {
        return "page";
    }

    public String userPage2() {
        return "page";
    }
    
    
    /**
     * Creates a new instance of NavigationBean
     */
    public NavigationBean() {
    }

}
