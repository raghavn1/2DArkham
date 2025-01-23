# Arkham2D
---
## File Structure

- ### Game Folder
  - #### Core Folder
    - ##### Scenes
      - Hud.java - Heads up display that shows the time, score, and level
    - ##### Screens
      - StartScreen.java - Shows the image of the starting with the option to click enter to start the game
      - PlayScreen.java - Main screen that actually holds the game and has the input handling, and drawing the actual assets for the game
      - EndScreen.java - Game over screen that pops up after the worldTimer is 0 or when the time has ended
    - ##### Sprites
      - Batarang.java
      - Batman.java
      - Enemy.java
      - InteractiveTileObject.java
      - Joker.java
      - MyContactlistener.java
      - Obstacles.java
      - Powerups.java
    - ##### Tools
      - B2WorldCreator.java
    - ##### Main.java - Main game class
   
  - #### Assets Folder
    - ##### Audio
      - BatmanMusic.mp3 - The music file that is played by LibGDX AssetManager in the PlayScreen
    - Arkham 2D.png - The background image for the StartScreen
    - Custom Edited - Castlevania Customs - Alchemy Laboratory Tileset.png - The tileset which the TiledMap is based on
    - ExperimentMap.tmx - The TiledMap file which is rendered as the map and contains the graphics level and the objects layer which the player and sprites collide on
    - ThugSprite.pack - The `.pack` file for the enemy sprite atlas
    - ThugSprite.png - The `.png` that the enemy sprites come from
    - assets.txt - The file that contains a list of all the assets used by LibGDX
    - batmanSprites.pack - The `.pack` file for Batman's sprite atlas
    - batmanSprites.png - The `.png` file that Batman's sprites come from
    - gameOver.jpg - The background image used to render the game over screen
