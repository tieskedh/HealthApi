package nl.hanze.healthapi;

import java.io.Closeable;

/**
 * Created by thijs on 5-10-2017.
 */

public interface JsonDataOutput extends Closeable {
    void write(String json);
}
