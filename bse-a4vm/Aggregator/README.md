# Project Title

BSE-Aggregator is desktop extension to BSE-A4VM mobile app to aid veterinarians to combine the collected data (CSV files) from BSE-A4VM in to a single CSV file. It can export a single file from any number of files and find ambiguities in the data as well, the mass export of data in CSV format that can be used with MS Excel or other data processing applications.

## Download Link
https://github.com/santoslab/apps-4-vet-med/releases/download/1.0/Aggregator.jar

### Prerequisities

It aggregates files only generated from BSE i-Phone/Android .CSV files.
It is recomended to keep all the aggregating files in a single folder before running the application for making it easier to select files.
Make sure the file extention is .CSV, if not then it has to be changed to CSV before aggregating it.


### Installing

Just a single step process, just download the file and double click it to run.

## Getting Started 

### Startup screen
There is only one Screen on this aggregator app.
![alt tag] (https://github.com/santoslab/apps-4-vet-med/blob/master/bse-a4vm/Aggregator/img/Agg1.jpg)
The app gives you 2 options from which you can choose from
1)	Choose a folder
2)	Choose files
And then an option to combine and save the file.

### Working:
#### To generate an aggregated CSV file with 2 or more CSV files:
Step 1) Click the button “Browse” under Choose files
Follow the screen to the folder where your csv files are located.
![alt tag] (https://github.com/santoslab/apps-4-vet-med/blob/master/bse-a4vm/Aggregator/img/Agg2.jpg)
Step 2) press ctrl and Select all the CSV files that are to be aggregated.
Step 3) Click the “Combine and Save” button and save the file with (Some-Name).CSV (it is recommended to have a (.csv) at the end of the file name else you will have to choose a program to open the file.)

#### To generate an aggregated CSV file with a folder containing CSV files:
Step 1) Click the button “Browse” under Choose folder
Follow the screen to the folder where your csv files are saved.
![alt tag] (https://github.com/santoslab/apps-4-vet-med/blob/master/bse-a4vm/Aggregator/img/Agg3.jpg)
Step 2) Select the desired folder.
NOTE: The desired folder must contain only CSV files else the app won’t be able to aggregate the file.
Step 3) Click the “Combine and Save” button and save the file with (Some-Name).CSV (it is recommended to have a (.csv) at the end of the file name else you will have to choose a program to open the file.)

## Understanding the output of the App

#### All Unique Bull IDs with same morphology Fields (Same number of columns)
When the bull IDs in all the CSV files to be aggregated are unique then the output file will be a single file having all the Bulls in the order of its Bull IDs and all the fields will be perfectly aligned.
```
File1.csv:
Bull_ID	Col1	Col2	Col3
B1		X		Y		Z

File2.csv
Bull_ID	Col1	Col2	Col3
B2		A		B		C

Aggregated_file.csv
Bull_ID	Col1	Col2	Col3
B1		X		Y		Z
B2		A		B		C

```
#### All Unique Bull IDs with different morphology Fields (Different number of columns)
When the bull IDs in all the CSV files to be aggregated are unique then the output file will be a single file having all the Bulls in the order of its Bull IDs but the columns will be a union of all the morphology fields present in all the different files hence a bull in a group which doesn't have a morphology field which may be present in any other bull will be empty (And NOT 0).
```
File1.csv:
Bull_ID	Col1	Col2	Col3	Mor1
B1		X		Y		Z		1

File2.csv
Bull_ID	Col1	Col2	Col3	Mor2
B2		A		B		C		2

Aggregated_file.csv
Bull_ID	Col1	Col2	Col3	Mor1	Mor2
B1		X		Y		Z		1		
B2		A		B		C				2

```
#### Repeated Bull IDs with different morphology Fields (Different number of columns)
When the bull IDs in all the CSV files to be aggregated have duplicated Bull ID (having Both Bull ID and Bull ID Type to be same) then the output file will be a single file having all the Bulls in the order of its Bull IDs and an extra Columns in the end for Collisions. 
For instance if 2 Bulls have a same Bull ID then all its fields will be compared, there may be 3 cases for the same
1) If the information for a particular column are different that column name will be added in the collision Field in the end. 
2) If for a particular column for one bull is empty and other is filled then information from filled column will be copied to the empty field of the other.
3) If both fields are empty for a particular column then no action is taken.
```
File1.csv:
Bull_ID	Col1	Col2	Col3	
B1		X		Y		Z	
B2		D				

File2.csv
Bull_ID	Col1	Col2	Col3
B2		A		B		

Aggregated_file.csv
Bull_ID	Col1	Col2	Col3	Collison
B1		X		Y		Z
B2		D		B				Col1
B2		A		B				Col1	
```
but the columns will be a union of all the morphology fields present in all the different files hence a bull in a group which doesn't have a morphology field which may be present in any other bull will be empty (And NOT 0).


## Built With

* Netbeans - IDE For Java
* JAVA Swing 


## Versioning

We use Github for versioning. For the versions available, see the [tags on this repository](https://github.com/santoslab/apps-4-vet-med/tree/master/bse-a4vm/Aggregator). 

## Authors

* **Shubh Chopra** - *Initial work* -

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Developer Team: Dr. Venkatesh-Prasad Ranganath, Joydeep Mitra
* Veterinarian Team: Nora Schrag, Robert Larson

