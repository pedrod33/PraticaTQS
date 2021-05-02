package ui;

import euromillions.CuponEuromillions;
import euromillions.Dip;
import euromillions.EuromillionsDraw;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoMain {
    private static Logger logger = Logger.getLogger(DemoMain.class.getName());
    private static final String INFO = "Information {}";
    /**
     * demonstrates a client for ramdom euromillions bets
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // played sheet
        CuponEuromillions thisWeek = new CuponEuromillions();
        logger.log(Level.INFO, INFO,"Betting with three random bets...");
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());

        // simulate a random draw
        EuromillionsDraw draw = EuromillionsDraw.generateRandomDraw();

        //report results
        logger.log(Level.INFO, INFO,"You played:");
        logger.log(Level.INFO, INFO,thisWeek.format());

        logger.log(Level.INFO, INFO,"Draw results:");
        logger.log(Level.INFO, INFO, draw.getDrawResults().format());

        logger.log(Level.INFO, INFO ,"Your score:");
        for (Dip dip : draw.findMatches(thisWeek)) {
            logger.log(Level.INFO, INFO,dip.format());

        }
    }
}
