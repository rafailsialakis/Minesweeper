import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class MineSweeper extends JFrame {
    private static final int rows = 8;
    private static final int cols = 8;
    private static final ImageResources resources = new ImageResources();

    private final ImageIcon Square = resources.getSquare();
    private final ImageIcon Flag = resources.getFlag();
    private final ImageIcon Smiley = resources.getSmiley();
    private final ImageIcon DeadSmiley = resources.getDeadSmiley();
    private final ImageIcon Mine = resources.getMine();
    private final ImageIcon MinePressed = resources.getMineblock_Pressed();
    private final ImageIcon Flagged_Wrong = resources.getFlag_Wrong();

    private static final ImageIcon Zero = resources.getZero();
    private static final ImageIcon One = resources.getOne();
    private static final ImageIcon Two = resources.getTwo();
    private static final ImageIcon Three = resources.getThree();
    private static final ImageIcon Four = resources.getFour();
    private static final ImageIcon Five = resources.getFive();
    private static final ImageIcon Six = resources.getSix();
    private static final ImageIcon Seven = resources.getSeven();
    private static final ImageIcon Eight = resources.getEight();

    private final JPanel Sweeper = new JPanel(new GridLayout(rows,cols));
    private final JLabel SmileyLabel = new JLabel(Smiley);
    private static final int totalMines = 8; // Track total mines

    private static final JButton[][] MineButton = new JButton[rows][cols];
    private static final ArrayList<JButton> Mines = new ArrayList<>();
    private static final ArrayList<JButton> MinesSweeped = new ArrayList<>();
    private final ArrayList<JButton> Flagged = new ArrayList<>();
    public MineSweeper()
    {
        this.InitializePanels();
        this.InitializeGame();
        this.setSize(600,745);
        this.setVisible(true);
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void InitializePanels() {
        // Main panel using BorderLayout without fixed preferred size
        JPanel gamePanel = new JPanel(new BorderLayout());

        // Create the menu panel (topmost)
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setPreferredSize(new Dimension(670, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(670, 140));

        JMenuBar menuBar = getBar();

        menuPanel.add(menuBar, BorderLayout.NORTH); // Add menu bar to menu panel

        headerPanel.add(SmileyLabel); // Add SmileyLabel to header panel

        Sweeper.setPreferredSize(new Dimension(670, 670));
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(Sweeper, BorderLayout.CENTER);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int finalI = i, finalJ = j;
                MineButton[i][j] = new JButton(Square);
                MineButton[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            RightClickListener(finalI, finalJ);
                        }
                    }
                });

                MineButton[i][j].addActionListener(e -> {
                    JButton ButtonPressed = (JButton) e.getSource();
                    if (!MinesSweeped.contains(ButtonPressed)) {
                        if (Mines.contains(ButtonPressed)) {
                            for (JButton b : Mines) {
                                b.setIcon(Mine);
                                if (Flagged.contains(b)) {
                                    b.setIcon(Flag);
                                }
                            }
                            for (JButton b : Flagged) {
                                if (!Mines.contains(b)) {
                                    b.setIcon(Flagged_Wrong);
                                }
                            }

                            SmileyLabel.setIcon(DeadSmiley);
                            MineButton[finalI][finalJ].setIcon(MinePressed);
                            MineButton[finalI][finalJ].setContentAreaFilled(false);
                            MineButton[finalI][finalJ].setFocusPainted(false);
                            MineButton[finalI][finalJ].setBorder(BorderFactory.createEmptyBorder());

                            JOptionPane.showMessageDialog(
                                    null,
                                    "You lost! Better luck next time!",
                                    "Game Over",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            System.exit(0);
                        } else {
                            checkNearbyMines(finalI, finalJ);
                        }
                    }
                });

                Sweeper.add(MineButton[i][j]);
            }
        }

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(Sweeper, BorderLayout.CENTER);

        gamePanel.add(menuPanel, BorderLayout.NORTH);
        gamePanel.add(contentPanel, BorderLayout.CENTER);

        this.setContentPane(gamePanel);
        this.pack();
        this.setContentPane(gamePanel);
    }

    private JMenuBar getBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(_ -> JOptionPane.showMessageDialog(this, "TODO", "TODO", JOptionPane.INFORMATION_MESSAGE));
        JMenuItem exit = new JMenuItem("Exit");

        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutItem = new JMenuItem("About me");

        aboutItem.addActionListener(_ -> JOptionPane.showMessageDialog(this, "Minesweeper v1.0 created by me for fun", "About", JOptionPane.INFORMATION_MESSAGE));

        exit.addActionListener(_ -> System.exit(0));

        fileMenu.add(newGame);
        fileMenu.add(exit);
        aboutMenu.add(aboutItem);
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        return menuBar;
    }


    public void InitializeGame() {
        Random r = new Random();
        int placedMines = 0;

        while (placedMines < totalMines) {
            int row = r.nextInt(rows);
            int col = r.nextInt(cols);

            if (!Mines.contains(MineButton[row][col])) {
                Mines.add(MineButton[row][col]);
                placedMines++;
            }
        }
    }

    public static void checkNearbyMines(int row, int col) {
        if (MinesSweeped.contains(MineButton[row][col])) return;

        int countOfMines = 0;
        int rows = MineButton.length;
        int cols = MineButton[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (Mines.contains(MineButton[newRow][newCol])) {
                        countOfMines++;
                    }
                }
            }
        }
        paintBlocks(countOfMines, row, col);

        // If the block is zero, recursively reveal neighbors
        if (countOfMines == 0) {
            revealAdjacentZeros(row, col);
        }
    }

    public static void revealAdjacentZeros(int row, int col) {
        int rows = MineButton.length;
        int cols = MineButton[0].length;

        // Loop through all 8 adjacent cells
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    JButton neighbor = MineButton[newRow][newCol];

                    if (!MinesSweeped.contains(neighbor) && !Mines.contains(neighbor)) {
                        checkNearbyMines(newRow, newCol);  // Recursively reveal
                    }
                }
            }
        }
    }
    public static void checkWin() {
        int totalCells = rows * cols;
        int safeCells = totalCells - totalMines;

        if (MinesSweeped.size() == safeCells) {
            JOptionPane.showMessageDialog(
                    null,
                    "Congratulations! You won!",
                    "You Won!",
                    JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        }
    }
    public static void paintBlocks(int countOfMines, int row, int col){
        switch (countOfMines){
            case 0: MineButton[row][col].setIcon(Zero); break;
            case 1: MineButton[row][col].setIcon(One); break;
            case 2: MineButton[row][col].setIcon(Two); break;
            case 3: MineButton[row][col].setIcon(Three); break;
            case 4: MineButton[row][col].setIcon(Four); break;
            case 5: MineButton[row][col].setIcon(Five); break;
            case 6: MineButton[row][col].setIcon(Six); break;
            case 7: MineButton[row][col].setIcon(Seven); break;
            case 8: MineButton[row][col].setIcon(Eight); break;
        }

        MinesSweeped.add(MineButton[row][col]);
        MineButton[row][col].setContentAreaFilled(false);
        MineButton[row][col].setFocusPainted(false);
        MineButton[row][col].setBorder(BorderFactory.createEmptyBorder());

        checkWin();
    }

    public void RightClickListener(int i, int j){
        if(!MinesSweeped.contains(MineButton[i][j]))
            if(MineButton[i][j].getIcon().equals(Square)){
                MineButton[i][j].setIcon(Flag);
                Flagged.add(MineButton[i][j]);
            } else if (MineButton[i][j].getIcon().equals(Flag)) {
                MineButton[i][j].setIcon(Square);
                Flagged.remove(MineButton[i][j]);
            }
    }

}
