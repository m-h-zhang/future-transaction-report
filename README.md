This project is a maven project with spring boot framework.

It needs  maven 3.3 or above  and JDK 9 or above.

1. How to build the software

     1.1. download or clone the project from the git hub to  your local folder

     1.2. enter into the project folder future-transaction-report.

     1.3. run 'mvn clean package' build the project to target folder.

2. How to use the software

     2.1.  At the same  project folder, run  
         'java -jar target\future-transaction-report-0.0.1-SNAPSHOT.jar'
          The software will read data from the existing file, input.txt form the classpath inside of jar file and generate output.csv and info.log
           in the current project folder.

     2.2.  To run new input.txt, create a new application.property file with
          new value of property 'input.file.name' in  the file,  then run the following command to use new property file.
          java -jar target\future-transaction-report-0.0.1-SNAPSHOT.jar --spring.config.location=file:/path-to-config-dir/application.properties

     2.3. To use new data format in input.txt,  create a new file,
          input_columns.txt in the classpath and it will override the file in the jar.

     2.4. The following properties are in application.properties, and all
          values can be overrided.

         input.column.file=input/input_columns.txt
         input.column.separator=,
         csv.column.break=,
         csv.line.break=\n
         logging.file=./info.log
         input.file.name=input/input.txt
         output.file.name=output.csv
         report.header.names=Client_Information,Product_Information,Total_Transaction_Amount

3. How to troubleshooting

   When error happens,  you can check the log to find which step below gets the issue.

   When the application starts,  it follows the following workflow.
   
       3.1. The application triggers a Job, which controls all steps.
       
       3.2. Firstly the Job calls a reader to read the input.txt line by line.
       
       3.3. For each line, the job will call a line mapper to map the selected data
           to a java object based on the data sequence and length in input_columns.txt,
           then aggregate the java object to a map.
      
      3.4. In the next step,  the job will call a processor to create a report Object.

      3.5. Lastly the job will call writer to write the report object to csv output file.

4. TODO list

    It is a little bit rush for this version in a short timeframe and it can just meet the requirement.  However there are some places        which can do better in the next version.

       4.1. unit test case
         The Unit test cases just covered the happy scenarios and need to add more error scenarios and boundary cases.
         
       4.2. Exception Handling
         At the moment, all exceptions would be logged and thrown out to root caller and the application will stop immediately.                  Exception
         handling can be added to process
         
       4.3. Migration to Spring batch for advanced functions
         Most classes are POJO classes with spring annotation. It could be very easy to migrate to spring batch for advanced functions.
