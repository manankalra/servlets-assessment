package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import models.*;


public class ScheduleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        int starttime = Integer.parseInt(request.getParameter("starttime"));
        int endtime = Integer.parseInt(request.getParameter("endtime"));
        String trainerName = request.getParameter("trainerName");
        String subjectOne = request.getParameter("subjectOne");
        String subjectTwo = request.getParameter("subjectTwo");
        String[] days = request.getParameterValues("day");

        Schedule schedule = (Schedule) request.getSession(true).getAttribute("schedule");

        ScheduleDAO scheduleDAO = new ScheduleDAO(getServletContext().getInitParameter("jdbcURL"),
                getServletContext().getInitParameter("jdbcUsername"),
                getServletContext().getInitParameter("jdbcPassword"));
        TrainerDAO trainerDAO = new TrainerDAO(getServletContext().getInitParameter("jdbcURL"),
                getServletContext().getInitParameter("jdbcUsername"),
                getServletContext().getInitParameter("jdbcPassword"));

        if(schedule == null) {
            schedule = new Schedule();
        }

        if(days != null) {
            for(int i=0; i<days.length; i++) {
                String dayString = days[i];
                int day;
                if(dayString.equalsIgnoreCase("MON")) day = 0;
                else if(dayString.equalsIgnoreCase("TUE")) day = 1;
                else if(dayString.equalsIgnoreCase("WED")) day = 2;
                else if(dayString.equalsIgnoreCase("THU")) day = 3;
                else if(dayString.equalsIgnoreCase("FRI")) day = 4;
                else day = 5;
                Trainer trainer = new Trainer(trainerName, subjectOne, subjectTwo);
                Period period = new Period(title, starttime, endtime, day, trainer);
                try {
                    scheduleDAO.insertPeriod(period);
                    trainerDAO.insertTrainer(trainer);
                    /*
                    Schedule temp = scheduleDAO.getPeriods(starttime);
                    Trainer tempTrainer = trainerDAO.getTrainer(trainerName);
                    if(temp.getPeriods().get(day).getTitle() == null && tempTrainer == null) {
                        scheduleDAO.insertPeriod(period);
                        trainerDAO.insertTrainer(trainer);
                    } else {
                        PrintWriter out = new PrintWriter(System.out);
                        out.write("Slot isn't available!");
                        out.flush();
                        out.close();
                    }
                    */
                } catch (SQLException s) {}
                schedule.addPeriod(period);
            }
        }
        request.getSession().setAttribute("schedule", schedule);
        request.getSession().setAttribute("trainerName", trainerName);
        getServletContext().getRequestDispatcher("/Schedule.jsp").forward(request, response);
    }

}
