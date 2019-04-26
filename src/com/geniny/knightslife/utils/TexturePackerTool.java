package com.geniny.knightslife.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerTool {

    public static void main(String[] args)
    {
        TexturePacker.process("res/unpacked","res/packed","textureres");
    }
}
