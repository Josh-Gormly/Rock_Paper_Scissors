import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame
{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel decidePnl;
    JPanel statsPnl;
    JPanel winnerPnl;

    JLabel titleLbl;
    JLabel playerWinsCount;
    JLabel computerWinsCount;
    JLabel tiesCount;

    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;

    ImageIcon rockImage;
    ImageIcon paperImage;
    ImageIcon scissorsImage;

    JTextField statsTF;
    int playerWins;
    int computerWins;
    int ties;
    JTextArea displayTA;
    JScrollPane scroller;

    Random rnd = new Random();

    public RockPaperScissorsFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(4,1));

        createTitlePnl();
        mainPnl.add(titlePnl);
        createStatsPnl();
        mainPnl.add(statsPnl);
        createWinnerPnl();
        mainPnl.add(winnerPnl);
        createDecidePnl();
        mainPnl.add(decidePnl);


        add(mainPnl);
        setSize(700,850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // creates a title panel that displays on the main panel
    private void createTitlePnl()
    {
        titlePnl = new JPanel();

        titleLbl = new JLabel("Rock, Paper, Scissors Game!!");

        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
    }

    // creates a stats panel that show the amount of win for the computer and regular player
    // also shows ties
    private void createStatsPnl()
    {
        statsPnl = new JPanel();

        playerWinsCount = new JLabel("Player Wins: 0");
        computerWinsCount = new JLabel("Computer Wins: 0");
        tiesCount = new JLabel("Ties: 0");

        statsPnl.add(playerWinsCount);
        statsPnl.add(computerWinsCount);
        statsPnl.add(tiesCount);

        statsTF = new JTextField(15);
    }

    // creates a text area that will display who the winner is
    private void createWinnerPnl()
    {
        winnerPnl = new JPanel();
        displayTA = new JTextArea(10,35);
        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);
        winnerPnl.add(scroller);

    }

    // Creates a panel that shows 4 buttons
    // Adds a border to the created panel
    // Each move is eventually going to be assigned to an integer
    private void createDecidePnl()
    {
        decidePnl = new JPanel();
        decidePnl.setLayout(new GridLayout(1,4));
        decidePnl.setBorder(new TitledBorder(new EtchedBorder(), "Pick"));

        rockImage = new ImageIcon("src/rock.jpg");
        paperImage = new ImageIcon("src/paper.jpg");
        scissorsImage = new ImageIcon("src/scissors.jpg");

        rockBtn = new JButton(rockImage);
        paperBtn = new JButton(paperImage);
        scissorsBtn = new JButton(scissorsImage);
        quitBtn = new JButton("Quit");

        decidePnl.add(rockBtn);
        decidePnl.add(paperBtn);
        decidePnl.add(scissorsBtn);
        decidePnl.add(quitBtn);

        rockBtn.addActionListener((ActionEvent ae) ->
        {
            displayTA.append("You play: Rock\n");
            play(0);
        });
        paperBtn.addActionListener((ActionEvent ae) ->
        {
            displayTA.append("You play: Paper\n");
            play(1);
        });
        scissorsBtn.addActionListener((ActionEvent ae) ->
        {
            displayTA.append("You play: Scissors\n");
            play(2);
        });
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
    }
    // updates the result panel to show the move that you choose and how it relates to what the computer chose
    // prints out who won each round
    // uses an if statement to decide the combination of winning
    // Rock = 0, Paper = 1, Scissors = 2
    private void play(int playerMove)
    {
        int computerMove = rnd.nextInt(3);

        if (playerMove == computerMove)
        {
            if (playerMove == 0)
            {
                displayTA.append("Computer plays Rock, it's a tie!\n");
            }
            else if (playerMove == 1)
            {
                displayTA.append("Computer plays Paper, it's a tie!\n");
            }
            else
            {
                displayTA.append("Computer plays Scissors, it's a tie!\n");
            }
            ties++;
            tiesCount.setText("Ties: " + ties);
        }
        else if (playerMove == 1 && playerMove == 0)
        {
            displayTA.append("Computer plays: Rock, Paper cover Rock (Player Wins!)");
            playerWins++;
        }
        else if (playerMove == 2 && computerMove == 0)
        {
            displayTA.append("Computer plays: Rock, Rock breaks Scissors (Computer Wins!)\n");
            computerWins++;
        }
        else if (playerMove == 0 && computerMove == 1)
        {
            displayTA.append("Computer plays: Paper, Paper covers Rock (Computer Wins!)\n");
            computerWins++;
        }
        else if (playerMove == 0 && computerMove == 2)
        {
            displayTA.append("Computer plays: Scissors, Rock breaks Scissors (Player Wins!)\n");
            playerWins++;
        }
        else if (playerMove == 1 && computerMove == 2)
        {
            displayTA.append("Computer plays: Scissors, Scissors cuts Paper (Computer Wins!)\n");
            computerWins++;
        }
        else if (playerMove == 2 && computerMove == 1)
        {
            displayTA.append("Computer plays: Paper, Scissors cuts Paper (Player Wins!)\n");
            playerWins++;
        }
        playerWinsCount.setText("Player Wins: " + playerWins);
        computerWinsCount.setText("Computer Wins: " + computerWins);
    }

}
