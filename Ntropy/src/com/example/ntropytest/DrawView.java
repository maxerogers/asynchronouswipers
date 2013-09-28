package com.example.ntropytest;
 
import java.util.ArrayList;
 
import java.util.List;
 
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
 
// @TODO stop relying on local variables, transmit move to engine
 
public class DrawView extends View implements OnTouchListener {
    private static final String TAG = "DrawView";
    Bitmap oneBM = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.one);
    Bitmap zeroBM = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.zero);
    //Bitmap f1BM = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.f1);
    Bitmap submitButton = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.endturn);
    Paint paint = new Paint();
    int[][] hitboxesX = new int[6][6];
    int[][] hitboxesY = new int[6][6];
    Paint[][] hitboxesP = new Paint[6][6];
    Paint ps = new Paint();
    Paint cursor;
    int xcursor, ycursor;
    int width = 60;
    int height = 60;
    Canvas myCanvas;
    boolean myTurn;
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    //submitFragment submit = new submitFragment();
    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
 
        //paint.setColor(Color.WHITE);
        //paint.setAntiAlias(true);
        for(int i=0;i<6;i++)
        {
                for(int j=0;j<6;j++)
                {
                        hitboxesP[i][j] = new Paint(); // all referencing same paint object?
                        hitboxesP[i][j].setColor(Color.WHITE);
                        hitboxesP[i][j].setAntiAlias(true);
                }
        }
       
    }
 
    @Override
    public void onDraw(Canvas canvas) {
        //canvas.drawBitmap( f1BM ,0, 0,null);
       // canvas.drawBitmap(oneBM, 200,200, null);
        canvas.drawBitmap(submitButton, 25.0f, 690.0f, null);
        for(int i=0;i<6;i++)
        {
                for(int j=0;j<6;j++)
                {
                        int xPos = 10+80*i;
                        int yPos = 200+80*j;
                        int width = 60;
                        int height = 60;
                        canvas.drawRect(xPos, yPos, xPos+width-1, yPos+height-1,hitboxesP[i][j]);
                }
        }
        System.out.println("Redraw");
    }
   
   
   
 
    public boolean onTouch(View view, MotionEvent event) {
        // if(event.getAction() != MotionEvent.ACTION_DOWN)
        // return super.onTouchEvent(event)
        System.out.println("hello world");
 
        int x = (int) event.getX();
        int y = (int) event.getY();
        int i = (x-10)/80;
        int j = (y-200)/80;
        if(x > 25 && x < 455 && y > 690 && y < 750)
        {
                if(cursor.getColor() != Color.WHITE)
                {
                       
                        // SUBMIT CODE HERE
                }
                return true;
        }
        if(i > 5 || j > 5 || i < 0 || j < 0)
        {
                return false;
        }
        if(cursor == null)
        {
                cursor = hitboxesP[i][j];
                xcursor = i;
                ycursor = j;
        }
        System.out.println(hitboxesP[i][j].getColor());
        System.out.println("x: "+x+"\ty: "+y);
        System.out.println("i: "+i+"\tj: "+j);
        if(event.getAction() == MotionEvent.ACTION_DOWN && cursor == hitboxesP[i][j])
        {
                switch(hitboxesP[i][j].getColor())
                {
                        case Color.WHITE: { cursor.setColor(Color.BLUE); break; }
                        case Color.BLUE: { cursor.setColor(Color.RED); break; }
                        case Color.RED: { cursor.setColor(Color.WHITE); break; }
                }
               
        }
        if(cursor.getColor() == Color.WHITE)
        {
                cursor = null;
        }
        System.out.println(hitboxesP[i][j].getColor());
 
        view.invalidate();
       
        return true;
    }
   
 
}
 
/*class submitFragment extends DialogFragment // bells and whistles; are you sure you want to submit?
{
        public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_submit_move)
               .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                           
                           
                           
                   }
               })
               .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
*/
