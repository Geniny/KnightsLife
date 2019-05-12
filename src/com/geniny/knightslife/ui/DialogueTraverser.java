package com.geniny.knightslife.ui;

import java.util.List;

public class DialogueTraverser
{
    private Dialogue dialogue;
    private DialogueNode currentNode;

    public DialogueTraverser(Dialogue dialogue)
    {
        this.dialogue = dialogue;
        currentNode = dialogue.getNode(dialogue.getStart());
    }

    public DialogueNode getNextNode(int pointerIndex)
    {
        DialogueNode nextNode = dialogue.getNode(currentNode.getPointer().get(pointerIndex));
        currentNode = nextNode;
        return  nextNode;
    }

    public List<String> getOptions()
    {
        return currentNode.getLabels();
    }

    public String getText()
    {
        return currentNode.getText();
    }

    public DialogueNode.NODE_TYPE getType()
    {
        return currentNode.getType();
    }

}
