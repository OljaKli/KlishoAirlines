package com.klisho.airlines.controller;

import com.klisho.airlines.Flight;
import dao.interfaces.FlightDao;
import org.joda.time.LocalTime;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ola-Mola on 12/06/16.
 */

@WebServlet("/admin/")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ProdDB"); //JNDI

            Connection conn = ds.getConnection();
            try {
                FlightDao dao = new FlightDao(conn);

                List<Flight> flights = dao.getAllFlights();
                request.setAttribute("flights", flights);

                //let the jsp show the page results
                request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
            } finally {
                conn.close();
            }
        } catch (NamingException ne) {
            throw new ServletException(ne);
        } catch (SQLException sqle) {
            throw new ServletException(sqle);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ProdDB"); //JNDI

            Connection conn = ds.getConnection();
            try {
                FlightDao dao = new FlightDao(conn);

                String flightNumber = request.getParameter("flightNumber");
                String from = request.getParameter("from");
                String to = request.getParameter("to");
                String departureTime = request.getParameter("departureTime");
                // TODO  распарсить галочки дней в List<Integer>, передать вместо EVERY_DAY
                // написать отдельный метод

                if (flightNumber != null &&
                        from != null &&
                        to != null &&
                        departureTime != null) {
                    dao.createFlight(flightNumber, from, to, LocalTime.parse(departureTime), Flight.EVERY_DAY);
                }

                List<Flight> flights = dao.getAllFlights();
                request.setAttribute("flights", flights);

                //let the jsp show the page results
                request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
            } finally {
                conn.close();
            }
        } catch (NamingException ne) {
            throw new ServletException(ne);
        } catch (SQLException sqle) {
            throw new ServletException(sqle);
        }
    }

}
