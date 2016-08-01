This section contains the source code of the BSE iPhone app. To make any changes, please fork the repository and follow the following steps:

1) Download and install xCode 7.3 or later.

2) Clone the repository onto a location in your computer (git clone https://github.com/santoslab/apps-4-vet-med.git)

3) Go to the folder where you have cloned the repository and then click bse-a4vm-iphone -> bse-a4vm -> iPhone -> BSE.xcworkspace

4a.) If you only see BSE.xcodeproj (with no down arrow on the left) left side of the screen, you need to add the project into your workspace. Control click on BSE.xcodeproj click on add files to "BSE" and then select BSE.xcodeproj and click add. Make sure there is another BSE (with down arrow) file below BSE.xcodeproj 

4b.) If you have BSE (with down arrow) file with BSE.xcodeproj and pods files on the left side of the xcode then you can move to next step.

5) Right beside the "Play" and "Stop" on the xcode make sure your scheme is BSE with the app icon and NOT anything else (with yellow suitcase icon). If it is click and select BSE. 

6)On the right of the icon is the space where you selce the simulator/connected device to run the application. If you want run your application on a simulator click and select under iOS Simulators else select your device name.

7) At this point you are good to go. You click the "Play" button and the application will built and run on your selected device.

SPECIAL INSTRUCTIONS FOR TESTING THE APPLICATION

8) Click in the down arrow beside BSE file (with blue icon) on left side of the screen to explore it and under that click BSE file (yellow Folder) and under that click down arrow beside BSEUITests (yellow folder) and then select BSEUITests.swift and open the file on xcode.

9) Scroll the file and look for the test fuction you want to test and click the emply diamond icon on the left of the name of the function. Make sure you have correct bulid devices on the step 5 and 6 above. As soon as you click the diamond icon xcode will built and run the test on your device.
