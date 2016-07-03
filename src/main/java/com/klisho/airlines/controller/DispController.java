package com.klisho.airlines.controller;

/**
 * Created by Ola-Mola on 16/06/16.
 */

import com.klisho.airlines.Employee;
import com.klisho.airlines.Flight;
import com.klisho.airlines.FlightAssignment;
import com.klisho.airlines.Profession;
import dao.interfaces.EmployeeDao;
import dao.interfaces.FlightAssignDao;
import dao.interfaces.FlightDao;
import org.joda.time.LocalTime;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet("/disp/")
public class DispController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AdminController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.debug("deGet Disp started:" + request.getRemoteAddr());

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ProdDB"); //JNDI

            Connection conn = ds.getConnection();
            loadData(request, response, conn);

            //let the jsp show the page results
            request.getRequestDispatcher("/disp/index.jsp").forward(request, response);
        } catch (NamingException ne) {
            throw new ServletException(ne);
        } catch (SQLException sqle) {
            logger.error("SQL exception:", sqle);
            throw new ServletException(sqle);
        }
    }

    private void loadData(HttpServletRequest request, HttpServletResponse response, Connection conn) throws ServletException, IOException, SQLException {
        try {
            EmployeeDao dao = new EmployeeDao(conn);

//                String cancelId = request.getParameter("delete");
//                if (cancelId != null && !cancelId.isEmpty()) {
//                    int cancelIdInt = Integer.parseInt(cancelId);
//                    dao.cancelFlight(cancelIdInt);
//                }

            Collection<Employee> employees = dao.getAllEmployees();
            request.setAttribute("employees", employees);

            Collection<Employee> pilots = dao.getAllEmployeesByProf(Profession.PILOT);
            request.setAttribute("pilots", pilots);

            Collection<Employee> radiops = dao.getAllEmployeesByProf(Profession.RADIOP);
            request.setAttribute("radiops", radiops);

            Collection<Employee> navigators = dao.getAllEmployeesByProf(Profession.NAVIGATOR);
            request.setAttribute("navigators", navigators);

            Collection<Employee> hostess = dao.getAllEmployeesByProf(Profession.HOSTESS);
            request.setAttribute("hostess", hostess);
//                Collection<Employee> pilots = dao.getAllEmployeesByProf(0);
//                request.setAttribute("pilots", pilots);
//
//                Collection<Employee> radiops = dao.getAllEmployeesByProf(1);
//                request.setAttribute("radiops", radiops);
//
//                Collection<Employee> navigators = dao.getAllEmployeesByProf(2);
//                request.setAttribute("navigators", navigators);
//
//                Collection<Employee> hostess = dao.getAllEmployeesByProf(3);
//                request.setAttribute("hostess", hostess);


            FlightDao flDao = new FlightDao(conn);
            List<Flight> flights = flDao.getAllFlights();
            request.setAttribute("flights", flights);

            FlightAssignDao assignDao = new FlightAssignDao(conn);
            List<FlightAssignment> assignments = assignDao.getAllFlightAssign();
            request.setAttribute("assignments", assignments);

        } finally {
            conn.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.debug("doPost Disp started:" + request.getRemoteAddr());

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ProdDB"); //JNDI

            Connection conn = ds.getConnection();
            try {
                        //FlightDao dao = new FlightDao(conn);
                FlightAssignDao flightAssignDao = new FlightAssignDao(conn);


                String flightId = request.getParameter("flights");
                String pilot = request.getParameter("employee");
                String radiop = request.getParameter("radiop");
                String navigator = request.getParameter("navigator");
                String hostess1 = request.getParameter("hostess1");
                String hostess2 = request.getParameter("hostess2");
                String hostess3 = request.getParameter("hostess3");

                if (flightId != null &&
                        pilot != null &&
                        radiop != null &&
                        navigator != null &&
                        hostess1 != null &&
                        hostess2 != null &&
                        hostess3 != null)
                {
                    logger.info("Creating new flightAssign");
                    flightAssignDao.createFlightAssign(
                            Integer.parseInt(flightId),
                            Integer.parseInt(pilot),
                            Integer.parseInt(radiop),
                            Integer.parseInt(navigator),
                            Integer.parseInt(hostess1),
                            Integer.parseInt(hostess2),
                            Integer.parseInt(hostess3));
                }

                loadData(request, response, conn);


                //let the jsp show the page results
                request.getRequestDispatcher("/disp/index.jsp").forward(request, response);
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

