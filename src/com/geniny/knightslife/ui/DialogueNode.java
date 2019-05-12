package com.geniny.knightslife.ui;

import java.util.ArrayList;
import java.util.List;

public class DialogueNode {

    private ArrayList<Integer> pointers = new ArrayList<>();
    private ArrayList<String> labels = new ArrayList<>();

    private String text;
    private int id;

    private NODE_TYPE type;

    public enum NODE_TYPE{

        MULTIPLE_CHOICE,
        LINEAR,
        END,
        ;
    }

    public DialogueNode(String text , int id)
    {
        this.text = text;
        this.id = id;
        type = NODE_TYPE.END;
    }

    public void addChoice(String option , int nodeId)
    {
        if(type == NODE_TYPE.LINEAR)
        {
            pointers.clear();
        }
        labels.add(option);
        pointers.add(nodeId);
        type = NODE_TYPE.MULTIPLE_CHOICE;
    }

    public void makeLinear(int nodeId)
    {
        pointers.clear();
        labels.clear();
        pointers.add(nodeId);
        type = NODE_TYPE.LINEAR;
    }

    public List<Integer> getPointer()
    {
        return pointers;
    }

    public List<String> getLabels()
    {
        return labels;
    }

    public NODE_TYPE getType()
    {
        return type;
    }

    public int getID()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }
}
