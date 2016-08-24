# Project Title

BSE-Reporter is desktop extension to BSE-A4VM mobile app to aid veterinarians Visualize the collected data (CSV files) from BSE-A4VM in to a single PDF file. It can also generate Single/Multi Bar-graphs based on the morphology information of the selected bull(s).
## Download Link


### Prerequisites

It generates PDF files from BSE i-Phone/Android .CSV files or BSE-Aggregator .CSV Files only.
Make sure the file extension is .CSV, if not then it has to be changed to CSV before generating a Report it.


### Installing

Just a single step process, just download the file, unzip it and double click the .jar file to run.

## Getting Started 

### Startup screen

There are 3 Screen in Reporter App.

####Screen 1 (Select a file)
 
![alt tag] (https://github.com/santoslab/apps-4-vet-med/blob/gh-pages/Reporter/img/Repo1.jpg)

On this screen there is an option to select a .CSV file (Only one file).

####Screen 2 (Select Columns for the reporter to generate)

Here you will see 2 Lists and 3 Buttons

![alt tag] (https://github.com/santoslab/apps-4-vet-med/blob/gh-pages/Reporter/img/Repo2.jpg)

1) List1: This contains all the columns present in your CSV file, this is where you can select all the required Columns to be reported.

2) List2: This is where you will see the selected columns after you have selected and pressed the "ADD->" button.

3) ADD: This is a button which is used to add the selected columns, you can add a single column or multiple columns at once. (You cannot select a columns twice, it won't appear twice in the List2).

4) Remove: If you wish to remove a column from list2/reporter you can just select a column in list2 and then press the remove button.

5) Next: After you are done you can move forward and click next for the next screen.

####Screen 3 (Select Bulls for the reporter to generate)

Here you will see 2 Lists, 5 Buttons and 3 check boxes

![alt tag] (https://github.com/santoslab/apps-4-vet-med/blob/gh-pages/Reporter/img/Repo3.jpg)

1) List1: This contains all the Bulls present in your CSV file, this is where you can select all the required Bulls to be reported (Bulls will be represented with their BullIDs, if a bull have multiple type of BullIDs all of them will be displayed).

2) List2: This is where you will see the selected Bulls after you have selected and pressed the "ADD->" button.

3) Add: This is a button which is used to add the selected Bulls, you can add a single Bulls or multiple Bulls at once. (You cannot select a Bull twice, it won't appear twice in the List2).

4) Remove: If you wish to remove a Bull from list2/reporter you can just select a Bull in list2 and then press the remove button.

5) Checkbox 1 (Single graph): If you want graphs for all the selected bulls individually then you have to select this option.

6) Checkbox 2 (Multiple graph): If you want a single graph having information about multiple bulls then select this option.

NOTE: You can select both, the app will make all the single graphs first and then will make a Multi graph for all the bulls. If only one bull is selected then a single-graph and a multi-graph is exactly the same.

7) Preview: This button is used to see a preview of the pdf you have created. If you want to change something before saving the file you can go back and deselect/Add more columns/Bulls in the graph.

8) Save: After you are satisfied you can move forward and click save for saving the file.
(NOTE: Please save the file in .PDF extension)


## Understanding the output of the App

![alt tag] (https://github.com/santoslab/apps-4-vet-med/blob/gh-pages/Reporter/img/Repo4.jpg)

#### Top left side of the app.
This is the Veterinary Heath Center at Kansas State University Logo

#### Top Right Side of the app.
This is the Rancher Information in the CSV file that you have selected, including:

1. Owner Name

2. Ranch Name

3. Address

4. City

5. State 

6. Zip

7. Phone number

#### Center
This the main table of the report where the columns are the fields that are filled via BSE app and rows are the bull information entered through the app.

#### Bottom

This is the space for graphs. If there is only one graph and the number of rows are below 13 then the graph will be present on the same page else the graph will move to the next page.


## Built With

* Netbeans - IDE For Java
* JAVA Swing 
* Java Pdf plug link

## Versioning

We use Github for versioning. For the versions available, see the [tags on this repository](https://github.com/santoslab/apps-4-vet-med/tree/master/bse-a4vm/Reporter). 

## Authors

* **Shubh Chopra** - *Initial work* -

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Developer Team: Dr. Venkatesh-Prasad Ranganath, Joydeep Mitra
* Veterinarian Team: Nora Schrag, Robert Larson

