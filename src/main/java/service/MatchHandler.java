package service;

import models.Match;

public class MatchHandler {
    private Match match;
    private int winnerNum = 0;

    public MatchHandler(Match m) {
        match = m;
    }

    public Match execute(String winnerName) {
        winnerNum = getWinnerNum(winnerName);
        if (winnerNum == 1) {
            match.setFirstPlayerGameScore(match.getFirstPlayerGameScore() + 1);
        } else {
            match.setSecondPlayerGameScore(match.getSecondPlayerGameScore() + 1);
        }
        recalculateSetScore();
        if (isMatchFinish()) {
            match.setWinnreName(winnerName);
        }
        return match;
    }

    private int getWinnerNum(String winnerName) {
        if (match.getFirstPlayerName().equals(winnerName)) {
            return 1;
        } else {
            return 2;
        }
    }

    private void recalculateSetScore() {
        if (isGameFinish()) {
            if (winnerNum == 1) {
                match.setFirstPlayerSetScore(match.getFirstPlayerSetScore() + 1);
                startNewGame();
            } else if (winnerNum == 2) {
                match.setSecondPlayerSetScore(match.getSecondPlayerSetScore() + 1);
                startNewGame();
            }
            recalculateMatchScore();
        }
    }

    private void recalculateMatchScore() {
        if (isSetFinish()) {
            if (winnerNum == 1) {
                match.setFirstPlayerMatchScore(match.getFirstPlayerMatchScore() + 1);
                startNewSet();
            } else if (winnerNum == 2) {
                match.setSecondPlayerMatchScore(match.getSecondPlayerMatchScore() + 1);
                startNewSet();
            }
        }

    }

    private boolean isGameFinish() {
        return (match.getFirstPlayerGameScore() >= 4 || match.getSecondPlayerGameScore() >= 4)
                && Math.abs(match.getFirstPlayerGameScore() - match.getSecondPlayerGameScore()) >= 2;
    }

    private boolean isSetFinish() {
        return (match.getFirstPlayerSetScore() >= 6 || match.getSecondPlayerSetScore() >= 6) &&
                Math.abs(match.getFirstPlayerSetScore() - match.getSecondPlayerSetScore()) >= 2;
    }

    private boolean isMatchFinish() {
        return (match.getFirstPlayerMatchScore() >= 2 || match.getSecondPlayerMatchScore() >= 2)
                && match.getFirstPlayerMatchScore() != match.getSecondPlayerMatchScore();
    }

    private void startNewGame() {
        match.setFirstPlayerGameScore(0);
        match.setSecondPlayerGameScore(0);
    }

    private void startNewSet() {
        match.setFirstPlayerSetScore(0);
        match.setSecondPlayerSetScore(0);
    }
}



