This section contains the source code of the BSE iPhone app. To make any changes, please fork the repository and follow the following steps:

1) Download and install xCode 7.3 or later.

2) Clone the repository onto a location in your computer (git clone https://github.com/santoslab/apps-4-vet-med.git)

3) Go to the folder where you have cloned the repository and then click _bse-a4vm-iphone -> bse-a4vm -> iPhone -> BSE.xcworkspace_.

4a.) If you only see _BSE.xcodeproj_ (with no down arrow on the left) in the left pane of Xcode, you need to add the project into your workspace. Control click on _BSE.xcodeproj_, select _add files to "BSE"_, select _BSE.xcodeproj_, and click on _Add_. Make sure there is _BSE_ entry (with down arrow next to it) below _BSE.xcodeproj_. 

4b.) If you have _BSE_ (with down arrow next to it) entry with _BSE.xcodeproj_ and pods files under it in the left pane of Xcode, then you can move to next step.

5) Beside the "Play" and "Stop" in Xcode toolbar, make sure _BSE_ is the currently selected scheme.  It not, select _BSE_ as the scheme by clicking on the current scheme. 

6) Click on the _BSE_ scheme to select the simulator/connected device to be used to run the application. If you want run your application on a simulator, select under an iOS simulator.

7) At this point you are good to go. You click the "Play" button and the application will built and run on your selected device.

8) If you would like to run the tests in the project, click on _Project -> Test_ from the top menu

9) If you would like to run individual tests, then 
  - Click in the down arrow beside _BSE_ entry (with blue icon) in left pane of Xcode to expand it.  Now click on _BSEUITests_ and then click on _BSEUITests.swift_ to open it in Xcode.
  - Scroll the file and look for the test fuction you want to test and click the emply diamond icon on the left of the name of the function. Make sure you have correct bulid devices on the step 5 and 6 above. As soon as you click the diamond icon xcode will built and run the test on your device.
  -After each built please make sure to remove/delete the application on the simulator/device before you run the test as each test considers that application to be freshly installed. This can be done by either manually deleting the application (press and hold and then click the red cross) or you can go to simulator->Reset Content and Settings
