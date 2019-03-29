/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;

import shoppingEJB.*;

/**
 *
 * @author reiner.dojen
 */
public class ShoppingCartServlet
        extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Shopping Cart</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=ShoppingCartServlet method=POST>");
        out.println("<input type=hidden name=action value=add>");
        out.println("<table>");
        out.println("<tr><td>Item</td><td>Quantity</td></tr>");
        out.println("<tr><td>PC:</td><td><input type=text name=quantityPC value=0></td></tr>");
        out.println("<tr><td>Monitor:</td><td><input type=text name=quantityMonitor value=0></td></tr>");
        out.println("<tr><td>Printer:</td><td><input type=text name=quantityPrinter value=0></td></tr>");
        out.println("<tr><td colspan=2><input type=submit></td></tr>");
        out.println("</table></form>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) 
                session.getAttribute("cart");
        if (shoppingCart == null) {

            try {
                System.out.println("request new bean\n");
                Context c = new InitialContext();
                shoppingCart = (ShoppingCart) c.lookup("java:global/03-ShoppingCartSFSB/03-ShoppingCartSFSB-ejb/ShoppingCartBean!shoppingEJB.ShoppingCart");
                session.setAttribute("cart", shoppingCart);
            } catch (NamingException ne) {
                throw new ServletException(ne);
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Shopping Cart</title>");
        out.println("</head>");
        out.println("<body>");

        String action = request.getParameter("action");
        if (action != null && action.equals("add")) {
            Integer pcQuantity = Integer.parseInt(request.getParameter("quantityPC"));
            shoppingCart.addItem("PC", pcQuantity);
            Integer monitorQuantity = Integer.parseInt(request.getParameter("quantityMonitor"));
            shoppingCart.addItem("Monitor", monitorQuantity);
            Integer printerQuantity = Integer.parseInt(request.getParameter("quantityPrinter"));
            shoppingCart.addItem("Printer", printerQuantity);
            out.println("<h2>The following items were added</h2>");
            out.println("<br>PC: " + pcQuantity);
            out.println("<br>Printer: " + printerQuantity);
            out.println("<br>Monitor: " + monitorQuantity);
            out.println("<h2>Your cart contains the following:</h2><br>");
            out.println(shoppingCart.getItemList());
            out.println("<h2><a href=ShoppingCartServlet>Add more Items</a></h2>");
            out.println("<form action=ShoppingCartServlet method=POST>");
            out.println("<input type=hidden name=action value=cancel>");
            out.println("<input type=submit value=Cancel>");
            out.println("</form><br>");
            out.println("<form action=ShoppingCartServlet method=POST>");
            out.println("<input type=hidden name=action value=checkout>");
            out.println("<input type=submit value=Checkout>");
            out.println("</form>");

        } else if (action != null && action.equals("cancel")) {
            out.println("<h2>Order cancelled</h2>");
            shoppingCart.cancel();
            session.removeAttribute("cart");
        } else if (action != null && action.equals("checkout")) {
            out.println("<h2>You checked out the following:</h2>");
            out.println(shoppingCart.checkout());
            session.removeAttribute("cart");
        }
        out.println("<h2><a href=\"/03-ShoppingCartSFSB-war/\">Back to start</a></h2>");
        out.println("</body>");
        out.println("</html>");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
