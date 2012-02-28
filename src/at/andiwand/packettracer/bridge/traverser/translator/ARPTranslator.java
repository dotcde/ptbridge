package at.andiwand.packettracer.bridge.traverser.translator;

import java.net.Inet4Address;

import at.andiwand.library.network.mac.MACAddress;
import at.andiwand.packetsocket.pdu.ARPPacket;
import at.andiwand.packettracer.bridge.ptmp.multiuser.pdu.MultiuserARPPacket;


public class ARPTranslator extends
		GenericPDUTranslator<ARPPacket, MultiuserARPPacket> {
	
	@Override
	public MultiuserARPPacket toMultiuserGeneric(ARPPacket packet) {
		MultiuserARPPacket result = new MultiuserARPPacket();
		
		result.setHardwareType(packet.getHardwareType());
		result.setProtocolType(packet.getOperation());
		result.setOperation(packet.getOperation());
		result.setSenderHardwareAddress((MACAddress) packet
				.getSenderHardwareAddress());
		result.setTargetHardwareAddress((MACAddress) packet
				.getTargetHardwareAddress());
		result.setSenderProtocolAddress((Inet4Address) packet
				.getSenderProtocolAddress());
		result.setTargetProtocolAddress((Inet4Address) packet
				.getTargetProtocolAddress());
		
		return result;
	}
	
	@Override
	public ARPPacket toNetworkGeneric(MultiuserARPPacket packet) {
		ARPPacket result = new ARPPacket();
		
		result.setHardwareType(packet.getHardwareType());
		result.setProtocolType(packet.getOperation());
		result.setOperation(packet.getOperation());
		result.setSenderHardwareAddress(packet.getSenderHardwareAddress());
		result.setSenderProtocolAddress(packet.getSenderProtocolAddress());
		result.setTargetHardwareAddress(packet.getTargetHardwareAddress());
		result.setTargetProtocolAddress(packet.getTargetProtocolAddress());
		
		return result;
	}
	
}