package mainpack;

import UserInput.Initializer;
import audioOutput.Audio;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.vfs2.FileSystemException;
import rendering.DisplayObjects;
import rendering.Renderer;
import resources.ResourceLoader;
import updater.GameTimer;
import updater.Updater;

import javax.imageio.ImageIO;
import javax.naming.SizeLimitExceededException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileSystemException, ParseException {
        CommandLine ln=parseCli(args);
//        showStoryLine();
//        try {
//            Initializer.initialize();
//        } catch (IllegalStateException | InputMismatchException e) {
//            System.out.println("You have to enter useful values, you know? smh");
//            System.exit(0);
//        }
        Var.gameState = StateEnum.EVADING_FORMULAS;
        Var.r = new Renderer(20, 20);
        if(!ln.hasOption("u"))Var.r.changeToAscii();
        Var.r.fillWithEmptyFrame(Var.r.screens.get(0));
//        r.fillWithEmptyFrameThick();
        Var.r.draw(new int[]{1, 1}, DisplayObjects.BLOCK, Var.r.peek());
        Var.r.draw(new int[]{2, 1}, DisplayObjects.BLOCK, Var.r.peek());
        Var.r.draw(new int[]{2, 2}, DisplayObjects.BLOCK, Var.r.peek());
        int[] playerPos = {1, 1};
        Var.r.renderFrame();
        Var.timer = new GameTimer();
        Updater.run(true);
    }

    private static void showStoryLine() {
        System.out.println("So...");
        sleep(1200);
        slowPrint("you want to walk the path of excellence.", 900);
        sleep(500);
        System.out.println("Good.");
        sleep(700);
        System.out.println("But first you need to survive.\n");
        sleep(1500);
        slowPrint("-> DISKRETE STRUKTUREN <-", 3500);
        sleep(800);

        //Prints the Game title
        System.out.println("Welcome To:");

        String title = """
                 _______  _______  _        _        _______  _______  ______   _______                    _                        \s
                (  ____ \\(  ___  )( \\      ( \\      (  ___  )(  ____ \\(  __  \\ (  ____ \\|\\     /||\\     /|( \\      |\\     /||\\     /|
                | (    \\/| (   ) || (      | (      | (   ) || (    \\/| (  \\  )| (    \\/| )   ( || )   ( || (      | )   ( || )   ( |
                | |      | (___) || |      | |      | |   | || (__    | |   ) || (_____ | (___) || |   | || |      | (___) || |   | |
                | |      |  ___  || |      | |      | |   | ||  __)   | |   | |(_____  )|  ___  || |   | || |      |  ___  || |   | |
                | |      | (   ) || |      | |      | |   | || (      | |   ) |      ) || (   ) || |   | || |      | (   ) || |   | |
                | (____/\\| )   ( || (____/\\| (____/\\| (___) || )      | (__/  )/\\____) || )   ( || (___) || (____/\\| )   ( || (___) |
                (_______/|/     \\|(_______/(_______/(_______)|/       (______/ \\_______)|/     \\|(_______)(_______/|/     \\|(_______)""";

        System.out.println(title);
        sleep(400);
        System.out.println("                                                Escape from Kuratowski");
        sleep(800);


        System.out.println("WASD to move");
        System.out.println("evade the formulas");
        sleep(2000);
        System.out.println("GO!");
        sleep(700);
    }

    private static void sleep(long millies) {
        try {
            Thread.sleep(millies);
        } catch (InterruptedException e) {
            //System.out.println("Thread is interrupted");
            Thread.currentThread().interrupt();
        }
    }

    private static void slowPrint(String text, int totalTime) {
        char[] textArr = text.toCharArray();
        int timeBetweenChars = totalTime / textArr.length;
        for (char charToPrint : textArr) {
            System.out.print(charToPrint);
            sleep(timeBetweenChars);
        }
        System.out.print("\n");
    }

    private static CommandLine parseCli(String[] args) throws ParseException {
        Options options=new Options();
        options.addOption("g",false,"furce swing gui hack");
        options.addOption("u",false,"use unicode");
        DefaultParser parser=new DefaultParser();
        return parser.parse(options,args);
    }

}
