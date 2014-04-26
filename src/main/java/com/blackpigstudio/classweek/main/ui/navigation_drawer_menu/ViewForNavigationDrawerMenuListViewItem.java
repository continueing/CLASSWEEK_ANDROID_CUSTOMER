package com.blackpigstudio.classweek.main.ui.navigation_drawer_menu;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.AbstractViewForListViewItem;
import com.blackpigstudio.classweek.main.module.listview.mvc_base.IListViewItem;

/**
 * Created by continueing on 2014. 4. 26..
 */
public class ViewForNavigationDrawerMenuListViewItem extends AbstractViewForListViewItem {
    TextView tv_contents;
    ImageView iv_left_icon;

    public ViewForNavigationDrawerMenuListViewItem(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext(), R.layout.item_navigation_drawer_menu, this);
    }

    @Override
    protected void initViews() {
        tv_contents = (TextView)findViewById_(R.id.tv_navigation_drawer_menu_contents);
        iv_left_icon = (ImageView)findViewById_(R.id.iv_navigation_drawer_menu_left_icon);
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        DrawerMenuItem drawerMenuItem = (DrawerMenuItem) aIListViewItem;
        tv_contents.setText(drawerMenuItem.getText());
        iv_left_icon.setImageResource(((DrawerMenuItem) aIListViewItem).getImageId());

    }

    public static class DrawerMenuItem implements IListViewItem
    {
        private int imageId;
        private String text;

        public DrawerMenuItem(int anImageId, String aText)
        {
            imageId = anImageId;
            text = aText;
        }

        public String getText() {
            return text;
        }

        public int getImageId() {
            return imageId;
        }
    }
}
