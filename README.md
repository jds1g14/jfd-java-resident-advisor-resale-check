# jfd-spring-resident-advisor-resale-check v1.0.0

# Getting Started
    1. Generate executable jar using 'mvn clean package'
    2. cd to the location in which the jar is located using command prompt
    3. input command:
       java -jar *jar_file_name*.jar "*resident-advisor-event-url*" "*refresh_period_mins*"  "*Ticket_Name*"
    
    Example Command:
       java -jar jfd-spring-resident-advisor-resale-check-1.0.0.jar "https://ra.co/events/1479289" "5" "Entry before 12am Release 2"
       
    The above command will check the webpage once every 5 mins for at least one "Live in Vehicle Pass" ticket. A windows popup
    will be generated if the ticket is available for sale. 
    
# Version History

    v1.0 Initial Commit


