/*
 * Class Name:    Aircraft
 *
 * Author:        Ashish Rawat
 * Creation Date: Tuesday, April 23 2019, 15:32 
 * Last Modified: Wednesday, April 24 2019, 18:52
 *
 */

public class Aircraft
{
    private String tail_code;
    private String status;
    private Aircrew crew;

    public Aircraft(String tail_code)
    {
        this.tail_code = tail_code;
        this.status = "on deck";
    }

    public Aircraft(String tail_code, String status)
    {
        this.tail_code = tail_code;
        this.status = status;
    }

    public void addAircrew(String name, String call_sign, int missions)
    {
        this.crew = new Aircrew(name, call_sign, missions);
    }

    public void addAircrew(String name, String call_sign)
    {
        this.crew = new Aircrew(name, call_sign);
    }

    public void setStatus(String status)
    {
        if (status.equalsIgnoreCase("airborne")) {
            this.crew.addMission();
        }
        this.status = status;
    }

    public Boolean checkAircrewLevel(String mission_level)
    {
        if (this.getCrewStatus() == true) {
            String current_level = this.crew.getStatus();
            if (mission_level.equalsIgnoreCase(current_level) || mission_level.equalsIgnoreCase("Rookie")) {
                return true;
            } else if (mission_level.equalsIgnoreCase("Trained") && (current_level.equalsIgnoreCase("Veteran") || current_level.equalsIgnoreCase("Ace pilot"))) {
                return true;
            } else if (mission_level.equalsIgnoreCase("Veteran") && current_level.equalsIgnoreCase("Ace pilot")) {
                return true;
            }
        }
        return false;
    }

    public Boolean onDeckStatus()
    {
        return this.status.equalsIgnoreCase("on deck");
    }

    public Boolean getCrewStatus()
    {
        if (this.crew != null) {
            return true;
        }
        return false;
    }

    public Boolean checkCallSign(String call_sign)
    {
        if (this.crew != null && this.crew.getCallSign(call_sign)) {
            return true;
        }
        return false;
    }

    public boolean checkCodeAssigned(String tail_code)
    {
        return tail_code.equalsIgnoreCase(this.tail_code);
    }

    public String toString()
    {
        if (this.crew == null) {
            return "\n\tAircraft\n\t[\n\t\tTail code: " + this.tail_code + "\n\t\tStatus: " + this.status + "\n\n\t\tThis aircraft has no assigned aircrew\n\t]";
        }
        return "\n\tAircraft\n\t[\n\t\tTail code: " + this.tail_code + "\n\t\tStatus: " + this.status + "\n\n\t\t" + this.crew + "\n\t]";
    }
}
