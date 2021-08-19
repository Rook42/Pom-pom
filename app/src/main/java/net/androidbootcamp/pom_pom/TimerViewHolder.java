package net.androidbootcamp.pom_pom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class TimerViewHolder extends RecyclerView.ViewHolder {
    private final TextView timerType;
    private final TextView timerTimes;
    private final ImageView timerImg;

    private TimerViewHolder(View itemView) {
        super(itemView);
        timerType = itemView.findViewById(R.id.textView);
        timerTimes = itemView.findViewById(R.id.textView3);
        timerImg = itemView.findViewById(R.id.imageView2);

    }

    static TimerViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()
        ).inflate(R.layout.recycler_view_item, parent, false);
        return new TimerViewHolder(view);
    }

    public void bind(String type, String times, int img) {
        timerType.setText(type);
        timerTimes.setText(times);
        timerImg.setImageResource(img);

    }

}
