# Bovine Respiratory Disease (BRD) Treatment Evaluation

BRD Tx Evaluation mobile app calculates the best drug treatment option based upon certain inputs (population and drug specific) for combating Bovine Respiratory Disease in beef cattle. This mobile app will be utilized by Stockers and Feedlot Managers in the industry.

## Requirements

* [Gradle](http://www.gradle.org/) 2.1+
* [Java](http://www.oracle.com/technetwork/java/javase/%20downloads/index.html) 1.8+
* [Android Studio](https://developer.android.com/studio/index.html) 2.1+

## Instructions

1. Meet the requirements
2. Clone the repository
3. Import the project
4. Clean and build the workspace
5. Run the code on an emulator or a connected device

## Testing
### Requirements
* [Node] (https://nodejs.org/en/download/) 4.5+
* [Appium] (http://appium.io/downloads.html) 1.5.3+
* [Maven] (https://maven.apache.org/download.cgi) 3.3.9
* IDE (Intellij Community Edition)
* Android Virtual Device API 23

### Instructions
1. Meet Requirements
2. Follow guidelines to correctly installing Maven, test path in Terminal using command "mvn -version"
3. Open Terminal, start up appium server using command "appium"
4. Verify Appium succesfully started, should say "Appium REST http interface listener started on 0.0.0.0:4723" (port may be different)
5. Open android project in Android Studio
6. On Navigation bar got to -> Build -> Build apk
7. Copy apk file into desired folder
8. Copy path to apk
9. Open UI project (brdtxe-ui-test)in Intellij
10. Navigate to UI Test Class "BrdTxEvalUITest"
11. Look for File variable named "app" and replace string with your path to built apk
12. Verify Android emulator properties within project (Make sure they match the properties of your AVD)
13. In Intellij, run test by running UI test class BrdTxEvalUITest
14. Android emulator should start up when running
15. Sit back and watch UI Test
