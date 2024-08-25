# Welcome to DnD Monster Fight!

Hello, I am <a href="https://github.com/pkncoder/">pkncoder</a>, and this is a DnD Encounter Simulator that has been my base project for multiple years!

Currently this is version 3 of DnD Monster Fight, and here's the run down of the other versions:
1. Made in python
   1. Make two enemies to watch them fight
   2. Also had connection to the DnD Api to pull pre-made monster data
   3. Was my first larger project I ever made
2. Made in Java
   1. Could make two parties, one for players and one for enemies
   2. No API conection
   3. Made for my final project in AP CSA
   4. Wasn't very well formatted, and didn't follow much java conventions
      1. Also wasn't really Object Orientated
3. Made in Java
   1. Can make as many parties as you want, although still clamped to Players & Enemies
   2. Uses data from the API
   3. Made as a finalle to my AP CSA class for what I have actually learned (because v2 was just embarrassing)
   4. Object Orientated I am pretty sure (still transfering from how not-very OOP python is)
   5. Tried to follow as much of java docs as I know and that I caught
4. Next version - Made in Javascript (Probally JQuerry for making HTML DOM easier and knowing how to use it)
   1. Most of the data in the Future Notes will be in this version (V4), and not the current version (V3)
   2. Lots of point and click, so full UI use
   3. Lots of customization and nice save features
   4. Possibly an online mode for battling others


## Current Capabilities

In DnD Monster Fight V3, you can create parties that will either hold Players or Enemies. You then create these Players / Enemies to fight it out in a PvE (Player vs. Enemy) battle to see who is the last one alive!


## Running

Running DnDMonsterFight.java will run the program<br>
Internet Connection is required


## Future Notes

This is a list of possible future notes on things that I may add (not necisairly in this version):
- [ ] Fuzzy matching on armors / weapons / classes (aka as much as I can)
- [ ] Standard array on abilities
- [ ] Random on basically everything
- [ ] Races
- [ ] Speed / Ranges
- [ ] Buying armor / weapon with money (aka. a budget)
- [ ] Backgrounds for starting equipment / gold
- [ ] Adding inventory controls
- [ ] Adding healing
- [ ] Save Feature
- [ ] Homebrew possibilites on all that I can
- [ ] Items
- [ ] Spells
- [x] No armor when creating enemies / players
- [ ] Using pre-made enemies / templates
  - [ ] Pre-made enemies from the DnD Api
  - [ ] Pre-made enemies from homebrew
  - [ ] Pre-made templates that follow a main idea of an enemy type that the user will customize to their liking
- [ ] Backstories and all that other lore stuff for characters / enemies / parties etc.
- [ ] Don't clamp the party types to just players / enemies
  - [ ] Maybe don't even have party types
- [ ] Name duplicates don't matter


## Todo in code

### Naming / Decitions

- [x] Better consistancy of naming conventions (ex. character / player)
  - Player only
- [x] Redo the switch statement area for commands to not make 1 million different variables and re-init them
- [x] Decide for the better on case sensitivity or no 
  - Case sensitive for base / party names, the rest not
- [x] Figure out what to do with name duplicates
   - When Re-writing GetPlayerCharacter.java don't allow any overlaps of names in any of the PartyColections
- [x] Decide on how to select bases (party & name, or just name)
  - Just by name for viewing, since there won't be any duplicates
  - For now party & name for removing


### Code Re-Writing or New Code

- [x] <a href="http://www.dnd5eapi.co/">DnD API</a> usage to get stats for items / armors etc.
    - [x] Removing ArmorColection.java
    - [x] Removing WeaponColection.java
    - [x] Removing ClassType.java
- [x] Rewright GetPlayerCharacter.java
- [x] Re-write GetHelperMethods.GetArmor() & GetHelperMethods.GetWeapon() to use better input validation
  - Its all in the api now and seems good
- [x] Don't allow name overlaps for players or enemies
- [x] Don't allow name overlaps for parties
- [x] Check all user output and make sure it is still intuitive and makes sense (and looks good)
- [x] Add a to hit modifier and damage modifier
  - [x] Actually calculate the correct value
  - Everything is a finnesse weapon in the idea of to hit and damage mods bc I can't tell the difference if there is one
- [x] Add a random value function to simple
- [x] Check for more things to add GetHelperMethods()
- [x] Check ALL input validation (ex. commas in weapons)
- [x] Rename the PlayerCharacter class to just Player
- [x] Add more customization to Simple.java (aka. error messages)
- [x] Check for allowed amounts of characters and parties in the 'done' command
- [ ] Add different win conditions
  - [ ] Players or enemies
  - [ ] Last party standing
  - [ ] Last character standing
- [x] Make the get num characters from PartyCollection only increase when we check it
- [ ] Basic custom weapon, armor, and class
- [x] Check Encounter.java for re-initializing variables that can be put to a larger scope
  - Wasn't really that bad so I just left it alone
- [x] Rewrite Base.java since it's old and hasn't been changed much to new terms
- [x] Make the to-strings better with a return ();
- [ ] Re-make the getStringInput(String, String[], String, boolean) / getStringInput(String, String[], String) to use a better system for swaping if you match with or against the array
  - Possibly add a variable that will just say if the string[] is matched or not matched (probbally a boolean)
- [ ] Make a welcome message to give users info such as usual terminology and how the commands work
  - Hp
  - Ac
  - Level
  - Party
  - Player
  - Enemy
  - The commands

### Hypothetically major re-writing
- [ ] Same code for players / enemies
    - [ ] Only difference being an array or something to choose what to use
    - [ ] Like an index that gets changed when player or enemy is used
    - [ ] Or using the input like an enum
- [ ] And basically the same for player / enemy parties (shoving them in a array like thing and using an index / enum like thing to choose which one to do magic with)
- [x] Rename the Base class to Character (finally a good name)
- [ ] Add viewing of weapons, armors, or classes when choosing them
- [ ] Add viewing of characters when attacking


### Api Re-Writing
- Api class
  - [ ] Rename the class to make more sense
  - [ ] Change the way you use the .get() method so you don't have to use gson's .toJsonObject().get() after the first use
  - [ ] Make JsonArrays easier to use
  - [ ] Make some shortcut so you don't have to for loop over every item in the JsonArray to get the same key 
    - [ ] Ex. array full of name and index keys. Method that will get every name key
  - [ ] Make an optional base url attribute that will be added onto every api call at the start
  - [ ] After all this re-write GetEnemy and GetPlayerCharacter
    - [ ] Change GetEnemy.printAndSaveApiData() to allow negitive indexing and make 0 the null num
    - [ ] Change GetPlayerCharater.getPlayerWeapon() so it is much more effiecient in the way it makes api calls (it needs it)


### Always Alive

- Error testing
- Updating comments to be true after re-writes & to have java doc