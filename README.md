# bbs-elevator

# Setup

Download project: git clone https://github.com/BBS-SonyIstanbul/bbs-elevator.git

Import project to your workspace via Gradle or Maven

- Run gradle clean build or
- Run mvn clean install

Check if everything is running fine:

Run KillMeTest.groovy

# Description

We all have been crazy about waiting elevators at least once or twice. Here is a sample code waiting for you to configure as your desired elevator logic.

- Elevator, Passenger are simple objects which have obvious functionalities.
- ElevatorLogic can deal with multiple elevators and open for your implementation as MyElevatorLogic
- Chrone indicates time changes in the logic. Please note that elevators are working as synchronized.
- Metric is being used for keeping the track of time.

# Goal

Multiple algorithms can be carried out for different goals of elevator system (eg. Least move of elevator, shortest waiting time for passenger, shortest lead time for passenger etc.)

Specifically for this Code Kata Session, we will be aiming for shortest lead time for passengers which means difference between calling an elevator and getting off one.

At the end of Kata Session, we all will be running ChroneSimulator to see whose output will be indicating shortest lead time.

Hint: Number of elevators is up to you!

# Execution

Please note that;

This is a 30 floor apartment which passengers mostly use floor 0 to embark/disembark!
Usage of elevators are weighted due to time of the day as:
- Between hours 0 and	1	weight is 10
- Between hours 1 and	7	weight is 5
- Between hours 7 and	9	weight is 30
- Between hours 9 and	17	weight is 10
- Between hours 17 and	20	weight is 30
- Between hours 20 and 24	weight is 15
Simulator will run for 5 days with 1000 passenger a day...

You can use MyElevatorLogic.groovy with Groovy or Java
