import javax.swing.JOptionPane;

public class ProjectPP1 {
	public static void main(String[] args) {

		// Welcome Message
		JOptionPane.showMessageDialog(null, "Welcome to the BlackJack game! \nWould you like to continue?",
				"BLACK JACK", JOptionPane.QUESTION_MESSAGE);

		String message = "To received your first pairs cards and see the dealer's first card press ok";

		JOptionPane.showMessageDialog(null, message);

		//for re-run the game
		String input2 = "OK";

		while (!input2.equalsIgnoreCase("")) {

			if (input2.equals("STOP")) {
				System.exit(0);
			}

			else {

				// Generate player card
				int playerCard1 = 0;
				int playerCard = 0;

				for (int i = 0; i < 2; i++) {
					playerCard1 = (int) (Math.random() * 10 + 1);
					playerCard = playerCard + playerCard1;
				}

				// Generate dealer cards
				int dealerCard1 = 0;
				int dealerCard = 0;

				for (int i = 0; i < 2; i++) {
					dealerCard1 = (int) (Math.random() * 10 + 1);
					dealerCard = dealerCard + dealerCard1;
				}

				// Avoiding the initial deal are 22
				if (playerCard == 22) {
					for (int i = 0; i < 2; i++) {
						playerCard1 = (int) (Math.random() * 10 + 1);
						playerCard = playerCard + playerCard1;
					}
				}

				else if (dealerCard == 22) {
					for (int i = 0; i < 2; i++) {
						dealerCard1 = (int) (Math.random() * 10 + 1);
						dealerCard = dealerCard + dealerCard1;
					}
				} 
				// First Round result
				JOptionPane.showMessageDialog(null, String.format("%s %d", "Your cards total value: ", playerCard)
						+ String.format("%s %d", "\nDealer's one card is: ", dealerCard1));

				// Prompt the player for next Step
				String message1 = "Do you want more cards (type 'HIT' to receive a new card, type 'STAY' to stop)? ";

				String input1 = JOptionPane.showInputDialog(message1).toUpperCase();

				/*while (!input1.equals("HIT")) {
					JOptionPane.showMessageDialog(null,
							"Please enter either 'HIT' or 'STAY' \n(Upper or lower case are accpeted)");
					input1 = JOptionPane.showInputDialog(message1).toUpperCase();
				}

				while (!input1.equals("STAY")) {
					JOptionPane.showMessageDialog(null,
							"Please enter either 'HIT' or 'STAY' \n(Upper or lower case are accpeted)");
					input1 = JOptionPane.showInputDialog(message1).toUpperCase();
				}*/

				// processing the HIT
				while (input1.equals("HIT")) {
					int newpCard = (int) (Math.random() * 10 + 1);

					if (dealerCard < 18) {
						dealerCard1 = (int) (Math.random() * 10 + 1);
						dealerCard = dealerCard + dealerCard1;
					}

					playerCard = playerCard + newpCard;

					if (playerCard > 21 && dealerCard < 22) {
						JOptionPane.showMessageDialog(null,
								String.format("%s %d", "You BUSTED \nYour total card value: ", playerCard)
										+ String.format("%s %d", "\nThe dealer's total card vaule: ", dealerCard));
						break;
					}

					else if (playerCard > 21 && dealerCard > 21) {
						JOptionPane.showMessageDialog(null,
								String.format("%s %d", "TIE GAME, DEALER WIN \nThe dealer's card vaules: ", dealerCard)
										+ String.format("%s %d", "\nYour total card values: ", playerCard));
						break;
					}

					else if (dealerCard > 21 && playerCard < 22) {
						JOptionPane.showMessageDialog(null,
								String.format("%s %d", "DEALER BUSTED \nthe dealer's card vaules: ", dealerCard)
										+ String.format("%s %d", "\nYou WIN\nYour total card values: ", playerCard));
						break;
					}

					JOptionPane.showMessageDialog(null,
							String.format("%s %d", "Now your total card value: ", playerCard)
									+ String.format("%s %d", "\nThe new dealer's cards: ", dealerCard1));
					input1 = JOptionPane.showInputDialog(message1).toUpperCase();
				}
					
				while (input1.equals("STAY")) {
					int dealerCard2 = 0;
					
					if (dealerCard < 18) {
						dealerCard2 = (int) (Math.random() * 10 + 1);
					}

					dealerCard = dealerCard + dealerCard2;

					if (dealerCard > 21) {
						JOptionPane.showMessageDialog(null,
								String.format("%s %d", "DEALER BUSTED \nthe dealer's card values: ", dealerCard)
										+ String.format("%s %d", "\nYou WIN\nYour total card values: ",
												playerCard));
						break;
					} 
					else if (playerCard < 22 && dealerCard < 22 && playerCard > dealerCard) {
						JOptionPane.showMessageDialog(null,
								String.format("%s %d", "You WIN \nYour card values: ", playerCard)
										+ String.format("%s %d", "\nThe dealer's card values: ", dealerCard));
						break;
					}
 
					else if (playerCard < 22 && dealerCard < 22 && playerCard < dealerCard) {
						JOptionPane.showMessageDialog(null,
								String.format("%s %d", "You LOSE \nYour card values: ", playerCard)
										+ String.format("%s %d", "\nThe dealer's card values: ", dealerCard));
						break;
					}
							
				
				}
			}
				// Next new game
				String message2 = "Do you want a new game? (type anything to continue and 'STOP' to exit the game)";
				input2 = JOptionPane.showInputDialog(message2).toUpperCase();
			
		}
	}
}