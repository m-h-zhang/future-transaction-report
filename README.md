This project is a maven project with spring boot framework.

It needs  maven 3.3 or above  and JDK 9 or above.

How to build the software

1. download or clone the project from the git hub to  your local folder

2. enter into the project folder future-transaction-report.

3. run 'mvn clean package' build the project to target folder.

How to use the software
 1.  At the same  project folder, run  'java -jar target\future-transaction-report-0.0.1-SNAPSHOT.jar'
     The software will read existing input.txt form the classpath inside of jar file and generate output.csv and info.log
      in the current project folder.
     
2.   To run new input.txt, create a new application.property file with  new value of property 'input.file.name' 
     in  the file,  then run the following command to use new property file.
     java -jar target\future-transaction-report-0.0.1-SNAPSHOT.jar --spring.config.location=file:/path-to-config-dir/application.properties
     
     
3.   You can even use new data format in input.txt by overriding the format information in  input_columns.txt and do the same steps as above 
     point to use this new format file.

t.   The following properties are in application.properties, and can be overrided.

    input.column.file=input/input_columns.txt
    input.column.separator=,
    csv.column.break=,
    csv.line.break=\n
    logging.file=./info.log
    input.file.name=input/input.txt
    output.file.name=output.csv
    report.header.names=Client_Information,Product_Information,Total_Transaction_Amount

How to troubleshooting
   When error happens,  you can check the log to find which step below gets the issue.

   When the application starts,  it follows the following workflow.
   1. The application triggers a Job, which controls all steps.
   2. Firstly the Job calls a reader to read the input.txt line by line.
   3. For each line, the job will call a line mapper to map the selected data
      to a java object based on the data sequence and length in input_columns.txt,
      then aggregate the java object to a map.
   4. In the next step,  the job will call a processor to create a report Object.

   5. Lastly the job will call writer to write the report object to csv output file.
