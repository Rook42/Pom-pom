package net.androidbootcamp.pom_pom;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;


public class TimerListAdapter extends ListAdapter<CompletedTimer, TimerViewHolder> {
    public TimerListAdapter(@NonNull DiffUtil.ItemCallback<CompletedTimer> diffCallback) {
        super(diffCallback);
    }

    @Override
    public TimerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TimerViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(TimerViewHolder holder, int position) {
        CompletedTimer current = getItem(position);
        holder.bind(current.getTimerType(), current.getTimerTimes(), current.getTimerImage());
    }

    static class ItemDiff extends DiffUtil.ItemCallback<net.androidbootcamp.pom_pom.CompletedTimer> {

        @Override
        public boolean areItemsTheSame(@NonNull CompletedTimer oldItem, @NonNull CompletedTimer newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CompletedTimer oldItem, @NonNull CompletedTimer newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }

    }
}
