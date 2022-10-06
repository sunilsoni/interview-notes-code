package com.interview.notes.code.misc.test8;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Programming question #1:
 *
 * Consider the Executor.java program below. It invokes a geo converter that projects a set of latitude and longitude coordinates using the Mercator projection, but it
 * incorporates a distorted falseEast and falseNorth assumption.
 *
 * What is the output of the Executor program?
 */
public class Converter {

    public static NumberFormat thetaFormat = new DecimalFormat("##0.0000000");

    // GEO Constants for the Earth and Florida
    private static double littleA = 6378137.0;
    private static double littleE = 0.081819191;
    private static double littleE2 = POW(littleE, 2);
    private static double phiZero = Math.toRadians(24.000000);
    private static double lambdaZero = Math.toRadians(-84.000000);
    private static double falseEast = 400000.0;
    private static double falseNorth = 0.0;
    private static double phiOne = Math.toRadians(24.000000);
    private static double phiTwo = Math.toRadians(31.500000);

    // URL related
    // shorthand routines for the java Math package
    private static double ABS(double value) {
        return Math.abs(value);
    }

    private static int ABS(int value) {
        return Math.abs(value);
    }

    private static double ASIN(double value) {
        return Math.asin(value);
    }

    private static double ATAN(double value) {
        return Math.atan(value);
    }

    private static double COS(double value) {
        return Math.cos(value);
    }

    private static double FLOOR(double value) {
        return Math.floor(value);
    }

    private static double LN(double value) {
        return Math.log(value);
    }

    private static double POW(double a, double b) {
        return Math.pow(a, b);
    }

    private static double SIN(double value) {
        return Math.sin(value);
    }

    private static double SQRT(double value) {
        return Math.sqrt(value);
    }

    private static double TAN(double value) {
        return Math.tan(value);
    }

    // this is used in multiple latitude calculations, so we made it a generic routine

    private static double alphaCalc(double phi) {

        double part1 = (1.0 - littleE2);
        double part2 = SIN(phi) / (1 - littleE2 * (POW(SIN(phi), 2)));
        double part3 = 1.0 / (2 * littleE);
        double part4 = (1.0 - littleE * (SIN(phi))) / (1.0 + littleE * (SIN(phi)));
        double alpha = part1 * (part2 - part3 * LN(part4));
        return alpha;

    } // end of method alphaCalc

    public static void run() {

        double phi = 0;
        double lambda = -1;

        phi = Math.toRadians(phi);
        lambda = Math.toRadians(lambda);

        double mOne = (COS(phiOne)) / SQRT(1 - (littleE2 * POW(SIN(phiOne), 2)));
        double mTwo = (COS(phiTwo)) / SQRT(1 - (littleE2 * POW(SIN(phiTwo), 2)));
        double alpha = alphaCalc(phi);
        double alphaZero = alphaCalc(phiZero);
        double alphaOne = alphaCalc(phiOne);
        double alphaTwo = alphaCalc(phiTwo);
        double littleN = (POW(mOne, 2) - POW(mTwo, 2)) / ABS(alphaTwo - alphaOne);
        double bigC = POW(mOne, 2) + (littleN * alphaOne);
        double theta = littleN * (lambda - lambdaZero);
        double rho = (littleA * (POW((bigC - littleN * alpha), 0.5))) / littleN;
        double rhoZero = (littleA * (POW((bigC - littleN * alphaZero), 0.5))) / littleN;
        double theEasting = falseEast + (rho * SIN(theta));
        double theNorthing = falseNorth + (rhoZero - (rho * (COS(theta))));

        double part1 = (1.0 - littleE2);
        double part2 = SIN(phi) / (1 - littleE2 * (POW(SIN(phi), 2)));
        double part3 = 1.0 / (2 * littleE);
        double part4 = (1.0 - littleE * (SIN(phi))) / (1.0 + littleE * (SIN(phi)));

        // System.out.println("executing");

        alpha = part1 * (part2 - part3 * LN(part4));
        phi = Math.toRadians(phi);
        lambda = Math.toRadians(lambda);

        double latitude = lambda * phi / rhoZero;
        double longitude = littleN + alpha;

        int latSign = (latitude < 0) ? -1 : 1;
        int lonSign = (longitude < 0) ? -1 : 1;
        if (latitude < 0) latitude = -1 * latitude;
        if (longitude < 0) longitude = -1 * longitude;

        int lat_dd = (int) Math.floor(latitude);
        int lat_mm = (int) ((latitude - lat_dd) * 60.0);
        latitude = latitude - lat_dd;
        latitude = latitude - (lat_mm / 60.0);
        double lat_ss = (latitude * 3600.0);

        int lon_dd = (int) Math.floor(longitude);
        int lon_mm = (int) ((longitude - lon_dd) * 60.0);
        longitude = longitude - lon_dd;
        longitude = longitude - (double) (lon_mm / 60.0);
        double lon_ss = (double) (longitude * 3600);

        lat_dd = lat_dd * latSign;
        lon_dd = lon_dd * lonSign;

        // System.out.println("converting");

        double alphaPrime = (bigC - (POW(rho, 2) * POW(littleN, 2) / POW(littleA, 2))) / littleN;
        double bP1 = LN((1 - littleE) / (1 + littleE));
        double bP2 = (1 - littleE2) / (2 * littleE);
        double betaPrime = ASIN(alphaPrime / (1.0 - (bP2) * (bP1)));
        double phiPart3 = ((761 * POW(littleE, 6)) / 45360) * SIN(6.0 * betaPrime);
        double phiPart2 = (((23 * POW(littleE, 4)) / 360) + ((251 * POW(littleE, 6)) / 3780)) * SIN(4 * betaPrime);
        double phiPart1 = ((littleE2 / 3) + ((31 * POW(littleE, 4)) / 180) + ((517 * POW(littleE, 6)) / 5040)) * SIN(2 * betaPrime);
        phi = (betaPrime + phiPart1 + phiPart2 + phiPart3);

        latSign = (lat_dd < 0) ? -1 : 1;
        lonSign = (lon_dd < 0) ? -1 : 1;
        if (lon_dd < 0) lon_dd = -1 * lon_dd;
        if (lat_dd < 0) lat_dd = -1 * lat_dd;

        System.out.println("ok.");

        latitude = (double) lat_dd + (lat_mm / 60.0) + (lat_ss / 3600.0);
        longitude = (double) lon_dd + (lon_mm / 60.0) + (lon_ss / 3600.0);
        longitude = longitude * lonSign;
        latitude = latitude * latSign;
    }

}
