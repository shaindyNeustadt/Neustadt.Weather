package neustadt.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DisplayWeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private Context context;


    public DisplayWeatherAdapter(List<Object> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof CurrentWeather) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.curr_weather_item, viewGroup, false);
                viewHolder = new CurrentWeatherViewHolder(v1, context);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.weather_list_item, viewGroup, false);
                viewHolder = new WeatherViewHolder(v2, context);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                CurrentWeatherViewHolder vh1 = (CurrentWeatherViewHolder) viewHolder;
                vh1.bind((CurrentWeather) items.get(position));
                break;
            case 1:
                WeatherViewHolder vh2 = (WeatherViewHolder) viewHolder;
                vh2.bind((ListItem) items.get(position));
                break;
        }
    }
}