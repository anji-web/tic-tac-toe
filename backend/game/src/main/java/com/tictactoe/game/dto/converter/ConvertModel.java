package com.tictactoe.game.dto.converter;

import com.tictactoe.game.dto.request.BoardDto;
import com.tictactoe.game.dto.response.BoardResponseDto;
import com.tictactoe.game.model.Board;

public class ConvertModel {
  public static BoardResponseDto convertCreateBoard(Board board){
    BoardResponseDto newBoard = new BoardResponseDto();
    newBoard.setGameId(board.getBoardId());
    newBoard.setBoardName(board.getBoardName());
    newBoard.setCountBoard(board.getRows());
    newBoard.setStatus(board.getStatus());
    int[][] movesDimension = new int[board.getRows()][board.getColumns()];
    newBoard.setMoves(movesDimension);
    return newBoard;
  }
}
