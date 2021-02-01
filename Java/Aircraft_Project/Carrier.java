/*
 * Class Name:    Carrier
 *
 * Author:        Ashish
 * Creation Date: Tuesday, April 23 2019, 15:27 
 * Last Modified: Wednesday, April 24 2019, 18:59
 * 
 * Class Description:
 *    This is the main driver program for the
 *    OOF Assignment C, Semester 1, 2019
 *
 *    You should be able to compile this program and see the 
 *    menus.
 *
 *    The methods alreay have their headers written.
 *
 *    It is suggested that you follow the order of the menu.
 *    The order in the menu is roughly from easiest to hardest.
 *
 *    Read the whole assignment first.
 *
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Carrier
{
    private Scanner kb; 
    private Aircraft plane1;
    private Aircraft plane2;

    private final int ADD_AIRCRAFT = 1;
    private final int DISPLAY = 2;
    private final int ADD_AIRCREW = 3;
    private final int LAUNCH = 4;
    private final int LAND = 5;
    private final int LOAD_FILE = 6;
    private final int EXIT = 7;

    public static void main(String[] args) throws IOException
    {
         Carrier c = new Carrier( );
         c.run();
    }

    public Carrier()
    {
         kb = new Scanner( System.in );
         plane1 = null;
         plane2 = null;
    }

    private void run() throws IOException
    {
          int choice = -1;
          while (choice != EXIT) {
               displayMenu();
               try {
                    System.out.print("\n\tEnter choice >> ");
                    choice = kb.nextInt();
                    // clear out the newline left after the int read
                    kb.nextLine();
                    process(choice);
               } catch (InputMismatchException ex) {
                    System.out.print("\n\tError! Numeric Input is required");
                    kb.nextLine();
               }
          }
    }

    private void displayMenu( )
    {
         System.out.println( "\n\n\tCarrier Ops Menu\n" );
         System.out.println( "\t" + ADD_AIRCRAFT + ". Add an aircraft");
         System.out.println( "\t" + DISPLAY + ". Display all");
         System.out.println( "\t" + ADD_AIRCREW + ". Add an aircrew");
         System.out.println( "\t" + LAUNCH + ". Launch an aircraft");
         System.out.println( "\t" + LAND + ". Recover an aircraft" );
         System.out.println( "\t" + LOAD_FILE + ". Load from file" );
         System.out.println( "\t" + EXIT + ". Close Carrier Ops" );
    }

    private void process(int choice) throws IOException
    {
         switch (choice) {
              case ADD_AIRCRAFT :
                   addAircraft();
                break;

              case DISPLAY :
                   display();
                break;

              case ADD_AIRCREW:
                   addAircrew();
                break;

              case LAUNCH :
                   launch();
                break;

              case LAND :
                   land();
                break;
               
              case LOAD_FILE :
                   loadFromFile();
                break;

              case EXIT :
                   // just trap this choice so that it does not show
                   // as an error
                break;

              default:
                   System.out.print("\n\t" + choice + " is not a valid choice");
                break;
         }
    }

    // always add to plane1 first, if both aircraft are available
    // this applies to reading from the file too.
    private void addAircraft()
    {
         if (this.plane1 != null && this.plane2 != null) {
              System.out.print("\n\tCannot add another aircraft, the Carrier is full");
         } else {
               boolean checkPlane1 = false;
               boolean checkPlane2 = false;
               String tailCode = this.getTailInput();

               if (this.plane1 != null) {
                    checkPlane1 = this.plane1.checkCodeAssigned(tailCode);
               }
               if (this.plane2 != null) {
                    checkPlane2 = this.plane2.checkCodeAssigned(tailCode);
               }

               if (! (checkPlane1 || checkPlane2)) {
                    if (this.plane1 == null) {
                         this.plane1 = new Aircraft(tailCode);
                    } else {
                         this.plane2 = new Aircraft(tailCode);
                    }
               } else {
                    System.out.print("\n\tThat tail code is already assigned\n\tTail codes must be unique!");
               }
         }
    }

    private Boolean addAircraftByFile(String tailCode, String status)
    {
         if (this.plane1 != null && this.plane2 != null) {
              System.out.print("\n\tCannot add another aircraft, the Carrier is full");
         } else {
               boolean checkPlane1 = false;
               boolean checkPlane2 = false;

               if (this.plane1 != null) {
                    checkPlane1 = this.plane1.checkCodeAssigned(tailCode);
               }
               if (this.plane2 != null) {
                    checkPlane2 = this.plane2.checkCodeAssigned(tailCode);
               }

               if (! (checkPlane1 || checkPlane2)) {
                    if (this.plane1 == null) {
                         this.plane1 = new Aircraft(tailCode, status);
                    } else {
                         this.plane2 = new Aircraft(tailCode, status);
                    }
                    return true;
               } else {
                    System.out.print("\n\tThat tail code is already assigned\n\tTail codes must be unique!");
               }
         }
         return false;
    }
 
    // Adding an aircrew does NOT have to be plane1 first and plane2
    // second. You can add an aircrew to plane2 first, provided that
    // plane2 exists and does not already have an aircrew, of course

    // aircrew call signs have to be unique

    private void addAircrew()
    {
          if (this.plane1 == null && this.plane2 == null) {
               System.out.print("\n\tNo Aircraft, add an aircraft before trying to add an aircrew!");
          } else {
               boolean checkPlane1 = false;
               boolean checkPlane2 = false;

               if ((this.plane1 != null && this.plane1.getCrewStatus()) && (this.plane2 != null && this.plane2.getCrewStatus()) ) {
                    System.out.print("\n\tAll aircraft already have aircrew");
               } else {
                    String tailCode = this.getTailInput();
                    if (this.plane1 != null) {
                         checkPlane1 = this.plane1.checkCodeAssigned(tailCode);
                    }
                    if (this.plane2 != null) {
                         checkPlane2 = this.plane2.checkCodeAssigned(tailCode);
                    }

                    if (checkPlane1 || checkPlane2) {
                         if ((checkPlane1 && this.plane1.getCrewStatus()) || (checkPlane2 && this.plane2.getCrewStatus()) ) {
                              System.out.print("\n\tThis aircraft already has an aircrew");
                         } else {
                              System.out.print("\n\tPlease Enter Crew Sign >> ");
                              String call_sign = kb.nextLine();
                              
                              if (this.checkCallSign(call_sign)) {
                                   System.out.print("\n\tThis call sign has already been assigned, call signs must be unique!");
                              } else {
                                   System.out.print("\n\tPlease enter Crew Name >> ");
                                   String name = kb.nextLine();
                                   if (checkPlane1) {
                                        this.plane1.addAircrew(name, call_sign);
                                   } else {
                                        this.plane2.addAircrew(name, call_sign);
                                   }
                              }
                         }
                    } else {
                         System.out.print("\n\tNo aircraft with that tail code was found");
                    }
               }
          }
    }

    private void addAircrewByFile(String tail_code, String status, String call_sign, String name, int missions)
    {
          if (this.plane1 == null && this.plane2 == null) {
               System.out.print("\n\tNo Aircraft, add an aircraft before trying to add an aircrew!");
          } else {
               boolean checkPlane1 = false;
               boolean checkPlane2 = false;

               String tailCode = tail_code;

               if (this.plane1 != null) {
                    checkPlane1 = this.plane1.checkCodeAssigned(tailCode);
               }
               if (this.plane2 != null) {
                    checkPlane2 = this.plane2.checkCodeAssigned(tailCode);
               }

               if (checkPlane1 || checkPlane2) {
                    if ((checkPlane1 && this.plane1.getCrewStatus()) || (checkPlane2 && this.plane2.getCrewStatus())) {
                         System.out.print("\n\tThis aircraft already has an aircrew");
                    } else {
                         if (this.checkCallSign(call_sign)) {
                              System.out.print("\n\tThis call sign has already been assigned, call signs must be unique!");
                         } else {
                              if (checkPlane1) {
                                   this.plane1.addAircrew(name, call_sign, missions);
                              } else {
                                   this.plane2.addAircrew(name, call_sign, missions);
                              }
                         }
                    }
               } else {
                    System.out.print("\n\tNo aircraft with that tail code was found");
               }
          }
    }
    
    private void launch()
    {
          if (this.plane1 == null && this.plane2 == null) {
               System.out.print("\n\tNo aircraft assigned to the Carrier yet");
          } else {
               boolean checkPlane1 = false;
               boolean checkPlane2 = false;

               String tailCode = this.getTailInput();

               if (this.plane1 != null) {
                    checkPlane1 = this.plane1.checkCodeAssigned(tailCode);
               }
               if (this.plane2 != null) {
                    checkPlane2 = this.plane2.checkCodeAssigned(tailCode);
               }

               if (checkPlane1  || checkPlane2) {
                    if ( (checkPlane1 && !this.plane1.getCrewStatus()) || (checkPlane2 && !this.plane2.getCrewStatus()) ) {
                         System.out.print("\n\tThis aircraft does not have an aircrew, cannot launch");
                    } else {
                         if ( (checkPlane1 && !this.plane1.onDeckStatus()) || (checkPlane2 && !this.plane2.onDeckStatus()) ) {
                              System.out.print("\n\tThis aircraft is already airborne");
                         } else {
                              System.out.print("\n\tPlease Enter Status of mission >> ");
                              String mission_level = kb.nextLine();
                              if (
                                   mission_level.equalsIgnoreCase("Rookie") || 
                                   mission_level.equalsIgnoreCase("Trained") || 
                                   mission_level.equalsIgnoreCase("Veteran") || 
                                   mission_level.equalsIgnoreCase("Ace pilot")
                               ) {
                                   if (checkPlane1 && this.plane1.checkAircrewLevel(mission_level)) {
                                        this.plane1.setStatus("airborne");
                                   } else if (checkPlane2 && this.plane2.checkAircrewLevel(mission_level)) {
                                        this.plane2.setStatus("airborne");
                                   } else {
                                        System.out.print("\n\tThe aircrew of this aircraft do not have the required qualifications");
                                   }
                              } else {
                                   System.out.print("\n\tSystem error! Invalid mission requirement level!");
                              }
                         }
                    }
               } else {
                    System.out.print("\n\tNo aircraft with that tail code was found");
               }
          }
    }

   // as always check that there is at least one aircraft
   // this really should be a method !!!
   private void land()
   {
          if (this.plane1 == null && this.plane2 == null) {
               System.out.print("\n\tThere is no aircraft assigned yet");
          } else {
               boolean checkPlane1 = false;
               boolean checkPlane2 = false;

               String tailCode = this.getTailInput();

               if (this.plane1 != null) {
                    checkPlane1 = this.plane1.checkCodeAssigned(tailCode);
               }
               if (this.plane2 != null) {
                    checkPlane2 = this.plane2.checkCodeAssigned(tailCode);
               }

               if (checkPlane1  || checkPlane2) {
                    if ( (checkPlane1 && this.plane1.onDeckStatus()) || (checkPlane2 && this.plane2.onDeckStatus()) ) {
                         System.out.print("\n\tNo Airplane is on mission!");
                    } else {
                         if (checkPlane1) {
                              this.plane1.setStatus("on deck");
                         } else {
                              this.plane2.setStatus("on deck");
                         }
                    }
               } else {
                    System.out.print("\n\tNo aircraft with that tail code was found");
               }
         }
     }

    private void loadFromFile() throws IOException
    {
          if (this.plane1 != null && this.plane2 != null) {
               System.out.print("\n\tCannot add another aircraft, the Carrier is full");
          } else {
               Scanner keyboard = new Scanner(System.in);
               System.out.print("\n\tEnter File Name >>> ");
               String fileName = keyboard.nextLine();
               
               File file = new File(fileName);
               
               if (! file.exists()) {
                    System.out.println("\n\tFile: \"" + fileName + "\" cannot found!");
               } else {
                    Scanner fileData = new Scanner(file);
                    if (! fileData.hasNext()) {
                         System.out.print("\n\t\"" + fileName + "\" is an empty file, can't proceed!");
                    } else {
                         int i = 0;
                         String d;

                         String tailCode=""; 
                         String status="";
                         String haveAircrew = "";
                         String call_sign=""; 
                         String name="";
                         Boolean finish;
                         int missions=0;
                          
                         while (fileData.hasNext()) {
                              finish = false;
                              d = fileData.nextLine();
                              if (i == 0) {
                                   tailCode = d;
                              }
                              if (i == 1) {
                                   status = d;
                              }
                              if (i == 2) {
                                   haveAircrew = d;
                              }
                              
                              if (haveAircrew.equalsIgnoreCase("0")) {
                                   i = 0;
                                   finish = true;
                                   haveAircrew = "";
                              } else if (haveAircrew.equalsIgnoreCase("1")) {
                                   if (i == 3) {
                                        call_sign = d;
                                   }
                                   if (i == 4) {
                                        name = d;
                                   }

                                   if (i == 5) {
                                        missions = Integer.parseInt(d);
                                        finish = true;
                                        i = 0;
                                        haveAircrew = "";
                                   } else {
                                        i++;
                                   }
                              } else {
                                   i++;
                              }

                              if (finish == true) {
                                   //System.out.printf("\n%s %s %s %s %s", tailCode, status, call_sign, name, missions);
                                   if (this.addAircraftByFile(tailCode, status)) {
                                        if (call_sign != null && !call_sign.isEmpty()) {
                                             this.addAircrewByFile(tailCode, status, call_sign, name, missions);
                                        }
                                   }
                                   tailCode = status = haveAircrew = call_sign = name = "";
                                   missions = 0;
                              }
                         }
                    }
               }
          }
     }

     private void display()
     {
          if (this.plane1 == null && this.plane2 == null) {
               System.out.print("\n\tThere is no aircraft assigned yet, nothing to display");
          } else {
               if (this.plane1 != null) {
                    System.out.println(this.plane1);
               }
               if (this.plane2 != null) {
                    System.out.println(this.plane2);
               }
          }
     }

    private Boolean checkCallSign(String callSign)
     {
          if ((this.plane1 != null && this.plane1.checkCallSign(callSign)) 
               || (this.plane2 != null && this.plane2.checkCallSign(callSign))
          ) {
               return true;
          }
          return false;
     }

     private String getTailInput()
     {
          System.out.print("\n\tEnter Tail Code: >> ");
          kb = new Scanner(System.in);
          String tailCode = kb.nextLine();
          return tailCode;
     }

     private void print(String args)
     {
          System.out.print(args);
     }
}