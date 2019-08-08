package com.yoncaapp.Service;

import android.content.Intent;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;
import com.yoncaapp.MainActivity;
import com.yoncaapp.R;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ScreenRecordingService {
    public static final int CAST_PERMISSION_CODE = 22;
    private static final String LOG = "ScreenRecording";
    public static DisplayMetrics mDisplayMetrics;
    public static MediaProjection mMediaProjection;
    public static VirtualDisplay mVirtualDisplay;
    public static MediaProjectionManager mProjectionManager;
    public static MediaRecorder mMediaRecorder;
    private static String status;
    public static List<String> resources;
    private static String targetFile;
    private static String filePath;
    private final static String directory = Environment.getExternalStorageDirectory() + File.separator + "Yonca";


    public static void stopRecording() {
        if (mMediaRecorder != null) {
            mMediaRecorder.stop();
            mMediaRecorder.reset();
        }
        if (mVirtualDisplay != null) {
            mVirtualDisplay.release();
        }
        if (mMediaProjection != null) {
            mMediaProjection.stop();
        }
        createVideoShareIntent();

    }

    public static void createVideoShareIntent() {

        Intent share = new Intent(Intent.ACTION_SEND);


        share.setType("video/*");


        File media = new File(filePath);

        Uri uri = Uri.fromFile(media);


        share.putExtra(Intent.EXTRA_STREAM, uri);

        MainActivity.getMainContext().startActivity(Intent.createChooser(share, MainActivity.getMainContext().getResources().getString(R.string.videoPaylas)));
    }


    private static String getCurSysDate() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    }

    public static void prepareRecording() {
        deleteDirectory();
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Toast.makeText(MainActivity.getMainContext(), R.string.bellegeUlasilamadi, Toast.LENGTH_SHORT).show();
            return;
        }
        final File folder = new File(directory);
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }

        if (success) {
            String videoName = ("capture_" + getCurSysDate() + ".mp4");
            filePath = directory + File.separator + videoName;
            targetFile = filePath + "_end";
            resources.add(filePath);
        } else {
            Toast.makeText(MainActivity.getMainContext(), R.string.klasorOlusturulamadi, Toast.LENGTH_SHORT).show();
            return;
        }

        int width = mDisplayMetrics.widthPixels ;
        int height = mDisplayMetrics.heightPixels;

        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mMediaRecorder.setVideoEncodingBitRate(4000000);
        mMediaRecorder.setVideoFrameRate(40);
        mMediaRecorder.setVideoSize(width, height);
        mMediaRecorder.setOutputFile(filePath);
        try {
            mMediaRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static boolean mergeMediaFiles() {
        try {
            String mediaKey = "vide";
            List<Movie> listMovies = new ArrayList<>();
            for (String filename : resources) {
                listMovies.add(MovieCreator.build(filename));
            }
            List<Track> listTracks = new LinkedList<>();
            for (Movie movie : listMovies) {
                for (Track track : movie.getTracks()) {
                    if (track.getHandler().equals(mediaKey)) {
                        listTracks.add(track);
                    }
                }
            }
            Movie outputMovie = new Movie();
            if (!listTracks.isEmpty()) {
                outputMovie.addTrack(new AppendTrack(listTracks.toArray(new Track[listTracks.size()])));
            }
            Container container = new DefaultMp4Builder().build(outputMovie);
            FileChannel fileChannel = new RandomAccessFile(String.format(targetFile), "rw").getChannel();
            container.writeContainer(fileChannel);
            fileChannel.close();
            return true;
        } catch (IOException e) {
            Log.e(LOG, "Error merging media files. exception: " + e.getMessage());
            return false;
        }
    }

    private static void deleteDirectory() {
        File path = new File(directory);
        if (path.exists()) {
            File[] files = path.listFiles();
            if (files == null) {
                return;
            }
            for (int i = 0; i < files.length; i++) {
                files[i].delete();

            }
        }
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        ScreenRecordingService.status = status;
    }
}
