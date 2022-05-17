package com.tictactoe.game.service;

import com.tictactoe.game.dto.request.BoardDto;
import com.tictactoe.game.dto.request.PlayDto;
import com.tictactoe.game.dto.response.BoardResponseDto;
import com.tictactoe.game.helper.GameHelper;
import com.tictactoe.game.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
  @Autowired
  private GameHelper gameHelper;

  public BoardResponseDto createGameBoard(BoardDto boardDto) throws Exception {
    BoardResponseDto newGame = gameHelper.createBoard(boardDto);
    return newGame;
  }

  public BoardResponseDto playGame(PlayDto playDto) throws Exception {
    BoardResponseDto result = gameHelper.playTheGame(playDto);
    return result;
  }

  public BoardResponseDto reset(){
    BoardResponseDto res = gameHelper.reset();
    return res;
  }
}
