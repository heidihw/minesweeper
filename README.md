# minesweeper.js

Plays a modified version of minesweeper.
- same as normal minesweeper:
  - 9x9 board with 10 mines randomly planted (beginner difficulty)
  - restart the game at any time
  - remaining mines and seconds passed timer display
  - smiley for indicating game state
- different:
  - when you hit a mine, the timer stops as normal,
    but you can keep going and solve the rest of the board
    - the mouse hold smiley is instead used
      to indicate "winning" after already losing

Takes inputs from keyboard and Arduino through serial comm

Keyboard:
- `r`: restart
- `e`: dig
- `f`: flag
- `WASD` or `arrow keys`: move

Arduino:
- 7 buttons on infrared remote:
```
      VOL+
 |<<  >||  >>|
  v   VOL-  ^
```
- corresponding to:
```
       up
left  down  right
dig   flag  restart
```

The IR receiver sends the value received on each relevant
button press, and otherwise a continuous stream to indicate
that no button was just pressed.
The red indicator light on the receiver blinks when a signal is received.

The IR receiver is connected to pin 9,
receives power through pin 10,
and is connected to ground at pin 11.

# Minesweeper.java

Plays minesweeper in the console.
