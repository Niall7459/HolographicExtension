<p align="center">
  <img src="https://www.spigotmc.org/attachments/screen-shot-2016-02-15-at-11-13-36-png.103584/" alt="Holographic Extension">
  <img src="https://api.kitesoftware.net/live/?plugin=HolographicExtension" alt="Live statistics">
</p>

# About
Holographic Extension is an add-on for Holographic Displays that adds 
animations like scroller text, typewriter, glowing and more.  
It also adds placeholders from PlaceholderAPI (requires ProtocolLib) to your holograms!

# Dependencies
- [Holographic Displays](https://dev.bukkit.org/projects/holographic-displays)
- [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/) (For using placeholders)
- [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) (Requires ProtocolLib to work)

# Adding and using animations
You first open the `animations.yml` of the plugin.  
In that file will you already find some default animations.

<details>
  <summary> default animations.yml (Click to show/hide content) </summary>
  
  ```yaml
  # *********************************
  # Holographic Extension Animations
  # *********************************
  # Animation Config File.
  # --> Find help on the spigot resource page.
  #
  example:
    speed: 0.1
    frames:
    - '<glow size=5 normal=&7&l start=&e&l middle=&6&l end=&e&l repeatfirst=10>Kite Holographic Extension</glow>'
  scroller:
    speed: 0.1
    frames:
    - '<scroll width=32 spacing=32>&cThis is a example scroller.</scroll>'
  typing:
    speed: 0.1
    frames:
    - "<typewriter pause=10 backwards=true>Kite Holographic Extension Example.</typewriter>"
  rainbow:
    speed: 0.1
    frames:
    - "<rainbow>Hey there, %player_name%</rainbow>"
  # NOTICE: To use these placeholders, you need PlaceholderAPI and correct extensions..
  # /papi ecloud download Player.
  # /papi ecloud download Server.
  ```
</details>

To add a new animation, simply add a new name and add `speed` (time of updating the animation in seconds) and `frames` (actual text 
of the animation).  
You then just add frames to the animation. (Supports predefined animations, which can be found 
[here](https://github.com/Niall7459/KiteBoard-Documentation/wiki/Animations))

Now you just have to save the file, reload the plugin and implement the animation to your holograms.  
To implement it, just use `{ex:<animation name>}` (replace `<animation name>` with the name you set in the animations-file).

# Implementing Placeholders
Holographic extensions allows you, to use placeholders from PlaceholderAPI in your holograms.  
To use those placeholders, you first have to make sure, that ProtocolLib is installed and running, since it won't work without it.

To use placeholders, you just have to add it to the hologram. It's that simple!

## Updating placeholders
The placeholders won't update on their own.  
You have to use the following special placeholders, in order to update it:

### Version 1.9.3 and below
- `{r0.1}` - Refresh every 0.1 seconds.
- `{r1}` - Refresh every second.
- `{r2}` - Refresh every 2 seconds.
- `{r5}` - Refresh every 5 seconds.
- `{r10}` - Refresh every 10 seconds.

**Example**: `/hd add example {r1}%player_time%`

### Version 1.10.0 and above
- `{fastest}` - Refresh every 0.1 seconds (recommended to use minimally).
- `{fast}` - Refresh every 0.5 seconds.
- `{medium}` - Refresh every second.
- `{slow}` - Refresh every 5 seconds.
- `{slower}` - Refresh every 10 seconds.
- `{slowest}` - Refresh every 45 seconds (best for performance).

**Example**: `/hd add example {medium}%player_time%`

# Commands
This plugin does **not** provide any editing commands.  
You still create and manage holograms through Holographic displays.

Those commands are provided by Hext:
- `/hext reload` - Reloads the animations.yml
- `/hext list` - Lists all available animations
- `/hext support` - Checks for any placeholder and/or ProtocolLib problems
- `/hext about` - General plugin information

You can also use `/ext` as an alias.

# Permissions
The permissions for commands are directly given to OP and (obviously) the console.  
You can give yourself `hext.admin` to use all commands.

**Other permissions**:
- `hext.help`
- `hext.info`
- `hext.about`
- `hext.reload`
- `hext.list`

# Predefined animations
A full list of already made animations can be found on the 
[KiteBoard-documentation wiki](https://github.com/Niall7459/KiteBoard-Documentation/wiki/Animations)

# License and terms
Please see the [COPYRIGHT.md](https://github.com/Niall7459/HolographicExtension/blob/master/COPYRIGHT.md) for terms and conditions.
