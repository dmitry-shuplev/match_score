package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.MatchProcessDto;
import service.MatchDao;
import service.MatchHandler;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    MatchProcessDto matchProcessDto;
    MatchHandler matchHandler;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        matchProcessDto = new MatchProcessDto();
        matchHandler = new MatchHandler(matchProcessDto);
        matchProcessDto.setFirstPlayerName((String) session.getAttribute("first"));
        matchProcessDto.setSecondPlayerName((String) session.getAttribute("second"));
        request.setAttribute("match", matchProcessDto);
        request.getRequestDispatcher("/match-score.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String winnerName = request.getParameter("playerName");
        matchProcessDto = matchHandler.execute(winnerName);
        request.setAttribute("match", matchProcessDto);
        if (matchProcessDto.getWinnreName().equals("none")) {
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);
        } else {
            MatchDao matchDao = new MatchDao();
            matchDao.saveMatch(matchDao.getMatchFromMatchProcessDto(matchProcessDto));
            request.getRequestDispatcher("/matches.jsp").forward(request, response);
        }
    }
}
