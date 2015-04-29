package io.vtm.mapapp.test;

import android.os.Bundle;
import org.oscim.android.cache.TileCache;
import org.oscim.layers.tile.TileLayer;
import org.oscim.layers.tile.buildings.S3DBLayer;
import org.oscim.layers.tile.vector.labeling.LabelLayer;
import org.oscim.theme.VtmThemes;
import org.oscim.tiling.TileSource;
import org.oscim.tiling.source.oscimap4.OSciMap4TileSource;

public class S3DBMapActivity extends BaseMapActivity {

	TileCache mS3dbCache;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mMap.setTheme(VtmThemes.DEFAULT);

		TileSource ts = OSciMap4TileSource.builder()
		    .url("http://opensciencemap.org/tiles/s3db")
		    .zoomMin(16)
		    .zoomMax(16)
		    .build();

		if (USE_CACHE) {
			mS3dbCache = new TileCache(this, null, "s3db.db");
			mS3dbCache.setCacheSize(512 * (1 << 10));
			ts.setCache(mS3dbCache);
		}
		TileLayer tl = new S3DBLayer(mMap, ts, true, false);
		mMap.layers().add(tl);
		mMap.layers().add(new LabelLayer(mMap, mBaseLayer));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mS3dbCache != null)
			mS3dbCache.dispose();
	}

}
