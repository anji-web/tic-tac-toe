package com.tictactoe.game.dto.response;

public class BoardResponseDto {
  private Long gameId;
  private String boardName;
  private int countBoard;
  private int rows;
  private int columns;
  private String status;
  private String winner;
  private int[][] moves;

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

  public int[][] getMoves() {
    return moves;
  }

  public void setMoves(int[][] moves) {
    this.moves = moves;
  }

  public int getRows() {
    return rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public int getColumns() {
    return columns;
  }

  public void setColumns(int columns) {
    this.columns = columns;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getWinner() {
    return winner;
  }

  public void setWinner(String winner) {
    this.winner = winner;
  }
}
