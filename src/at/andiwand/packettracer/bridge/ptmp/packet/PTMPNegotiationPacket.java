package at.andiwand.packettracer.bridge.ptmp.packet;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import at.andiwand.packettracer.bridge.ptmp.PTMPAuthentication;
import at.andiwand.packettracer.bridge.ptmp.PTMPCompression;
import at.andiwand.packettracer.bridge.ptmp.PTMPConfiguration;
import at.andiwand.packettracer.bridge.ptmp.PTMPDataReader;
import at.andiwand.packettracer.bridge.ptmp.PTMPDataWriter;
import at.andiwand.packettracer.bridge.ptmp.PTMPEncoding;
import at.andiwand.packettracer.bridge.ptmp.PTMPEncryption;


public class PTMPNegotiationPacket extends PTMPPacket {
	
	private static enum EncodingTranslator {
		TEXT(1, PTMPEncoding.TEXT),
		BINARY(2, PTMPEncoding.BINARY);
		
		private static final Map<Integer, PTMPEncoding> decodingMap = new HashMap<Integer, PTMPEncoding>();
		private static final Map<PTMPEncoding, Integer> encodingMap = new HashMap<PTMPEncoding, Integer>();
		
		static {
			for (EncodingTranslator translator : values()) {
				decodingMap.put(translator.rawEncoding, translator.encoding);
				encodingMap.put(translator.encoding, translator.rawEncoding);
			}
		}
		
		public static PTMPEncoding decode(int rawEncoding) {
			PTMPEncoding result = decodingMap.get(rawEncoding);
			if (result == null)
				throw new IllegalStateException("Unsupported encoding!");
			return result;
		}
		
		public static int encode(PTMPEncoding encoding) {
			Integer result = encodingMap.get(encoding);
			if (result == null)
				throw new IllegalStateException("Unsupported encoding!");
			return result;
		}
		
		private final int rawEncoding;
		private final PTMPEncoding encoding;
		
		private EncodingTranslator(int rawEncoding, PTMPEncoding encoding) {
			this.rawEncoding = rawEncoding;
			this.encoding = encoding;
		}
	}
	
	private static enum EncryptionTranslator {
		NONE(1, PTMPEncryption.NONE),
		XOR(2, PTMPEncryption.XOR);
		
		private static final Map<Integer, PTMPEncryption> decodingMap = new HashMap<Integer, PTMPEncryption>();
		private static final Map<PTMPEncryption, Integer> encodingMap = new HashMap<PTMPEncryption, Integer>();
		
		static {
			for (EncryptionTranslator translator : values()) {
				decodingMap
						.put(translator.rawEncryption, translator.encryption);
				encodingMap
						.put(translator.encryption, translator.rawEncryption);
			}
		}
		
		public static PTMPEncryption decode(int rawEncryption) {
			PTMPEncryption result = decodingMap.get(rawEncryption);
			if (result == null)
				throw new IllegalStateException("Unsupported encryption!");
			return result;
		}
		
		public static int encode(PTMPEncryption encryption) {
			Integer result = encodingMap.get(encryption);
			if (result == null)
				throw new IllegalStateException("Unsupported encryption!");
			return result;
		}
		
		private final int rawEncryption;
		private final PTMPEncryption encryption;
		
		private EncryptionTranslator(int rawEncryption,
				PTMPEncryption encryption) {
			this.rawEncryption = rawEncryption;
			this.encryption = encryption;
		}
	}
	
	private static enum CompressionTranslator {
		NO(1, PTMPCompression.NO),
		ZLIB_DEFAULT(2, PTMPCompression.ZLIB_DEFAULT);
		
		private static final Map<Integer, PTMPCompression> decodingMap = new HashMap<Integer, PTMPCompression>();
		private static final Map<PTMPCompression, Integer> encodingMap = new HashMap<PTMPCompression, Integer>();
		
		static {
			for (CompressionTranslator translator : values()) {
				decodingMap.put(translator.rawCompression,
						translator.compression);
				encodingMap.put(translator.compression,
						translator.rawCompression);
			}
		}
		
		public static PTMPCompression decode(int rawEncoding) {
			PTMPCompression result = decodingMap.get(rawEncoding);
			if (result == null)
				throw new IllegalStateException("Unsupported compression!");
			return result;
		}
		
		public static int encode(PTMPCompression encoding) {
			Integer result = encodingMap.get(encoding);
			if (result == null)
				throw new IllegalStateException("Unsupported compression!");
			return result;
		}
		
		private final int rawCompression;
		private final PTMPCompression compression;
		
		private CompressionTranslator(int rawCompression,
				PTMPCompression compression) {
			this.rawCompression = rawCompression;
			this.compression = compression;
		}
	}
	
	private static enum AuthenticationTranslator {
		CLEAR_TEXT(1, PTMPAuthentication.CLEAR_TEXT),
		SIMPLE(2, PTMPAuthentication.SIMPLE),
		MD5(4, PTMPAuthentication.MD5);
		
		private static final Map<Integer, PTMPAuthentication> decodingMap = new HashMap<Integer, PTMPAuthentication>();
		private static final Map<PTMPAuthentication, Integer> encodingMap = new HashMap<PTMPAuthentication, Integer>();
		
		static {
			for (AuthenticationTranslator translator : values()) {
				decodingMap.put(translator.rawAuthentication,
						translator.authentication);
				encodingMap.put(translator.authentication,
						translator.rawAuthentication);
			}
		}
		
		public static PTMPAuthentication decode(int rawEncoding) {
			PTMPAuthentication result = decodingMap.get(rawEncoding);
			if (result == null)
				throw new IllegalStateException("Unsupported authentication!");
			return result;
		}
		
		public static int encode(PTMPAuthentication encoding) {
			Integer result = encodingMap.get(encoding);
			if (result == null)
				throw new IllegalStateException("Unsupported authentication!");
			return result;
		}
		
		private final int rawAuthentication;
		private final PTMPAuthentication authentication;
		
		private AuthenticationTranslator(int rawAuthentication,
				PTMPAuthentication authentication) {
			this.rawAuthentication = rawAuthentication;
			this.authentication = authentication;
		}
	}
	
	private static final String PTMP_IDENTIFIER = "PTMP";
	private static final int PTMP_VERSION = 1;
	
	private UUID applicationId;
	private PTMPConfiguration configuration;
	private Date timestamp;
	
	public PTMPNegotiationPacket(int type, UUID applicationId,
			PTMPConfiguration configuration, Date timestamp) {
		super(type);
		
		this.applicationId = applicationId;
		this.configuration = configuration;
		this.timestamp = timestamp;
	}
	
	public PTMPNegotiationPacket(int type, PTMPConfiguration configuration) {
		this(type, UUID.randomUUID(), configuration, new Date());
	}
	
	public PTMPNegotiationPacket(PTMPDataReader reader) {
		super(reader);
	}
	
	public PTMPNegotiationPacket(byte[] packet, PTMPEncoding encoding) {
		super(packet, encoding);
	}
	
	public PTMPNegotiationPacket(PTMPEncodedPacket packet) {
		super(packet);
	}
	
	public PTMPNegotiationPacket(PTMPNegotiationPacket packet) {
		super(packet);
		
		applicationId = packet.applicationId;
		configuration = packet.configuration;
		timestamp = packet.timestamp;
	}
	
	public UUID getApplicationId() {
		return applicationId;
	}
	
	public PTMPConfiguration getConfiguration() {
		return configuration;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void getValue(PTMPDataWriter writer) {
		writer.writeString(PTMP_IDENTIFIER);
		writer.writeInt(PTMP_VERSION);
		writer.writeUuid(applicationId);
		writer.writeInt(EncodingTranslator.encode(configuration.getEncoding()));
		writer.writeInt(EncryptionTranslator.encode(configuration
				.getEncryption()));
		writer.writeInt(CompressionTranslator.encode(configuration
				.getCompression()));
		writer.writeInt(AuthenticationTranslator.encode(configuration
				.getAuthentication()));
		writer.writeTimestamp(timestamp);
		writer.writeInt(configuration.getKeepalive());
		writer.writeString("");
	}
	
	public void setApplicationId(UUID applicationId) {
		this.applicationId = applicationId;
	}
	
	public void setConfiguration(PTMPConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public void parseValue(PTMPDataReader reader) {
		String ptmpIdentifier;
		int ptmpVersion;
		PTMPEncoding encoding;
		PTMPEncryption encryption;
		PTMPCompression compression;
		PTMPAuthentication authentication;
		int keepalive;
		
		ptmpIdentifier = reader.readString();
		if (!ptmpIdentifier.equals(PTMP_IDENTIFIER))
			throw new IllegalArgumentException("PTMP identifier not found!");
		
		ptmpVersion = reader.readInt();
		if (ptmpVersion != PTMP_VERSION)
			throw new IllegalArgumentException("Unsupported PTMP version!");
		
		applicationId = reader.readUuid();
		encoding = EncodingTranslator.decode(reader.readInt());
		encryption = EncryptionTranslator.decode(reader.readInt());
		compression = CompressionTranslator.decode(reader.readInt());
		authentication = AuthenticationTranslator.decode(reader.readInt());
		timestamp = reader.readTimestamp();
		keepalive = reader.readInt();
		reader.readString();
		
		configuration = new PTMPConfiguration(encoding, encryption,
				compression, authentication, keepalive);
	}
	
	protected boolean legalType(int type) {
		return (type == TYPE_NEGOTIATION_REQUEST)
				|| (type == TYPE_NEGOTIATION_RESPONSE);
	}
	
}