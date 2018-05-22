# =========================== Variables ===========================
#
# Change here if you want to read different files
#
data 	= read.csv(file = "noFishing.csv", header = FALSE, sep = ",")
years	= length(data)
trial	= length(data[, 1])
avg.pop	= vector(length = years)
se.pop  = vector(length = years)
x		= seq(1:100)
newData = matrix(nrow = years, ncol = trial)

# =========================== Functions ===========================
# This function changes Standard Deviation to Sample Standard Deviation.
sd_to_ssd = function(sd) {
	temp = sd ^ 2
	temp = temp * trial
	temp = temp / (trial - 1)
	return 	(sqrt(temp))
}

# A helper function for plotting that gives the user the ability of zooming.
drawPlot = function(zoom = FALSE, zoom.x1 = 20, zoom.x2 = 50, zoom.y1 = 4500, zoom.y2 = 4800) {
	if (zoom)
		plot(x, avg.pop, type = "l", 
			xlim = c(zoom.x1, zoom.x2),
			ylim = c(zoom.y1, zoom.y2),
			col = "blue",
			main = paste("Fish population during 100 years with ", trial, " trial\n No Fishing during each year\n", 								"zoom in x = (", zoom.x1, ", ", zoom.x2, ") y = (", zoom.y1, ", ", zoom.y2, ")"),
			xlab = "Year", ylab = "Fish Population")
	else
		plot(x, avg.pop, type = "l", col = "blue",
			main = paste("Fish population during 100 years with ", trial, " trial\n No Fishing during each year"),
			xlab = "Year", ylab = "Fish Population")
	
	points(x, avg.pop + se.pop, pch = '-', col = "orange")
	points(x, avg.pop - se.pop, pch = '-', col = "orange")
	for (i in 1:years)
		lines(c(i, i), c(avg.pop[i] - se.pop[i], avg.pop[i] + se.pop[i]), lwd = 2, col = "red")
}

# =========================== Calculation ===========================
# Reshaping data
for (col in 1:years)
	for (row in 1:trial)
		newData[col, row] = data[row, col]

# Calculating average and Sample Standard Deviation for each year
sqrtN	= sqrt(trial)
for (i in 1: years) {
	avg.pop[i] 	= sum(newData[i, ]) / trial
	ssd			= sd_to_ssd(sd(newData[i, ]))
	se.pop[i]	= ssd / sqrtN
}

# =========================== Plotting ===========================
par(mfrow = c(1, 2))
drawPlot(zoom = FALSE)
drawPlot(zoom = TRUE, zoom.x1 = 30, zoom.x2 = 100, zoom.y1 = 4800, zoom.y2 = 4900)
