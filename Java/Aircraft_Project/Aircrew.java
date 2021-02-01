/*
 * Class Name:    Aircrew
 *
 * Author:        Ashish
 * Creation Date: Tuesday, April 23 2019, 15:38 
 * Last Modified: Wednesday, April 24 2019, 18:43
 * 
 * Class Description:
 *  This is the Aircrew class
 */

public class Aircrew
{
    private String name;
    private String call_sign;
    private int missions;
    private String status;

    public Aircrew(String name, String call_sign, int missions) //// This is the constructor that is called when a new Aircrew is added from the text file..
    {
        this.name = name;
        this.call_sign = call_sign;
        this.missions = missions;
        this.setStatus();
    }

    public Aircrew(String name, String call_sign)
    {
        this.name = name;
        this.call_sign = call_sign;
        this.missions = 0;
        this.setStatus();
    }

    public void setStatus()
    {
        if (this.missions > 16) {
            this.status = "Ace pilot";
        } else if (this.missions >= 11) {
            this.status = "Veteran";
        } else if (this.missions >= 6) {
            this.status = "Trained";
        } else if (this.missions >= 0) {
            this.status = "Rookie";
        }
    }

    public String getStatus()
    {
        return this.status;
    }

    public boolean getCallSign(String call_sign)
    {
        return call_sign.equalsIgnoreCase(this.call_sign);
    }

    public void addMission()
    {
        this.missions++;
        this.setStatus();
    }
    
    public String toString()
    {
        return "Aircrew\n\t\t[\n\t\t\tName: " + this.name + "\n\t\t\tCall Sign: " + this.call_sign + "\n\t\t\tMissions: " + this.missions + "\n\t\t\tStatus:" + this.status + "\n\t\t]";
    }
}
