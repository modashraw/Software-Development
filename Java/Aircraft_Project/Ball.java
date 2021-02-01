import java.util.*;
import java.io.*;

public class Ball
{
   public static void main(String[] args) throws IOException
   {
      //Given Initialized constants
      final double INITIAL_ANGLE       = 3.73; //degrees
      final double INITIAL_HEIGHT      = 65.00;
      final double INITIAL_DISTANCE    = 1000.00;
      
      //Variables to play with
      double angle = INITIAL_ANGLE;
      double height = INITIAL_HEIGHT;
      double distance = INITIAL_DISTANCE;

      // Commands to execute
      final String GU  = "gear up";
      final String GD  = "gear down";
      final String HU  = "hook up";
      final String HD  = "hook down";
      final String FU  = "flaps up";
      final String FD  = "flaps down";
      final String MA  = "maintain";
      final String PU  = "power up";
      final String PD  = "power down";
      final String EJ  = "eject";
      
      //Intialize default value
      int hook_up = 0;
      int gear_up = 0;
      int flaps_up = 0;
      
      //Check if any command changes any variable and display messages accordingly
      boolean cmd_accepted = true;
      
      //Check if command is valid for e.g -> eject and invalid command will return valid = false
      boolean valid;
      String command = "";
      
      //Get speed
      System.out.print("Enter speed (in knots) >> ");
      
      Scanner speedInput = new Scanner(System.in);
      double speed = speedInput.nextDouble();
      
      
      System.out.println();
      System.out.printf("Starting distance from carrier = %.2f feet\n", INITIAL_HEIGHT);
      System.out.printf("Starting height from carrier deck = %.2f feet\n", INITIAL_DISTANCE);
      System.out.printf("Starting angle = %.2f degrees\n", INITIAL_ANGLE);
      
      //Run loop till eject is find out
      do {
         valid = true;
         cmd_accepted = true;
         System.out.println("\n\tFlight Menu");
         System.out.println("------------------");
         System.out.printf("\n\t %s \n\t %s \n\t %s \n\t %s \n\t %s \n\t %s \n\t %s \n\t %s \n\t %s \n\t %s\n\n", GU, GD, HU, HD, FU, FD, MA, PU, PD, EJ);
         System.out.print(">> ");
         
         //Get command from user
         Scanner commandInput = new Scanner(System.in);
         command = commandInput.nextLine().toLowerCase();
         
         switch(command) {
            // Check if gear up, if not add 20.0 to height else display Already up and set cmd_accepted = false 
            case GU:
               if (gear_up == 1) {
                  height += 20.0;
                  gear_up = 0;
               } else {
                  System.out.println("Gears are already up!\n");
                  cmd_accepted = false;
               }
               break;
            
            // Check if gear down, if not reduct 20.0 to height else display Already down and set cmd_accepted = false 
            case GD:
               if (gear_up == 0) {
                  height -= 20.0;
                  gear_up = 1;
               } else {
                  System.out.println("Gears are already down!\n");
                  cmd_accepted = false;
               }
               break;
            
            // Check if hook up, if not reduct 10.0 to height else display Already up and set cmd_accepted = false 
            case HU:
               if (hook_up == 1) {
                  hook_up += 10.0;
                  hook_up = 0;
               } else {
                  System.out.println("Hook is already up!\n");
                  cmd_accepted = false;
               }
               break;
            
            // Check if hook down, if not reduct 10.0 to height else display Already down and set cmd_accepted = false 
            case HD:
               if (hook_up == 0) {
                  height -= 10.0;
                  hook_up = 1;
               } else {
                  System.out.println("Hook is already down!\n");
                  cmd_accepted = false;
               }
               break;
            
            // Check if flaps up on 0 or down level, if condtion true add 15.0 to height else level the flaps to 0. 
            // Will keep increase height even flaps are already up when given up command again
            case FU:
               if (flaps_up >= 0) {
                  if (flaps_up == 1) {
                     System.out.println("Flaps are already up!\n");
                  } else {
                     flaps_up++;
                  }
                  height += 15.0;
               } else {
                  flaps_up++;
               }
               break;
            
            
            // Check if flaps up on 1 or up level, if condtion true reduct 15.0 to height else level the flaps to 0. 
            // Will keep reducing height even flaps are already down when given up command again
            case FD:
               if (flaps_up <= 0) {
                  if (flaps_up == -1) {
                     System.out.println("Flaps are already down!\n");
                  } else {
                     flaps_up--;
                  }
                  height -= 15.0;
               } else {
                  flaps_up--;
               }
               break;
            
            // Maintainence will leveled the flaps to 0. Won't effect any height
            case MA:
               flaps_up = 0;
               break;
            
            // Power up will add 5.0 to height
            case PU:
               height += 5.0;
               break;
            
            // Power down will reduct 5.0 to height
            case PD:
               height -= 5.0;
               break;
            
            // EJECT will exit from program
            case EJ:
               System.out.println("\t\tTough decision, better to save yourself");
               System.out.println("\t\tWe can always get another plane");
               break;
            
            // Case Else when command is not found, go ask for command again
            default:
               System.out.println(command  + " is not a valid command, please re-enter\n");
               valid = false;
               break;
            
         }
         
         if (valid == true) {
            if (cmd_accepted == true) {
               
               //Change variables according to command given
               distance = distance - (speed * 1.688);
               double rad = height / distance;
               angle = Math.toDegrees(Math.tan(rad));
               
               System.out.printf("Distance from carrier = %.2f feet\n", distance);
               System.out.printf("Height from carrier deck = %.2f feet\n", height);
               
               if ((height >= 15 && angle > 0)|| command.equalsIgnoreCase(EJ)) {
                  System.out.printf("Angle = %.2f degrees\n", angle);
               }
               
               if (! command.equalsIgnoreCase(EJ)) {
                  if (distance > 0 && angle > 15) {
                     System.out.println("\n\tLight showing\n");
                     System.out.println("\tBLINKING RED");
                     System.out.println("\tYou're so high you may be in space next");
                     System.out.println("\tReduce power quickly!!!");
                  } else if (distance > 0 && (angle > 10 && angle <= 15)) {
                     System.out.println("\n\tLight showing\n");
                     System.out.println("\tRED");
                     System.out.println("\tWay too high, potential wave off\t\nLooking at a go round\t\nReduce height immediately!!");
                  } else if (angle < 1 && distance >= 0) {
                     System.out.println("\t\nLight showing\n");
                     System.out.println("\tBLINKING RED");
                     System.out.println("\tAbout to crash into the ship or the ocean");
                  } else if (angle > 5 && angle <= 10) {
                     System.out.println("\t\nLight showing\n");
                     System.out.println("\tAMBER");
                     System.out.println("\tTOO HIGH!!! Reduce power");
                  } else if (angle >= 1 && angle <= 5) {
                     System.out.println("\t\nLight showing\n");
                     System.out.println("\tGREEN");
                     System.out.println("\tOn the glide path");
                  } else if (distance < -400) {
                     System.out.println("You are too far down the deck");
                     System.out.println("You have missed the landing wires, overshoot!!");
                     //command = EJ;
                  } else if ((height >= 0 && height <= 15) && (distance < 0 && distance > -400)) {
                     if (gear_up == 0) {
                        System.out.println("You crashed!!");
                        System.out.println("You found the deck but forgot to put your landing gear down!!");
                     } else if (hook_up == 0) {
                        System.out.println("\nYou found the deck");
                        System.out.println("but did you forget something like putting the hook down!!");
                        System.out.println("Go round!!");
                     } else {
                        System.out.println("\nCongratulations!!");
                        System.out.println("Landing on a carrier deck is the hardest thing");
                        System.out.println("well done");
                     }
                     //command = EJ;
                  }
               }
            }
         }
      } while (! command.equalsIgnoreCase(EJ));
   }
}