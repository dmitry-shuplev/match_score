package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import service.MatchHandler;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    Match match;
    MatchHandler matchHandler;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        match = new Match();
        matchHandler = new MatchHandler(match);
        match.setFirstPlayerName("Иванов");
        match.setSecondPlayerName("Петров");
        request.setAttribute("match", match);
        request.getRequestDispatcher("/match-score.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String winnerName = request.getParameter("playerName");
        match = matchHandler.execute(winnerName);
        request.setAttribute("match", match);
        if (match.getWinnreName().equals("none")) {
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/matches.jsp").forward(request, response);
        }
    }
}
