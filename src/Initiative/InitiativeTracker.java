package Initiative;

import java.util.ArrayList;

public class InitiativeTracker {
    //Variables---------------------------------------------------------------------------------------------------------
    private ArrayList<Combatant> combatants = null; //list of combatants
    private int cursor = -1; //must be negative one so that when the button is hit for the first time, it pulls up
    //the first combatant, not the second one

    //Methods-----------------------------------------------------------------------------------------------------------
    /**
     * Moves to the next turn in initiative
     */
    public Combatant nextTurn(){
        cursor++;
        if(cursor > combatants.size()-1){
            cursor = 0;
        }
        return combatants.get(cursor);
    }

    /**
     * This method adds a combatant to the ArrayList of "Combatant"s
     * @param combatant the Combatant to be added
     */
    public void addCombatant(Combatant combatant){
        int i = 0;
        while(i < combatants.size() && combatant.compareTo(combatants.get(i)) < 0){
            i++;
        }
        combatants.add(i,combatant);
    }

    /**
     * Sorts combatants using a quicksort algorithm
     */
    public void sortCombatants(){
        QuickSort(combatants, 0, combatants.size()-1);
    }

    /**
     * Sorts the array A
     * @param A the array to be sorted
     * @param p the lower bound to be sorted
     * @param q the upper bound to be sorted
     */
    public void QuickSort(ArrayList<Combatant> A, int p, int q) {
        //if the lower bound is larger or equal to the upper bound then the array is sorted
        if(p < q) {
            //finds sorts based on larger or smaller than a value
            int r = partition(A,p,q);
            //then recursively calls itself to sort the new sections
            QuickSort(A,p,r-1);
            QuickSort(A,r+1,q);
        }
    }

    /**
     * Sorts the array by being larger or smaller than a given value
     * @param A the array to be sorted
     * @param p the upper bound
     * @param q the lower bound
     * @return the point where the numbers are now larger or smaller exclusivly on respective sides
     */
    public int partition(ArrayList<Combatant> A, int p, int q) {
        //selects the pivot then gets the value from that point
        int pivot = q;
        Combatant pivVal = A.get(pivot);
        int i = p,j = q-1;
        //searches for the values to swap, by comparing to the pivot Value
        while(i <= j) {
            while(i <= j && A.get(i).compareTo(pivVal) > 0) {
                i++;
            }
            while(i <= j && A.get(j).compareTo(pivVal) < 0) {
                j--;
            }
            //if a swap is needed it swaps
            if(i<=j) {
                Combatant temp = A.get(i);
                A.set(i,A.get(j));
                A.set(j,temp);
                i++;
                j--;
            }
        }
        //swaps the pivot to create a barrier for each side
        A.set(pivot,A.get(i));
        A.set(i,pivVal);
        //returns the index of where the array is partitioned
        return i;
    }

    /**
     * Returns if the list contains combatants
     * @return True if it contains combatants
     */
    public boolean hasCombatants(){
        if(combatants == null)
            return false;
        return !combatants.isEmpty();
    }

    /**
     * Returns the combatants
     * @return combatants
     */
    public ArrayList<Combatant> getCombatants(){
        return combatants;
    }

    /**
     * This method removes a combatant from the ArrayList of "Combatant"s
     * @param combatant the combatant to be removed
     * @return If the Combatant "combatant" was removed
     */
    public boolean removeCombatant(Combatant combatant){
        try {
            if (combatant == null) {
                new errorWindow().errorWindow("No Combatant Selected to be Removed");
            }
            return combatants.remove(combatant);
        }catch(NullPointerException e){
            return false;
        }
    }

    //Constructors------------------------------------------------------------------------------------------------------
    /**
     * Default Constructor
     */
    public InitiativeTracker(){
        this.combatants = new ArrayList<Combatant>();
    }

    /**
     * Constructor
     * @param combatants list of combatants
     */
    public InitiativeTracker(ArrayList<Combatant> combatants) {
        this.combatants = combatants;
    }
}
