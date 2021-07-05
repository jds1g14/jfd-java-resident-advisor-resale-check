# jfd-spring-resident-advisor-resale-check v2.0.1

# Getting Started
    1. Generate executable jar using 'mvn clean package'
    2. cd to the location in which the jar is located using command prompt
    3. input command:
       java -jar *jar_file_name*.jar "*resident-advisor-event-url*" "*refresh_period_mins*"  "*Ticket_Name*"
    
    Example Command:
       java -jar jfd-spring-resident-advisor-resale-check-0.3.jar "https://ra.co/events/1326673" "5"  "Live In Vehicle Pass"
       
    The above command will check the webpage once every 5 mins for at least one "Live in Vehicle Pass" ticket. A windows popup
    will be generated if the ticket is available for sale. 
    
# Version History

    v0.1 - Initial Commit
    v0.2 - Introduction of user input (Main method args)
    v0.3 - Display Notifications and added looping for ticket check
    v1.0 - Fix issue where refresh period would elapse before checking webpage
    V.2.0.1 - Added ability to query multiple releases and addition of jar to github


