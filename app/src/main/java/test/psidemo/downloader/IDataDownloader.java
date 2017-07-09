package test.psidemo.downloader;

/*
 * Downloads PSI data.
 */

public interface IDataDownloader {
    /*
     * Listener for data download.
     */
    interface IDownloadListener {
        /*
         * Called when download is done.
         *
         * @param data The downloaded data.
         */
        void onDownloadDone(String data);
    }

    /*
     * Downloads data and calls listener when done.
     *
     * @param listener The listener that will be called when download is done.
     */
    void download(IDownloadListener listener);
}
