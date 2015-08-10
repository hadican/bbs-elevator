package com.deniz.elevator

/**
 * author: TRYavasU
 * date: 01/08/2015
 */
class Chrone {

    int now = 0
    def futurePassengers
    Metric metric
    MyElevatorLogic elevatorLogic

    Chrone(maxFloor, futurePassengers, numberOfElevators) {
        this.metric = new Metric(futurePassengers)
        elevatorLogic = new MyElevatorLogic(maxFloor, numberOfElevators, metric)
        this.futurePassengers = futurePassengers
    }

    void passTime() {
        def elevators = elevatorLogic.elevators
        //        println "===Time $now==="
        elevators.each {
            //          println "Elevator $it.id is on floor: $it.currentFloor"
        }
        //Call elevator if passenger's time has come
        futurePassengers.findAll {
            it.time == now
        }.each { Passenger passenger ->
            //println "Passenger $passenger.name is calling elevator from floor: $passenger.sourceFloor  at time:$passenger.time"
            elevatorLogic.addPassengerToCallerList(passenger, now)
            futurePassengers.remove(passenger)
        }

        //Load passengers
        elevatorLogic.decideElevatorToEmbark(now)

        elevators.each { Elevator elevator ->
            //Unload passengers
            elevator.disembarkPassengers(now)
        }

        //Move elevators
        elevatorLogic.moveElevators()

        now++
    }
}
