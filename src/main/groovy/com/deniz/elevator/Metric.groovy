package com.deniz.elevator

/**
 * author: TRYavasU
 * date: 03/08/2015
 */
class Metric {
    def passengerMetric = []
    def elevatorMetric = []

    Metric(passengers) {
        this.passengerMetric = passengers?.collect {
            [name: it.name]
        }
    }

    void passengerCalledElevator(passenger, now) {
        passengerMetric.find { it.name == passenger.name }.callTime = now
    }

    void loadPassengerMetric(caller, now) {
        passengerMetric.find { it.name == caller.name }.boardingTime = now
    }

    void unloadPassengerMetric(passenger, now) {
        passengerMetric.find { it.name == passenger.name }.departingTime = now
    }
}
