package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.PlayerDao;

import java.io.IOException;

@WebServlet("/index")
public class StartServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayerDao playerDao = new PlayerDao();
        String firstPlayerName =playerDao.nameFormated(request.getParameter("firstPlayer")) ;
        String secondPlayerName = playerDao.nameFormated(request.getParameter("secondPlayer"));

        if (!playerDao.isCurrentPlayerExist(firstPlayerName)) {
            playerDao.crateNewPlayer(firstPlayerName);
            //log
            System.out.println("Player create" + firstPlayerName);
        }

        if (!playerDao.isCurrentPlayerExist(secondPlayerName)) {
            playerDao.crateNewPlayer(secondPlayerName);
            //log
            System.out.println("Player create" + secondPlayerName);
        }
//log
        System.out.println(playerDao.nameFormated(firstPlayerName) + ":" + playerDao.nameFormated(secondPlayerName));
        HttpSession session = request.getSession();


        session.setAttribute("first", firstPlayerName);
        session.setAttribute("second", secondPlayerName);
        response.sendRedirect(request.getContextPath() + "/match-score");
    }
}
