# Game-of-Life

Inspired by John Connway's Game of Life: https://playgameoflife.com
<br />
The Game of Life is a cellular automaton, which shows simultaneous cell death and birth based on certain rules. 

Rules: 

    -Each cell with one neighbour or no neighbour dies 
    -Each cell with four or more neighbours dies
    -Each cell with two or three neighbors survives
    -When a space is empty, each cell with three neighbors becomes populated 
  
The starting data from the file will be read in from a text file (input files) and the simulation will run on a turn-by-turn basis. There is also a taminator class (shown as a T in grid), where the taminator can't die or be born. 
The Biosphere will follow the orginal rules of the games, while the Prosperous Biosphere will follow different rules. 

Different Rules: 

    -Deaths occur if there is fewer than one neighbour or more than four neighbours 
    -Births occur if there are exactly 4 neighbours
    
The user can enter 'P' or 'p' (regardless of the input file chosen by the user) to apply the rules of the ProsperousBiosphere. An 'R' or an 'r' will apply the rules of the regular biosphere.
The user can also quit using 'Q' or 'q' to end the simulation. 

To run the program: 

    1. First compile GameofLife.java in terminal 
    2. Then run GameofLife.java to start simulation 
    3. The program will ask for input (ProsperousBiosphere or Biosphere rules) 
    4. The program will ask for name of the input file (ex. prosperous.txt)
    5. Any key can be hit to continue the turn or user can quit by hitting 'Q' or 'q'
    6. The display will show three grids (previous generation, the births and deaths, and a view of the Taminator).
    
    
  

