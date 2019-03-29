/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myManagedBeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import shoppingEJB.ShoppingCart;

/**
 *
 * @author puser
 */
@Named(value = "shoppingBean")
@SessionScoped
public class ShoppingBean implements Serializable {

    private int quantityPC = 0;
    private int quantityPrinter = 0;
    private int quantityMonitor = 0;

    private String order = "";
    // use dependency injection to connect to
    // stateful session bean ShoppingCartBean
    // as the managed bean is session scoped
    // this is ok here 
    // in a servlet implementation you *must*
    // use JNDI lookup
    @EJB
    ShoppingCart cart;

    /**
     * Creates a new instance of ShoppingBean
     */
    public ShoppingBean() {
    }

    /**
     * Adds new items to the shopping cart - quantities are taken from instance
     * variables
     */
    public void addToBasket() {
        cart.addItem("PC", quantityPC);
        cart.addItem("Monitor", quantityMonitor);
        cart.addItem("Printer", quantityPrinter);
        // reset counter values
        quantityPC = quantityMonitor = quantityPrinter = 0;
    }

    /**
     * Remove items from the shopping cart - quantities are taken from instance
     * variables. Note: ShoppingCart SFSB takes care of too large values
     */
    public void removeFromBasket() {
        cart.removeItem("PC", quantityPC);
        cart.removeItem("Monitor", quantityMonitor);
        cart.removeItem("Printer", quantityPrinter);
        // reset counter values
        quantityPC = quantityMonitor = quantityPrinter = 0;
    }

    /**
     * Checkout shopping cart - only stores checked out items in instance
     * variable and removes releases SFSB cart
     *
     * @return Value "checkout" for auto navigation
     */
    public String checkout() {
        order = cart.getItemList().replace("<br>", "");
        cart.checkout();
        return "checkout";
    }

    /**
     * Cancels the order. Only releases SFSB cart
     *
     * @return Value "cancel" for auto navigation
     */
    public String cancel() {
        cart.cancel();
        return "cancel";
    }

    /**
     * Returns a list of items and their quantities that are currently in
     * shopping cart
     *
     * @return Items/quantities in shopping cart
     */
    public String getItemList() {
        String content = cart.getItemList();
        return content.replace("<br>", "");
    }

    /**
     * Destroys current session
     *
     * @return value "index"
     */
    public String index() {
        // invalidate session to remove reference 
        // to shopping cart - you want a new one next time to make
        // sure to receive a new SFSB
        FacesContext.getCurrentInstance().getExternalContext().
                invalidateSession();
        return "index";
    }

    /**
     * Getter for quantityPC
     * @return quantityPC
     */
    public int getQuantityPC() {
        return quantityPC;
    }

    /**
     * Setter for quantityPC
     * @param quantityPC new value for quantityPC
     */
    public void setQuantityPC(int quantityPC) {
        this.quantityPC = quantityPC;
    }

    /**
     * Getter for quantityPrinter
     * @return quantityPrinter
     */
    public int getQuantityPrinter() {
        return quantityPrinter;
    }

    /**
     * Setter for quantityPrinter
     * @param quantityPrinter new value for quantityPrinter
     */
    public void setQuantityPrinter(int quantityPrinter) {
        this.quantityPrinter = quantityPrinter;
    }

    /**
     * Getter for quantityMonitor
     * @return quantityMonitor
     */
    public int getQuantityMonitor() {
        return quantityMonitor;
    }

    /**
     * Setter for quantityMonitor
     * @param quantityMonitor  new value for quantityMonitor
     */
    public void setQuantityMonitor(int quantityMonitor) {
        this.quantityMonitor = quantityMonitor;
    }

    /**
     * Getter for order
     * @return order
     */
    public String getOrder() {
        return order;
    }

}
