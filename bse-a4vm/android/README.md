**Requirements:**

1. Android Studio 1.5.1.
2. JRE 1.6.0.

This section contains the source code of the BSE Android app. To make any changes, please fork the repository and follow the following steps:

1. Clone the repository onto a location in your computer (git clone https://github.com/santoslab/apps-4-vet-med.git)
2. Once you have cloned it, on your computer edit the sdk.dir field in the file `apps-4-vet-med/bse-a4vm/android/local.properties`. The value of this field should be the path where the Android SDK was installed. It should be of the form `/path/to/android/sdk`.
3. Open Android Studio. Select _Open an existing Android Studio Project_ and select the `apps-4-vet-med/bse-a4vm/android/app`. Android Studio will ask if you want to build the project. Select _Yes_. The project should build successfully.
4. You can now run the code on an emulator or by connecting to a device.


**How to run the tests:**

1.  Once you have followed the above steps to setup this project in Android Studio, you can run tests in the AndroidTest folder.
2.  In Android Studio go to *Build Variants* on the left hand pane of the editor. Select *Android Instrumentation Tests* as the *Test Artifcact*.
3.  In the *AndroidTest* folder, go to `VetInfoActivityTest.java`. Notice the commments section at the top of this file. It will say that these tests should be run first. This means that these tests have no dependencies. Right click on it and run it on an emulator or device.
4.  Run all tests in this folder similarly. Before running a group of tests in that file notice the comments. The comments will contain further instructions. Follow those instructions before running tests in the file.
