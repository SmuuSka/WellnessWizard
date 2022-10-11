package fi.metropolia.javacrew.wellnesswizardapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class Henkilo
 * here you control the behaviour and state of the Henkilo
 * @author tristan
 */

public class Henkilo {
    private static Henkilo ourInstance = null;

    /**
     * Saves Henkilo object to singleton
     * @param ourInstance
     */
    public static void setInstance(Henkilo ourInstance) {
        Henkilo.ourInstance = ourInstance;
    }

    private String nimi;
    private int ika;
    private int pituus;
    private double paino;
    private String sukupuoli;
    private int syödytKalorit;
    private float steps;
    private float compensationSteps;
    private HashMap<String, Double> uni;



    /**
     * the state of a new born Henkilo.
     * Constructor for person class.
     * Defines name,age,height,weight,gender.
     * States for sleep, eaten calories and steps.
     * @param nimi name of Henkilo
     * @param ika age of Henkilo
     * @param pituus height of Henkilo
     * @param paino weight of Henkilo
     * @param sukupuoli gender of Henkilo
     */

    public Henkilo(String nimi, int ika, int pituus, double paino, String sukupuoli) {
        this.nimi = nimi;
        this.ika = ika;
        this.pituus = pituus;
        this.paino = paino;
        this.sukupuoli = sukupuoli;
        this.uni = new HashMap<>();
        this.syödytKalorit = 0;
        this.steps = 0;
        this.compensationSteps = 0;

    }

    /**
     * Method to add your sleep for the past night
     * takse in a number value and adds it tu hashmap with the current date.
     * @param nukuttuAika is the value the user gives and it describes the slept time.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void paivitaUni(double nukuttuAika) {
        String date = java.time.LocalDate.now().toString();
        if (this.uni.containsKey(date)) {
            System.out.println("On jo nukuttu tänään!");
        } else {
            this.uni.put(java.time.LocalDate.now().toString(), nukuttuAika);
            System.out.println("nukutaanpa sitten!!");
        }
    }

    /**
     * Prints out the hashmap where the string value is the date and double the amount of sleep.
     */
    public void getUni() {
        for (Map.Entry<String, Double> entry : uni.entrySet()) {
            System.out.println("Päivämäärä " + entry.getKey() + " nukuttu " + entry.getValue() + " tuntia");
        }
    }

    /**
     * Returns the state of compensationSteps of Henkilo object.
     * described elsewhere.
     * @return compensationSteps
     */
    public float getCompensationSteps() {
        return compensationSteps;
    }

    /**
     * Increments the state of compensation steps by value of parameter.
     * @param compensationSteps
     */
    public void setCompensationSteps(float compensationSteps) {
        this.compensationSteps += compensationSteps;
    }

    /**
     * Resets the value of compensation steps to 0.
     */
    public void resetCompensationSteps() {
        this.compensationSteps = 0;
    }

    /**
     * Returns the current amount of steps of the Henkilo object.
     * @return steps
     */
    public float getSteps() {
        return steps;
    }

    /**
     * Increments the current amount of steps by the value of parameter.
     * @param steps
     */
    public void setSteps(float steps) {
        this.steps = steps;
    }

    /**
     * Resets the value of steps to 0.
     */
    public void resetSteps() {
        this.steps = 0;
    }

    /**
     * Returns the singleton, instance of a Henkilo object.
     * @return instance of Henkilo object
     */
    public static Henkilo getInstance() {
        return ourInstance;
    }

    /**
     * Returns the name of the Henkilo object.
     * @return
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Returns the age of the Henkilo object.
     * @return age
     */
    public int getIka() {
        return ika;
    }

    /**
     * Returns the height of the Henkilo object.
     * @return height
     */
    public int getPituus() {
        return pituus;
    }

    /**
     * Returns the wight of the Henkilo object.
     * @return weight
     */
    public double getPaino() {
        return paino;
    }

    /**
     * Returns the Gender of the Henkilo object.
     * @return gender
     */
    public String getSukupuoli() {
        return sukupuoli;
    }

    /**
     * Sets the name for Henkilo object.
     * @param nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Sets the age for Henkilo object.
     * @param ika
     */
    public void setIka(int ika) {
        this.ika = ika;
    }

    /**
     * Sets the height for Henkilo object.
     * @param pituus
     */
    public void setPituus(int pituus) {
        this.pituus = pituus;
    }

    /**
     * Sets the weight for Henkilo object.
     * @param paino
     */
    public void setPaino(double paino) {
        this.paino = paino;
    }

    /**
     * Sets the gender for Henkilo object.
     * @param sukupuoli
     */
    public void setSukupuoli(String sukupuoli) {
        this.sukupuoli = sukupuoli;
    }

    /**
     * Returns eaten calories of Henkilo object.
     * @return
     */
    public int getSyödytKalorit() {
        return syödytKalorit;
    }

    /**
     * Increments current eaten calories by the value given as parameter.
     * @param syödytKalorit
     */
    public void setSyödytKalorit(int syödytKalorit) {
        this.syödytKalorit += syödytKalorit;
    }

    /**
     * Resets eaten eaten calories to zero.
     */
    public void nollaaSyodytKalorit() {
        this.syödytKalorit = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Henkilo other = (Henkilo) obj;
        if (this.ika != other.ika) {
            return false;
        }
        if (this.pituus != other.pituus) {
            return false;
        }
        if (Double.doubleToLongBits(this.paino) != Double.doubleToLongBits(other.paino)) {
            return false;
        }
        if (!Objects.equals(this.nimi, other.nimi)) {
            return false;
        }
        if (!Objects.equals(this.sukupuoli, other.sukupuoli)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.nimi);
        hash = 73 * hash + this.ika;
        hash = 73 * hash + this.pituus;
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.paino) ^ (Double.doubleToLongBits(this.paino) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.sukupuoli);
        return hash;
    }

    /**
     * Returns a string where you have current values of Henkilo.
     * @return
     */
    @Override
    public String toString() {
        return "Nimi: " + nimi + ", ika: " + ika + ", pituus: " + pituus + ", paino: " + paino + ", sukupuoli: " + sukupuoli;
    }

}
