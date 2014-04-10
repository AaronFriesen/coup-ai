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

The Player Card Reasoner
------------------------
This is the module that tries to deduce what the other players have based on cards the agent has seen and actions he has seen enemies take.

Things that the Player card Reasoner needs to make available:

* % Probability that Player A has card C
* Gamestate corresponding to current most likely state of all players
* Most unknown card (card that this agent has the least knowledge about).

The Action Chooser
------------------
This is the module that, given a gamestate, tries to decide what course of action should be taken. Less of a clear idea of what should be done on this one. Maybe implement general strategies?

I will work on drafting up Interfaces for these three to talk to each other. That way, we can work on them independently without messing with work that the other is doing.
