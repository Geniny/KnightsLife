package com.geniny.knightslife.ui;

import java.util.HashMap;
import java.util.Map;

public class Dialogue {

    private Map<Integer, DialogueNode> nodes = new HashMap<>();

        public DialogueNode getNode(int id)
        {
            return nodes.get(id);
        }

        public void addNode(DialogueNode node)
        {
            this.nodes.put(node.getID(),node);
        }

        public int getStart()
        {
            return 0;
        }
}
