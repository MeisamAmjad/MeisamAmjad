#library("EpiModel")
#library("ndtv")

# Initializing a network with 500 people with different
# races and ages. 
nw <- network.initialize(n = 500, directed = FALSE)
nw <- set.vertex.attribute(nw, "race", rbinom(500, 1, 0.5))
nw <- set.vertex.attribute(nw, "age", sample(18:50, 500, TRUE))

# Defining the formation of the model from races and ages.
# Defining distribution and coefficients
formation 	<- ~edges + nodefactor("race") + absdiff("age") + concurrent
target.stats 	<- c(50, 70, 100, 30)
coef.diss 	<- dissolution_coefs(dissolution = ~offset(edges), duration = 40)
est 			<- netest(nw, formation, target.stats, coef.diss, verbose = FALSE)

# Defining the infection prabability az 20% with population 
# of 50 infected people.
# Defining a Susceptible to Infected (SI) model
param 	<- param.net(inf.prob = 0.2)
init 	<- init.net(i.num = 50)
control 	<- control.net(type = "SI", nsteps = 50, nsims = 1, verbose = FALSE)
sim 		<- netsim(est, param, init, control)

# Defining the final network based on the model
nw <- get_network(sim)

# Defining a color for elements in the network
nw <- color_tea(nw, verbose = FALSE)

######################################################
# Preparing the parameters for producing the animation
slice.par 	<- list(start = 1, end = 50, interval = 1, 
                  aggregate.dur = 1, rule = "any")
render.par 	<- list(tween.frames = 10, show.time = FALSE)
plot.par 		<- list(mar = c(0, 0, 0, 0))

compute.animation(nw, slice.par = slice.par, verbose = TRUE)

race 		<- get.vertex.attribute(nw, "race")
race.shape 	<- ifelse(race == 1, 4, 50)

age 			<- get.vertex.attribute(nw, "age")
age.size 		<- age/25

# producing the animation and saving it
render.d3movie(
    nw,
    render.par 	= render.par,
    plot.par 		= plot.par,
    vertex.cex 	= age.size,
    vertex.sides 	= race.shape,
    vertex.col 	= "ndtvcol",
    edge.col 		= "darkgrey",
    vertex.border 	= "lightgrey",
    displaylabels 	= FALSE,
    filename 		= paste0(getwd(), "/movie.html"))

#render.d3movie(nw)
#data(model)
#render.animation(model)
#ani.replay()
