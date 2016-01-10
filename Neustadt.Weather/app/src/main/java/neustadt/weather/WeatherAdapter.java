package neustadt.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeatherAdapter  extends RecyclerView.Adapter<WeatherViewHolder> {

        private WList[] currentWeather;
        private Context context;

        public WeatherAdapter(WList[] currentWeather, Context context) {
            this.currentWeather = currentWeather;
            this.context = context;
        }

        @Override
        public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
            return new WeatherViewHolder(itemView, context);
        }

        @Override
        public void onBindViewHolder(final WeatherViewHolder holder, final int position) {
            holder.bind(currentWeather[position]);
        }

        @Override
        public int getItemCount() {
            return currentWeather.length;
        }
    }


