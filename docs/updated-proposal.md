Team name: Mazerunners
Team members: Matthias Snyder, Autumn Bertram, Ryan Mansour

# Introduction 

We plan to create a top-down 2D procedurally generated maze for our player to navigate and accomplish specific set tasks. Beginning with simple and straightforward tasks, such as collecting objects/getting to a certain point. After each completion of a task, the level is finished and the player will automatically be pushed into the next level with an increasingly more difficult task. As the player progresses, the maze will become more challenging in a variety of ways. 

[MENU AND DISPLAYS] The game will open to the main menu, from which the player will be able to start the game, choose a level, or enter the options menu (which may include difficulty toggles). There will be a GUI display for the specific task needed to complete the level. Updated display when tasks are partially completed. This in turn would allow the player to know exactly what needs to be done to complete the level and how close they are to finishing. Additionally, there would be a GUI that will display health and modified movements gained during the level. This will be displayed at the center bottom of the screen. A clickable tab will direct the player to a menu pop-up, which displays the options: resume, exit, objectives, and map (will possibly move the map tab outside of the menu pop-up).

[PLAYER MECHANICS]The player will be able to move their avatar with their arrow keys. The limited movement for the character would be aided by collectibles around the map that would adjust the movement based on specific collectibles found. Plans for hazards/enemies in later development. 

[ENEMIES] Enemies will be present opposing the player adding a degree of skill and knowledge needed to overcome the enemy. Enemies will be scaled with the level which in turn will cause higher leveled mazes to contain higher leveled enemies. Increasing the level of the enemy will consist of increasing the health and ability of the enemy. Additionally, some enemies will be reserved for higher levels as they will be inherently made more difficult than previous enemies. Planned to have multiple types of enemies each with their own abilities and nuance making them unique and situational for the player. Enemies per level will be determined by a pool of enemy types. This pool will be increased as the levels increase (ie level one will have a more limited selection of enemies that may be generated than compared to level 10).

# Anticipated Technologies

GitHub, Java, hosting platform
Classes: player, enemy (abstract) - specific enemy types under this, maze, mazeGame (main class), GUI, collectible (abstract) - specific collectibles under this, 

# Method/Approach

Weekly meetings, see where everyone is at, ensuring everything is on time and current features working properly. Dividing certain tasks of the project.
 
# Estimated Timeline
**# of weeks remaining until Game Release: 7 (as of 10/6/22)**
- Define characteristics of the game (week 1)
- Diagrams of menu, maze, and class hierarchy **(ETA: 1 week Thursday, October 14)**
- Menu Demo **(ETA: 1 week Thursday, October 13)**
- In-class Status Update and Demonstration **(ETA: 1 week Friday October 14)** 🎉
- Maze Generation Demo **(ETA: 3 weeks Thursday, October 27)**
- Functioning Player Demo **(ETA: 4 weeks Thursday, November 3)**
- Enemy vs Player Demo **(ETA: 5 weeks Thursday, November 10)**
- Additional features; hazards enemies; extras 
- In depth testing and debugging **(ETA: 6 weeks Thursday, November 17)**
- GAME RELEASE: set up on hosting platform and playable **(ETA: 7 weeks Thursday, November 24)** 🎉
- Final debugging and user testing **(ETA: 8 weeks Thursday, December 1)**
- Preparations for game presentation **(ETA: 8.5 weeks Tuesday, December 6)**
  - Slides/ppt?
  - Predetermined format for demonstrating game mechanics
- Presenting game to class **(ETA: 9 weeks Wednesday, December 7)** 🎉

# Anticipated Problems

- Ensuring that the procedural mazes are solvable innately, as well as every feature being added doesn’t cause situations that result in the maze being unsolvable.

- Keeping up with the workload/not taking off more than we’re able to.
