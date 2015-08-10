package com.deniz.elevator
/**
 * author: TRYavasU
 * date: 02/08/2015
 */
abstract class ElevatorLogic {
    def elevators
    def callerList = []
    Metric metric

    ElevatorLogic(maxFloor, numberOfElevators, metric) {
        def elevators = []
        numberOfElevators.times {
            elevators << new Elevator(maxFloor, it + 1, metric)
        }
        this.elevators = elevators
        this.metric = metric
    }

    void addPassengerToCallerList(def passenger, now) {
        callerList.add(passenger)
        metric.passengerCalledElevator(passenger, now)
    }

    void decideElevatorToEmbark(now) {
        callerList.each { Passenger caller ->
            def suitableElevator = findSuitableElevator(caller)
            if (suitableElevator) {
                suitableElevator.loadPassenger(caller, now)
                //println "Passenger with name : $caller.name has entered the elevator: $suitableElevator.id wants to go to floor: $caller.targetFloor"
                callerList -= caller
            }
        }
    }

    abstract Elevator findSuitableElevator(caller)

    abstract def moveElevators()

}