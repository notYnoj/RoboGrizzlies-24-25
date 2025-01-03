package org.firstinspires.ftc.teamcode.mechanics.arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class arm {
    utility_motor extender;
    utility_motor rotator;

    public static boolean extender_dir = true;
    public static boolean rotator_dir = true;
    public static int max_height = 60;
    public static int mid_height = 60;
    public static double basket_height = 54;
    public static double basket_rotation = 0.7;


    public static int max_rotation = 1;
    public static int min_rotation = 0;
    public static double high_bar_rotation = 0.3;
    public static int high_bar_height = 38;

    public static int ground_pick_up = 24;
    public static int ground_pick_up_rev = 14;


    public static double ticks_per_inch_ex = 75;
    public static double ticks_per_rotation = 568;
    public static double ticks_per_degree = 568/90;
    public static double offset = 25;



    public arm(LinearOpMode l) {
        extender = new utility_motor(l.hardwareMap, "extender", extender_dir);
        rotator = new utility_motor(l.hardwareMap, "rotator", rotator_dir);
    }

    public void up(){rotator.setPlace((int) (max_rotation * ticks_per_degree), 0.5);}
    public void down(){rotator.setPlace(0, 0.5);}


    public void extend() {rotator.setPlace(25000, 0.5);}
    //useless function
    public void extend_mid() {extender.setPlace((int) (mid_height * ticks_per_inch_ex), 1);}
    public void basket() {
        extender.setPlace((int) (basket_height * ticks_per_rotation), 1);
        rotator.setPlace((int) (basket_rotation * ticks_per_rotation), 0.5);
    }

    public void high_bar() {
        extender.setPlace((int) (high_bar_height * ticks_per_inch_ex), 1.0);
        rotator.setPlace((int) (high_bar_rotation * ticks_per_rotation), 0.5);
    }

    public void ground() {
        extender.setPlace((int) (ground_pick_up * ticks_per_inch_ex), 1);
        rotator.setPlace(0, 0.5);
    }
    public void reverse_ground() {
        extender.setPlace((int) (ground_pick_up_rev * ticks_per_inch_ex), 1);
        rotator.setPlace((int) ( ticks_per_rotation), 0.5);
    }
    public void partial_retract() {
        extender.setPlace((int) (30 * ticks_per_inch_ex), 1);
    }

    public void setExtPos(double in){extender.setPlace((int) ( in*ticks_per_rotation), 1);}
    public void setRotPos(double deg){rotator.setPlace((int) (deg*ticks_per_rotation), 0.3);}

    //go to 45
    public void rot0(){
        double amount1 = 45;
        extender.setPlace((int) ((amount1 + offset) * ticks_per_degree), 0.2);
    }
    //make it go to 0 deg
    public void rot1(){
        extender.setPlace((int) (offset* ticks_per_degree), 0.2);
    }
    //go to 115 deg
    public void rot2(){
        double amount2 = 130;
        extender.setPlace((int) ((amount2 + offset) * ticks_per_degree), 0.2);
    }
    //go all the way back
    public void rot3(){
        double amount3 = 300;
        extender.setPlace((int) ((amount3+offset) * ticks_per_degree), 0.1);
    }

    public void retract() {rotator.setPlace(-500, 1);}

    public void setExtenderPower(double power) {extender.setPower(power);}
    public void setRotatorPower(double power) {rotator.setPower(power);}

    public void testExtenderTicks() {extender.setPlace((int) (10 * ticks_per_inch_ex), 0.3);}
    public void testRotatorTicks() {rotator.setPlace((int) (90 * ticks_per_rotation), 0.3);}
    public void retract_reset(){
        rotator.setPlace(-3500, 0.7);
    }

    public boolean isBusy() {return (extender.getPower() > 0 || rotator.getPower() > 0);}

}
