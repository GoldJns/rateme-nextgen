package com.app.rateme.model.converter;

import com.app.rateme.model.Position;
import jakarta.persistence.AttributeConverter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PositionConverter implements AttributeConverter<Position, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(final Position position) {
        // mysql stores SRID (4 bytes) and WKB (21 bytes) of geometry types
        final ByteBuffer buffer = ByteBuffer.allocate(25);

        // put SRID
        buffer.putInt(0);

        // put byteOrder
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put((byte) 0x01);

        //put geometryType, 1 -> Point
        buffer.putInt(1);

        // put lon and lat (x and y)
        buffer.putDouble(position.getLon());
        buffer.putDouble(position.getLat());

        return buffer.array();
    }

    @Override
    public Position convertToEntityAttribute(final byte[] dbData) {
        // database returns geometry types as well-known binary (wkb)
        // Point with coordinates 1,1 in wkb-format:
        // 0101000000000000000000F03F000000000000F03F
        // meaning:
        // 01 : byte order
        // 01000000 : geometry type
        // 000000000000F03F : X
        // 000000000000F03F : Y

        if (dbData.length < 21) {
            throw new RuntimeException("convertToEntityAttribute failed: size < 21 bytes, actual size: " + dbData.length);
        }

        final ByteBuffer buffer = ByteBuffer.wrap(dbData);

        // mysql stores SRID and WKB of geometry types
        // see: https://dev.mysql.com/doc/refman/8.0/en/gis-data-formats.html#gis-internal-format
        // SRID is 4 bytes and can be ignored for simplicity
        // skip first bytes until 21 remain
        buffer.position(dbData.length - 21);

        final byte byteOrder = buffer.get();
        if (byteOrder == 0x00) {
            buffer.order(ByteOrder.BIG_ENDIAN);
        } else if (byteOrder == 0x01) {
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            throw new RuntimeException("convertToEntityAttribute failed: unknown value for byteOrder, actual value: " + byteOrder);
        }

        final int geometryType = buffer.getInt();
        if (geometryType != 1) {
            throw new RuntimeException("convertToEntityAttribute failed: geometry type != 1 (not a point), actual value: " + geometryType);
        }

        final double lon = buffer.getDouble();
        final double lat = buffer.getDouble();

        return new Position(lon, lat);
    }
}
