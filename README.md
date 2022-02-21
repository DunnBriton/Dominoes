# HW2

(CONSOLE VERSION)

How to Use:
- The console gives options on what a player can do.
- Select option that is within given values/range.
- If incorrect input or incorrect play, turn is reset.
- Win condition is for user and boneyard to have 0 dominoes.
- If there is no more move than both players lose.
- Look at top left and bottom right sides of tiles on the board
    once the edges rap.

Known Bugs/Unfinished features:
- There are no known bugs for the console version.
- Winner is currently determined by first to have zero dominoes
    when boneyard is empty. Does not take points into consideration.
- Currently, if you draw your turn ends. Need to 
    implement draw tell play or fail.
- Does not check if player can play before drawing.

(GUI VERSION)

How to Use:
- Player starts with first round.
- Click on domino in hand, draw domino, or declare unplayable for turn.
- Visually see how many in users hand and text tells info 
    about boneyard and computer domino amounts.

Known Bugs/Unfinished features:
- Winner is currently determined by first to have zero dominoes
  when boneyard is empty. Does not take points into consideration.
- Currently, if you draw your turn ends. Need to
  implement draw tell play or fail.
- Does not check if player can play before drawing.
- Dominoes do not line up correctly when played to board.
- Dominoes are sometimes unable to be played if rotation is not correct.
- Computer's plays are invisible. Invisible dominoes are still in logic 
    so the game does not break from this and game can continue if you 
    guess the correct match.
- Other bugs most likely exist since the code has not been fully tested.