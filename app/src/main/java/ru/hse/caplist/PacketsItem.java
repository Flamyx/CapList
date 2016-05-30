package ru.hse.caplist;

import org.jnetpcap.packet.PcapPacket;


public class PacketsItem
{

    private final String IpFrom, IpTo;
    private final int length;

    public PacketsItem(PcapPacket pcapPacket)
    {
        IpFrom = null;
        IpTo = null;
        length = 0;
    }

    public String getIpFrom() {
        return IpFrom;
    }

    public String getIpTo() {
        return IpTo;
    }

    public int getLength() {
        return length;
    }
}
