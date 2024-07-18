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
4. Made in Javascript (ooo the future)


## Current Capabilities

Can create, remove, and view parties, players, and enemies


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
- [ ] Homebrew all that I can
- [ ] Items
- [ ] Spells
- [ ] No weapon / armor when creating enemies / players
- [ ] Using pre-made enemies / templates
  - [ ] Pre-made enemies from the DnD Api
  - [ ] Pre-made enemies from a homebrew
  - [ ] Templates could work like the usual stuff for the speicies and then editing it as the user wishes
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


### Code re-writing

- [ ] <a href="http://www.dnd5eapi.co/">DnD API</a> usage to get stats for items / armors etc. (probably fetched before running) and plopped in a json
    - [ ] Removing ArmorColection.java
    - [ ] Removing WeaponColection.java
    - [ ] Removing ClassType.java
- [x] Rewright GetPlayerCharacter.java
- [ ] Same code for players / enemies
    - [ ] Only difference being an array or something to choose what to use
    - [ ] Like an index that gets changed when player or enemy is used
    - [ ] Or using the input like an enum
- [ ] And basically the same for player / enemy parties (shoving them in a array like thing and using an index / enum like thing to choose which one to do magic with)
- [ ] Re-write GetHelperMethods.GetArmor() & GetHelperMethods.GetWeapon() to use better input validation
- [ ] Don't allow name overlaps for Parties or characters
- [ ] Update / make better querries & just output in general


### New code

- [ ] A menu class to hold the outputed text in the terminal and makes it more intuitive on how / when the terminal has to clear


### Always Alive

- Error testing on CRACK
- Updating comments to be true after re-writes & to have java docs