package java_saya;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

class AES {
    private static String AES_CBS_PADDING = "AES/CBC/NoPadding";

    public static String ALGORITHM = "AES";

    public static byte[] connection_password = "4KiZkbVkFerMZGT3".getBytes();

    public static byte[] connection_iv = "2s7EMLxg4I4fBlGw".getBytes();

    public static byte[] decrypt(byte data[])
    {
        try {
            return depadding(decrypt(connection_password, connection_iv, data));
        }catch(Exception err){return "Error Decrypt".getBytes();}
    }

    public static String toprintable(byte data[])
    {
        String printable =  "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ ";
        String to_return = "";
        for (byte a: data)
        {
            for (int i = 0; i < printable.length(); i++)
            {
                char charnya = printable.charAt(i);
                if (charnya==(char)a)
                {
                    to_return += charnya;
                    break;
                }
            }
        }
        return to_return;
    }
    public static byte[] encrypt(byte data[])
    {
        try {
            return encrypt(connection_password, connection_iv, data);
        }catch(Exception err){return "Error Encrypt".getBytes();}
    }
    public static byte[] depadding(byte[] a)
    {
        byte to_return[] = new byte[a.length];
        for (int i = 0; i < a.length; i++)
        {
            byte apa = a[i];
            if (((int)apa)<=0){break;}
            if (((int) '�')==((int)apa)){break;}
            to_return[i] = apa;
            String test = "";
        }
        return to_return;
    }

    public static byte[] padding(byte[] a)
    {
        int jumlah = a.length;
        while ((jumlah%16)!=0)
        {
            jumlah++;
        }
        byte []to_return = new byte[jumlah];
        for (int i = 0; i < a.length;i++)
        {
            to_return[i] = a[i];
        }
        byte pad= 0;
        for (int i = a.length; i < jumlah-a.length;i++)
        {
            to_return[i] = pad;
        }
        return to_return;
    }
    public static byte[] encrypt(final byte[] key, final byte[] IV, final byte[] message) throws Exception {
        return encryptDecrypt(Cipher.ENCRYPT_MODE, key, IV, padding(message));
    }
    public static byte[] decrypt(final byte[] key, final byte[] IV, final byte[] message) throws Exception {
        return depadding(encryptDecrypt(Cipher.DECRYPT_MODE, key, IV, (message)));
    }
    public static char[] toCharBuff(byte byt[])
    {
        char to_return[] = new char[byt.length];
        for (int i = 0; i < byt.length; i++)
        {
            char r = (char)(((int)byt[i])+256);
            to_return[i] = r;
        }
        return to_return;
    }
    private static byte[] encryptDecrypt(final int mode, final byte[] key, final byte[] IV, final byte[] message)
            throws Exception {
        final Cipher cipher = Cipher.getInstance(AES_CBS_PADDING);
        final SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
        final IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(mode, keySpec, ivSpec);
        return cipher.doFinal(message);
    }

}
