package neustadt.weather;

public class CurrentWeather {

    private Weather[] weather;
    private Main main;
    private String name;

    public int getTemp() {
        return main.getTemp();
    }

    public int getMin() {
        return main.getMin();
    }

    public int getMax() {
        return main.getMax();
    }

    public String getIcon() {
        return weather[0].getIcon();
    }

    public String getDescription() {
        return weather[0].getDescription();
    }

    public String getName() {
        return name;
    }

}