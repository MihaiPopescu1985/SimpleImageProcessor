<h1>Simple image processor written in java</h1>

For simplicity in adding new features and to maximize the workspace, we have chosen that every transformation on the image be applicable by accessing a menu.
We have identified two interfaces and a basic class.
A first interface refers to displaying the image, so we can simply call it "Window". The class that will implement this interface will need to: create a working area that displays the image, has an event processor, and contains menus,
The second interface refers to the menu. This is quite simple, it only needs a name and a String command, which can be "understood" by the event processor.
We still have the basic class, the one that applies image changes that we will call suggestively, ImageProcessor. Because image changes will be triggered by events, they will make an internal class of the ImageProcessor class, which will have the responsibility of processing the events.
To add new features:
- the addOption function will be added to add a new menu containing the event message generated.
- the new added menu message will be added to the actionPerformed function in the ProcessEvents class.
- The new method will be implemented in the ImageProcessor class

Implemented functionalities:
- mirroring
- image rotation
- brightness



<img src="Screenshots/Captură de ecran din 2019-04-07 11-41-19.png" alt="1" width="400" height="225">
<img src="Screenshots/Captură de ecran din 2019-04-07 11-42-14.png" alt="1" width="400" height="225">
<img src="Screenshots/Captură de ecran din 2019-04-07 11-42-55.png" alt="1" width="400" height="225">
<img src="Screenshots/Captură de ecran din 2019-04-07 11-43-28.png" alt="1" width="400" height="225">
<img src="Screenshots/Captură de ecran din 2019-04-07 11-44-18.png" alt="1" width="400" height="225">
<img src="Screenshots/Captură de ecran din 2019-04-07 11-44-36.png" alt="1" width="400" height="225">
<img src="Screenshots/Captură de ecran din 2019-04-07 11-45-52.png" alt="1" width="400" height="225">
