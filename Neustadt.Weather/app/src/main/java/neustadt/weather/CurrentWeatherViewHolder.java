package neustadt.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CurrentWeatherViewHolder extends RecyclerView.ViewHolder {
    private TextView description;
    private TextView maxTemp;
    private TextView minTemp;
    private ImageView icon;
    private Context context;
    private TextView temp;
    private TextView forecast;

    public CurrentWeatherViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        description = (TextView) itemView.findViewById(R.id.description);
        maxTemp = (TextView) itemView.findViewById(R.id.maxTemp);
        minTemp = (TextView) itemView.findViewById(R.id.minTemp);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        temp = (TextView) itemView.findViewById(R.id.temp);
        forecast = (TextView) itemView.findViewById(R.id.forecast);
    }

    public void bind(CurrentWeather currentWeather) {
        description.setText(currentWeather.getDescription());
        maxTemp.setText(currentWeather.getMax() + "°");
        minTemp.setText("        " + currentWeather.getMin() + "°");

        temp.setText(currentWeather.getTemp() + "°");
        Picasso.with(context).load("http://openweathermap.org/img/w/" +
                currentWeather.getIcon() + ".png").into(icon);

        forecast.setText("Forecast");
            }
}



