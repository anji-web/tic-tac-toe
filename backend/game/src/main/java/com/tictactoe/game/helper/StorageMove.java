package com.tictactoe.game.helper;

import com.tictactoe.game.dto.response.BoardResponseDto;
import java.util.HashMap;
import java.util.Map;

public class StorageMove {
  private static Map<Long, BoardResponseDto> moves = new HashMap<>();
  private static StorageMove instance;

  public StorageMove() {
  }

  public static synchronized StorageMove getInstance(){
    if (instance == null){
      instance = new StorageMove();
    }
    return instance;
  }

  public Map<Long, BoardResponseDto> getMoves(){
    return moves;
  }

  public void setInstance(BoardResponseDto boardDto){
    moves.put(boardDto.getGameId(), boardDto );
  }

  public void resetInstance(){
    moves.clear();
  }
}
