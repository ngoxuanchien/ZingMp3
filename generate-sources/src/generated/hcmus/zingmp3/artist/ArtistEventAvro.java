/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package hcmus.zingmp3.artist;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class ArtistEventAvro extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7419396629960266393L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ArtistEventAvro\",\"namespace\":\"hcmus.zingmp3.artist\",\"fields\":[{\"name\":\"eventType\",\"type\":\"string\"},{\"name\":\"artist\",\"type\":{\"type\":\"record\",\"name\":\"ArtistAvro\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"name\",\"type\":\"string\",\"default\":\"\"},{\"name\":\"alias\",\"type\":\"string\"},{\"name\":\"playlistId\",\"type\":[\"string\",\"null\"]},{\"name\":\"thumbnail\",\"type\":\"string\"},{\"name\":\"totalFollow\",\"type\":\"int\"},{\"name\":\"sortBiography\",\"type\":[\"string\",\"null\"]},{\"name\":\"biography\",\"type\":[\"string\",\"null\"]},{\"name\":\"national\",\"type\":[\"string\",\"null\"]},{\"name\":\"realName\",\"type\":[\"string\",\"null\"]},{\"name\":\"birthday\",\"type\":[\"string\",\"null\"]},{\"name\":\"awards\",\"type\":[{\"type\":\"array\",\"items\":\"string\",\"default\":[]},\"null\"]},{\"name\":\"songs\",\"type\":[{\"type\":\"array\",\"items\":\"string\",\"default\":[]},\"null\"]},{\"name\":\"composedSongs\",\"type\":[{\"type\":\"array\",\"items\":\"string\",\"default\":[]},\"null\"]}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<ArtistEventAvro> ENCODER =
      new BinaryMessageEncoder<ArtistEventAvro>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<ArtistEventAvro> DECODER =
      new BinaryMessageDecoder<ArtistEventAvro>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<ArtistEventAvro> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<ArtistEventAvro> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<ArtistEventAvro>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this ArtistEventAvro to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a ArtistEventAvro from a ByteBuffer. */
  public static ArtistEventAvro fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence eventType;
  @Deprecated public hcmus.zingmp3.artist.ArtistAvro artist;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public ArtistEventAvro() {}

  /**
   * All-args constructor.
   * @param eventType The new value for eventType
   * @param artist The new value for artist
   */
  public ArtistEventAvro(java.lang.CharSequence eventType, hcmus.zingmp3.artist.ArtistAvro artist) {
    this.eventType = eventType;
    this.artist = artist;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return eventType;
    case 1: return artist;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: eventType = (java.lang.CharSequence)value$; break;
    case 1: artist = (hcmus.zingmp3.artist.ArtistAvro)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'eventType' field.
   * @return The value of the 'eventType' field.
   */
  public java.lang.CharSequence getEventType() {
    return eventType;
  }

  /**
   * Sets the value of the 'eventType' field.
   * @param value the value to set.
   */
  public void setEventType(java.lang.CharSequence value) {
    this.eventType = value;
  }

  /**
   * Gets the value of the 'artist' field.
   * @return The value of the 'artist' field.
   */
  public hcmus.zingmp3.artist.ArtistAvro getArtist() {
    return artist;
  }

  /**
   * Sets the value of the 'artist' field.
   * @param value the value to set.
   */
  public void setArtist(hcmus.zingmp3.artist.ArtistAvro value) {
    this.artist = value;
  }

  /**
   * Creates a new ArtistEventAvro RecordBuilder.
   * @return A new ArtistEventAvro RecordBuilder
   */
  public static hcmus.zingmp3.artist.ArtistEventAvro.Builder newBuilder() {
    return new hcmus.zingmp3.artist.ArtistEventAvro.Builder();
  }

  /**
   * Creates a new ArtistEventAvro RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ArtistEventAvro RecordBuilder
   */
  public static hcmus.zingmp3.artist.ArtistEventAvro.Builder newBuilder(hcmus.zingmp3.artist.ArtistEventAvro.Builder other) {
    return new hcmus.zingmp3.artist.ArtistEventAvro.Builder(other);
  }

  /**
   * Creates a new ArtistEventAvro RecordBuilder by copying an existing ArtistEventAvro instance.
   * @param other The existing instance to copy.
   * @return A new ArtistEventAvro RecordBuilder
   */
  public static hcmus.zingmp3.artist.ArtistEventAvro.Builder newBuilder(hcmus.zingmp3.artist.ArtistEventAvro other) {
    return new hcmus.zingmp3.artist.ArtistEventAvro.Builder(other);
  }

  /**
   * RecordBuilder for ArtistEventAvro instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ArtistEventAvro>
    implements org.apache.avro.data.RecordBuilder<ArtistEventAvro> {

    private java.lang.CharSequence eventType;
    private hcmus.zingmp3.artist.ArtistAvro artist;
    private hcmus.zingmp3.artist.ArtistAvro.Builder artistBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(hcmus.zingmp3.artist.ArtistEventAvro.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.eventType)) {
        this.eventType = data().deepCopy(fields()[0].schema(), other.eventType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.artist)) {
        this.artist = data().deepCopy(fields()[1].schema(), other.artist);
        fieldSetFlags()[1] = true;
      }
      if (other.hasArtistBuilder()) {
        this.artistBuilder = hcmus.zingmp3.artist.ArtistAvro.newBuilder(other.getArtistBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing ArtistEventAvro instance
     * @param other The existing instance to copy.
     */
    private Builder(hcmus.zingmp3.artist.ArtistEventAvro other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.eventType)) {
        this.eventType = data().deepCopy(fields()[0].schema(), other.eventType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.artist)) {
        this.artist = data().deepCopy(fields()[1].schema(), other.artist);
        fieldSetFlags()[1] = true;
      }
      this.artistBuilder = null;
    }

    /**
      * Gets the value of the 'eventType' field.
      * @return The value.
      */
    public java.lang.CharSequence getEventType() {
      return eventType;
    }

    /**
      * Sets the value of the 'eventType' field.
      * @param value The value of 'eventType'.
      * @return This builder.
      */
    public hcmus.zingmp3.artist.ArtistEventAvro.Builder setEventType(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.eventType = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'eventType' field has been set.
      * @return True if the 'eventType' field has been set, false otherwise.
      */
    public boolean hasEventType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'eventType' field.
      * @return This builder.
      */
    public hcmus.zingmp3.artist.ArtistEventAvro.Builder clearEventType() {
      eventType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'artist' field.
      * @return The value.
      */
    public hcmus.zingmp3.artist.ArtistAvro getArtist() {
      return artist;
    }

    /**
      * Sets the value of the 'artist' field.
      * @param value The value of 'artist'.
      * @return This builder.
      */
    public hcmus.zingmp3.artist.ArtistEventAvro.Builder setArtist(hcmus.zingmp3.artist.ArtistAvro value) {
      validate(fields()[1], value);
      this.artistBuilder = null;
      this.artist = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'artist' field has been set.
      * @return True if the 'artist' field has been set, false otherwise.
      */
    public boolean hasArtist() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'artist' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public hcmus.zingmp3.artist.ArtistAvro.Builder getArtistBuilder() {
      if (artistBuilder == null) {
        if (hasArtist()) {
          setArtistBuilder(hcmus.zingmp3.artist.ArtistAvro.newBuilder(artist));
        } else {
          setArtistBuilder(hcmus.zingmp3.artist.ArtistAvro.newBuilder());
        }
      }
      return artistBuilder;
    }

    /**
     * Sets the Builder instance for the 'artist' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public hcmus.zingmp3.artist.ArtistEventAvro.Builder setArtistBuilder(hcmus.zingmp3.artist.ArtistAvro.Builder value) {
      clearArtist();
      artistBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'artist' field has an active Builder instance
     * @return True if the 'artist' field has an active Builder instance
     */
    public boolean hasArtistBuilder() {
      return artistBuilder != null;
    }

    /**
      * Clears the value of the 'artist' field.
      * @return This builder.
      */
    public hcmus.zingmp3.artist.ArtistEventAvro.Builder clearArtist() {
      artist = null;
      artistBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArtistEventAvro build() {
      try {
        ArtistEventAvro record = new ArtistEventAvro();
        record.eventType = fieldSetFlags()[0] ? this.eventType : (java.lang.CharSequence) defaultValue(fields()[0]);
        if (artistBuilder != null) {
          record.artist = this.artistBuilder.build();
        } else {
          record.artist = fieldSetFlags()[1] ? this.artist : (hcmus.zingmp3.artist.ArtistAvro) defaultValue(fields()[1]);
        }
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<ArtistEventAvro>
    WRITER$ = (org.apache.avro.io.DatumWriter<ArtistEventAvro>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<ArtistEventAvro>
    READER$ = (org.apache.avro.io.DatumReader<ArtistEventAvro>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}