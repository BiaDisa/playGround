package tools;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ManualDecrypt {

    /**
     * dev	F2oL59GkgU6KLZippIRaQvzQ
     * testing	QFy8xonqYP26XnPf2awbQF3I
     * prod	Rlc1BqEs2okSiqShMuCx5CJP
     */

    public static void main(String[] args) {
        String password = "F2oL59GkgU6KLZippIRaQvzQ";

        String crypt = "hEhNRgKpcmCK8Czbzj9RT1+EDq/7zwR4FRfdoO4btF3tVXzikAXNRw==";

        decrypted(password,crypt);
       /* String message = "material_center_rw";
        String encrypted = encrypted(password, message);
        // String encrypted = "LnIfI1J3JckCIPyMG1QDBZgL51TaVOhnLeUKJ8ceJMEYaLUx9A6gcoIAfaehR2O8h3lP8J2kb48tNK7n6uVpZsG7a9Cke/NGeELdj0sibnlnv+Gv88tM8Ec8rKaA+L85";

        decrypted(password, encrypted);*/


     /*   File file = new File("C:\\solidBackup\\project-aspect\\biz\\ext-sys-integration\\src\\main\\resources\\application-prod.yml");

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读一行，读入null时文件结束
            while ((tempString = reader.readLine()) != null) {
                Thread.sleep(100);
                if (tempString.contains("ENC(")) {
                    int i = tempString.indexOf("ENC(");

                    String substring = tempString.substring(i + 4, tempString.length() - 1);
                    System.out.println(tempString);
                    decrypted(password, substring);


                }


                line++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }*/

    }

    public static String encrypted(String password, String message) {
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword(password);
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setConfig(config);
        String encrypt = encryptor.encrypt(message);
        System.out.printf("Encrypted message %s\n", "ENC(" + encrypt + ")");
        setSysClipboardText("ENC(" + encrypt + ")");
        return encrypt;
    }

    public static void decrypted(String password, String encrypt) {
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword(password);
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setConfig(config);
        String decrypt = encryptor.decrypt(encrypt);
        System.out.printf("Dncrypted message: %s\n", decrypt);
    }

    /**
     * 将字符串复制到剪切板。
     */
    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }

}




