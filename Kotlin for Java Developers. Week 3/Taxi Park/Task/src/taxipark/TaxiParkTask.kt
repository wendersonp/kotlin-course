package taxipark


/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        allDrivers.filter { it !in (trips.map { trip -> trip.driver } )}.toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        allPassengers.filter { passenger ->
            trips.count { trip ->
                passenger in trip.passengers
            } >= minTrips
        }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        allPassengers.filter { passenger ->
            trips.count {trip ->
                driver == trip.driver && passenger in trip.passengers
            } > 1
        }.toSet()

fun Trip.hasDiscount(): Boolean = (discount ?: 0.0) > 0

fun Trip.hasNoDiscount(): Boolean = !hasDiscount()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        allPassengers.filter { passenger ->
            val passengerTrips = trips.filter {passenger in it.passengers}
            passengerTrips.count(Trip::hasDiscount) > passengerTrips.count(Trip::hasNoDiscount)
        }.toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return trips.groupingBy { (it.duration/10) * 10..(it.duration/10) * 10 + 9 }
            .eachCount()
            .maxByOrNull { (_, second) -> second }
            ?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) {
        return false
    }
    val driversIncomeList = allDrivers.map { driver ->
        driver to trips
                    .filter { trip -> driver == trip.driver }
                    .sumOf { it.cost }
    }
    val driversIncomeSorted = driversIncomeList
            .map { it.second }
            .sortedDescending()
    val totalIncome = driversIncomeSorted.sum()
    val selectedCount = (driversIncomeSorted.size * 0.2).toInt()
    val paretoIncome = driversIncomeSorted
            .slice(0 until selectedCount)
            .sum()
    return paretoIncome >= totalIncome * 0.8
}