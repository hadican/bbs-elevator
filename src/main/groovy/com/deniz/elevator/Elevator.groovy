package com.deniz.elevator

import static com.deniz.elevator.Elevator.Direction.*

/**
 * author: TRYavasU
 * date: 02/08/2015
 */
class Elevator {

    def id
    int currentFloor = 0
    Direction direction = IMMOBILE
    def passengerList = []
    def maxFloor
    def effort = 0
    def metric

    Elevator(maxFloor, id, metric) {
        this.maxFloor = maxFloor
        this.id = id
        this.metric = metric
    }

    void move(Direction direction) {
        if (direction == DOWN) {
            effort++
            currentFloor--
        } else if (direction == UP) {
            effort++
            currentFloor++
        }
        this.direction = direction
    }

    void disembarkPassengers(now) {
        passengerList.findAll {
            it.targetFloor == currentFloor
        }.each {
            //println "Passenger with name : $it.name has left the elevator"
            metric.unloadPassengerMetric(it, now)
            passengerList.remove(it)
        }
    }

    void loadPassenger(passenger, now) {
        passenger.isTemporary = false
        passengerList.add(passenger)
        metric.loadPassengerMetric(passenger, now)
    }

    enum Direction {
        UP, DOWN, IMMOBILE
    }

}
