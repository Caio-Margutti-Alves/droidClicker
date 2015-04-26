package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.camera;

import java.io.File;

import android.os.Environment;

public final class BaseAlbumDirFactory extends AlbumStorageDirFactory {

	// Standard storage location for digital camera files
	private static final String CAMERA_DIR = "/dcim/";

	@Override
	public File getAlbumStorageDir(String albumName) {
		return new File (
				Environment.getExternalStorageDirectory()
				+ CAMERA_DIR
				+ albumName
		);
	}
}
