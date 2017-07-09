package test.psidemo.map;

import test.psidemo.downloader.IDataDownloader;
import test.psidemo.model.PsiData;
import test.psidemo.parser.IDataParser;
import test.psidemo.util.DataUtil;

/**
 * Map Controller implementation. This downloads and parses data.
 */

public class MapControllerImpl implements IMapController {
    private IDataDownloader mDownloader;
    private IDataParser mParser;

    public MapControllerImpl(IDataDownloader downloader, IDataParser parser) {
        mDownloader = downloader;
        mParser = parser;
    }

    @Override
    public void getMapData(final IMapView mapView) {
        if (mapView != null && mDownloader != null && mParser != null) {
            mDownloader.download(new IDataDownloader.IDownloadListener() {
                @Override
                public void onDownloadDone(String data) {
                    PsiData psiData = mParser.parse(data);
                    mapView.update(DataUtil.getInstance().getLatestMapData(psiData));
                }
            });
        }
    }
}
