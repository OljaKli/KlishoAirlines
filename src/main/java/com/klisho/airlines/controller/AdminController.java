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
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Ola-Mola on 12/06/16.
 */

@WebServlet("/admin/")
public class AdminController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AdminController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        logger.info("deGet():" + request.getRemoteAddr());

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ProdDB"); //JNDI

            Connection conn = ds.getConnection();
            try {
                FlightDao dao = new FlightDao(conn);

                String cancelId = request.getParameter("delete");
                if (cancelId != null && !cancelId.isEmpty()) {
                    int cancelIdInt = Integer.parseInt(cancelId);
                    dao.cancelFlight(cancelIdInt);
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
            logger.error("SQL exception:", sqle);
            throw new ServletException(sqle);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.debug("doPost():" + request.getRemoteAddr());

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
                // написать отдельный метод, а мб и нет

               List<Integer> daysOfWeek = new ArrayList<>();

                if(request.getParameter("chkMon") != null) {
                daysOfWeek.add(1);    }

                if(request.getParameter("chkTue") != null) {
                    daysOfWeek.add(2);    }

                if(request.getParameter("chkWed") != null) {
                    daysOfWeek.add(3);    }

                if(request.getParameter("chkThu") != null) {
                    daysOfWeek.add(4);    }

                if(request.getParameter("chkFri") != null) {
                    daysOfWeek.add(5);    }

                if(request.getParameter("chkSat") != null) {
                    daysOfWeek.add(6);    }

                if(request.getParameter("chkSun") != null) {
                    daysOfWeek.add(7);    }


                if (flightNumber != null &&
                        from != null &&
                        to != null &&
                        departureTime != null) {
                    logger.info("Creating new flight: " + flightNumber);
                    dao.createFlight(flightNumber, from, to, LocalTime.parse(departureTime), daysOfWeek);

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
            logger.error("SQL exception:", sqle);
            throw new ServletException(sqle);
        }
    }

}
