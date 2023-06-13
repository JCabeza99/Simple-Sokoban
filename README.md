# Sokoban

Sokoban is a game in which you have to handle a character to carry boxes to goals 

## File hierarchy

The general overview of the project's file hierarchy is the following:

```
├── src
   ├── main
   │   ├── java
   │   │   └── es
   │   │       └── upm
   │   │           └── pproject
   │   │               └── sokoban
   │   │                   ├── controller
   │   │                   ├── model
   │   │                   └── view
   └── test
       ├── java
       │   └── es
       │       └── upm
       │           └── pproject
       │               └── sokoban
```
### Main

| package | summary |
| ------ | ------ |
| es.upm.pproject.sokoban.controller | This package is used for the implementation of all the logic that our project needs. It contains all the functionality that is aviable in our application and holds the data of the existing courses and students.|
| es.upm.pproject.sokoban.view | This package control the GUI of the game. |
| es.upm.pproject.sokoban.model | Holds the structure that a course and a student should have|

### Test

| package | summary |
| ------ | ------ |
| es.upm.pproject.Sokoban | All our unit tests are specified here. The code coverage is measured with the ammout of code the tests cover. The main goal is to reach 100% code coverage |

## Methods overview

You can see here a brief explanation on how the methods work and some  pre-conditions that are checked in them.

- **undo:** After pressing a button returns a movement from the movements that have been saved, also works with ctrl + Z.

- **restartLevel:** After pressing a button restart the level by returning everything to its initial location.

- **restartGame:** After pressing a button restart the game by returning to the first level with everything in its starting position.

- **save:** Saves all game data in a Json.

- **load:** Loads all game data from a Json.
