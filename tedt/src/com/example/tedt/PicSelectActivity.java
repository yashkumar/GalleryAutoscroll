package com.example.tedt;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * PicSelectActivity presents image gallery
 * - user can select new images to display within scrolling thumbnail gallery
 * - user can select individual image to display at larger size
 *
 * Sue Smith
 * Mobiletuts+ Tutorial - Importing and Displaying Images with the Android Gallery
 * June 2012
 */
public class PicSelectActivity extends Activity { 
	
	//variable for selection intent
	private final int PICKER = 1;
	
	//variable to store the currently selected image
	private int currentPic = 0;
	//adapter for gallery view
	private PicAdapter imgAdapt;
	//gallery object
	private Gallery picGallery;
	//image view for larger display
	private ImageView picView;
	Timer timer;
    /**
     * instantiate the interactive gallery
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //get the large image view
        picView = (ImageView) findViewById(R.id.picture);
        
        //get the gallery view
        picGallery = (Gallery) findViewById(R.id.customGallery1);
        
        
        //create a new adapter
        imgAdapt = new PicAdapter(this);
        //set the gallery adapter
        picGallery.setAdapter(imgAdapt);
        
        //set long click listener for each gallery thumbnail item
       
        
        //set the click listener for each item in the thumbnail gallery
       
     
    }
    
    /**
     * Base Adapter subclass creates Gallery view
     * - provides method for adding new images from user selection
     * - provides method to return bitmaps from array
     *
     */
    public class PicAdapter extends BaseAdapter {
    	
    	//use the default gallery background image
        int defaultItemBackground;
        
        //gallery context
        private Context galleryContext;

        //array to store bitmaps to display
        private Bitmap[] imageBitmaps;
        //placeholder bitmap for empty spaces in gallery
        Bitmap placeholder;

        //constructor
        public PicAdapter(Context c) {
        	
        	//instantiate context
        	galleryContext = c;
        	
        	//create bitmap array
            imageBitmaps  = new Bitmap[10];
            //decode the placeholder image
            placeholder = BitmapFactory.decodeResource(getResources(),R.drawable.icon);
            System.out.println("length is "+imageBitmaps.length);
            //set placeholder as all thumbnail images in the gallery initially
            for(int i=0; i<imageBitmaps.length; i++)
            	imageBitmaps[i]=placeholder;
            
            //get the styling attributes - use default Andorid system resources
            TypedArray styleAttrs = galleryContext.obtainStyledAttributes(R.styleable.PicGallery);
            //get the background resource
            defaultItemBackground = styleAttrs.getResourceId(
            		R.styleable.PicGallery_android_galleryItemBackground, 0);
            //recycle attributes
            styleAttrs.recycle();
        }

        //BaseAdapter methods
        
        //return number of data items i.e. bitmap images
        public int getCount() {

            return imageBitmaps.length;
        }

        //return item at specified position
        public Object getItem(int position) {
            return position;
        }

        //return item ID at specified position
        public long getItemId(int position) {
            return position;
        }

        //get view specifies layout and display options for each thumbnail in the gallery
        public View getView(int position, View convertView, ViewGroup parent) {

        	//create the view
            ImageView imageView = new ImageView(galleryContext);
            //specify the bitmap at this position in the array
            imageView.setImageBitmap(imageBitmaps[position]);
            //set layout options
            imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));
            //scale type within view area
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //set default gallery item background
            imageView.setBackgroundResource(defaultItemBackground);
            //return the view
            return imageView;
        }
        
        //custom methods for this app
        
        //helper method to add a bitmap to the gallery when the user chooses one
        public void addPic(Bitmap newPic)
        {
        	//set at currently selected index
        	imageBitmaps[currentPic] = newPic;
        }
        
        //return bitmap at specified position for larger display
        public Bitmap getPic(int posn)
        {
        	//return bitmap at posn index
        	return imageBitmaps[posn];
        }
    }
    
    /**
     * Handle returning from gallery or file manager image selection
     * - import the image bitmap
     */
    
    Handler handler=new Handler()
    		{
    	public void handleMessage(android.os.Message msg) {
//    		picGallery.scrollTo(picGallery.getScrollX()+3, picGallery.getScrollY());
    		System.out.println("scroll x is "+picGallery.getScrollX());
    		System.out.println("scroll x is "+picGallery.getScrollY());
    		//picGallery.s
    	};
    		};
    class mytimer extends TimerTask
    {

		@Override
		public void run() {
			handler.sendEmptyMessage(0);
		
			
		}
    	
    }
    
}
