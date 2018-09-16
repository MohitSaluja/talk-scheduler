# Talk Scheduler Code​ ​Challenge  
This is a spring boot console application. When you run ChallengeApplication class as a Java application then it asks for below two inputs:     
PLEASE PROVIDE INPUT FILE'S FILENAME AND ABSOLUTE PATH. e.g /Users/Mohit.Saluja/Desktop/scope/Sessions.txt    
PLEASE PROVIDE OUTPUT FILE'S FILENAME AND ABSOLUTE PATH. e.g /Users/Mohit.Saluja/Desktop/scope/Sessions-output.txt    
    
Once you provide input and output file, this project schedules input talks/sessions to the workshop/meeting rooms.    
    
## Task:

Task is to write a software that helps in scheduling sessions for workshops. 

The workshop has multiple tracks each of which has a morning and afternoon session.
Each session contains multiple talks.
Morning sessions begin at 9am and must finish by 12 noon, for lunch.
Afternoon sessions begin at 1pm and must finish in time for the Meet Your Colleagues Event.
The Meet Your Colleagues Event can start no earlier than 4:00PM and no later than 5:00PM.
No talk title has numbers in it.
All talk lengths are either in minutes or lightning (5 minutes).
Presenters will be very punctual; there needs to be no gap between sessions.


### Sample Input file:  

Create better mocks for Spring Boot 65min
More Java for people 40min
Fun with Kotlin 30min
Managing dependencies with Maven 45min
Better error handling in Java 45min
Scala from JEE guys lightning
Slack for old people 60min
Finance Domain explained 45min
Healthier lunch in Berlin 30min
Scope Future 30min
Better work in Teams 45min
Best Spring Features 60min
Advance Spring Boot 60min
Why Clojure Matters 45min
Living in Berlin 30min
Working with Azure 30min
Maintain Java Code 60min
Better Way of reading Books 30min
What you need to know about ExtJS 30min


### Sample output file:  
Track 1:
9:00 AM Create better mocks for Spring Boot 65min
10:05 AM Slack for old people 60min
11:05 AM Managing dependencies with Maven 45min
11:50 AM Scala from JEE guys lightning
12:00 PM Lunch
1:00 PM Best Spring Features 60min
2:00 PM Advance Spring Boot 60min
3:00 PM Maintain Java Code 60min
4:00 PM Better error handling in Java 45min
4:45 PM Meet Your Colleagues Event

Track 2:
9:00 AM Finance Domain explained 45min
9:45 AM Better work in Teams 45min
10:30 AM Why Clojure Matters 45min
11:15 AM More Java for people 40min
12:00 PM Lunch
1:00 PM Fun with Kotlin 30min
1:30 PM Healthier lunch in Berlin 30min
2:00 PM Scope Future 30min
2:30 PM Living in Berlin 30min
3:00 PM Working with Azure 30min
3:30 PM Better Way of reading Books 30min
4:00 PM What you need to know about ExtJS 30min
4:30 PM Meet Your Colleagues Event


#### Configuratons:  
Below configurations are kept configurable. 

challenge.lightningMeetingDuration=5
challenge.morningStartHour=9
challenge.morningStartMinutes=0
challenge.lunchHour=12
challenge.lunchMinutes=0
challenge.lunchDuration=60
challenge.afternoonEndHour=17
challenge.afternoonEndMinutes=0
challenge.meetYourColleaguesEarliestStartHour=4
challenge.meetYourColleaguesEarliestStartMinutes=0
