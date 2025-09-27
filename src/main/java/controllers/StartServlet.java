package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import service.MatchDao;
import service.PlayerDao;

import java.io.IOException;

@WebServlet("/index")
public class StartServlet extends HttpServlet {

    PlayerDao playerDao;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        playerDao = new PlayerDao(sessionFactory);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstPlayerName = playerDao.nameFormated(request.getParameter("firstPlayer"));
        String secondPlayerName = playerDao.nameFormated(request.getParameter("secondPlayer"));

        if (!playerDao.isCurrentPlayerExist(firstPlayerName)) {
            playerDao.crateNewPlayer(firstPlayerName);
        }

        if (!playerDao.isCurrentPlayerExist(secondPlayerName)) {
            playerDao.crateNewPlayer(secondPlayerName);
        }
//log

        response.sendRedirect(request.getContextPath() + "/match-score");
    }
}
