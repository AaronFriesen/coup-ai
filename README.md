Coup-AI Readme
==============

There are three major parts to this project:

1. The Game Engine
2. The Player Card Reasoner (Probably Markov Model)
3. The Action Chooser (Needs to interact with Card Reasoner)

The Game Engine
---------------
This is the game itself. It needs to encompass allowing the user to choose actions, calling player bluffs, and responding to bluff calls. Features, in order of necessity:

* Choosing Actions
* Calling Bluffs
* Creating games
* Typing messages to/interacting with other players
* GUI
* Network capability

Currently, it is implemented using a Stack of Actions (which are <Player, Move> pairs). The Controller exposes methods that allow the View to put Actions onto the stack from each player, and additionally has a method which executes those actions.

The Player Card Reasoner
------------------------
This is the module that tries to deduce what the other players have based on cards the agent has seen and actions he has seen enemies take.

Things that the Player card Reasoner needs to make available:

* % Probability that Player A has card C
* Gamestate corresponding to current most likely state of all players
* Most unknown card (card that this agent has the least knowledge about).
* This will be implemented using the [jahmm](https://code.google.com/p/jahmm/ "Welcome to the") (pronounced jam) Java Hidden Markov Model library.

The Action Chooser
------------------
This is the module that, given a gamestate, tries to decide what course of action should be taken. Implemented using a [Behavior Tree](http://www.altdevblogaday.com/2011/02/24/introduction-to-behavior-trees/). I'd read the whole thing if you want to get a good idea how decision trees work.

* Possible actions include bluffing, telling the truth, calling bluffs below a specific certainty threshold, and taking "safe" routes to victory.
* This will be implemented using the [jbt](https://github.com/gaia-ucm/jbt) Java Behavior Tree library.
