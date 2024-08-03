# Visual Calculator for Ratadrabik Effects in Magic the Gathering

<p align="center">
  <img src="https://cards.scryfall.io/large/front/9/3/9315812d-03e8-4eb4-a693-e7adf281f7fb.jpg?1673308046" alt="Ratadrabik of Urborg" width="300"/>
</p>

<br /><br />
One of my favorite cards in the Magic the Gathering format known as 'Commander' is [Ratadrabik of Urborg](https://scryfall.com/card/dmu/213/ratadrabik-of-urborg). For my Ratadrabik deck, this create card supports and aristocrats style play, while also supporting finite and infinite loops to deal damage through tokens spawned by creature death while Ratadrabik is in play. 

As I have refined my deck over the years, it became clear that the power level of the deck is directly in relation to the number of effects I can create that multiple or add to the number of triggers for both Ratadrabik, and the cards in the '99' that are responsible for dealing damnage to my opponents -- typically through life loss.

At first glance, calculating these effects seems  bit trivial, but with the right board state, these calculations shift from being linear in nature to being exponential. With so many replacement effects in play that cascade calculations for downstream effects, I realized that a tool would be helpful for me, if only to speed up play, and therefore less the misery, of my opponents. 

## Usage
The application was written for Android in Java and uses an object-oriented classes for calculating board state and effects, while an 'engine' steps through the effects created by a given action occurring on the provided board state to determine the outcome with regards to how that action impacts:

* Player Health Gained
* Opponent Life Lost
* Resulting Board State

To use the application, you must use the UI to add the relevant components of your board state that will factor into the final board state, then select the action that occurs. Once that is done, the application will update the visual board state and provide the above values to speed up the game. A future version will provide a detailed explanation of the calculations behind each of the resulting values.


## Capabilities

### Triggers
Ratadrabik can currently account for the following triggers
* When This Creature Dies
* When This Creature Enters the Battlefield
* Another Creature Dies
* Another Creature Enters the Battlefield
* Another Creature Leaves the Battlefield
* When You Gain Life
* Attack Triggers
* Myriad (Also an Effect Amplifier)d

### Effect Amplifiers
* Triggers an Additional Time
* Triggers (twice,thrice, etc.) that many times
* Create (twice,thrice, etc.) that many tokens

### Conditionals
* Has Flying
* Is a Zombie
* Is Legendary
* Is a Token

## Engine Support
The board state engine supports the following considerations for trigger and effect stacking
* Spawning creates and creatures entering the battlefield as separate steps to ensure proper stacking for certain effects.
* Creatures dying and being removed as separate steps to account for exile, and leave the battlefield triggers which do not require creature death
