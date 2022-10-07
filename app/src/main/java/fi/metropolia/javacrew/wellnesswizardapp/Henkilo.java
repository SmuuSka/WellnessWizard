package fi.metropolia.javacrew.wellnesswizardapp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Tassa on javaDoc
 *
 * @tristan version 0.1
 */


public class Henkilo {
    private static Henkilo ourInstance = null;

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
    private HashMap<String, Double> uni;

    public Henkilo(String nimi, int ika, int pituus, double paino, String sukupuoli) {
        this.nimi = nimi;
        this.ika = ika;
        this.pituus = pituus;
        this.paino = paino;
        this.sukupuoli = sukupuoli;
        this.uni = new HashMap<>();
        this.syödytKalorit = 0;
        this.steps = 0;
    }



    public void paivitaUni(double nukuttuAika) {
        String date = java.time.LocalDate.now().toString();
        if (this.uni.containsKey(date)) {
            System.out.println("On jo nukuttu tänään!");
        } else {
            this.uni.put(java.time.LocalDate.now().toString(), nukuttuAika);
            System.out.println("nukutaanpa sitten!!");
        }
    }

    public void getUni() {
        for (Map.Entry<String, Double> entry : uni.entrySet()) {
            System.out.println("Päivämäärä " + entry.getKey() + " nukuttu " + entry.getValue() + " tuntia");
        }
    }

    public float getSteps() {
        return steps;
    }

    public void setSteps(float steps) {
        this.steps += steps;
    }
    public void resetSteps() {
        this.steps = 0;
    }

    public static Henkilo getInstance() {
        return ourInstance;
    }

    public String getNimi() {
        return nimi;
    }

    public int getIka() {
        return ika;
    }

    public int getPituus() {
        return pituus;
    }

    public double getPaino() {
        return paino;
    }

    public String getSukupuoli() {
        return sukupuoli;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setIka(int ika) {
        this.ika = ika;
    }

    public void setPituus(int pituus) {
        this.pituus = pituus;
    }

    public void setPaino(double paino) {
        this.paino = paino;
    }

    public void setSukupuoli(String sukupuoli) {
        this.sukupuoli = sukupuoli;
    }

    public int getSyödytKalorit() {
        return syödytKalorit;
    }

    public void setSyödytKalorit(int syödytKalorit) {
        this.syödytKalorit += syödytKalorit;
    }

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

    @Override
    public String toString() {
        return "Nimi: " + nimi + ", ika: " + ika + ", pituus: " + pituus + ", paino: " + paino + ", sukupuoli: " + sukupuoli;
    }

}
