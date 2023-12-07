package com.st6.ChristmasLED.Command;

import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;

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
            i = 0; // zero out counter variable
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

    //RainbowCycle method
    public void cmdRainbowCycle() {
        // Toggle the value of stripOn at the beginning of the method
        stripOn = !stripOn;

        if (stripOn) {
            System.out.println("cmdRainbowCycle() on");
            i = 0; // zero out counter variable
            for (int pixel = 0; pixel < 256 * 5 && stripOn; pixel++) { // 5 cycles of all colors on wheel
                for (int j = 0; j < ledDriver.getNumPixels(); j++) {
                    ledDriver.setPixelColour(j, PixelColour.wheel(((j * 256 / ledDriver.getNumPixels()) + j) & 255));
                }
                ledDriver.render();
                PixelAnimations.delay(25);
                i++;
            }
        } else {
            System.out.println("cmdRainbowCycle() off");
            ledDriver.allOff();
        }
    }


    //boy oh boy, these need work. ayayay.

    public void cmdStrobe() {
        // Toggle the value of stripOn at the beginning of the method
        stripOn = !stripOn;

        // set to blue w/ max brightness for demo sake
        rVal = 0;
        gVal = 0;
        bVal = 255;
        brightVal = 255;

        if (stripOn) {
            System.out.println("this is where we'd enter a value for Red,Green,Blue (0-255).");
            i = 0; // zero out counter variable

            //adjust brightness by updating the driver
            ledDriver = new WS281x(gpioNum, brightVal, pixelNum);

            System.out.println("Executing cmdStrobe() with RGB(" + rVal + ", " + gVal + ", " + bVal + ") and brightness(" + brightVal + ")");

            //code for anim
            while (stripOn) {
                PixelAnimations.delay(250);
                for (int j = 0; j < ledDriver.getNumPixels(); j++) {
                    ledDriver.setPixelColourRGB(j, rVal, bVal, gVal);
                }
                ledDriver.render();
                PixelAnimations.delay(500);
                for (int j = 0; j < ledDriver.getNumPixels(); j++) {
                    ledDriver.allOff();
                }
                i++;
            }
        } else {
            System.out.println("cmdStrobe() off");
            ledDriver.allOff();
        }
    }



    //Static method
    public void cmdStatic() {
        // Toggle the value of stripOn at the beginning of the method
        stripOn = !stripOn;

        //setting to blue w/ max brightness for demo's sake
        rVal = 0;
        gVal = 0;
        bVal = 255;
        brightVal = 255;

        if (stripOn) {
            //change brightness by updating the driver? Seems janky.
            ledDriver = new WS281x(gpioNum, brightVal, pixelNum);
            i = 0; // zero out counter variable

            System.out.println("Executing cmdStatic() with RGB(" + rVal + ", " + gVal + ", " + bVal + ") and brightness(" + brightVal + ")");

            //code for anim
            for (int j = 0; j < ledDriver.getNumPixels(); j++) {
                ledDriver.setPixelColourRGB(j, rVal, bVal, gVal); //note how it's actually RBG, not RGB.
            }

            ledDriver.render();
            PixelAnimations.delay(50);
            i++;
        } else {
            System.out.println("cmdStatic() off");
            ledDriver.allOff();
        }
    }

    //Wipe method
    public void cmdWipe() {
        // Toggle the value of stripOn at the beginning of the method
        stripOn = !stripOn;

        //color #1 = red for demo sake
        int rVal1 = 255;
        int gVal1 = 0;
        int bVal1 = 0;
        //color #2 = green for demo sake
        int rVal2 = 0;
        int gVal2 = 255;
        int bVal2 = 0;
        //color #3 = blue for demo sake
        int rVal3 = 0;
        int gVal3 = 0;
        int bVal3 = 255;
        brightVal = 255;

        if (stripOn) {
            //Right now this lets the user assign 3 colors. Maybe make an array?
            i = 0; // zero out counter variable

            System.out.println("Executing cmdWipe()");
            System.out.println("");

            //loop anim
            while (stripOn) {
                //code for anim
                System.out.println("cmdWipe(): Color #1");
                for (int j = 0; j < ledDriver.getNumPixels() && stripOn; j++) {
                    ledDriver.setPixelColourRGB(j, rVal1, bVal1, gVal1);
                    ledDriver.render();
                    PixelAnimations.delay(50);
                }

                System.out.println("cmdWipe(): Color #2");
                for (int j = 0; j < ledDriver.getNumPixels() && stripOn; j++) {
                    ledDriver.setPixelColourRGB(j, rVal2, bVal2, gVal2);
                    ledDriver.render();
                    PixelAnimations.delay(50);
                }

                System.out.println("cmdWipe(): Color #3");
                for (int j = 0; j < ledDriver.getNumPixels() && stripOn; j++) {
                    ledDriver.setPixelColourRGB(j, rVal3, bVal3, gVal3);
                    ledDriver.render();
                    PixelAnimations.delay(50);
                }
                i++;
            }
        } else {
            System.out.println("cmdWipe() off");
            ledDriver.allOff();
        }
    }

}