package ru.hse.caplist;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

public class Packets
{

    private static Ip4 ip4;
    private static Ip6 ip6;
    private static Tcp tcp;
    private static Udp udp;

    static
    {
        ip4 = new Ip4();
        ip6 = new Ip6();
        tcp = new Tcp();
        udp = new Udp();
    }

    private String IpFrom, IpTo;
    private int PortFrom, PortTo, length;

    public Packets(PcapPacket pcapPacket)
    {

        if(pcapPacket.hasHeader(Ip6.ID))
        {
            pcapPacket.getHeader(ip6);
            IpFrom = FormatUtils.asStringIp6(ip6.source(), true).toLowerCase();
            IpTo = FormatUtils.asStringIp6(ip6.destination(), true).toLowerCase();
        }

        else if (pcapPacket.hasHeader(Ip4.ID))
        {
            pcapPacket.getHeader(ip4);
            IpFrom = FormatUtils.ip(ip4.source());
            IpTo = FormatUtils.ip(ip4.destination());
        }

        if (pcapPacket.hasHeader(Tcp.ID))
        {
            pcapPacket.getHeader(tcp);
            PortFrom = tcp.source();
            PortTo = tcp.destination();
        } else if (pcapPacket.hasHeader(Udp.ID))
        {
            pcapPacket.getHeader(udp);
            PortFrom = udp.source();
            PortTo = udp.destination();
        }

        length = pcapPacket.getCaptureHeader().caplen();
    }

    public String getIpFrom()
    {
        return IpFrom;
    }

    public String getIpTo()
    {
        return IpTo;
    }

    public int getPortFrom()
    {
        return PortFrom;
    }

    public int getPortTo()
    {
        return PortTo;
    }

    public int getLength()
    {
        return length;
    }

}

