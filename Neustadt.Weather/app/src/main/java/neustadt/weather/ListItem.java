package neustadt.weather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListItem {
    private Long dt;
    private Weather[] weather;
    private Temp temp;

    public int getMin() {
        return temp.getMin();
    }

    public int getDay() {
        return temp.getDay();
    }

    public int getMax() {
        return temp.getMax();
    }

    public String getDate() {
        Date date =  new Date(dt * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        return formatter.format(date).toString();
        }


    public String getIcon() {
        return weather[0].getIcon();
    }

    public String getDescription() {
        return weather[0].getDescription();
    }

}