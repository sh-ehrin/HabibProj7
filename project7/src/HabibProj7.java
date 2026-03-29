import cardgames.*;
import javax.swing.JOptionPane;

public class HabibProj7{
    public static void main(String [] args){
        
        //instantiating a new GUI object with 2 cards
        //7) it should not show any cards yet because we have no declared any card objects
        GUI gui = new GUI(2, true);
        
        //8) instantiating a new Deck object default constuctor
        Deck deck1 = new Deck();
        //shuffling deck1
        deck1.shuffleDeck();
        
        //9)store and display the starting amount
        double bankAmnt = 100;
        gui.showAmount(bankAmnt);
        
        //10) deal two cards from the deck and store obj references
        Card card1 = deck1.dealCard();
        Card card2 = deck1.dealCard();
        
        //order the cards and show them in gui
        Card[] dealtCards = new Card[2];
        if (card1.getValue() <= card2.getValue()){
            dealtCards[0] = card1;
            dealtCards[1] = card2;
            gui.showCard(dealtCards[0]);
            gui.showCard(dealtCards[1]);
        }
        else {
            dealtCards[0] = card2;
            dealtCards[1] = card1;
            gui.showCard(dealtCards[0]);
            gui.showCard(dealtCards[1]);
        }
        
        //11) prompts user for Before, between, or after
        String ans = JOptionPane.showInputDialog("Will the next card be Before, Between, or After? Enter 0 for Before, Enter 1 for Between, Enter 2 for After");
        
        int bba = Integer.parseInt(ans);
        if (bba == 0){
            System.out.println("Before");
        }
        else if (bba == 1){
            System.out.println("Between");
        }
        else if (bba == 2){
            System.out.println("After");
        }
        else{
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
        double winnings = betAmnt*2;
        if (bba == 0 && topCard.getValue() < dealtCards[0].getValue()){
            gui.showMessage("Congratulations! You won $" + winnings);
            gui.showAmount(bankAmnt+winnings);
        }
        else if (bba == 1 && topCard.getValue() >= dealtCards[0].getValue() && topCard.getValue() <= dealtCards[1].getValue()){
            gui.showMessage("Congratulations! You won $" + winnings);
            gui.showAmount(bankAmnt+winnings);
        }
        else if (bba == 2 && topCard.getValue() > dealtCards[1].getValue()){
            gui.showMessage("Congratulations! You won $" + winnings);
            gui.showAmount(bankAmnt+winnings);
        }
        else {
            gui.showMessage("Aw man! You lost $" + bet);
            gui.showAmount(bankAmnt);
        }
    }
}