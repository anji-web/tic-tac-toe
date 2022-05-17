<template>
  <div>
    <div class="container-fluid">
      <div class="title">
          <h1>Tic Tac Toe</h1>
      </div>
      <div class="content">
          <b-card border-variant="success" class="text-center">
            <div class="main">
              <h4 v-if="flagCreate">Create game</h4>
              <div class="form">
                  <div class="row" v-if="flagCreate">
                    <div class="col-4"> 
                    </div>

                    <div class="col-4">
                      <b-form>
                        <b-form-group>
                        <label for="">Board name</label>
                        <b-form-input v-model="boardModel.boardName"></b-form-input>
                      </b-form-group>
                      </b-form>
                    </div>
                  </div>

                  <div>
                    <p>
                      {{boardModel.countBoard}}
                    </p>
                  </div>

                  <div class="row" v-if="flagCreate">
                    <div class="col-4"> 
                    </div>

                    <div class="col-4">
                      <b-form>
                        <b-form-group>
                        <label for="">Board size</label>
                        <div class="row">
                          <div class="col-3">
                            <b-form-checkbox value="3" v-model="boardModel.countBoard">
                              3x3
                            </b-form-checkbox>
                          </div>

                          <div class="col-3">
                            <b-form-checkbox value="5" v-model="boardModel.countBoard">
                              5 x 5
                            </b-form-checkbox>
                          </div>

                          <div class="col-3">
                            <b-form-checkbox value="9" v-model="boardModel.countBoard">
                              9 x 9
                            </b-form-checkbox>
                          </div>

                          <div class="col-3">
                            <b-form-checkbox value="11" v-model="boardModel.countBoard">
                              11 x 11
                            </b-form-checkbox>
                          </div>
                        </div>
                      </b-form-group>
                      </b-form>
                    </div>
                  </div>

                  <div class="row mt-4" v-if="flagCreate">
                    <div class="col-4"> 
                    </div>

                    <div class="col-4">
                      <button @click="createGame" class="btn btn-success">Create</button>
                    </div>
                  </div>


                  <div class="row mt-4" v-if="flagBoard">
                    <div class="col-4"> 
                    </div>

                    <div class="col-4">
                      <table class="table table-bordered" >
                          <tr v-for="(i, index) in boardMoves" :key="index">
                            <td v-for="(j, index2) in boardSize" :key="index2" @click="playGame(index, index2)">
                              <p style="font-size:100px;"> {{i[index2]}} </p>
                            </td>
                          </tr>
                      </table>
                    </div>
                  </div>
              </div>
            </div>
          </b-card>
      </div>
    </div>
  </div>
</template>


<style scoped>
.title {
 text-align: center;
 margin-top: 20px;
 font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif; 
}
.content {
  margin-top: 20px;
}
.main {
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.form {
  margin-top: 30px;
}
.table, tr,td {
  box-shadow: 2px 2px 2px 2px black;
  border: 3px solid black;
  cursor: pointer;
  height: 150px;
  border-spacing: 30px;
}
</style>

<script>
import Axios from "axios";
import Swal from "sweetalert2";
export default {
  name: 'IndexPage',
  data() {
    return {
      flagBoard : false,
      flagCreate : true,
      boardSize: [],
      boardModel : {
        boardName : "",
        countBoard : "",
      },
      playModel : {
          gameId : 0,
          objectRow : 0,
          objectColumn : 0,
          playerMove : 0
      },
      boardMoves : [],
      win : false,
      winnerPerson : "",
      gameId : 0
    }
  },
  mounted() {
  },
  methods: {
    async createGame(){
      this.win = false;
      let dto = this.boardModel;
      let url = "http://localhost:8080/game/create-game";

      Axios.post(url, dto).then(data => {
        if(data.data.status == "Start game"){
          this.playModel.gameId = data.data.gameId
          data.data.moves.forEach(i => {
            this.boardSize.push(i)
            this.boardMoves.push(i)
          })
          this.checkMoves();
          this.flagBoard = true
          this.flagCreate = false
        }
      })
    },
    async playGame(row, column){
      debugger
      this.playModel.playerMove = 1;
      this.playModel.objectRow = row
      this.playModel.objectColumn = column
      let dto = this.playModel;
      let url = "http://localhost:8080/game/play-game";

      Axios.post(url, dto).then(data => {
          this.boardMoves = [];
          data.data.moves.forEach(i => {
            this.boardMoves.push(i)
          })
          this.checkMoves()
          if (data.data.winner != null) {
            this.win = true
            this.winnerPerson = data.data.winner;
            Swal.fire({
              title: `${this.winnerPerson} , you can reset the game`,
              confirmButtonText: 'Reset',
            }).then(res => {
                    if (res.isConfirmed) {
                      this.reset();
                    }
                  })
          }
      })
    },
    checkMoves(){
         for (let i = 0; i < this.boardMoves.length; i++) {
            for (let j = 0; j < this.boardMoves[i].length; j++) {
              if (this.boardMoves[i][j] == 0) {
                this.boardMoves[i][j] = " ";
              }
              if (this.boardMoves[i][j] == 1) {
                this.boardMoves[i][j] = "X";
              }
              if (this.boardMoves[i][j] == 2) {
                this.boardMoves[i][j] = "O";
              }
            }
          }
    },
    reset(){
      let url = "http://localhost:8080/game/reset";
      Axios.get(url).then(i => {
        this.boardModel.boardName = i.boardName;
        this.boardModel.countBoard = i.countBoard;
        this.flagCreate = true;
        this.flagBoard = false;
        this.boardSize = []
        this.boardMoves = []
      })
    }
  },
}
</script>
