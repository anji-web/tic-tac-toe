package com.tictactoe.game.helper;

import com.tictactoe.game.dto.converter.ConvertModel;
import com.tictactoe.game.dto.request.BoardDto;
import com.tictactoe.game.dto.request.PlayDto;
import com.tictactoe.game.dto.response.BoardResponseDto;
import com.tictactoe.game.interfaces.GameInterface;
import com.tictactoe.game.model.Board;
import com.tictactoe.game.repositories.BoardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameHelper implements GameInterface {
  @Autowired
  private BoardRepository boardRepository;

  @Override
  public BoardResponseDto createBoard(BoardDto boardDto) throws Exception {
    Board newBoard = new Board();
    if (boardDto.getBoardName() == null || boardDto.getCountBoard() == 0){
      throw new Exception("Board name or count board should not be empty");
    }
    newBoard.setBoardName(boardDto.getBoardName());
    newBoard.setRows(boardDto.getCountBoard());
    newBoard.setColumns(boardDto.getCountBoard());
    newBoard.setStatus(StaticHelper.START);
    boardRepository.save(newBoard);
    BoardResponseDto result = ConvertModel.convertCreateBoard(newBoard);
    StorageMove.getInstance().setInstance(result);
    return result;
  }

  @Override
  public BoardResponseDto playTheGame(PlayDto playDto) throws Exception {
    Board board = boardRepository.getById(playDto.getGameId());
    BoardResponseDto getBoard = StorageMove.getInstance().getMoves().get(board.getBoardId());
    getBoard.setStatus(StaticHelper.IN_PROGRESS);
    if (board.getStatus().equals(StaticHelper.FINISHED)){
      throw new Exception("Game already finished");
    }
    int[][] boardMoves = getBoard.getMoves();
    Random randomPath = new Random();
    for (int i = 0; i < boardMoves.length; i++){
      for (int j = 0; j < boardMoves[i].length; j++){

//        player turn
          boardMoves[playDto.getObjectRow()][playDto.getObjectColumn()] = playDto.getPlayerMove();

//        cpu turn
          int randomInt = randomPath.nextInt(boardMoves[i].length - 1) + 1;
          if (boardMoves[i][randomInt] == 0 && boardMoves[i][randomInt] != StaticHelper.PLAYER_MOVE){
              boardMoves[i][randomInt] = StaticHelper.CPU_MOVE;
              getBoard = checkWin(boardMoves, getBoard);
              return getBoard;
          }
      }

    }
    return getBoard;
  }

  @Override
  public BoardResponseDto reset() {
    BoardResponseDto responseDto = new BoardResponseDto();
    StorageMove.getInstance().resetInstance();
    return responseDto;
  }

  private BoardResponseDto checkWin(int[][] boardMoves, BoardResponseDto boardResponseDto){
    int counter = 0;
//    variabel vertical
    int[][] checkWinCombinationV = new int[boardMoves.length][boardMoves.length];

//    variabel horizontal
    int[][] checkWinCombinationH = new int[boardMoves.length][boardMoves.length];

//    variabel diagonal
    int[][] checkWinCombinationD = new int[2][boardMoves.length];

    List<Integer> allMoves = new ArrayList<>();

    for (int i = 0; i < boardMoves.length; i++){
      for (int j = 0; j < boardMoves[i].length; j++){
        allMoves.add(boardMoves[i][j]);
      }
    }

//    vertical loops
    verticalLoops(counter, checkWinCombinationV);

//    horizontal loops
    int temp;
    horizontalLoops(checkWinCombinationH);

//    diagonal loops
    diagonalLoops(checkWinCombinationV, checkWinCombinationD);

//    check vertical combination
    for (int i = 0; i < checkWinCombinationV.length; i++){
      int countResultPlayer1 = 0;
      int countResultCpu = 0;
      for (int j = 0; j < checkWinCombinationV[i].length; j++){
        if (allMoves.get(checkWinCombinationV[i][j]) == StaticHelper.PLAYER_MOVE){
          countResultPlayer1++;
            if (countResultPlayer1 == boardMoves.length){
                boardResponseDto.setStatus(StaticHelper.FINISHED);
                boardResponseDto.setWinner("Player 1 win");
                return boardResponseDto;
            }
        }
        if (allMoves.get(checkWinCombinationV[i][j]) == StaticHelper.CPU_MOVE){
          countResultCpu++;
            if (countResultCpu == boardMoves.length){
              boardResponseDto.setStatus(StaticHelper.FINISHED);
              boardResponseDto.setWinner("CPU win");
              return boardResponseDto;
            }
        }
      }
    }

    //    check horizontal combination
    for (int i = 0; i < checkWinCombinationH.length; i++){
      int countResultPlayer1 = 0;
      int countResultCpu = 0;
      for (int j = 0; j < checkWinCombinationH[i].length; j++){
        if (allMoves.get(checkWinCombinationH[i][j]) == StaticHelper.PLAYER_MOVE){
          countResultPlayer1++;
          if (countResultPlayer1 == boardMoves.length){
            boardResponseDto.setStatus(StaticHelper.FINISHED);
            boardResponseDto.setWinner("Player 1 win");
            return boardResponseDto;
          }
        }
        if (allMoves.get(checkWinCombinationH[i][j]) == StaticHelper.CPU_MOVE){
          countResultCpu++;
          if (countResultCpu == boardMoves.length){
            boardResponseDto.setStatus(StaticHelper.FINISHED);
            boardResponseDto.setWinner("CPU win");
            return boardResponseDto;
          }
        }
      }
    }

    //    check diagonal combination
    for (int i = 0; i < checkWinCombinationD.length; i++){
      int countResultPlayer1 = 0;
      int countResultCpu = 0;
      for (int j = 0; j < checkWinCombinationD[i].length; j++){
        if (allMoves.get(checkWinCombinationD[i][j]) == StaticHelper.PLAYER_MOVE){
          countResultPlayer1++;
          if (countResultPlayer1 == boardMoves.length){
            boardResponseDto.setStatus(StaticHelper.FINISHED);
            boardResponseDto.setWinner("Player 1 win");
            return boardResponseDto;
          }
        }
        if (allMoves.get(checkWinCombinationD[i][j]) == StaticHelper.CPU_MOVE){
          countResultCpu++;
          if (countResultCpu == boardMoves.length){
            boardResponseDto.setStatus(StaticHelper.FINISHED);
            boardResponseDto.setWinner("CPU win");
            return boardResponseDto;
          }
        }
      }
    }

    return boardResponseDto;
  }

  private void diagonalLoops(int[][] checkWinCombinationV, int[][] checkWinCombinationD) {
    int temp;
    for (int i = 0; i < checkWinCombinationD.length; i++){
      temp = i;
      for (int j = 0; j < checkWinCombinationD[i].length; j++){
        if (i == 0){
          if (j == 0){
            checkWinCombinationD[i][j] = checkWinCombinationV[i][j];
          }else {
            temp++;
            checkWinCombinationD[i][j] = checkWinCombinationV[temp][j];
          }
        }else {
          if (j == 0){
            checkWinCombinationD[i][j] = checkWinCombinationV[j][checkWinCombinationV.length-temp];
          }else {
            temp++;
            checkWinCombinationD[i][j] = checkWinCombinationV[j][checkWinCombinationV.length-temp];
          }
        }
      }
    }
  }

  private void horizontalLoops(int[][] checkWinCombinationH) {
    int temp;
    for (int i = 0; i < checkWinCombinationH.length; i++){
      temp = i;
      for (int j = 0; j < checkWinCombinationH[i].length; j++){
            if (j == 0){
              checkWinCombinationH[i][j] = temp;
            }else {
              checkWinCombinationH[i][j] = temp + checkWinCombinationH.length;
              temp = checkWinCombinationH[i][j];
            }
      }
    }
  }

  private void verticalLoops(int counter, int[][] checkWinCombinationV) {
    for (int i = 0; i < checkWinCombinationV.length; i++){
      for (int j = 0; j < checkWinCombinationV[i].length; j++){
          checkWinCombinationV[i][j] = counter;
          counter++;
      }
    }
  }
}
