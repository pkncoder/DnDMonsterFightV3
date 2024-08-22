# Welcome to DnD Monster Fight!

Hello, I am <a href="https://github.com/pkncoder/">pkncoder</a>, and this is a DnD Encounter Simulator that I have been working on for the past couple of years!

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
4. Next version - Made in Javascript (Probally JQuerry for ease and knowing how to read / write it)
   1. Most of the data in the Future Notes will be in this version (V4), and not the current version (V3)
   2. Lots of point and click, so full UI use
   3. Lots of customization and nice save features


## Current Capabilities

Enables you to make as many parties as you want, each either being an enemy or player party.<br>
Create enemies or players and add them to your parties for them to fight to a last man standing battle.


## Running

Running DnDMonsterFight.java will run the program


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
  - [ ] Pre-made enemies from a homebrew
  - [ ] Templates could work like the usual stuff for the speicies and then editing it as the user wishes
- [ ] Backstories and all that other lore stuff for characters / enemies / parties etc.
- [ ] Don't clamp the party types to just players / enemies
  - [ ] Maybe don't even have party types
- [ ] Name duplicates don't matter
- [ ] More than just last-man standing win conditions
  - [ ] Last party standing
  - [ ] Player parties vs. Enemy parties


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


### Code Re-Writing

- [x] <a href="http://www.dnd5eapi.co/">DnD API</a> usage to get stats for items / armors etc.
    - [x] Removing ArmorColection.java
    - [x] Removing WeaponColection.java
    - [x] Removing ClassType.java
- [x] Rewright GetPlayerCharacter.java
- [ ] Same code for players / enemies
    - [ ] Only difference being an array or something to choose what to use
    - [ ] Like an index that gets changed when player or enemy is used
    - [ ] Or using the input like an enum
- [ ] And basically the same for player / enemy parties (shoving them in a array like thing and using an index / enum like thing to choose which one to do magic with)
- [x] Re-write GetHelperMethods.GetArmor() & GetHelperMethods.GetWeapon() to use better input validation
  - Its all in the api now and seems good
- [ ] Don't allow name overlaps for Parties or characters
- [ ] Check all user output and make sure it is still intuitive and makes sense (and looks good)
- [ ] Add a to hit modifier and damage modifier
  - [ ] Actually calculate the correct value
- [ ] Add a random value function to simple
- [ ] Check for more things to add GetHelperMethods()

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
    - [ ] Check ALL input validation (ex. commas in weapons)


### Always Alive

- Error testing on CRACK
- Updating comments to be true after re-writes & to have java doc