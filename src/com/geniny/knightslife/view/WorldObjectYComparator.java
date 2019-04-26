package com.geniny.knightslife.view;

import com.geniny.knightslife.model.YSortable;

import java.util.Comparator;

public class WorldObjectYComparator implements Comparator<YSortable> {

    @Override
    public int compare(YSortable o1 , YSortable o2)
    {
        if(o1.getWorldY() < o2.getWorldY())
        {
            return -1;
        }
        else if(o1.getWorldY() > o2.getWorldY())
        {
            return 1;
        }
        return 0;
    }
}
