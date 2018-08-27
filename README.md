# TicTacToe2.0
Tic Tac Toe with 3 Player (one of them is a AI). You can set the board field size and also each player characters.

---

### How to run this code:
Compile this code, access to console is mandatory.

##### In Console ...

1. The name of first player has to be type in console (The name must be big than 2 characters, otherwise the same ask will be asked again)

2. The name of second player has to be type in console (The name must be big than 2 characters, otherwise the same ask will be asked again)

3. The Tic Tac Toe 2.0 will Randomize who's going to play first:

4. Following message will appears, indicating who should play (See Ref-1 for AI): 
      > Dale - Player Nº 2 :: this is your turn, please make your move...
   * The move is Line,Column ... ex: 0,1
   
5. After each turn, the game checks if the game has a winner. In case of draw, you have to fullfill the Board and then a draw message you will be displayed.


###### [Ref-1]: AI plays automaticaly. Every auto move from AI, it will be displayed and also a new board you displyed in console.

---

### Config File

The Tic Tac Toe 2.0 looking for 4 settings: 
* battlefieldSize
  - It should be a integer between 3 or 10, otherwise the default will set (default value = 3)
  
* playerOneMark
  - It should a single character, otherwise the default will set (default value = ✖)
  
* playerTwoMark
  - It should a single character, otherwise the default will set (default value = ●)
  
* playerAiMark
  - It should a single character, otherwise the default will set (default value = ▼)

Anything else will be ignored.

##### Syntax
`<keyword>:<value>` ex: `battlefieldSize:3`


---
Thank you for playing my game!

