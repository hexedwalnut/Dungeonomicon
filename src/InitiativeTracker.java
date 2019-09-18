public class InitiativeTracker {
    private int initiativeCount = 0;
    private String[] initiativeName;
    private int[] initiativeNumber;
    private int lastInitiativeNumberIndex = 0;

    public InitiativeTracker(String[] initiativeName, int[] initiativeNumber) {
        if (initiativeName.length == initiativeNumber.length) {
            for (int i = 0; i < initiativeNumber.length - 1; i++) {
                int min = i;
                for (int j = i + 1; j < initiativeNumber.length; j++) {
                    if (initiativeNumber[j] < initiativeNumber[min]) {
                        min = j;
                    }
                }
                int tempNumber = initiativeNumber[min];
                String tempName = initiativeName[min];
                initiativeNumber[min] = initiativeNumber[i];
                initiativeName[min] = initiativeName[i];
                initiativeNumber[i] = tempNumber;
                initiativeName[i] = tempName;
            }

            this.initiativeName = initiativeName;
            this.initiativeNumber = initiativeNumber;
        }
    }

    public int getInitiativeCount() {
        return initiativeCount;
    }

    public void setInitiativeCount(int initiativeCount) {
        this.initiativeCount = initiativeCount;
    }

    public String[] getInitiativeNameFull() {
        return initiativeName;
    }

    public String getInitiativeNameIndex(int index) {
        return initiativeName[index];
    }

    public int[] getInitiativeNumberFull() {
        return initiativeNumber;
    }

    public int getInitiativeNumberIndex(int index) {
        return initiativeNumber[index];
    }

    public void nextInitiativeCount() {
        initiativeCount++;
    }

    public int getLastInitiativeNumberIndex() {
        return lastInitiativeNumberIndex;
    }

    public void setLastInitiativeNumberIndex(int lastInitiativeNumberIndex) {
        this.lastInitiativeNumberIndex = lastInitiativeNumberIndex;
    }
}
