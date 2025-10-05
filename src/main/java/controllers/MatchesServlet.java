package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.MatchWebDto;
import org.hibernate.SessionFactory;
import service.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private final int NotesInPalge = 15;
    MatchesHadler matchesHadler;
    MatchesDao matchesDao;


    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        matchesHadler = new MatchesHadler(sessionFactory);
        matchesDao = new MatchesDao(sessionFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MatchWebDto> matchesWebDto = matchesHadler.convertToMatchWebDto(matchesDao.getAllMatches());

        request.setAttribute("matchWebDto", matchesWebDto);
        request.getRequestDispatcher("/matches.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse respnse) throws ServletException, IOException {

        String foundPlayer = request.getParameter("pName");
        List<MatchWebDto> matchesWebDto = matchesHadler.convertToMatchWebDto(matchesDao.getMatchesByPlayer(foundPlayer));

        request.setAttribute("matchWebDto", matchesWebDto);
        request.getRequestDispatcher("/matches.jsp").forward(request, respnse);
    }
}
