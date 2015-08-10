package com.deniz.elevator

/**
 * author: TRYavasU
 * date: 02/08/2015
 */
class Passenger {

    String name
    int time
    int sourceFloor
    int targetFloor
    boolean isTemporary

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", sourceFloor=" + sourceFloor +
                ", targetFloor=" + targetFloor +
                ", isTemporary=" + isTemporary +
                '}';
    }

    int nextStep() {
        isTemporary ? sourceFloor : targetFloor
    }

    Elevator.Direction desiredDirection() {
        if (sourceFloor > targetFloor) {
            Elevator.Direction.DOWN
        } else {
            Elevator.Direction.UP
        }
    }
}
