**Requirements:**

1. Android Studio 1.5.1+.
2. JRE 1.6.0+.

This section contains the source code of the BSE Android app. To make any changes, please fork the repository and follow the following steps:

1. Clone the repository onto a location in your computer (git clone https://github.com/santoslab/apps-4-vet-med.git)
2. Once you have cloned it, on your computer edit the sdk.dir field in the file `apps-4-vet-med/bse-a4vm/android/local.properties`. The value of this field should be the path where the Android SDK was installed. It should be of the form `/path/to/android/sdk`.
3. Open Android Studio. Select _Open an existing Android Studio Project_ and select the `apps-4-vet-med/bse-a4vm/android/app`. Android Studio will ask if you want to build the project. Select _Yes_. The project should build successfully.
4. You can now run the code on an emulator or by connecting to a device.


**How to run the tests (using Android Studio):**

1.  Once you have followed the above steps to setup this project in Android Studio, you can run tests in the AndroidTest folder.
2.  In Android Studio go to *Build Variants* on the left hand pane of the editor. Select *Android Instrumentation Tests* as the *Test Artifcact*.
3.  In the *AndroidTest* folder, go to `BSETestSuite.java` right click on it and run it. You should be able to see the test results in the 'Run' window. All tests should pass.

**How to run the tests (using Gradle):**


Use the following command to run all tests from gradle:

  `./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=edu.ksu.cs.a4vm.bse.BSETestSuite`
  
All tests should pass with the message that Build was successful.
