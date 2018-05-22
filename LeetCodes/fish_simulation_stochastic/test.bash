#!/bin/bash

#PBS -N test
#PBS -l walltime=0:30:00
#PBS -l mem=128MB
#PBS -l nodes=1:ppn=1
#PBS -S /bin/bash
#PBS -j oe

# Change to directory from where PBS job was submitted
cd $PBS_O_WORKDIR

for threads in 1 1 1 1 1;
do
	# Timing with single thread
	echo "---------------------[ Matching ]---------------------"
	export OMP_NUM_THREADS=${threads}
	/usr/bin/time -v java Group_Project 1 data04.txt 1000000
done

for threads in 1 1 1 1 1;
do
	# Timing with single thread
	echo "---------------------[ Gale Shapley ]---------------------"
	export OMP_NUM_THREADS=${threads}
	/usr/bin/time -v java Group_Project 2 data04.txt 1000000
done

# Finally print NUMA information for this node
echo "---------------------[ NUMA configuration ]---------------------"
numactl -H

#end of script
