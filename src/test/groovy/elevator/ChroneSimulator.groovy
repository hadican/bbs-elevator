package elevator

import com.deniz.elevator.Chrone
import com.deniz.elevator.Passenger
import elevator.util.RandomUtil
import org.junit.Test

/**
 * author: TRYavasU
 * date: 01/08/2015
 */
class ChroneSimulator {

    final def PROBABILITY_OF_FLOORS = [[0, 1, 50], [1, 31, 2]]
    final int NO_OF_PASSENGERS = 1000
    final int NO_OF_ELEVATORS = 5

    final
    def PROBABILITY_IN_TIME = [[0, 3600, 10], [3601, 25200, 5], [25201, 32400, 30], [32401, 61200, 10], [61201, 72000, 30], [72001, 86400, 15]]
    final def TIMES = 5

    @Test
    void callElevatorInTimeTest() {
        def maxFloor = PROBABILITY_OF_FLOORS[PROBABILITY_OF_FLOORS.size() - 1].get(1) - 1
        for (int i = 1; i <= NO_OF_ELEVATORS; i++) {
            def averageServingTime = 0
            def averageTravelTime = 0
            def averageLeadTime = 0
            TIMES.times {
                def passengers = []
                boolean justiceFlag = true

                NO_OF_PASSENGERS.times {
                    def sourceFloor = RandomUtil.generatedRandomness(PROBABILITY_OF_FLOORS)
                    def targetFloor = RandomUtil.generatedRandomness(PROBABILITY_OF_FLOORS)
                    while (sourceFloor == targetFloor) {
                        if (justiceFlag) {
                            targetFloor = RandomUtil.generatedRandomness(PROBABILITY_OF_FLOORS)
                            justiceFlag = false
                        } else {
                            sourceFloor = RandomUtil.generatedRandomness(PROBABILITY_OF_FLOORS)
                            justiceFlag = true
                        }
                    }
                    passengers << new Passenger(time: RandomUtil.generatedRandomness(PROBABILITY_IN_TIME), sourceFloor: sourceFloor, targetFloor: targetFloor, name: RandomUtil.randomIdentifier())
                }

/*
                println "PASSENGER MANIFEST"
                passengers.sort {
                    it.time
                }.each {
                    println it
                }
*/

                Chrone chrone = new Chrone(maxFloor, passengers, i)

                while (chrone.elevatorLogic.elevators.passengerList.sum() || chrone.elevatorLogic.callerList || chrone.futurePassengers) {
                    chrone.passTime()
                }

                def sumOfPassengerCallElevators = chrone.metric.passengerMetric.callTime.sum()
                def sumOfPassengerBoardElevators = chrone.metric.passengerMetric.boardingTime.sum()
                def sumOfPassengerDepartElevators = chrone.metric.passengerMetric.departingTime.sum()

                averageServingTime += (sumOfPassengerBoardElevators - sumOfPassengerCallElevators) / NO_OF_PASSENGERS

                averageTravelTime += (sumOfPassengerDepartElevators - sumOfPassengerBoardElevators) / NO_OF_PASSENGERS

                averageLeadTime += (sumOfPassengerDepartElevators - sumOfPassengerCallElevators) / NO_OF_PASSENGERS

                /*
                println "a"
                println "Passenger travel time: ${averageTravelTime}"
                println "Passenger journey time: ${averageLeadTime}"
                println "Caller wait time: ${averageServingTime}"
                 */
            }

            println "$NO_OF_PASSENGERS passengers in $TIMES day with $i elevators on a $maxFloor floor apartment"
            println "Caller wait time: ${averageServingTime / TIMES}"
            println "Passenger travel time: ${averageTravelTime / TIMES}"
            println "Passenger journey time: ${averageLeadTime / TIMES}"
        }
    }

}