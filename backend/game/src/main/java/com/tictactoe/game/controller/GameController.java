package com.tictactoe.game.controller;

import com.tictactoe.game.dto.request.BoardDto;
import com.tictactoe.game.dto.request.PlayDto;
import com.tictactoe.game.dto.response.BoardResponseDto;
import com.tictactoe.game.model.Board;
import com.tictactoe.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
@CrossOrigin("http://localhost:3000")
public class GameController {
  @Autowired
  private GameService gameService;

  @PostMapping("create-game")
  public ResponseEntity createGame(@RequestBody BoardDto boardDto){
    try {
      BoardResponseDto newBoard = gameService.createGameBoard(boardDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(newBoard);
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping("play-game")
  public ResponseEntity playGame(@RequestBody PlayDto playDto){
    try {
      BoardResponseDto newBoard = gameService.playGame(playDto);
      return ResponseEntity.status(HttpStatus.OK).body(newBoard);
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("reset")
  public ResponseEntity reset(){
    BoardResponseDto responseDto = gameService.reset();
    return ResponseEntity.ok(responseDto);
  }
}
