package at.andiwand.packettracer.bridge.ptmp.multiuser.packet;

import at.andiwand.packettracer.bridge.ptmp.PTMPDataReader;
import at.andiwand.packettracer.bridge.ptmp.PTMPDataWriter;
import at.andiwand.packettracer.bridge.ptmp.PTMPEncoding;
import at.andiwand.packettracer.bridge.ptmp.packet.PTMPEncodedPacket;


public class MultiuserNetworkNamePacket extends MultiuserPacket {
	
	public static final int TYPE = TYPE_NETWORK_NAME;
	
	private String networkName;
	
	public MultiuserNetworkNamePacket(String networkName) {
		super(TYPE);
		
		this.networkName = networkName;
	}
	
	public MultiuserNetworkNamePacket(PTMPDataReader reader) {
		super(reader);
	}
	
	public MultiuserNetworkNamePacket(byte[] packet, PTMPEncoding encoding) {
		super(packet, encoding);
	}
	
	public MultiuserNetworkNamePacket(PTMPEncodedPacket packet) {
		super(packet);
	}
	
	public MultiuserNetworkNamePacket(MultiuserNetworkNamePacket packet) {
		super(packet);
		
		networkName = packet.networkName;
	}
	
	public String getNetworkName() {
		return networkName;
	}
	
	@Override
	public void getValue(PTMPDataWriter writer) {
		writer.writeString(networkName);
	}
	
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	
	@Override
	public void parseValue(PTMPDataReader reader) {
		networkName = reader.readString();
	}
	
	@Override
	protected boolean legalType(int type) {
		return type == TYPE;
	}
	
}