iPodMod
=======
iPod Mod Sound Importer is now separated into another repo instead of branch

WARNING! Mod state is unstable! Do not attempt to debug! Will be stablilized later

About Debugging:
Due to strict debug limitations, do not debug directly! The code will look in the mods folder
instead of mcp/src folder because of IpodSoundLoader. To debug properly, compile your code,
pack it in a zip file (its filename must start with "iPodMod"), put the zip to %appdata%\
.minecraft\mods, and run your local MC with forge 1.6.X.

Known Bugs:
BlockIpodCharger (java.lang.NoClassDefFoundError)
  - occurs at FMLInit
  Effect: While showing Mojang window, it will close directly without warnings or stuff.

IpodSoundLoader (Varies)
  - its code is not yet stable and super buggy
