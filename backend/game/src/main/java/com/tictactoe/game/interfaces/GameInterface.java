package com.tictactoe.game.interfaces;

import com.tictactoe.game.dto.request.BoardDto;
import com.tictactoe.game.dto.request.PlayDto;
import com.tictactoe.game.dto.response.BoardResponseDto;

public interface GameInterface {
  public BoardResponseDto createBoard(BoardDto boardDto) throws Exception;

  public BoardResponseDto playTheGame(PlayDto playDto) throws Exception;

  public BoardResponseDto reset();

}
