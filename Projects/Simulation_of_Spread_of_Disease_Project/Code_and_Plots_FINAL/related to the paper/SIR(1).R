
# Simulation of SIR disease transmission model
# Introduction to Computational Science -- Shiflet & Shiflet 
# Module 6.2
# R solution by Stephen Davies, University of Mary Washington

# Set up our time increment and our vector (array) of x (time) values
# change deltaX to either 1, 5 or 10 to get all 3 graphs

deltaX 			<- 5   				# day
x 				<- seq(0, 150, deltaX)

# Constants
infectionRate	<- .5  				# 1/people*day (rate per infected per day)
recoveryRate 	<- 1/3       		# 1/day
initSpop 		<- 1          		# people (number of people susceptible)
initIpop 		<- 1.26 * 10^-6     # people (number of people infected)
initRpop 		<- 0            		# people (number of people recovered)

# Set up our stock variables and initial conditions.
Spop 			<- vector(length = length(x))
Spop[1] 			<- initSpop
Ipop 			<- vector(length = length(x))
Ipop[1] 			<- initIpop
Rpop 			<- vector(length = length(x))
Rpop[1] 			<- initRpop

# Loop a standard number of times, starting with i=2 since we've already
# set up the simulation's initial conditions at i=1.
for (i in 2:length(x)) {

    # Compute rates of change (in people per day) for each of the three
    #   categories.
    Sprime 		<- -(Spop[i - 1] * Ipop[i - 1] * infectionRate)
    Rprime 		<- Ipop[i - 1] * recoveryRate
    Iprime 		<- -Sprime - Rprime

    # Increase or decrease the population of each category based on
    #   the rates of change for this time step.
    Spop[i] 	<- Spop[i - 1] + (Sprime) * deltaX
    Ipop[i] 	<- Ipop[i - 1] + (Iprime) * deltaX
    Rpop[i] 	<- Rpop[i - 1] + (Rprime) * deltaX
}

plot(x, Spop, type = "n", xlab = "Time (days)", ylab = "Population",
    ylim = c(0, initSpop + initIpop + initRpop))
lines(x, Spop, lwd = 8, lty = "solid", col = "blue")
lines(x, Ipop, lwd = 8, lty = "solid", col = "red")
lines(x, Rpop, lwd = 8, lty = "solid", col = "green")
legend("topright", legend = c("Susceptible", "Infected", "Recovry"), 
		lty = c("solid", "solid", "solid"),
		col = c("blue", "red", "green"))
