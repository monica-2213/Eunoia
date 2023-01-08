package com.example.eunoia.ToDoList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eunoia.R;
import com.example.eunoia.ToDoList.Adapter.ToDoAdapter;

public class RecyclerViewTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ToDoAdapter adapter;

    public RecyclerViewTouchHelper(ToDoAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.RIGHT){
            AlertDialog.Builder builder= new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    adapter.deleteTask(position);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    adapter.notifyItemChanged(position);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            adapter.editItem(position);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        final int DIRECTION_RIGHT = 1;
        final int DIRECTION_LEFT = 2;


        if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE && isCurrentlyActive){
            int direction = dX > 0 ? DIRECTION_RIGHT : DIRECTION_LEFT;
            int absoluteDisplacement = Math.abs((int)dX);

            switch (direction){
                case DIRECTION_RIGHT:
                    View itemViewRight = viewHolder.itemView;
                    ColorDrawable bgRight = new ColorDrawable();
                    bgRight.setColor(Color.RED);
                    bgRight.setBounds(itemViewRight.getLeft(), itemViewRight.getTop(),itemViewRight.getRight(),itemViewRight.getBottom());
                    bgRight.draw(c);

                    Drawable icon = ActivityCompat.getDrawable(adapter.getContext(), R.drawable.delete);
                    int deleteIconTop = itemViewRight.getTop() + (itemViewRight.getHeight() - icon.getIntrinsicHeight()) / 2;
                    int deleteIconMargin = (itemViewRight.getHeight() - icon.getIntrinsicHeight()) / 2;
                    int deleteIconLeft = itemViewRight.getLeft() + deleteIconMargin;
                    int deleteIconRight = deleteIconLeft+icon.getIntrinsicWidth();
                    int deleteIconBottom = deleteIconTop + icon.getIntrinsicHeight();
                    icon.setBounds(deleteIconLeft,deleteIconTop,deleteIconRight,deleteIconBottom);
                    icon.draw(c);
                    break;

                case DIRECTION_LEFT:
                    View itemViewLeft = viewHolder.itemView;
                    ColorDrawable bgLeft = new ColorDrawable();
                    bgLeft.setColor(Color.BLUE);
                    bgLeft.setBounds(itemViewLeft.getLeft(), itemViewLeft.getTop(),itemViewLeft.getRight(),itemViewLeft.getBottom());
                    bgLeft.draw(c);

                    Drawable iconLeft = ActivityCompat.getDrawable(adapter.getContext(), R.drawable.edit);
                    int editIconTop = itemViewLeft.getTop() + (itemViewLeft.getHeight() - iconLeft.getIntrinsicHeight()) / 2;
                    int editIconMargin = (itemViewLeft.getHeight() - iconLeft.getIntrinsicHeight()) / 2;
                    int editIconLeft = itemViewLeft.getRight() - editIconMargin - iconLeft.getIntrinsicWidth();
                    int editIconRight = itemViewLeft.getRight() - editIconMargin;
                    int editIconBottom = editIconTop + iconLeft.getIntrinsicHeight();
                    iconLeft.setBounds(editIconLeft,editIconTop,editIconRight,editIconBottom);
                    iconLeft.draw(c);
                    break;
            }
        }
    }
}