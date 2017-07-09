package test.psidemo.downloader;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Implements data download from a specific URL.
 */

public class DataDownloaderImpl implements IDataDownloader {
    private static final String PSI_URL = "https://api.data.gov.sg/v1/environment/psi";
    private static final String DATA_KEY = "GzgowDpqIQeLVe6DJxt2BxyqyfMF7HVr";
    private static final int BUF_LEN = 1000;

    @Override
    public void download(final IDownloadListener listener) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... args) {
                HttpsURLConnection cxn = null;
                StringBuilder sb = new StringBuilder();
                try {
                    URL url = new URL(PSI_URL);
                    cxn = (HttpsURLConnection)url.openConnection();
                    cxn.setRequestProperty("api-key", DATA_KEY);

                    BufferedReader br = new BufferedReader(new InputStreamReader(cxn.getInputStream()));
                    char [] arr = new char[BUF_LEN];

                    int r = br.read(arr, 0, BUF_LEN);
                    while (r != -1) {
                        sb.append(arr, 0, r);
                        r = br.read(arr, 0, BUF_LEN);
                    }
                } catch (MalformedURLException e) {
                    // Do nothing.
                } catch (IOException e) {
                    // Do nothing.
                } finally {
                    if (cxn != null) {
                        cxn.disconnect();
                    }
                }

                return sb.toString();
            }

            public void onPostExecute(String data) {
                listener.onDownloadDone(data);
            }
        }.execute();
    }
}
