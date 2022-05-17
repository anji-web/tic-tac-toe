package com.tictactoe.game.dto.request;

public class PlayDto {
  private Long gameId;
  private int objectRow;
  private int objectColumn;
  private int playerMove;

  public Long getGameId() {
    return gameId;
  }

  public void setGameId(Long gameId) {
    this.gameId = gameId;
  }

  public int getObjectRow() {
    return objectRow;
  }

  public void setObjectRow(int objectRow) {
    this.objectRow = objectRow;
  }

  public int getObjectColumn() {
    return objectColumn;
  }

  public void setObjectColumn(int objectColumn) {
    this.objectColumn = objectColumn;
  }

  public int getPlayerMove() {
    return playerMove;
  }

  public void setPlayerMove(int playerMove) {
    this.playerMove = playerMove;
  }
}
