package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.MatchWebDto;
import service.MatchHandler;
import service.MatchesDao;
import service.MatchesHadler;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MatchesDao matchesDao = new MatchesDao();
        MatchesHadler matchesHadler = new MatchesHadler();
        List<MatchWebDto> matchesWebDto = matchesHadler.convertToMatchWebDto(matchesDao.getAllMatches());

        request.getRequestDispatcher("/matches.jsp").forward(request, response);
    }
}
