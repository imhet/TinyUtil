package im.het.tiny.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * Created by het on 16/6/2.
 */
public final class Md5Util {

    /**
     * 计算文件的 md5
     */
    public static String calculateFileMd5(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(byteBuffer);
            in.close();

            return byteArrayToHex(messageDigest.digest());
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String byteArrayToHex(byte[] b) {
        String hs = "";
        String stmp;

        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));

            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }

            if (n < b.length - 1) {
                hs = hs + "";
            }
        }

        return hs;
    }

    private Md5Util() {

    }

}
