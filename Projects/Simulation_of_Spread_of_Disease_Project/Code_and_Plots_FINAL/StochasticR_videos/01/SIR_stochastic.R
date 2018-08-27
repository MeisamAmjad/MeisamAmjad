library(EpiModel)
#library(ndtv)

#param <- param.icm(inf.prob = 0.2, act.rate = 0.2, rec.rate = 1/50)
param <- param.icm(inf.prob = 0.2, act.rate = 0.3, rec.rate = 1/50)
init <- init.icm(s.num = 500, i.num = 10, r.num = 0)
control <- control.icm(type = "SIR", nsteps = 500, nsims = 5)
mod2 <- icm(param, init, control)
mod2
plot(mod2, main="Infection probability = 0.2\n Recovery rate = 1/50\nRandom action rate = 20%")