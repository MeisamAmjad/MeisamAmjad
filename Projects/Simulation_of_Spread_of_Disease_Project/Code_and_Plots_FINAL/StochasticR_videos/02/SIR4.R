#library(EpiModel)
#library(ndtv)

# Network model estimation
num.m1		 <- num.m2 <- 250  # number of Susceptibles
nw           <- network.initialize(n = num.m1 + num.m2, 
									bipartite = num.m1, 
									directed = FALSE)

# For the target statistics, we specify the number of edges from
# a mean degree as 0.66 (100/2 * 0.66 = 165)
formation  <- ~edges + b1degree(0:1) + b2degree(0:1)
target.stats <- c(165, 100, 137.5, 135, 77.5)
#target.stats <- c(33, 20, 27.5, 27, 15.5)

coef.diss <- dissolution_coefs(dissolution = ~offset(edges), duration = 10, d.rate = 0)

# Reestimate the model with new coefficient
est <- netest(nw, formation, target.stats, coef.diss, verbose = FALSE)

# Reset parameters to include demographic rates
param   <- param.net(inf.prob 	= 0.2, 
					inf.prob.m2 	= 0.2,
					rec.rate 	= 0.02,
					rec.rate.m2 	= 0.02)
						
init    <- init.net(i.num 		= 10, 
					i.num.m2 	= 10, 
					r.num 		= 0,
					r.num.m2		= 0)
						
control <- control.net(type 	= "SIR",
						nsims 	= 5,
						nsteps 	= 500)

# Simulate the model with new network fit
mod    <- netsim(est, param, init, control)

# plot(mod)

# Automatic coloring of infected nodes as red
#par(mfrow = c(1, 2), mar = c(0, 0, 2, 0))
Sys.sleep(30)
for (i in 1:500){	
	plot(mod, 
		type = "network",
		sims = "max",
		displayisolates = TRUE,
		col.status = TRUE, 
		main = "SIR model",
		plots.joined = TRUE,
		legend = FALSE,
		qnts.smooth = TRUE,
		at = i)	
	#Sys.sleep(1)
}
