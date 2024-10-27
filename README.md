Alien Battle Game
Alien Battle Game is a turn-based strategy game where players control an alien character to engage in battle with an computer-controlled opponent. Players and computer opponents have unique abilities and stats, and the goal is to strategically plan attacks and defences to outlast and defeat the opposing character.

How to Play
Game Objective
The main objective of the Alien Battle Game is to reduce the opposing player’s health points (HP) to zero while managing your own HP and defending against attacks.

Main menu
Start the game by clicking the “Play” button on the start menu. Then select a “breed” of your alien (Red - average stats, Green - high HP but low damage, Blue-high attack but low HP). Computer generates its aliens and gameplay follows.

Gameplay
Players alternate turns. Each turn, the player selects an action while considering the opponent’s potential response. The game’s logic makes moves based on strategy to challenge the player. The actions include:

	Attacks: 
			Each attack has 3 variables. First is damage, representing the attack's damage, next one is hit chance, that represents the % probability of successfully hitting the opponent. The last one is damage’s variability. This is the possible deviation of damage value.
	•	Attack 1: A basic attack that deals moderate damage with moderate hit chance.
	•	Attack 2: An alternate attack with a higher damage but lower hit chance.
	•	Ultimate: A powerful attack with high damage but may have a lower hit. Can be used each 4 rounds.
	Defence: Reduces incoming damage during the opponent’s next turn. Can be used together with an attack and is ready to be used every 3 rounds.
	Perks: Every 3 rounds opponents get to choose a perk to improve their stats in one of 3 domains - health, damage and defence. These change every round, and their exact values are luck-based.
The game ends when the player’s HP reaches zero. If alien’s do first, a new alien is generated based on the player’s stats. Score represents the total damage dealt by the player in one game.




Learning Goals
This project helped us build a solid understanding of game development concepts, specifically within the Java programming language, and introduced us to fundamental collaborative tools and design principles in software engineering.


Version Control with Git
Using Git for version control, we gained experience in managing and tracking the development process. Key aspects of our workflow included:
Branch Management: We used branches, creating separate branches for new versions, and testing phases. This helped in isolating changes and minimizing conflicts.
Merging and Collaboration: Branches were merged regularly, allowing us to track and review each other’s work effectively. Git’s versioning system allowed us to revert to earlier versions if needed, maintaining project consistency.
Game Release: After we finished the development, we published our first game release.
Our sources:
https://training.github.com/downloads/github-git-cheat-sheet/
https://git-scm.com/doc
https://www.nobledesktop.com/learn/git/git-branches
https://github.com/

Procedural Generation of Characters
To add depth and replayability, we implemented a procedural generation system for creating both player and computer characters:
Random Attributes: Each character is generated with randomised stats within set ranges, ensuring variability in gameplay. Computer’s alien is generated based on a prototype(player’s alien) and a base alien of chosen breed. With this we achieved the possibility of creating similarly levelled aliens after the previous one is killed, keeping the game balanced and challenging.
Character Abilities: Characters have varying unique abilities. The computer tracks the player's actions, based on which it makes its own decisions.
Our sources:
https://dev.to/goals/runtime-procedural-character-generation-161d
https://gamedevacademy.org/what-is-procedural-generation/#How_to_Get_Started_with_Procedural_Generation
