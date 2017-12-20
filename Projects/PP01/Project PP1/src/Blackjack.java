import java.util.Arrays;

import javax.swing.JOptionPane;

public class Blackjack {

	public static void main(String[] args) {
		
		//Declare Variables
		String userChoice = "START";
		int playerCard=0,playerCardTotal=0;
		int dealerCard=0,dealerCardTotal=0;
		int[] playerCards= new int[10];
		int[] dealerCards= new int[10];
		int i,j;
		
		FULLGAME:
		do {
			JOptionPane.showMessageDialog(null,"Welcome to the BlackJack game! Let's start a new game!",
					"BLACK JACK SIMULATION", JOptionPane.QUESTION_MESSAGE);
			JOptionPane.showMessageDialog(null, "Shuffling the Cards(You will receive two cards)!");
			
			//Initialization to start new game
			Arrays.fill(playerCards, 0);
			Arrays.fill(dealerCards, 0);
			playerCardTotal=dealerCardTotal=0;
			i=j=0;
			
			//Initial Generation of first two cards!
			for(i=0;i<2;i++) {
				//Generate Player Card 
				playerCard=1+(int)(Math.random()*11);
				playerCardTotal+=playerCard;
				playerCards[i]=playerCard;
						
				//Generate Dealer Card
				dealerCard=1+(int)(Math.random()*11);
				dealerCardTotal+=dealerCard;
				dealerCards[i]=dealerCard;
			}
			
			if((playerCardTotal>21 || dealerCardTotal>21)) { //To ensure the player and dealer  
						                                    //are not busted in first two rounds
				JOptionPane.showMessageDialog(null, "Re-starting the game as you or the dealer got 22 in first two"
						+ "rounds!");
				continue;
			}
			
			//Display initial game status
			JOptionPane.showMessageDialog(null, "Player's Card Total : "+playerCardTotal+
                    "\nDealer's 1st Card Value : "+dealerCards[0]);
		
		GAME:
		do {
			i=2;j=2;		
			//Get user input to HIT or STAY
			userChoice=JOptionPane.showInputDialog(null,"Type 'HIT' or 'STAY'");
						
			if(userChoice.equalsIgnoreCase("HIT")) {
				
				
				//Generate Player Card 
				playerCard=1+(int)(Math.random()*11);
				playerCardTotal+=playerCard;
				playerCards[i++]=playerCard;
				
				if(dealerCardTotal<=17) {
					//Generate Dealer Card
					dealerCard=1+(int)(Math.random()*11);
					dealerCardTotal+=dealerCard;
					dealerCards[j++]=dealerCard;
				}
					
				//Display Game Status
				JOptionPane.showMessageDialog(null, "Player's Card Total : "+playerCardTotal+
						                            "\nDealer's Card : "+ dealerCard);
				
			}
			
			if(userChoice.equalsIgnoreCase("STAY")) {
				if(dealerCardTotal<=17) {
					//Generate Dealer Card
					dealerCard=1+(int)(Math.random()*11);
					dealerCardTotal+=dealerCard;
					dealerCards[j++]=dealerCard;
				}
				
				//Display Game Status
				JOptionPane.showMessageDialog(null, "Player's Card Total : "+playerCardTotal+
                        "\nDealer's Card Total : "+dealerCardTotal);
			}
			
			//Display Final Game Results
			if(playerCardTotal<21 && dealerCardTotal>21) { //Player Wins!
					JOptionPane.showMessageDialog(null, "YOU WIN THE GAME! \n DEALER BUSTED!"+
						"\nPlayer's Card Total : "+playerCardTotal+
                        "\nDealer's Card Total : "+dealerCardTotal);
					break GAME;
			
			}
			else if(playerCardTotal>21 && dealerCardTotal<21){ //Dealer Wins!
				JOptionPane.showMessageDialog(null, "YOU ARE BUSTED! \n DEALER WINS!"+
						"\nPlayer's Card Total : "+playerCardTotal+
                        "\nDealer's Card Total : "+dealerCardTotal);
				break GAME;	
			
			}
			else if((dealerCardTotal>=18 || (playerCardTotal<=21 
                    && userChoice.equalsIgnoreCase("STAY")))) { //Either of the player choose to STAY
				
				if(playerCardTotal>dealerCardTotal) {
					JOptionPane.showMessageDialog(null, "YOU WIN THE GAME! \n DEALER BUSTED!"+
							"\nPlayer's Card Total : "+playerCardTotal+
	                        "\nDealer's Card Total : "+dealerCardTotal+
	                        "\nPlayer Cards : "+Arrays.toString(playerCards)+
	                        "\nDealer Cards : "+Arrays.toString(dealerCards));
					break GAME;
				}
				else if(playerCardTotal==dealerCardTotal) {
				JOptionPane.showMessageDialog(null, "IT'S A TIE GAME! \n DEALER WINS!"+
						"\nPlayer's Card Total : "+playerCardTotal+
                        "\nDealer's Card Total : "+dealerCardTotal+
                        "\nPlayer Cards : "+Arrays.toString(playerCards)+
                        "\nDealer Cards : "+Arrays.toString(dealerCards));
				break GAME;
				}
				else {
					JOptionPane.showMessageDialog(null, "YOU ARE BUSTED! \n DEALER WINS!"+
							"\nPlayer's Card Total : "+playerCardTotal+
	                        "\nDealer's Card Total : "+dealerCardTotal+
	                        "\nPlayer Cards : "+Arrays.toString(playerCards)+
	                        "\nDealer Cards : "+Arrays.toString(dealerCards));
					break GAME;
				}
			
				
				
			}
			else if(playerCardTotal>=21 && dealerCardTotal>=21) { // TIE GAME
				JOptionPane.showMessageDialog(null, "IT'S A TIE GAME! \n DEALER WINS!"+
						"\nPlayer's Card Total : "+playerCardTotal+
                        "\nDealer's Card Total : "+dealerCardTotal+
                        "\nPlayer Cards : "+Arrays.toString(playerCards)+
                        "\nDealer Cards : "+Arrays.toString(dealerCards));
				break GAME;
			
			}
			
			
			
			
			}while(!userChoice.equalsIgnoreCase("STOP"));
			
			//Reset the Game
			playerCardTotal=dealerCardTotal=0;
			userChoice=JOptionPane.showInputDialog(null,"Starting a New GAME! Type 'STOP' to quit the game!");
			
			
		}while(!userChoice.equalsIgnoreCase("STOP"));
	
	JOptionPane.showMessageDialog(null, "Game Over!\nThanks for playing BlackJack! :)");
		
	}

}
