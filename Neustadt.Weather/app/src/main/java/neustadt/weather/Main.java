package neustadt.weather;

public class Main {

    private double temp;
    private double temp_min;
    private double temp_max;

    public int getTemp() {
        return (int) temp;
    }

    public int getMin() {
        return (int) temp_min;
    }

    public int getMax() {
        return (int) temp_max;
    }
}