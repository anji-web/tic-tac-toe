package com.tictactoe.game.dto.request;

public class BoardDto {
  private Long gameId;
  private String boardName;
  private int countBoard;

  public Long getGameId() {
    return gameId;
  }

  public void setGameId(Long gameId) {
    this.gameId = gameId;
  }

  public String getBoardName() {
    return boardName;
  }

  public void setBoardName(String boardName) {
    this.boardName = boardName;
  }

  public int getCountBoard() {
    return countBoard;
  }

  public void setCountBoard(int countBoard) {
    this.countBoard = countBoard;
  }

}
