package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PlayerDao;

import java.io.IOException;

@WebServlet("/index")
public class StartServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstPlayerName = request.getParameter("firstPlayer");
        String secondPlayerName = request.getParameter("secondPlayer");
        PlayerDao playerDao = new PlayerDao();
        System.out.println(playerDao.nameFormated(firstPlayerName)+":"+playerDao.nameFormated(secondPlayerName));
        request.getRequestDispatcher("/match-score.jsp").forward(request, response);

    }
}
