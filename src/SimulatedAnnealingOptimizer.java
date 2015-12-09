import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class SimulatedAnnealingOptimizer {

	public static void main(String[] args) throws IOException {
		double max_temp = 100.0;	// The max temperature in the simulated annealing
		double cur_temp = max_temp;	// The current temperature in the simulated annealing, initialized at the max temp
		double step_size = .10;
		
		int sessions_at_cur_temp = 1;	// The number of sessions to play at the current temperature.
		double stop_percent = 1;		// The percentage value of the max_temp at which to stop.
		
		double heuristic_min = -100.0;	//
		double heuristic_max =  100.0;	// The max and min values passed to the AI constructor.
		
		GameRunner myRunner = new GameRunner();	// Initialize a GameRunner
		boolean Continue = true;
		
		int session_number = 0;
		
		// BEGIN ANNEALING
		double[] current_heuristic = myRunner.generateParameters();
		double current_winrate = myRunner.playSession(current_heuristic);
		
		while(cur_temp > max_temp * stop_percent * 0.01 && Continue){	// Stop the simulation when current temp is less than some percent of the max_temp
			for(int i = 0; i < sessions_at_cur_temp; i++){	// For each session we play at current temp:
				double[] heuristic_prime = current_heuristic.clone();	// Clone the current heuristic into heuristic prime (before changing heuristic prime)
				
				double[] shuffled_heuristic = heuristic_prime;			// Shuffle the heuristic prime for fair iteration
				Collections.shuffle(Arrays.asList(shuffled_heuristic));	// 
				
				for(int j = 0; j < shuffled_heuristic.length; j++){								// For each variable in the shuffled heuristic prime:
					double old_value = shuffled_heuristic[j];									// Store the old value in case the new is not accepted
					shuffled_heuristic[j] += step_size;	
					double winrate_prime = myRunner.playSession(heuristic_prime);				// Calculate the winrate with the heuristic prime
					if(winrate_prime > current_winrate){										// If the winrate prime is greater than the current winrate,
						current_heuristic = heuristic_prime.clone();							// Take it immediately
						current_winrate = winrate_prime;
						if(current_winrate >= 0.95){												// If your winrate is high, break
							break;
						}
						continue;
					}
					else if (Math.random() < Math.exp(Math.abs(winrate_prime - current_winrate) * -1/cur_temp)){ // Else, for some probability (decreasing with temperature)															// Else,
						current_heuristic = heuristic_prime.clone();											 // Take the winrate prime
						current_winrate = winrate_prime;
						continue;
					}
					else{shuffled_heuristic[j] = old_value;}									// Otherwise, we don't accept the new heuristic
					
<<<<<<< HEAD
					if(current_winrate > 0.999){												// If your winrate is high, break
						Continue = false;
						break;
=======
					shuffled_heuristic[j] -= step_size;	
					winrate_prime = myRunner.playSession(heuristic_prime);				// Calculate the winrate with the heuristic prime
					if(winrate_prime > current_winrate){										// If the winrate prime is greater than the current winrate,
						current_heuristic = heuristic_prime.clone();							// Take it immediately
						current_winrate = winrate_prime;
						if(current_winrate >= 0.95){												// If your winrate is high, break
							break;
						}
					}
					else if (Math.random() < Math.exp(Math.abs(winrate_prime - current_winrate) * -1/cur_temp)){ // Else, for some probability (decreasing with temperature)															// Else,
						current_heuristic = heuristic_prime.clone();											 // Take the winrate prime
						current_winrate = winrate_prime;
>>>>>>> 60f7758a074b86235136aa418700d658e957535a
					}
					else{shuffled_heuristic[j] = old_value;}
					
					
				}
				if(current_winrate >= 0.95){												// If your winrate is high, break
					break;
				}
				
				cur_temp = max_temp * 0.9;														// Decrement the current temp and start again
				System.out.print("Completed session # ");
				System.out.print(session_number);
				session_number++;
			}
		}
		myRunner.close();
	}
}
