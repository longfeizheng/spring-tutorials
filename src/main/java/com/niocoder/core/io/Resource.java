package com.niocoder.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 2018/11/1.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

    String getDescription();
}
