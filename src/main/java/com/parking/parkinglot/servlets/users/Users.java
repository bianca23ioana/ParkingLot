package com.parking.parkinglot.servlets.users;

import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.ejb.InvoiceBean;
import com.parking.parkinglot.ejb.UserBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DeclareRoles({"READ_USERS", "WRITE_USERS", "INVOICE"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_USERS"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"WRITE_USERS", "INVOICE"})})

@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {
    @Inject
    UserBean usersBean;
    @Inject
    InvoiceBean invoiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obținem lista tuturor utilizatorilor și o setăm ca atribut
        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);

        // Verificăm dacă utilizatorul este în grupul "INVOICING"
        if (request.isUserInRole("INVOICING")) {
            // Dacă utilizatorul are acces la facturi și există user IDs
            if (!invoiceBean.getUserIds().isEmpty()) {
                // Obținem numele utilizatorilor pe baza user IDs
                Collection<String> usernames = usersBean.findUsernamesByUserIds(invoiceBean.getUserIds());
                request.setAttribute("invoices", usernames);
            }
        } else {
            // Dacă utilizatorul nu are rolul "INVOICING", nu setăm datele despre facturi
            request.setAttribute("invoices", null);
        }

        // Redirectăm către JSP
        request.getRequestDispatcher("/WEB-INF/pages/users/users.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        String[] userIdsAsString = request.getParameterValues("user_ids");
        if (userIdsAsString != null) {
            List<Long> userIds = new ArrayList<>();
            for (String userIdAsString : userIdsAsString) {
                userIds.add(Long.parseLong(userIdAsString));
            }
            invoiceBean.getUserIds().addAll(userIds);
        }
        response.sendRedirect(request.getContextPath() + "/Users");
    }
}