# Bull Soundness Evaluation (BSE-A4VM)

*  [General Information](#GenInfo)
*  [Initial Setup](#Setup)
*  [Home Screen](#HomeScreen)
  *  [Clean Up](#Cleanup)
*  [Groups](#Groups)
  *  [View Dashboard](#Dashboard)
  *  [Create New Group](#NewGrp)
  *  [Edit a Group](#EditGrp)
  *  [Export](#Export)
*  [Bulls](#Bulls)
  *  [Bull Dashboard](#BullBoard)
  *  [Bull Exams](#Exams)
  *  [Bull Information](#BullInfo)
  *  [Sex & Mating Information](#SexInfo)
  *  [Physical Parameters](#Parameters)
  *  [Morphology Dashboard](#Morph)
  *  [Motility Information and Classification Information](#Motility)
*  [Feedback](#Feedback)

<h1 id="GenInfo">General Information</h1>
BSE-A4VM mobile apps aid veterinarians to collect data while examining bulls. They allows for the collection of nearly 100+ fields about bulls and the mass export of data in CSV format that can be used with MS Excel or other data processing applications.
<h3>Download the app from:</h3>
* [for iPhone](https://itunes.apple.com/us/app/bull-soundness-evaluation/id1099677800?mt=8) *TODO: Add link to app page on iStore*
* [for Android](https://play.google.com/store/apps/details?id=edu.ksu.cis.bse_a4m_android&hl=en)

_For most part, these mobile apps do not require network access._  They will require access to the email client on the phone when the user decides to email the collected data for purposes of aggregation and reporting.

Since the mobile app has consistent look and feel on both Android and iPhone platforms, the rest of this documentation will describe the app with no specific reference to the platform.  When screen shots are provided for illustration, screen shots from both iPhone app (left) and Android app (right) will be provided.

<h1 id="Setup">Initial Setup</h1>
Upon opening the app for the first time after installation, veterinarian will be prompted to input personal information such as name and email address.

Current veterinary information can be viewed and edited via the _Settings_ page of the app.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/Vetinfo.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/VetInfo.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>

Before proceeding to collecting data, morphology fields will also have to be configured. This information will allow you to use the in-application counter to assist in collecting count data about bull semen samples.  By default, the first field is labelled as _Normal_ and the users cannot reconfigure this field.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/Morphology.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/MorphConfig.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>

<h1 id="HomeScreen">Home Screen</h1>
To open the app, click on BSE app icon on your phone.  This will open the home screen of the app.  From here, you can navigate to the _Groups_ page and the _Settings_ page. 

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/Home.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/Home.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>

<h3 id="Cleanup">Clean Up</h3>
The app saves the collected data on the phone until you delete it.  So, to delete all data collected prior to 30 days, click on _Delete 30+ day old data_.  This deletion is permanent and cannot be undone.  You can also delete data corresponding to each group (from the groups page *TODO: is this true for both apps?*. It is recommended that you periodically delete old data to prevent the app from occupying the storage space on your phone.

<h1 id="Groups">Groups</h1>
A _group_ is a group of bulls along with their examination data.  Each ranch/location/owner can have multiple groups, e.g., one group for each day of the visit to the ranch, one group for the morning batch of bulls and one group for the afternoon batch of bulls.


<h3 id="Dashboard">View Dashboard</h3>
From the home screen, you can get to the _Dashboard_ by clicking on the _Groups_ button.  The dashboard allows you to view the list of groups on the phone and create new groups.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/Groups.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/GrpDashboard.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>

<h3 id="NewGrp">Create New Group</h3>
If you chose to create a new group by clicking _Add New Group_, then you will be prompted to input data about the owner of the set of bulls to be examined in this group.  Once you click _Save_, the data will be saved and you will be able to collect data about bulls.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/NewGrp.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/NewGrp.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>

<h3 id="EditGrp">Edit a Group</h3>
This screen allows you to view and edit data for bulls in the current group (by clicking _View/Edit Bulls_). It also allows you to export the data for the current group of bulls (by clicking _Export Data_).  

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/SingleGroup.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/Grp.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>

<h3 id="Export">Export Group</h3>
You can export the data for the current group by clicking _Export Data_ button.  This will package the data for the group as a CSV file, open the email client on your phone, create an email with the CSV file as an attachment, and give you back the control to edit the email and send it.

<h1 id="Bulls">Bulls</h1>

<h3 id="BullBoard">Bull Dashboard</h3>
This screen allows you to view and edit data about bulls.  The data about a specific bull can be viewed and edited by clicking on the corresponding _Bull ID_ button.  A new entry for a bull can be added to the group by clicking on _Add New Bull_ button.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/BullDashboard.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/BullDashboard.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>

<h3 id="Exams">Bull Exams</h3>
This screen allows you to enter various data about the bull being examined (or edit previously collected data).  Data about bulls are grouped into sets of related data, e.g., bull info, morphology, motility.  Each group can be accessed by clicking the corresponding button.

To assist the examiner make a thorough examination, the app uses a simple color scheme.  
  - If the examiner visits a data group and fills out some (not all) data fields, then the button or the marble for the group changes to blue color.
  - If the examiner filled all data fields of a data group, then the button or the marble for the group changes to green color.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/BullExam.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/BullExams.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>


<h3 id="BullInfo">Bull Information</h3>
In this screen, each field is associated with a hint that suggests the type of information accepted by the field. A field is highlighted in red if you enter invalid data.  Also, at least one of the ID fields -- Tag, RFID, Brand, and Tattoo -- has to be filled.  The age of a bull is mandatory.  If you provide the date of birth, the age of the bull will be automatically filled. Remember to click on the _Save_ button after you have entered the necessary (mandatory) information.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/BullInfo.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/iPhone/BullInfo1.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/BullInfo.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>


<h3 id="SexInfo">Sex & Mating Information</h3>
In this screen, each field is associated with a hint that suggests the type of information accepted by the field.  Remember to click the _Save_ button before you navigate away from the screen. If you enter invalid information, the app will notify you by highlighting corresponding fields in red.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/Mating.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/Mating.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>


<h3 id="Parameters">Physical Parameters</h3>
This screen has two buttons. One will take the user to the _Measurements_ screen and the other will take the user to the _Physical Exam_ screen. In these screens, you can enter data in the fields.  Again, there will be hints about the type of valid data, and the app will notify you of invalid data  by highlighting corresponding fields in red.  Also, remember to click the _Save_ button before your navigate away from the screens.

Each field is associated with a check box to indicate "normal" or not.  Check this boxes when you do not want to enter a description but want to record the corresponding parameter is normal.  If you do not provide any description and not check the corresponding bos, then the app will record the parameter as abnormal.  If you wish to record all parameters as normal, then you can use the _Select all as Normal_ check box at the bottom of the screen to mark all parameters as normal -- this will check the check boxes associated with all parameters.  Unchecking this check box will uncheck the check boxes associated with all the parameters on the screen.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/PhyParms.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/PhyParms.png?raw=true" width="200" height="400" /></td>
 </tr>
 <tr>
  <td><img src="img/screenshots/iPhone/Measure.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/Measurements.png?raw=true" width="200" height="400" /></td>
 </tr>
 <tr>
  <td><img src="img/screenshots/iPhone/phyInfo.PNG" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/PhyExam.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>


<h3 id="Morph">Morphology Dashboard</h3>
Morphology information is captured in the _Morphology Dashboard_ screen.  This screen will have:

1. A button at the top called _Add New Semen Collection_.
2. A list of _Semen collection counts_ for the bull (in the current examination session).

Upon clicking _Add New Semen Collection_, you will be presented with a page full of buttons (up to eight buttons). These buttons represent the Morphology information you had setup in the _Settings_ section of the app. You can gather tally data for various morphology features by clicking on the corresponding button. 

During collection, when you switch buttons, the app will generate a unique sound alert .  Similarly, when the tally count reaches the limit/threshold for a morphology feature, the app will generate a unique sound alert.

For each semen collection, you can view the total count and the break down via the _Morphology Count_ screen by selecting a specific sample.  If needed, you can modify the recorded count in this screen by clicking on the edit morphology button at the bottom of the screen.

If you change the morphology configuration while collecting morphology counts for a particular group, those changes will not be reflected for that group. If you want to collect morphology counts based on the modified configuration then we suggest that you create a new group.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/MorphDashboard.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/MorphDashboard.png?raw=true" width="200" height="400" /></td>
 </tr>
 <tr>
  <td><img src="img/screenshots/iPhone/MorphInfo.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/MorphCount.png?raw=true" width="200" height="400" /></td>
 </tr>
 <tr>
  <td><img src="img/screenshots/iPhone/EditMorphCount.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/EditMorphCount.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>


<h3 id="Motility">Motilility Information and Classification Information</h3>
As with previous screens, the data collected on these screens are self explanatory (and hints are associated with fields). Again, remember to _Save_ the data before navigating away from the screen.  Also, the app will notify you of invalid data by highlighting the corresponding fields in red.

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/Motility.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/Motility.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>

<table>
 <tr>
  <td><img src="img/screenshots/iPhone/Classification.PNG?raw=true" width="200" height="400" /></td>
  <td><img src="img/screenshots/Android/Classify.png?raw=true" width="200" height="400" /></td>
 </tr>
</table>

<h3 id="Feedback">Feedback</h3>
If you have any comments, feedback, or issues, please email them to us at <a href="mailto:mobile-apps-group@santoslab.org">mobile-apps-group@santoslab.org</a>.

