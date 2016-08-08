package hardcorequesting.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by TimeTheCat on 8/8/2016.
 */
public class BackupTask implements Runnable {

    private FileInputStream inputStream = null;
    private FileOutputStream outputStream = null;
    private boolean result = true;
    private File fileToBackUp = null;
    private File backup = null;

    BackupTask(File fileToBackup, File backup) {
        this.fileToBackUp = fileToBackup;
        this.backup = backup;
    }

    @Override
    public void run() {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(fileToBackUp);
            outputStream = new FileOutputStream(backup);
            while (inputStream.available() > 0) {
                outputStream.write(inputStream.read());
            }
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    public boolean getResult() {
        return result;
    }
}
