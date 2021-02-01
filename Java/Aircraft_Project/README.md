This small java programs help to simulate fly with commands given to it. The purpose of this project to utilize OOP's concept in-depth, and make pilot to land flight safely on land without crashing
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
AIRCREW CLASS
-------------------------------------------------------------------------------------------------
A constructor that takes name, call sign, and missions as parameters. This is the constructor
that is called when a new Aircrew is added from the text file. From the file, all three of these
parameters have values. 
-------------------------------------------------------------------------------------------------
Recall that it is the number of missions that determines the status, so there has to be a way
for the constructor to set the status. If this constructor is also used for keyboard input, then the
number of missions must be set to 0 as the Aircrew has just been created.
Either way, the status must be set by the constructor, not the user.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
AIRCRAFT
The Aircraft class has the following attributes:
   • tail code This is a String (text) and may consist of more than one word

The tail code is the unique identifier for a Aircraft.
   • status This is a String (text). It has one of 2 values, either "on deck" or "airborne". "on deck" indicates that the Aircraft is not on a mission, "airborne" indicates that the Aircraft is on a mission.
   • crew This is the Aircrew class object reference for the Aircrew object that is associated with this Aircraft (when the Aircrew object is added and instantiated)
