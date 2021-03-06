#Remove all objects from workspace.
remove (list = objects() )
#Load add-on packages - deSolve - contains lsoda function - differential equation solver.
library (deSolve) 
## 
## Attaching package: 'deSolve'
## 
## The following object is masked from 'package:graphics':
## 
##     matplot
#Function to compute derivatives of the differential equations.
seir_model = function (current_timepoint, state_values, parameters)
{
  # create state variables (local variables)
  S = state_values [1]        # susceptibles
  E = state_values [2]        # exposed
  I = state_values [3]        # infectious
  R = state_values [4]        # recovered
  
  with ( 
    as.list (parameters),     # variable names within parameters can be used 
         {
           # compute derivatives
           dS = (-beta * S * I)
           dE = (beta * S * I) - (delta * E)
           dI = (delta * E) - (gamma * I)
           dR = (gamma * I)
           
           # combine results
           results = c (dS, dE, dI, dR)
           list (results)
         }
    )
}
#Parameters
contact_rate = 10                     # number of contacts per day
transmission_probability = 0.07       # transmission probability
infectious_period = 5                 # infectious period
latent_period = 2                     # latent period
#Compute values of beta (tranmission rate) and gamma (recovery rate).
beta_value = contact_rate * transmission_probability
gamma_value = 1 / infectious_period
delta_value = 1 / latent_period
#Compute Ro - Reproductive number.
Ro = beta_value / gamma_value
#Disease dynamics parameters.
parameter_list = c (beta = beta_value, gamma = gamma_value, delta = delta_value)
#Initial values for sub-populations.
W = 9990        # susceptible hosts
X = 1           # infectious hosts
Y = 0           # recovered hosts
Z = 9           # exposed hosts
#Compute total population.
N = W + X + Y + Z
#Initial state values for the differential equations.
initial_values = c (S = W/N, E = X/N, I = Y/N, R = Z/N)
#Output timepoints.
timepoints = seq (0, 50, by=1)
#Simulate the SEIR epidemic.
#############
output = lsoda (initial_values, timepoints, seir_model, parameter_list)
#############
#Plot dynamics of Susceptibles sub-population.
plot (S ~ time, data = output, type='b', col = 'blue')       
# Plot dynamics of Exposed sub-population
plot (E ~ time, data = output, type='b', col = 'pink')  

#Plot dynamics of Infectious sub-population.
plot (I ~ time, data = output, type='b', col = 'red') 

#Plot dynamics of Recovered sub-population.
plot (R ~ time, data = output, type='b', col = 'green')  

#Plot dynamics of Susceptibles, Exposed, Infectious and Recovered sub-populations in the same plot.
# susceptible hosts over time
plot (S ~ time, data = output, type='b', ylim = c(0,1), col = 'blue', ylab = 'S, E, I, R', main = 'SEIR epidemic') 

# remain on same frame
par (new = TRUE)    

# exposed hosts over time
plot (E ~ time, data = output, type='b', ylim = c(0,1), col = 'pink', ylab = '', axes = FALSE)

# remain on same frame
par (new = TRUE) 

# infectious hosts over time
plot (I ~ time, data = output, type='b', ylim = c(0,1), col = 'red', ylab = '', axes = FALSE) 

# remain on same frame
par (new = TRUE)  

# recovered hosts over time
plot (R ~ time, data = output, type='b', ylim = c(0,1), col = 'green', ylab = '', axes = FALSE)