package ru.hse.caplist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PacketAdapter extends RecyclerView.Adapter<PacketAdapter.ViewHolder>
{

    private ArrayList<Packets> packets;
    private OnPacketSelectedListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.number) public TextView number;
        @Bind(R.id.ip_from) public TextView ip_from;
        @Bind(R.id.ip_to) public TextView ip_to;
        @Bind(R.id.length) public TextView length;
        public View view;

        public ViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    public PacketAdapter(final OnPacketSelectedListener listener)
    {
        this.listener = listener;
        this.packets = new ArrayList<>();
    }

    @Override
    public PacketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.packet_view, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Packets packet = packets.get(position);

        holder.number.setText(String.valueOf(position + 1));
        holder.ip_from.setText(packet.getIpFrom());
        holder.ip_to.setText(packet.getIpTo());
        holder.length.setText(String.valueOf(packet.getLength()).concat(" bytes"));

        holder.view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onPacketSelected(packet);
            }
        } );
    }

    @Override
    public int getItemCount()
    {
        return packets.size();
    }

    public void setData(final List<Packets> packets)
    {
        this.packets.clear();
        this.packets.addAll(packets);
        notifyDataSetChanged();
    }

    interface OnPacketSelectedListener
    {
        void onPacketSelected(final Packets packet);
    }
}
