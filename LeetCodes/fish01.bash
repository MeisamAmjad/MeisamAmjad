#!/bin/bash

#PBS -N test
#PBS -l walltime=10:00:00
#PBS -l mem=1GB
#PBS -l nodes=1:ppn=1
#PBS -S /bin/bash
#PBS -j oe

# Change to directory from where PBS job was submitted
cd $PBS_O_WORKDIR

c++ -c -Wall -std=c++14 -O3 noFishing.cpp -o noFishing

# Timing with single thread
echo "---------------------[ NoFishing ]---------------------"
export OMP_NUM_THREADS=1
/usr/bin/time -v ./noFishing

#end of script
