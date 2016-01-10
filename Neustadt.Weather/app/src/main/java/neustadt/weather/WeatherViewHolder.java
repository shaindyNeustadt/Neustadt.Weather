package neustadt.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    private TextView dayOfWeek;
    private TextView maxTemp;
    private TextView minTemp;
    private ImageView image;
    private Context context;


    public WeatherViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        dayOfWeek = (TextView) itemView.findViewById(R.id.dayOfWeek);
        maxTemp = (TextView) itemView.findViewById(R.id.maxTemp);
        minTemp = (TextView) itemView.findViewById(R.id.minTemp);
        image = (ImageView) itemView.findViewById(R.id.image);
        }

    public void bind(WList list) {
        dayOfWeek.setText(list.getDate());
        maxTemp.setText(list.getMax() + "°");
        minTemp.setText("  " + list.getMin() + "°");

       Picasso.with(context).load("http://openweathermap.org/img/w/" +
              list.getIcon() + ".png").into(image);



    }
}


