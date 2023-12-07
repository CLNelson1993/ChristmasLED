package com.st6.ChristmasLED.Command;

import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;

import java.util.Scanner;

public class Receiver {
    int gpioNum = 18;
    int brightDefault = 255;
    int pixelNum = 100;
    LedDriverInterface ledDriver = new WS281x(gpioNum, brightDefault, pixelNum);
    int rVal;
    int gVal;
    int bVal;
    int brightVal;

    boolean stripOn = false; //on and off state for loops
    int i = 0; //counter variable for loops

    //Close method (Turn off strip when users choose to close program)
    public void cmdClose() {
        System.out.println("cmdClose() - Shutting off, please wait.");
        stripOn = false;
        }

    //Rainbow method
    public void cmdRainbow() {
        // Toggle the value of stripOn at the beginning of the method
        stripOn = !stripOn;
        //set strip to default (in case brightness was changed using cmdStatic)
        ledDriver = new WS281x(gpioNum, brightDefault, pixelNum);
        //create an array of colors using PixelColour
        int[] colors = PixelColour.RAINBOW;


        //animation code
        // Check the value of stripOn before running the animation loop
        if (stripOn) {
            System.out.println("cmdRainbow() on");
            i = 0; // counter variable
            while (stripOn) {
                for (int pixel = 0; pixel < ledDriver.getNumPixels(); pixel++) {
                    ledDriver.setPixelColour(pixel, colors[(i + pixel) % colors.length]);
                }

                ledDriver.render();
                PixelAnimations.delay(50);
                i++;
            }
        } else {
            // If stripOn is false, turn off all the LEDs.
            // I hate everything about this. You have to wait for the increment counter to go up before it finally turns off.
            // It only takes like 10 seconds at most, but that's precious time that the average user wouldn't be happy with wasting.
            System.out.println("cmdRainbow() off");
            ledDriver.allOff();
        }
    }

    //Static method
    public void cmdStatic() {

        //change brightness by updating the driver
        ledDriver = new WS281x(gpioNum, brightVal, pixelNum);

        System.out.println("Executing cmdStatic() with RGB(" + rVal + ", " + gVal + ", " + bVal + ") and brightness(" + brightVal + ")");
        System.out.println("");

        //code for anim
        for (int i = 0; i < ledDriver.getNumPixels(); i++) {
            ledDriver.setPixelColourRGB(i, rVal, bVal, gVal); //note how it's actually RBG, not RGB.
        }

        ledDriver.render();
    }

    //RomSimpson's anims
    public void cmdWipe() {
        //Right now this lets the user assign 3 colors. Maybe make an array?

        //user prompt + scanner #1
        System.out.println("CHOOSE COLOR #1");
        System.out.println("");

        //user prompt + scanner #2
        System.out.println("CHOOSE COLOR #2");
        System.out.println("");

        //user prompt + scanner #3

        System.out.println("CHOOSE COLOR #3");
        System.out.println("");

        System.out.println("Executing cmdWipe()");
        System.out.println("");

        //loop anim
        while (true) {
            //code for anim
            System.out.println("cmdWipe(): Color #1");
            for (int i = 0; i < ledDriver.getNumPixels(); i++) {
            //    ledDriver.setPixelColourRGB(i, rVal1, bVal1, gVal1);
                ledDriver.render();
                PixelAnimations.delay(50);
            }

            System.out.println("cmdWipe(): Color #2");
            for (int i = 0; i < ledDriver.getNumPixels(); i++) {
            //    ledDriver.setPixelColourRGB(i, rVal2, bVal2, gVal2);
                ledDriver.render();
                PixelAnimations.delay(50);
            }

            System.out.println("cmdWipe(): Color #3");
            for (int i = 0; i < ledDriver.getNumPixels(); i++) {
            //    ledDriver.setPixelColourRGB(i, rVal3, bVal3, gVal3);
                ledDriver.render();
                PixelAnimations.delay(50);
            }
        }

    }

    public void cmdRainbowCycle() {
        for (int j = 0; j < 256 * 5; j++) { // 5 cycles of all colors on wheel
            for (int i = 0; i < ledDriver.getNumPixels(); i++) {
                ledDriver.setPixelColour(i, PixelColour.wheel(((i * 256 / ledDriver.getNumPixels()) + j) & 255));
            }
            ledDriver.render();
            PixelAnimations.delay(25);
        }

    }

    public void cmdStrobe() {
        //user prompt + scanner
        System.out.println("Enter a value for Red (0-255).");

        //change brightness by updating the driver
        ledDriver = new WS281x(gpioNum, brightVal, pixelNum);

        System.out.println("Executing cmdStrobe() with RGB(" + rVal + ", " + gVal + ", " + bVal + ") and brightness(" + brightVal + ")");
        System.out.println("");

        //code for anim
        while (true) {
            for (int i = 0; i < ledDriver.getNumPixels(); i++) {
                ledDriver.setPixelColourRGB(i, rVal, bVal, gVal);
            }
            ledDriver.render();
            PixelAnimations.delay(500);
            for (int i = 0; i < ledDriver.getNumPixels(); i++) {
                ledDriver.allOff();
            }
            PixelAnimations.delay(250);
        }
    }

}

