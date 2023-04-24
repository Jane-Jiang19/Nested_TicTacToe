# Nested_TicTacToe
This is a variation of the classic game of tic-tac-toe, where 2 players take turns
putting X's and O's in a 3x3 grid, and the first to get 3 in a row (horizontally, vertically, or
diagonally) wins.   

The players could be human or computer.  

In this version, there are 9 instances of the game being played
simultaneously. Each turn, a player may make a single move in any one of the 9 game
instances. (Note that the game instances can thus become unbalanced in the number of
moves if a player does not respond directly but instead plays in another instance.) The
game instances are themselves arranged in a 3x3 grid, constituting a meta-game. Each
time a player wins a game instance, they take the corresponding square in the meta-game.
If a game instance results in a tie, that game instance is replaced with a fresh instance with
no moves played. The game is over when a player wins the meta-game.

