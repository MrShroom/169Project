import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class SimulatedAnnealingOptimizer {

	public static void main(String[] args) throws IOException {
		double max_temp = 100.0;	// The max temperature in the simulated annealing
		double cur_temp = max_temp;	// The current temperature in the simulated annealing, initialized at the max temp
		
		int sessions_at_cur_temp = 1;	// The number of sessions to play at the current temperature.
		double stop_percent = 1;		// The percentage value of the max_temp at which to stop.
		
		double heuristic_min = -100.0;	//
		double heuristic_max =  100.0;	// The max and min values passed to the AI constructor.
		
		GameRunner myRunner = new GameRunner();	// Initialize a GameRunner
		
		// BEGIN ANNEALING
		double[] current_heuristic = myRunner.generateParameters();
		double current_winrate = myRunner.playSession(current_heuristic);
		
		while(cur_temp > max_temp * stop_percent * 0.01){	// Stop the simulation when current temp is less than some percent of the max_temp
			for(int i = 0; i < sessions_at_cur_temp; i++){	// For each session we play at current temp:
				double[] heuristic_prime = current_heuristic.clone();	// Clone the current heuristic into heuristic prime (before changing heuristic prime)
				
				double[] shuffled_heuristic = heuristic_prime;			// Shuffle the heuristic prime for fair iteration
				Collections.shuffle(Arrays.asList(shuffled_heuristic));	// 
				
				for(int j = 0; j < shuffled_heuristic.length; j++){								// For each variable in the shuffled heuristic prime:
					double old_value = shuffled_heuristic[j];									// Store the old value in case the new is not accepted
					shuffled_heuristic[j] = Math.random() * heuristic_max*2 + heuristic_min;	// Set the variable to a random double in range
					double winrate_prime = myRunner.playSession(heuristic_prime);				// Calculate the winrate with the heuristic prime
					if(winrate_prime > current_winrate){										// If the winrate prime is greater than the current winrate,
						current_heuristic = heuristic_prime.clone();							// Take it immediately
						current_winrate = winrate_prime;
					}
					else if (Math.random() < Math.exp(Math.abs(winrate_prime - current_winrate) * -1/cur_temp)){ // Else, for some probability (decreasing with temperature)															// Else,
						current_heuristic = heuristic_prime.clone();											 // Take the winrate prime
						current_winrate = winrate_prime;
					}
					else{shuffled_heuristic[j] = old_value;}									// Otherwise, we don't accept the new heuristic
					
					if(current_winrate > 0.999){												// If your winrate is high, break
						break;
					}
				}
				
				cur_temp = max_temp * 0.96;														// Decrement the current temp and start again				}
			}
		}
		myRunner.close();
	}
}
