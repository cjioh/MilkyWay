package org.total.interview.server.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by total on 12/6/15.
 */
public class PasswordManagerImpl implements PasswordManager {

    public String encode(String password) {
        return DigestUtils.md5Hex(password);
    }

}