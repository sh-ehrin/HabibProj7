
import cardgames.*;
import javax.swing.JOptionPane;

public class HabibProj7 {

    public static void main(String[] args) throws InterruptedException {
        //iestablishing global variables
        boolean replay = true;
        double bankAmnt = Double.parseDouble(JOptionPane.showInputDialog("Please enter your Bank Amount in decimal form."));
        
        //instantiating a new GUI object with 2 cards
        //7) it should not show any cards yet because we have no declared any card objects
        GUI gui = new GUI(2, true);

        //8) instantiating a new Deck object default constuctor
        Deck deck1 = new Deck();

        while (replay) {
            //reset player and deck cards
            gui.clearDeckCard();
            gui.clearPlayerCards();
            //show updated amounts
            gui.showAmount(bankAmnt);
            gui.showBet(0);
            
            //shuffling deck1
            deck1.shuffleDeck();

            //9)store and display the starting amount
            gui.showAmount(bankAmnt);

            //10) deal two cards from the deck and store obj references
            Card card1 = deck1.dealCard();
            Card card2 = deck1.dealCard();

            //order the cards and show them in gui
            Card[] dealtCards = new Card[2];
            if (card1.getValue() <= card2.getValue()) {
                dealtCards[0] = card1;
                dealtCards[1] = card2;
                gui.showCard(dealtCards[0]);
                gui.showCard(dealtCards[1]);
            } else {
                dealtCards[0] = card2;
                dealtCards[1] = card1;
                gui.showCard(dealtCards[0]);
                gui.showCard(dealtCards[1]);
            }

            //11) prompts user for Before, between, or after
            String ans = JOptionPane.showInputDialog("Will the next card be Before, Between, or After? \nEnter 0 for Before \nEnter 1 for Between \nEnter 2 for After");

            int bba = Integer.parseInt(ans);
            if (bba == 0) {
                System.out.println("Before");
            } else if (bba == 1) {
                System.out.println("Between");
            } else if (bba == 2) {
                System.out.println("After");
            } else {
                System.out.println("invalid input");
                System.exit(0);
            }

            //12) prompt user for bet amount. show bet amount
            String bet = JOptionPane.showInputDialog("Please enter your bet amount in a decimal value:");
            double betAmnt = Double.parseDouble(bet);
            gui.showBet(betAmnt);

            //13) show the card that you are betting on in the deck card area.
            bankAmnt -= betAmnt;
            gui.showAmount(bankAmnt);
            Card topCard = deck1.dealCard();
            gui.showDeckCard(topCard);

            //14) determine if the user won
            double winnings = betAmnt * 2;
            if (bba == 0 && topCard.getValue() < dealtCards[0].getValue()) {
                gui.showMessage("Congratulations! You won $" + winnings);
                bankAmnt += winnings;
                gui.showAmount(bankAmnt);
            } else if (bba == 1 && topCard.getValue() >= dealtCards[0].getValue() && topCard.getValue() <= dealtCards[1].getValue()) {
                gui.showMessage("Congratulations! You won $" + winnings);
                bankAmnt += winnings;
                gui.showAmount(bankAmnt);
            } else if (bba == 2 && topCard.getValue() > dealtCards[1].getValue()) {
                gui.showMessage("Congratulations! You won $" + winnings);
                bankAmnt += winnings;
                gui.showAmount(bankAmnt);
            } else {
                gui.showMessage("Aw man! You lost $" + bet);
                gui.showAmount(bankAmnt);
            }
            
            Thread.sleep(4000);

            String playAgain = JOptionPane.showInputDialog("Do you want to play again? \nEnter 0 for No \nEnter 1 for Yes");

            if (Integer.parseInt(playAgain) == 0) {
                replay = false;
                betAmnt = 0;
                gui.showBet(betAmnt);
                gui.showMessage("");
                if (bankAmnt < 0){
                    System.out.println("Thank you for playing. You are in debt. Your bank total is $" + bankAmnt);
                }
                else{
                    System.out.println("Thank you for playing. Your bank total is $" + bankAmnt);
                }
                System.exit(0);
            }
            else if(Integer.parseInt(playAgain) == 1){
                replay = true; 
                betAmnt = 0;
                gui.showBet(betAmnt);
                gui.showMessage("");
            }
        }
    }
}
