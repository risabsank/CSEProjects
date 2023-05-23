/*
 * Name: Risab Sankar
 * Email: rsankar@ucsd.edu
 * PID: A17383972
 * Sources used: None
 * 
 * This file is used to implement the Sanctuary class. It includes
 * methods to acquire information about a sanctuary and remove and
 * release from a sanctuary.
 */

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class stores information about sanctuaries and methods
 * to add and remove animals from the sanctuary.
 * 
 * Instance variables:
 * sanctuary - species/population in sanctuary
 * maxAnimals - max animals allowed in sanctuary
 * maxSpecies - max species allowed in sanctuary
 */
public class Sanctuary {
    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;
    private final int maxSpecies;

    /**
     * initializes an object of Sanctuary
     * 
     * @param maxAnimals max animals allowed in sanctuary
     * @param maxSpecies max species allowed in sanctuary
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals <= 0 || maxSpecies <= 0 ||
                maxSpecies > maxAnimals) {
            throw new IllegalArgumentException();
        } else {
            this.maxAnimals = maxAnimals;
            this.maxSpecies = maxSpecies;
            this.sanctuary = new HashMap<>();
        }
    }

    /**
     * counts number of species population in sanctuary
     * 
     * @param species the species being counted
     * @return total number of that particular species
     */
    public int countForSpecies(String species) {
        if (species == null) {
            throw new IllegalArgumentException();
        } else {
            Integer returnVal = sanctuary.get(species);
            if (returnVal == null) {
                return 0;
            } else {
                return returnVal;
            }
        }
    }

    /**
     * gets total animals in the sanctuary
     * 
     * @return total animals in the sanctuary
     */
    public int getTotalAnimals() {
        int total = 0;
        for (HashMap.Entry<String, Integer> entry : sanctuary.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    /**
     * gets total species in the sanctuary
     * 
     * @return total species in the sanctuary
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }

    /**
     * gets max animals allowed in sanctuary
     * 
     * @return max animals in the sanctuary
     */
    public int getMaxAnimals() {
        return this.maxAnimals;
    }

    /**
     * gets max species in sanctuary
     * 
     * @return max species in sanctuary
     */
    public int getMaxSpecies() {
        return this.maxSpecies;
    }

    /**
     * adds certain number of a species into the sanctuary
     * 
     * @param species species being saved
     * @param num     number of that species being saved
     * @return number of species that could not be saved
     */
    public int rescue(String species, int num) {
        if (num <= 0 || species == null) {
            throw new IllegalArgumentException();
        } else {
            if (sanctuary.containsKey(species)) {
                if (num + getTotalAnimals() <= maxAnimals) {
                    sanctuary.replace(species, num + countForSpecies(species));
                    return 0;
                } else {
                    int animalsNotSaved = num + getTotalAnimals() - maxAnimals;
                    int animalsSaved = maxAnimals - getTotalAnimals();
                    sanctuary.replace(species, countForSpecies(species) + animalsSaved);
                    return animalsNotSaved;
                }
            } else {
                if (num + getTotalAnimals() <= maxAnimals &&
                        1 + getTotalSpecies() <= maxSpecies) {
                    sanctuary.put(species, num);
                    return 0;
                } else if (getTotalAnimals() == maxAnimals) {
                    return num;
                } else if (1 + getTotalSpecies() > maxSpecies) {
                    return num;
                } else {
                    // checks to see how many animals can be saved if overflow
                    int animalsNotSaved = num + getTotalAnimals() - maxAnimals;
                    int animalsSaved = maxAnimals - getTotalAnimals();
                    sanctuary.put(species, animalsSaved);
                    return animalsNotSaved;
                }
            }
        }
    }

    /**
     * releases a certain amount of a certain species
     * 
     * @param species species being released
     * @param num     amount of the species being released
     */
    public void release(String species, int num) {
        if (num <= 0 || num > countForSpecies(species) ||
                species == null || !sanctuary.containsKey(species)) {
            throw new IllegalArgumentException();
        } else {
            // gets difference between initial and amount being released
            int initial = countForSpecies(species);
            int diff = initial - num;
            // adjusts key/key values acccordingly
            if (diff == 0) {
                sanctuary.remove(species);
            } else {
                sanctuary.replace(species, diff);
            }
        }
    }
}
