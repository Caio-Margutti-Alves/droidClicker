package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.camera;

import java.io.File;

import android.os.Environment;

public final class FroyoAlbumDirFactory extends AlbumStorageDirFactory {

	@Override
	public File getAlbumStorageDir(String albumName) {
		// TODO Auto-generated method stub
		return new File(
		  Environment.getExternalStoragePublicDirectory(
		    Environment.DIRECTORY_PICTURES
		  ), 
		  albumName
		);
	}
}
