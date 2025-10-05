package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.MatchProcessDto;
import service.MatchHandler;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    MatchProcessDto match;
    MatchHandler matchHandler;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        match = new MatchProcessDto();
        matchHandler = new MatchHandler(match);
        match.setFirstPlayerName((String)request.getAttribute("first"));
        match.setSecondPlayerName((String)request.getAttribute("second"));
              request.setAttribute("match", match);
        request.getRequestDispatcher("/match-score.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String winnerName = request.getParameter("pName");
        match = matchHandler.execute(winnerName);
        request.setAttribute("match", match);
        if (match.getWinnreName().equals("none")) {
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/matches.jsp").forward(request, response);
        }
    }
}
