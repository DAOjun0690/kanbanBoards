package util;

import model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthCheck {

    public final static int All = 0;
    public final static int Developer = 1;

    public static boolean isAuth(HttpServletRequest request, int roleCode) {
        boolean result = false;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            System.out.println("Not authenticated.");
        } else if (account.getRoleCode() == roleCode) {
            result = true;
        }
        return result;
    }
}
