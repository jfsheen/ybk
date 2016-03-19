package cc.aisc.ybk.content.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sjf on 16-1-14.
 */
public abstract class Loggable {
    protected static Logger LOGGER = null;

    public Loggable() {
        this.LOGGER = LoggerFactory.getLogger(getClass().getName());
    }
}