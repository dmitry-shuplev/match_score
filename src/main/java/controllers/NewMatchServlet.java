package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import service.MatchHandler;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet  {
    Match match = new Match();
    MatchHandler matchHandler = new MatchHandler(match);

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

match.setFirstPlayerName("Иванов");
match.setSecondPlayerName("Петров");
       request.setAttribute("match", match);
       request.getRequestDispatcher("/new-match.jsp").forward(request, response);
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String winnerName = request.getParameter("playerName");
       if(winnerName.equals(match.getFirstPlayerName())){
           match.setFirstPlayerGameScore( match.getFirstPlayerGameScore()+15);
       }else{
           match.setSecondPlayerGameScore( match.getSecondPlayerGameScore()+15);
       }
       request.setAttribute("match", match);
       request.getRequestDispatcher("/new-match.jsp").forward(request, response);
   }
}
