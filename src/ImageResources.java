import javax.swing.*;
import java.awt.*;

public class ImageResources {
    ImageIcon Square = new ImageIcon(new ImageIcon("Resources/SQUARE.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Smiley = new ImageIcon(new ImageIcon("Resources/SMILEY.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    ImageIcon DeadSmiley = new ImageIcon(new ImageIcon("Resources/SMILEY_DEATH.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    ImageIcon Flag  = new ImageIcon(new ImageIcon("Resources/FLAG.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Mine = new ImageIcon(new ImageIcon("Resources/MINEBLOCK.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Zero = new ImageIcon(new ImageIcon("Resources/0.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon One = new ImageIcon(new ImageIcon("Resources/1.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Two = new ImageIcon(new ImageIcon("Resources/2.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Three = new ImageIcon(new ImageIcon("Resources/3.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Four = new ImageIcon(new ImageIcon("Resources/4.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Five = new ImageIcon(new ImageIcon("Resources/5.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Six = new ImageIcon(new ImageIcon("Resources/6.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Seven = new ImageIcon(new ImageIcon("Resources/7.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Eight = new ImageIcon(new ImageIcon("Resources/8.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Flag_Wrong = new ImageIcon(new ImageIcon("Resources/FLAG_WRONG.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    ImageIcon Mineblock_Pressed = new ImageIcon(new ImageIcon("Resources/MINEBLOCK_PRESSED.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));


    public ImageIcon getSquare() {
        return Square;
    }

    public ImageIcon getSmiley() {
        return Smiley;
    }

    public ImageIcon getFlag() {
        return Flag;
    }

    public ImageIcon getDeadSmiley() {
        return DeadSmiley;
    }

    public ImageIcon getTwo() {
        return Two;
    }

    public ImageIcon getThree() {
        return Three;
    }

    public ImageIcon getFour() {
        return Four;
    }

    public ImageIcon getFive() {
        return Five;
    }

    public ImageIcon getSix() {
        return Six;
    }

    public ImageIcon getSeven() {
        return Seven;
    }

    public ImageIcon getEight() {
        return Eight;
    }

    public ImageIcon getMine() {
        return Mine;
    }

    public ImageIcon getOne() {
        return One;
    }

    public ImageIcon getZero(){
        return Zero;
    }

    public ImageIcon getFlag_Wrong() {return Flag_Wrong;}

    public ImageIcon getMineblock_Pressed() {return Mineblock_Pressed;}
}
